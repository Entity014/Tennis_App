const sqlite3 = require('sqlite3').verbose();
const path = require('path');
const bcrypt = require('bcryptjs');

const dbPath = path.resolve(__dirname, 'tennis.db');

const db = new sqlite3.Database(dbPath, (err) => {
  if (err) console.error('Error opening Tennis database:', err.message);
  else console.log('Connected to the SQLite database.');
});

// For backward compatibility
const primaryDb = db;
const subDb = db;

// Run query on database
const runQuery = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    db.run(sql, params, function (err) {
      if (err) {
        reject(err);
      } else {
        resolve(this);
        // If it's a write query on the bookings table, broadcast the update event
        const lowerSql = sql.toLowerCase();
        if (lowerSql.includes('bookings') && 
            (lowerSql.includes('insert') || lowerSql.includes('update') || lowerSql.includes('delete'))) {
          console.log(`[DB WRITE LOG] SQL: ${sql.trim()}, changes: ${this.changes}`);
          if (this.changes > 0 && typeof global.broadcastEvent === 'function') {
            console.log(`[DB BROADCAST] Broadcasting booking-updated due to: ${sql.trim()}`);
            global.broadcastEvent({ type: 'booking-updated' });
          }
        }
      }
    });
  });
};


// Fetch all rows
const allQuery = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    db.all(sql, params, (err, rows) => {
      if (err) reject(err);
      else resolve(rows);
    });
  });
};

// Fetch a single row
const getQuery = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    db.get(sql, params, (err, row) => {
      if (err) reject(err);
      else resolve(row);
    });
  });
};

// Consistent reads (point to same DB now)
const getPrimaryQuery = getQuery;
const allPrimaryQuery = allQuery;


// Initialize schema synchronously during startup
const initDb = async () => {
  try {
    // 1. Users Table
    await runQuery(`
      CREATE TABLE IF NOT EXISTS users (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        username TEXT UNIQUE NOT NULL,
        email TEXT UNIQUE NOT NULL,
        password_hash TEXT NOT NULL,
        role TEXT DEFAULT 'user',
        avatar TEXT,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP
      )
    `);

    // Add forgot password columns to users table (fails silently if they already exist)
    try {
      await runQuery('ALTER TABLE users ADD COLUMN reset_token TEXT');
      await runQuery('ALTER TABLE users ADD COLUMN reset_token_expiry DATETIME');
    } catch (e) {}

    // Add display_name column to users table (fails silently if it already exists)
    try {
      await runQuery('ALTER TABLE users ADD COLUMN display_name TEXT');
    } catch (e) {}

    // 2. Courts Table
    await runQuery(`
      CREATE TABLE IF NOT EXISTS courts (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        price_per_hour REAL NOT NULL,
        description TEXT,
        image_name TEXT
      )
    `);

    // 3. Bookings Table
    await runQuery(`
      CREATE TABLE IF NOT EXISTS bookings (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        user_id INTEGER NOT NULL,
        court_id INTEGER NOT NULL,
        date TEXT NOT NULL,
        start_time TEXT NOT NULL,
        end_time TEXT NOT NULL,
        price REAL NOT NULL,
        status TEXT DEFAULT 'pending',
        pin_code TEXT NOT NULL,
        payment_method TEXT,
        payment_status TEXT DEFAULT 'pending',
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (user_id) REFERENCES users(id),
        FOREIGN KEY (court_id) REFERENCES courts(id)
      )
    `);

    // 4. Verified Slips Table
    await runQuery(`
      CREATE TABLE IF NOT EXISTS verified_slips (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        image_hash TEXT UNIQUE NOT NULL,
        trans_ref TEXT UNIQUE NOT NULL,
        qr_payload TEXT UNIQUE,
        booking_id INTEGER,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP
      )
    `);

    // Add qr_payload column to verified_slips table (fails silently if it already exists)
    try {
      await runQuery('ALTER TABLE verified_slips ADD COLUMN qr_payload TEXT');
    } catch (e) {}

    console.log('Database tables initialized.');

    // Seed Initial Courts if empty
    const courtCount = await getQuery('SELECT COUNT(*) as count FROM courts');
    if (courtCount.count === 0) {
      console.log('Seeding initial courts...');
      const initialCourts = [
        {
          name: 'Grand Slam Court A',
          price_per_hour: 600,
          description: 'Premium court with advanced lighting and professional ventilation systems.',
          image_name: 'court_indoor_a'
        },
        {
          name: 'Vanguard Court B',
          price_per_hour: 400,
          description: 'Beautiful outdoor court under floodlights, perfect for standard match play.',
          image_name: 'court_outdoor_b'
        },
        {
          name: 'Wimbledon Arena C',
          price_per_hour: 800,
          description: 'Championship-grade indoor arena with turf feedback and full climate control.',
          image_name: 'court_indoor_c'
        },
        {
          name: 'Apex Court D',
          price_per_hour: 500,
          description: 'Standard outdoor court designed to offer optimal joint safety and ball rebound speed.',
          image_name: 'court_outdoor_d'
        }
      ];

      for (const court of initialCourts) {
        await runQuery(
          'INSERT INTO courts (name, price_per_hour, description, image_name) VALUES (?, ?, ?, ?)',
          [court.name, court.price_per_hour, court.description, court.image_name]
        );
      }
    }

    // Seed Default Users if empty
    const userCount = await getQuery('SELECT COUNT(*) as count FROM users');
    if (userCount.count === 0) {
      console.log('Seeding default users...');
      const salt = await bcrypt.genSalt(10);
      const playerPass = await bcrypt.hash('player123', salt);
      const modPass = await bcrypt.hash('mod1234A', salt);
      const adminPass = await bcrypt.hash('admin123', salt);

      await runQuery(
        'INSERT INTO users (username, email, password_hash, role) VALUES (?, ?, ?, ?)',
        ['player1', 'player1@tennis.com', playerPass, 'user']
      );
      await runQuery(
        'INSERT INTO users (username, email, password_hash, role) VALUES (?, ?, ?, ?)',
        ['staff1', 'staff1@tennis.com', modPass, 'mod']
      );
      await runQuery(
        'INSERT INTO users (username, email, password_hash, role) VALUES (?, ?, ?, ?)',
        ['admin', 'admin@tennis.com', adminPass, 'admin']
      );
    }

    console.log('Database seeding completed.');
  } catch (err) {
    console.error('Error during database initialization/seeding:', err.message);
  }
};

module.exports = {
  primaryDb,
  subDb,
  initDb,
  runQuery,
  allQuery,
  getQuery,
  getPrimaryQuery,
  allPrimaryQuery
};
