const express = require('express');
const router = express.Router();
const db = require('../database');

// PIN expiry time in minutes (must match admin.js)
const PIN_EXPIRY_MINUTES = 1; // Change to 10 for production

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

  db.get(`SELECT * FROM sessions WHERE pin = ? AND status = 'UNUSED'`, [pin], (err, session) => {
    if (err) return res.status(500).json({ error: err.message });
    if (!session) return res.status(404).json({ error: 'Invalid or already used PIN' });

    // Check if the PIN has expired (older than PIN_EXPIRY_MINUTES)
    const createdTime = new Date(session.created_at.replace(' ', 'T') + 'Z').getTime();
    const now = Date.now();
    const diffMinutes = (now - createdTime) / (1000 * 60);

    if (diffMinutes > PIN_EXPIRY_MINUTES) {
      db.run(`UPDATE sessions SET status = 'EXPIRED' WHERE pin = ?`, [pin]);
      return res.status(400).json({ error: `This PIN has expired (not used within ${PIN_EXPIRY_MINUTES} minutes)` });
    }

    // Mark session as ACTIVE and associate with device
    db.run(`UPDATE sessions SET status = 'ACTIVE', device_id = ?, used_at = CURRENT_TIMESTAMP WHERE pin = ?`, 
      [device_id, pin], (updateErr) => {
        if (updateErr) return res.status(500).json({ error: updateErr.message });
        
        // Update device status
        db.run(`UPDATE devices SET status = 'IN_USE', last_heartbeat = CURRENT_TIMESTAMP WHERE device_id = ?`, [device_id]);

        res.json({ 
          message: 'PIN verified successfully',
          duration_minutes: session.duration_minutes
        });
    });
  });
});

// 3. Heartbeat & Sync (Called by Android periodically)
router.post('/heartbeat', (req, res) => {
  const { device_id, battery_level, status } = req.body; // status: 'IN_USE', 'LOCKED'
  if (!device_id) return res.status(400).json({ error: 'device_id is required' });

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
          db.run(`UPDATE sessions SET status = 'EXPIRED' WHERE device_id = ? AND status = 'ACTIVE'`, [device_id], function(sessionErr) {
            if (sessionErr) console.error('Error expiring active session on heartbeat LOCKED:', sessionErr.message);
          });
        }
        
        // Return the command so the device can process it
        res.json({ message: 'Heartbeat received', pending_command: pendingCommand });
    });
  });
});

module.exports = router;
