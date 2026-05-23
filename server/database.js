const sqlite3 = require('sqlite3').verbose();
const path = require('path');
const fs = require('fs');

// Ensure data directory exists
const dataDir = path.join(__dirname, 'data');
if (!fs.existsSync(dataDir)) {
  fs.mkdirSync(dataDir);
}

const dbPath = path.join(dataDir, 'database.sqlite');
const db = new sqlite3.Database(dbPath, (err) => {
  if (err) {
    console.error('Error opening database', err.message);
  } else {
    console.log('Connected to the SQLite database.');
    
    // Create Devices Table
    db.run(`CREATE TABLE IF NOT EXISTS devices (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      device_id TEXT UNIQUE NOT NULL,
      status TEXT DEFAULT 'OFFLINE',
      last_heartbeat DATETIME DEFAULT CURRENT_TIMESTAMP,
      battery_level INTEGER DEFAULT 100,
      pending_command TEXT,
      court_id INTEGER
    )`);
    // Add column if table already existed (ignore error if it already has it)
    db.run(`ALTER TABLE devices ADD COLUMN pending_command TEXT`, () => {});
    db.run(`ALTER TABLE devices ADD COLUMN court_id INTEGER`, () => {});

    // Create Sessions Table
    db.run(`CREATE TABLE IF NOT EXISTS sessions (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      pin TEXT UNIQUE NOT NULL,
      duration_minutes INTEGER,
      status TEXT DEFAULT 'UNUSED',
      device_id TEXT,
      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
      used_at DATETIME,
      court_id INTEGER,
      booking_date TEXT,
      start_time TEXT,
      end_time TEXT,
      booking_id INTEGER UNIQUE
    )`);
    // Add columns if table already existed for backwards compatibility
    db.run(`ALTER TABLE sessions ADD COLUMN court_id INTEGER`, () => {});
    db.run(`ALTER TABLE sessions ADD COLUMN booking_date TEXT`, () => {});
    db.run(`ALTER TABLE sessions ADD COLUMN start_time TEXT`, () => {});
    db.run(`ALTER TABLE sessions ADD COLUMN end_time TEXT`, () => {});
    db.run(`ALTER TABLE sessions ADD COLUMN booking_id INTEGER`, () => {});
    db.run(`CREATE UNIQUE INDEX IF NOT EXISTS idx_sessions_booking_id ON sessions(booking_id)`, () => {});
  }
});

module.exports = db;
