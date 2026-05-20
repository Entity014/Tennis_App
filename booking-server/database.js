const sqlite3 = require('sqlite3').verbose();
const path = require('path');
const bcrypt = require('bcryptjs');

const primaryDbPath = path.resolve(__dirname, 'primary.db');
const subDbPath = path.resolve(__dirname, 'sub.db');

const primaryDb = new sqlite3.Database(primaryDbPath, (err) => {
  if (err) console.error('Error opening Primary database:', err.message);
  else console.log('Connected to the SQLite Primary database.');
});

const subDb = new sqlite3.Database(subDbPath, (err) => {
  if (err) console.error('Error opening Sub database:', err.message);
  else console.log('Connected to the SQLite Sub database.');
});

// Run query on PRIMARY database (Writes) and replicate to SUB database
const runQuery = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    primaryDb.run(sql, params, function (err) {
      if (err) {
        reject(err);
      } else {
        const result = this;
        // Asynchronously replicate the write operation to the sub database with a simulated lag of 50ms
        setTimeout(() => {
          subDb.run(sql, params, (subErr) => {
            if (subErr) {
              console.error('Replication Error: Failed to write to Sub replica.', subErr.message);
            }
          });
        }, 50);
        resolve(result);
      }
    });
  });
};

// Fetch all rows from SUB database (Reads)
const allQuery = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    subDb.all(sql, params, (err, rows) => {
      if (err) reject(err);
      else resolve(rows);
    });
  });
};

// Fetch a single row from SUB database (Reads)
const getQuery = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    subDb.get(sql, params, (err, row) => {
      if (err) reject(err);
      else resolve(row);
    });
  });
};

// Fetch a single row from PRIMARY database (Consistent Reads)
const getPrimaryQuery = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    primaryDb.get(sql, params, (err, row) => {
      if (err) reject(err);
      else resolve(row);
    });
  });
};

// Fetch all rows from PRIMARY database (Consistent Reads)
const allPrimaryQuery = (sql, params = []) => {
  return new Promise((resolve, reject) => {
    primaryDb.all(sql, params, (err, rows) => {
      if (err) reject(err);
      else resolve(rows);
    });
  });
};


// Initialize schema on both databases synchronously during startup
const initDb = async () => {
  const runOnBoth = (sql, params = []) => {
    return Promise.all([
      new Promise((res, rej) => primaryDb.run(sql, params, err => err ? rej(err) : res())),
      new Promise((res, rej) => subDb.run(sql, params, err => err ? rej(err) : res()))
    ]);
  };

  const getOnSub = (sql, params = []) => {
    return new Promise((res, rej) => subDb.get(sql, params, (err, row) => err ? rej(err) : res(row)));
  };

  try {
    // 1. Users Table
    await runOnBoth(`
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
      await runOnBoth('ALTER TABLE users ADD COLUMN reset_token TEXT');
      await runOnBoth('ALTER TABLE users ADD COLUMN reset_token_expiry DATETIME');
    } catch (e) {
      // Columns likely already exist
    }

    // Add display_name column to users table (fails silently if it already exists)
    try {
      await runOnBoth('ALTER TABLE users ADD COLUMN display_name TEXT');
    } catch (e) {
      // Column likely already exists
    }

    // 2. Courts Table (removed location/environment)
    await runOnBoth(`
      CREATE TABLE IF NOT EXISTS courts (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        price_per_hour REAL NOT NULL,
        description TEXT,
        image_name TEXT
      )
    `);

    // 3. Bookings Table (removed court location/environment)
    await runOnBoth(`
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

    // 4. Verified Slips Table (to cache verified slips and avoid duplicate API calls)
    await runOnBoth(`
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
      await runOnBoth('ALTER TABLE verified_slips ADD COLUMN qr_payload TEXT');
    } catch (e) {
      // Column likely already exists
    }

    console.log('Primary and Sub database tables initialized.');

    // Seed Initial Courts if empty
    const courtCount = await getOnSub('SELECT COUNT(*) as count FROM courts');
    if (courtCount.count === 0) {
      console.log('Seeding initial courts to both databases...');
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
        await runOnBoth(
          'INSERT INTO courts (name, price_per_hour, description, image_name) VALUES (?, ?, ?, ?)',
          [court.name, court.price_per_hour, court.description, court.image_name]
        );
      }
    }

    // Seed Default Users if empty
    const userCount = await getOnSub('SELECT COUNT(*) as count FROM users');
    if (userCount.count === 0) {
      console.log('Seeding default users to both databases...');
      const salt = await bcrypt.genSalt(10);
      const playerPass = await bcrypt.hash('player123', salt);
      const adminPass = await bcrypt.hash('admin123', salt);

      await runOnBoth(
        'INSERT INTO users (username, email, password_hash, role) VALUES (?, ?, ?, ?)',
        ['player1', 'player1@tennis.com', playerPass, 'user']
      );
      await runOnBoth(
        'INSERT INTO users (username, email, password_hash, role) VALUES (?, ?, ?, ?)',
        ['admin', 'admin@tennis.com', adminPass, 'admin']
      );
    }

    console.log('Database seeding completed on Primary and Sub.');
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
