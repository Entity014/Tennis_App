const express = require('express');
const router = express.Router();
const db = require('../database');
const crypto = require('crypto');

// PIN expiry time in minutes (PINs not used within this time will expire)
const PIN_EXPIRY_MINUTES = 10; // Change to 10 for production

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

// 3. Get all sessions (with pagination support)
router.get('/sessions', (req, res) => {
  const limit = Math.min(parseInt(req.query.limit) || 10, 100);
  const offset = parseInt(req.query.offset) || 0;

  // Auto-expire UNUSED sessions older than PIN_EXPIRY_MINUTES
  const expirySeconds = PIN_EXPIRY_MINUTES * 60;
  db.run(`UPDATE sessions SET status = 'EXPIRED' WHERE status = 'UNUSED' AND (strftime('%s', 'now') - strftime('%s', created_at)) > ${expirySeconds}`, function(expireErr) {
    if (expireErr) console.error('Error expiring unused sessions:', expireErr.message);

    // Auto-expire ACTIVE sessions whose duration has elapsed
    db.run(`UPDATE sessions SET status = 'EXPIRED' WHERE status = 'ACTIVE' AND (strftime('%s', 'now') - strftime('%s', used_at)) > (duration_minutes * 60)`, function(activeExpireErr) {
      if (activeExpireErr) console.error('Error expiring active sessions:', activeExpireErr.message);

      db.get(`SELECT COUNT(*) as total FROM sessions`, [], (countErr, countRow) => {
        if (countErr) return res.status(500).json({ error: countErr.message });

        db.all(`SELECT * FROM sessions ORDER BY created_at DESC LIMIT ? OFFSET ?`, [limit, offset], (err, rows) => {
          if (err) return res.status(500).json({ error: err.message });
          res.json({ sessions: rows, total: countRow.total, limit, offset, server_time: Date.now() });
        });
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
      db.run(`UPDATE sessions SET status = 'EXPIRED' WHERE device_id = ? AND status = 'ACTIVE'`, [deviceId], function(err) {
        if (err) return console.error('Error force expiring session:', err.message);
      });
    } else if (command === 'UNLOCK') {
      db.run(`UPDATE devices SET status = 'IN_USE' WHERE device_id = ?`, [deviceId], function(err) {
        if (err) return console.error('Error force updating device status to IN_USE:', err.message);
      });
    }
  });

  res.json({ message: `Command ${command} queued and status synchronized for device ${deviceId}.` });
});

module.exports = router;
