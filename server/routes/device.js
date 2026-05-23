const express = require('express');
const router = express.Router();
const db = require('../database');
const { autoExpireSessions } = require('../utils');



// 1. Register Device
router.post('/register', (req, res) => {
  const { device_id } = req.body;
  if (!device_id) return res.status(400).json({ error: 'device_id is required' });

  db.run(`INSERT OR IGNORE INTO devices (device_id, status) VALUES (?, 'OFFLINE')`, [device_id], function(err) {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ message: 'Device registered successfully', device_id });
  });
});

// 2. Verify PIN
router.post('/verify-pin', (req, res) => {
  const { device_id, pin } = req.body;
  if (!device_id || !pin) return res.status(400).json({ error: 'device_id and pin are required' });

  autoExpireSessions(() => {
    // 1. Get the device to check its court assignment
    db.get(`SELECT court_id FROM devices WHERE device_id = ?`, [device_id], (err, device) => {
      if (err) return res.status(500).json({ error: err.message });
      if (!device) return res.status(404).json({ error: 'Device not registered. Please register first.' });

      const deviceCourtId = device.court_id;
      if (!deviceCourtId) {
        return res.status(400).json({ error: 'This device is not assigned to any court. Please assign a court in the admin dashboard.' });
      }

      // 2. Find matching session (matching court_id or NULL for legacy manual PINs)
      db.get(
        `SELECT * FROM sessions WHERE pin = ? AND status = 'UNUSED' AND (court_id IS NULL OR court_id = ?)`,
        [pin, deviceCourtId],
        (err, session) => {
          if (err) return res.status(500).json({ error: err.message });
          if (!session) return res.status(404).json({ error: 'Invalid, expired, or wrong court PIN' });

          const nowMs = Date.now();
          let durationMinutes = session.duration_minutes;

          // 3. If it's a synced booking, validate schedule and calculate duration
          if (session.booking_date && session.start_time && session.end_time) {
            const startTimeStr = `${session.booking_date}T${session.start_time}:00+07:00`;
            const endTimeStr = `${session.booking_date}T${session.end_time}:00+07:00`;
            const startTimeMs = new Date(startTimeStr).getTime();
            const endTimeMs = new Date(endTimeStr).getTime();

            const ALLOWED_EARLY_BUFFER_MS = 10 * 60 * 1000; // 10 minutes buffer

            if (nowMs < startTimeMs - ALLOWED_EARLY_BUFFER_MS) {
              return res.status(400).json({ error: `Too early. Your booking starts at ${session.start_time}` });
            }

            if (nowMs >= endTimeMs) {
              db.run(`UPDATE sessions SET status = 'EXPIRED' WHERE id = ?`, [session.id]);
              return res.status(400).json({ error: 'This booking has already ended.' });
            }

            // Calculate remaining minutes from now until the end of the booking
            durationMinutes = Math.max(1, Math.floor((endTimeMs - nowMs) / (1000 * 60)));
          }

          // 4. Mark session as ACTIVE and associate with device
          db.run(
            `UPDATE sessions SET status = 'ACTIVE', device_id = ?, used_at = CURRENT_TIMESTAMP WHERE id = ?`,
            [device_id, session.id],
            (updateErr) => {
              if (updateErr) return res.status(500).json({ error: updateErr.message });

              // Update device status
              db.run(`UPDATE devices SET status = 'IN_USE', last_heartbeat = CURRENT_TIMESTAMP WHERE device_id = ?`, [device_id]);

              res.json({
                message: 'PIN verified successfully',
                duration_minutes: durationMinutes
              });
            }
          );
        }
      );
    });
  });
});

// 3. Heartbeat & Sync (Called by Android periodically)
router.post('/heartbeat', (req, res) => {
  const { device_id, battery_level, status } = req.body; // status: 'IN_USE', 'LOCKED'
  if (!device_id) return res.status(400).json({ error: 'device_id is required' });

  autoExpireSessions(() => {
    // Read the pending command before clearing it
    db.get(`SELECT pending_command FROM devices WHERE device_id = ?`, [device_id], (err, row) => {
      if (err) return res.status(500).json({ error: err.message });
      const pendingCommand = row ? row.pending_command : null;

      // Force locked status if a LOCK command is pending, to avoid transient IN_USE overrides during sync
      let finalStatus = status;
      if (pendingCommand === 'LOCK') {
        finalStatus = 'LOCKED';
      } else if (pendingCommand === 'UNLOCK') {
        finalStatus = 'IN_USE';
      }

      db.run(`UPDATE devices SET status = COALESCE(?, status), battery_level = COALESCE(?, battery_level), last_heartbeat = CURRENT_TIMESTAMP, pending_command = NULL WHERE device_id = ?`,
        [finalStatus, battery_level, device_id], function(updateErr) {
          if (updateErr) return res.status(500).json({ error: updateErr.message });
          
          // If device reports it is LOCKED (or forced to LOCKED), automatically expire any ACTIVE sessions for this device!
          if (finalStatus === 'LOCKED') {
            db.all(`SELECT id, booking_id FROM sessions WHERE device_id = ? AND status = 'ACTIVE'`, [device_id], (err, activeSessions) => {
              if (err) {
                console.error('Error getting active sessions on heartbeat:', err.message);
              } else if (activeSessions && activeSessions.length > 0) {
                activeSessions.forEach(session => {
                  db.run(`UPDATE sessions SET status = 'EXPIRED' WHERE id = ?`, [session.id], (sessionErr) => {
                    if (sessionErr) {
                      console.error('Error expiring session on heartbeat:', sessionErr.message);
                    } else if (session.booking_id) {
                      try {
                        const { updateCloudBookingStatus } = require('../sync');
                        updateCloudBookingStatus(session.booking_id, 'completed');
                      } catch (syncErr) {
                        console.error('[Heartbeat] Error triggering cloud booking status update:', syncErr.message);
                      }
                    }
                  });
                });
              }
            });
          }
          
          // Return the command so the device can process it
          res.json({ message: 'Heartbeat received', pending_command: pendingCommand });
      });
    });
  });
});

module.exports = router;
