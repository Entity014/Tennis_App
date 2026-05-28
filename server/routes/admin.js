const express = require('express');
const router = express.Router();
const db = require('../database');
const crypto = require('crypto');
const { autoExpireSessions } = require('../utils');

// 0. Admin Authentication Middleware
router.use((req, res, next) => {
  const authHeader = req.headers.authorization;
  const secret = process.env.KIOSK_SYNC_SECRET;
  
  if (secret && (!authHeader || authHeader !== `Bearer ${secret}`)) {
    return res.status(401).json({ error: 'Unauthorized: Invalid Admin Token' });
  }
  next();
});

// PIN expiry time in minutes (PINs not used within this time will expire)
const PIN_EXPIRY_MINUTES = 10;

// Utility to generate a random 6-digit PIN
function generatePIN() {
  return Math.floor(100000 + Math.random() * 900000).toString();
}

// 1. Get all devices
router.get('/devices', (req, res) => {
  db.all(`SELECT * FROM devices ORDER BY last_heartbeat DESC`, [], (err, rows) => {
    if (err) return res.status(500).json({ error: err.message });
    res.json(rows);
  });
});

// 1.5 Clear all devices
router.delete('/devices', (req, res) => {
  db.run(`DELETE FROM devices`, function(err) {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ message: 'All connected devices cleared' });
  });
});

// 2. Generate a new PIN
router.post('/generate-pin', (req, res) => {
  const { duration_minutes } = req.body;
  if (!duration_minutes || isNaN(duration_minutes)) {
    return res.status(400).json({ error: 'duration_minutes is required and must be a number' });
  }

  const pin = generatePIN();
  db.run(`INSERT INTO sessions (pin, duration_minutes) VALUES (?, ?)`, [pin, duration_minutes], function(err) {
    if (err) {
      // Very rare collision, in real app we'd retry
      return res.status(500).json({ error: 'Failed to generate PIN, please try again.' });
    }
    res.json({ message: 'PIN generated', pin, duration_minutes });
  });
});

// 2.5 Get list of courts from the booking-server (Railway)
router.get('/courts', async (req, res) => {
  const serverUrl = process.env.BOOKING_SERVER_URL;
  if (!serverUrl) {
    return res.json([]);
  }

  try {
    const cleanUrl = serverUrl.replace(/\/$/, '');
    const response = await fetch(`${cleanUrl}/api/courts`);
    if (!response.ok) {
      return res.status(500).json({ error: 'Failed to fetch courts from booking-server' });
    }
    const courts = await response.json();
    res.json(courts);
  } catch (err) {
    console.error('[Admin API] Error fetching courts:', err.message);
    res.status(500).json({ error: err.message });
  }
});

// 2.6 Assign court to device
router.post('/device/:id/court', (req, res) => {
  const { court_id } = req.body;
  const deviceId = req.params.id;

  const assignedCourtId = court_id ? parseInt(court_id) : null;

  db.run(`UPDATE devices SET court_id = ? WHERE device_id = ?`, [assignedCourtId, deviceId], function(err) {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ message: `Device ${deviceId} assigned to court ${assignedCourtId || 'none'}` });
  });
});

// 3. Get all sessions (with pagination support)
router.get('/sessions', (req, res) => {
  const limit = Math.min(parseInt(req.query.limit) || 10, 100);
  const offset = parseInt(req.query.offset) || 0;

  // Use the timezone-safe autoExpireSessions utility to update states
  autoExpireSessions(() => {
    db.get(`SELECT COUNT(*) as total FROM sessions`, [], (countErr, countRow) => {
      if (countErr) return res.status(500).json({ error: countErr.message });

      db.all(`SELECT * FROM sessions ORDER BY CASE status WHEN 'ACTIVE' THEN 1 WHEN 'UNUSED' THEN 2 WHEN 'EXPIRED' THEN 3 ELSE 4 END ASC, created_at DESC LIMIT ? OFFSET ?`, [limit, offset], (err, rows) => {
        if (err) return res.status(500).json({ error: err.message });
        res.json({ sessions: rows, total: countRow.total, limit, offset, server_time: Date.now() });
      });
    });
  });
});

// 3.5 Clear all sessions
router.delete('/sessions', (req, res) => {
  db.run(`DELETE FROM sessions`, function(err) {
    if (err) return res.status(500).json({ error: err.message });
    res.json({ message: 'All sessions cleared' });
  });
});

// 4. Force Unlock / Lock
router.post('/device/:id/force', (req, res) => {
  const { command } = req.body; // 'UNLOCK' or 'LOCK'
  const deviceId = req.params.id;
  
  db.serialize(() => {
    db.run(`UPDATE devices SET pending_command = ? WHERE device_id = ?`, [command, deviceId], function(err) {
      if (err) return console.error('Error setting pending command:', err.message);
    });

    if (command === 'LOCK') {
      db.run(`UPDATE devices SET status = 'LOCKED' WHERE device_id = ?`, [deviceId], function(err) {
        if (err) return console.error('Error force updating device status to LOCKED:', err.message);
      });
      
      db.get(`SELECT id, booking_id FROM sessions WHERE device_id = ? AND status = 'ACTIVE'`, [deviceId], (err, session) => {
        if (err) return console.error('Error getting active session for device:', err.message);
        if (session) {
          db.run(`UPDATE sessions SET status = 'EXPIRED' WHERE id = ?`, [session.id], function(updateErr) {
            if (updateErr) return console.error('Error force expiring session:', updateErr.message);
            if (session.booking_id) {
              try {
                const { updateCloudBookingStatus } = require('../sync');
                updateCloudBookingStatus(session.booking_id, 'completed');
              } catch (syncErr) {
                console.error('[ForceLock] Error triggering cloud booking status update:', syncErr.message);
              }
            }
          });
        }
      });
    } else if (command === 'UNLOCK') {
      db.run(`UPDATE devices SET status = 'IN_USE' WHERE device_id = ?`, [deviceId], function(err) {
        if (err) return console.error('Error force updating device status to IN_USE:', err.message);
      });
    }
  });

  res.json({ message: `Command ${command} queued and status synchronized for device ${deviceId}.` });
});

// 5. Manual Pull Database Sync
router.post('/sync', async (req, res) => {
  try {
    const { syncBookings } = require('../sync');
    await syncBookings();
    res.json({ message: 'Database synchronization completed successfully.' });
  } catch (err) {
    console.error('[Admin API] Error in manual sync:', err.message);
    res.status(500).json({ error: err.message });
  }
});

module.exports = router;
