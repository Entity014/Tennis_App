require('dotenv').config();
const express = require('express');
const cors = require('cors');
const path = require('path');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const https = require('https');
const crypto = require('crypto');
const { Jimp } = require('jimp');
const jsQR = require('jsqr');
const nodemailer = require('nodemailer');
const { initDb, runQuery, allQuery, getQuery, getPrimaryQuery, allPrimaryQuery } = require('./database');

const cleanupExpiredBookings = async () => {
  try {
    // Delete bookings that are pending and created more than 5 minutes ago (using UTC CURRENT_TIMESTAMP)
    await runQuery(`
      DELETE FROM bookings 
      WHERE status = 'pending' 
      AND created_at < datetime('now', '-5 minutes')
    `);
  } catch (err) {
    console.error('Error cleaning up expired bookings:', err.message);
  }
};


const app = express();
const PORT = process.env.PORT || 3001;
const JWT_SECRET = process.env.JWT_SECRET || 'tennis_secret_key_123';

app.use(cors());
app.use(express.json({ limit: '10mb' }));
app.use(express.urlencoded({ limit: '10mb', extended: true }));
// Serve JS and CSS with no-cache so browsers always get the latest version
app.use((req, res, next) => {
  if (req.path.endsWith('.js') || req.path.endsWith('.css')) {
    res.setHeader('Cache-Control', 'no-cache, no-store, must-revalidate');
    res.setHeader('Pragma', 'no-cache');
    res.setHeader('Expires', '0');
  }
  next();
});
app.use(express.static(path.join(__dirname, 'public')));

// Helper for making HTTPS GET requests using native node https module
const httpsGet = (url) => {
  return new Promise((resolve, reject) => {
    https.get(url, (res) => {
      let data = '';
      res.on('data', chunk => data += chunk);
      res.on('end', () => {
        try {
          resolve(JSON.parse(data));
        } catch (e) {
          reject(e);
        }
      });
    }).on('error', reject);
  });
};

// Authentication Middleware
const authenticateToken = (req, res, next) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token) return res.status(401).json({ message: 'Access token required' });

  jwt.verify(token, JWT_SECRET, (err, user) => {
    if (err) return res.status(403).json({ message: 'Invalid or expired token' });
    req.user = user;
    next();
  });
};

// --- Config Route ---
app.get('/api/config', (req, res) => {
  res.json({
    googleClientId: process.env.GOOGLE_CLIENT_ID || '',
    facebookAppId: process.env.FACEBOOK_APP_ID || '',
    prompayReceiverId: process.env.PROMPAY_RECEIVER_ID || '0917291840'
  });
});

// --- Auth Routes ---
app.post('/api/auth/register', async (req, res) => {
  const { username, email, password } = req.body;
  if (!username || !email || !password) {
    return res.status(400).json({ message: 'All fields are required' });
  }

  // Password complexity validation
  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
  if (!passwordRegex.test(password)) {
    return res.status(400).json({ 
      message: 'Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one number.' 
    });
  }

  try {
    const existingUser = await getQuery('SELECT * FROM users WHERE username = ? OR email = ?', [username, email]);
    if (existingUser) {
      return res.status(400).json({ message: 'Username or email already exists' });
    }

    const salt = await bcrypt.genSalt(10);
    const passwordHash = await bcrypt.hash(password, salt);

    await runQuery(
      'INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)',
      [username, email, passwordHash]
    );

    res.status(201).json({ message: 'User registered successfully' });
  } catch (err) {
    res.status(500).json({ message: 'Server error during registration', error: err.message });
  }
});

app.post('/api/auth/login', async (req, res) => {
  const { username, password } = req.body;
  if (!username || !password) {
    return res.status(400).json({ message: 'Username and password are required' });
  }

  try {
    const user = await getQuery('SELECT * FROM users WHERE username = ?', [username]);
    if (!user) {
      return res.status(400).json({ message: 'Invalid credentials' });
    }

    const isMatch = await bcrypt.compare(password, user.password_hash);
    if (!isMatch) {
      return res.status(400).json({ message: 'Invalid credentials' });
    }

    const token = jwt.sign({ id: user.id, username: user.username, role: user.role }, JWT_SECRET, { expiresIn: '24h' });
    res.json({
      token,
      user: {
        id: user.id,
        username: user.username,
        email: user.email,
        role: user.role
      }
    });
  } catch (err) {
    res.status(500).json({ message: 'Server error during login', error: err.message });
  }
});
// Helper to send Password Reset PIN via SMTP (nodemailer)
const sendResetPinEmail = async (toEmail, pinCode) => {
  const host = process.env.SMTP_HOST;
  const port = parseInt(process.env.SMTP_PORT || '587');
  const user = process.env.SMTP_USER;
  const pass = process.env.SMTP_PASS;

  if (!host || !user || !pass) {
    console.log('[SMTP] SMTP settings not fully configured in env. Skipping real email send.');
    return false;
  }

  try {
    const transporter = nodemailer.createTransport({
      host,
      port,
      secure: port === 465, // true for port 465, false for other ports
      auth: { user, pass }
    });

    const mailOptions = {
      from: `"AcePoint Support" <${user}>`,
      to: toEmail,
      subject: 'AcePoint Password Reset PIN',
      html: `
        <div style="font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f7; color: #51545e;">
          <div style="max-width: 570px; margin: 0 auto; background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);">
            <h2 style="color: #2b6cb0; text-align: center;">AcePoint Password Reset</h2>
            <p>Hello,</p>
            <p>We received a request to reset your AcePoint account password. Please use the following 6-digit PIN code to complete the reset process:</p>
            <div style="text-align: center; margin: 30px 0;">
              <span style="font-size: 32px; font-weight: bold; letter-spacing: 5px; color: #1a202c; background-color: #edf2f7; padding: 10px 20px; border-radius: 5px; border: 1px solid #cbd5e0;">${pinCode}</span>
            </div>
            <p>This PIN is valid for 15 minutes. If you did not request a password reset, please ignore this email or contact support.</p>
            <hr style="border: none; border-top: 1px solid #e2e8f0; margin: 30px 0;" />
            <p style="font-size: 12px; color: #a0aec0; text-align: center;">&copy; 2026 AcePoint. All rights reserved.</p>
          </div>
        </div>
      `
    };

    await transporter.sendMail(mailOptions);
    console.log(`[SMTP] Reset PIN email sent successfully to: ${toEmail}`);
    return true;
  } catch (err) {
    console.error('[SMTP] Failed to send reset email via SMTP:', err.message);
    return false;
  }
};

// Forgot Password Endpoint
app.post('/api/auth/forgot-password', async (req, res) => {
  const { email } = req.body;
  if (!email) return res.status(400).json({ message: 'Email is required' });

  try {
    const user = await getQuery('SELECT * FROM users WHERE email = ?', [email]);
    if (!user) {
      // Do not reveal if the user exists for security reasons
      return res.json({ message: 'If the email exists, a PIN has been sent.' });
    }

    // Generate a 6-digit PIN
    const resetToken = Math.floor(100000 + Math.random() * 900000).toString();
    // Expiry: 15 minutes from now
    const expiry = new Date(Date.now() + 15 * 60 * 1000).toISOString();

    await runQuery('UPDATE users SET reset_token = ?, reset_token_expiry = ? WHERE id = ?', [resetToken, expiry, user.id]);

    // Send real email via SMTP helper
    const emailSent = await sendResetPinEmail(email, resetToken);

    if (emailSent) {
      res.json({ message: 'A password reset PIN has been sent to your email.' });
    } else {
      res.status(500).json({ message: 'Failed to send password reset email. Please ensure SMTP configuration is active or try again later.' });
    }
  } catch (err) {
    res.status(500).json({ message: 'Server error', error: err.message });
  }
});

// Reset Password Endpoint
app.post('/api/auth/reset-password', async (req, res) => {
  const { email, token, newPassword } = req.body;
  if (!email || !token || !newPassword) {
    return res.status(400).json({ message: 'All fields are required' });
  }

  const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
  if (!passwordRegex.test(newPassword)) {
    return res.status(400).json({ message: 'Password does not meet complexity requirements.' });
  }

  try {
    const user = await getQuery('SELECT * FROM users WHERE email = ? AND reset_token = ?', [email, token]);
    if (!user) {
      return res.status(400).json({ message: 'Invalid token or email' });
    }

    if (new Date() > new Date(user.reset_token_expiry)) {
      return res.status(400).json({ message: 'Reset token has expired' });
    }

    const salt = await bcrypt.genSalt(10);
    const passwordHash = await bcrypt.hash(newPassword, salt);

    await runQuery('UPDATE users SET password_hash = ?, reset_token = NULL, reset_token_expiry = NULL WHERE id = ?', [passwordHash, user.id]);

    res.json({ message: 'Password has been reset successfully. You can now login.' });
  } catch (err) {
    res.status(500).json({ message: 'Server error', error: err.message });
  }
});


// Google Login Endpoint (Strict live tokeninfo verification)
app.post('/api/auth/google-login', async (req, res) => {
  const { credential } = req.body;

  if (!credential) {
    return res.status(400).json({ message: 'Google Credential token is missing' });
  }

  try {
    console.log('Verifying live Google ID Token...');
    const googleTokenInfo = await httpsGet(`https://oauth2.googleapis.com/tokeninfo?id_token=${credential}`);
    
    if (googleTokenInfo.error || !googleTokenInfo.email) {
      return res.status(400).json({ message: 'Invalid Google Credential token' });
    }
    
    const email = googleTokenInfo.email;
    const name = googleTokenInfo.name || googleTokenInfo.given_name || 'Google User';
    const googleId = googleTokenInfo.sub;

    // Process local user insertion or token generation
    let user = await getQuery('SELECT * FROM users WHERE email = ?', [email]);
    
    if (!user) {
      const suffix = Math.floor(100 + Math.random() * 900);
      const socialUsername = `${name.replace(/\s+/g, '').toLowerCase()}${suffix}`;
      
      const salt = await bcrypt.genSalt(10);
      const dummyPassword = await bcrypt.hash(`google_pass_dummy_${googleId}`, salt);
      
      await runQuery(
        'INSERT INTO users (username, email, password_hash, role, avatar, display_name) VALUES (?, ?, ?, ?, ?, ?)',
        [socialUsername, email, dummyPassword, 'user', 'google', name]
      );
      
      user = await getPrimaryQuery('SELECT * FROM users WHERE email = ?', [email]);
    } else {
      // Update display_name if it was not set or changed
      await runQuery('UPDATE users SET display_name = ?, avatar = ? WHERE id = ?', [name, 'google', user.id]);
      user = await getPrimaryQuery('SELECT * FROM users WHERE email = ?', [email]);
    }

    const token = jwt.sign({ id: user.id, username: user.username, role: user.role }, JWT_SECRET, { expiresIn: '24h' });
    res.json({
      token,
      user: {
        id: user.id,
        username: user.username,
        email: user.email,
        role: user.role,
        avatar: user.avatar,
        display_name: user.display_name
      }
    });
  } catch (err) {
    res.status(500).json({ message: 'Google login server error', error: err.message });
  }
});

app.get('/api/auth/me', authenticateToken, async (req, res) => {
  try {
    const user = await getQuery('SELECT id, username, email, role, avatar, display_name FROM users WHERE id = ?', [req.user.id]);
    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }
    res.json(user);
  } catch (err) {
    res.status(500).json({ message: 'Server error', error: err.message });
  }
});

// --- Court & Availability Routes ---
app.get('/api/courts', async (req, res) => {
  const { date } = req.query; // YYYY-MM-DD
  if (!date) {
    return res.status(400).json({ message: 'Date query parameter is required (format: YYYY-MM-DD)' });
  }

  try {
    await cleanupExpiredBookings();
    const courts = await allPrimaryQuery('SELECT * FROM courts');
    const bookings = await allQuery('SELECT court_id, start_time, end_time FROM bookings WHERE date = ? AND status IN (\'paid\', \'pending\')', [date]);

    // Format court details with booked timeslots (removed location)
    const courtList = courts.map((court) => {
      const bookedSlots = bookings
        .filter((b) => b.court_id === court.id)
        .map((b) => ({ start: b.start_time, end: b.end_time }));
      return {
        ...court,
        booked_slots: bookedSlots
      };
    });

    res.json(courtList);
  } catch (err) {
    res.status(500).json({ message: 'Server error fetching courts', error: err.message });
  }
});

// A simple async mutex class to prevent race conditions during booking creation
class SimpleMutex {
  constructor() {
    this.queue = Promise.resolve();
  }
  async acquire() {
    let release;
    const next = new Promise(resolve => { release = resolve; });
    const current = this.queue;
    this.queue = next;
    await current;
    return release;
  }
}
const bookingMutex = new SimpleMutex();

// --- Booking Routes ---
app.post('/api/bookings', authenticateToken, async (req, res) => {
  const release = await bookingMutex.acquire();
  try {
    const { court_id, date, start_time, end_time, promo_code } = req.body;
    const user_id = req.user.id;

    if (!court_id || !date || !start_time || !end_time) {
      return res.status(400).json({ message: 'Missing booking details' });
    }

    await cleanupExpiredBookings();

    // Prevent booking slots in the past (Thailand timezone offset +07:00)
    const slotDateTime = new Date(`${date}T${start_time}:00+07:00`);
    if (slotDateTime < new Date()) {
      return res.status(400).json({ message: 'Cannot book a timeslot in the past.' });
    }

    // Prevent bookings more than 3 months in advance
    const maxDate = new Date();
    maxDate.setMonth(maxDate.getMonth() + 3);
    if (slotDateTime > maxDate) {
      return res.status(400).json({ message: 'Cannot book more than 3 months in advance.' });
    }

    const court = await getQuery('SELECT * FROM courts WHERE id = ?', [court_id]);
    if (!court) {
      return res.status(404).json({ message: 'Court not found' });
    }

    const collision = await getQuery(
      `SELECT * FROM bookings 
       WHERE court_id = ? AND date = ? AND status IN ('paid', 'pending')
       AND (start_time < ? AND end_time > ?)`,
      [court_id, date, end_time, start_time]
    );

    if (collision) {
      return res.status(409).json({ message: 'Timeslot is already booked' });
    }

    const startHour = parseInt(start_time.split(':')[0]);
    const endHour = parseInt(end_time.split(':')[0]);
    const duration = endHour - startHour;
    let price = court.price_per_hour * duration;

    // Apply Promo Code validation on the server
    if (promo_code === 'ACE10') {
      price = price * 0.90; // 10% discount
    }

    // Support test mode pricing (0.1 Baht)
    if (req.body.is_test === true) {
      price = 0.1;
    }

    const pin_code = Math.floor(100000 + Math.random() * 900000).toString();

    const result = await runQuery(
      `INSERT INTO bookings (user_id, court_id, date, start_time, end_time, price, pin_code) 
       VALUES (?, ?, ?, ?, ?, ?, ?)`,
      [user_id, court_id, date, start_time, end_time, price, pin_code]
    );

    const bookingId = result.lastID;
    const newBooking = await getPrimaryQuery(
      `SELECT b.*, c.name as court_name
       FROM bookings b 
       JOIN courts c ON b.court_id = c.id 
       WHERE b.id = ?`,
      [bookingId]
    );

    res.status(201).json(newBooking);
  } catch (err) {
    res.status(500).json({ message: 'Server error placing booking', error: err.message });
  } finally {
    release();
  }
});

app.get('/api/bookings/my-bookings', authenticateToken, async (req, res) => {
  try {
    const bookings = await allQuery(
      `SELECT b.*, c.name as court_name, c.image_name 
       FROM bookings b 
       JOIN courts c ON b.court_id = c.id 
       WHERE b.user_id = ? 
       ORDER BY b.date DESC, b.start_time DESC`,
      [req.user.id]
    );
    res.json(bookings);
  } catch (err) {
    res.status(500).json({ message: 'Server error fetching user bookings', error: err.message });
  }
});

app.get('/api/bookings/:id', authenticateToken, async (req, res) => {
  try {
    const booking = await getQuery(
      `SELECT b.*, c.name as court_name, c.image_name 
       FROM bookings b 
       JOIN courts c ON b.court_id = c.id 
       WHERE b.id = ? AND b.user_id = ?`,
      [req.params.id, req.user.id]
    );
    if (!booking) {
      return res.status(404).json({ message: 'Booking not found' });
    }
    res.json(booking);
  } catch (err) {
    res.status(500).json({ message: 'Server error fetching booking details', error: err.message });
  }
});

// --- Payment Route ---
app.post('/api/payment/:bookingId', authenticateToken, async (req, res) => {
  const { payment_method } = req.body;
  const bookingId = req.params.bookingId;

  if (!payment_method) {
    return res.status(400).json({ message: 'Payment method is required' });
  }

  try {
    await cleanupExpiredBookings();

    const booking = await getPrimaryQuery('SELECT * FROM bookings WHERE id = ? AND user_id = ?', [bookingId, req.user.id]);
    if (!booking) {
      return res.status(404).json({ message: 'Booking not found' });
    }

    if (booking.status === 'paid') {
      return res.status(400).json({ message: 'Booking is already paid' });
    }

    if (payment_method === 'PromptPay QR') {
      const { slip_image } = req.body;
      console.log('[Payment Route] Request received for bookingId:', bookingId);
      if (!slip_image) {
        console.log('[Payment Route] Error: No slip image provided in request body');
        return res.status(400).json({ message: 'Payment slip image is required' });
      }

      // Calculate SHA-256 hash of the base64 slip image to prevent redundant API calls
      const imageHash = crypto.createHash('sha256').update(slip_image).digest('hex');
      console.log('[Payment Route] Calculated slip image hash:', imageHash);

      // Check if this exact slip image was already verified before
      const existingSlip = await getPrimaryQuery(
        'SELECT * FROM verified_slips WHERE image_hash = ?',
        [imageHash]
      );
      if (existingSlip) {
        console.log('[Payment Route] Error: Slip image hash already verified in database');
        // Delete booking to release slot
        await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
        return res.status(400).json({ message: 'This slip has already been used for another booking.' });
      }

      // Read and decode QR code from the base64 slip image locally
      let localQRData = null;
      try {
        const base64Data = slip_image.replace(/^data:image\/\w+;base64,/, "");
        const imageBuffer = Buffer.from(base64Data, 'base64');
        const jimpImage = await Jimp.read(imageBuffer);
        
        const imageData = {
          data: new Uint8ClampedArray(jimpImage.bitmap.data),
          width: jimpImage.bitmap.width,
          height: jimpImage.bitmap.height
        };
        
        const decodedQR = jsQR(imageData.data, imageData.width, imageData.height);
        if (decodedQR && decodedQR.data) {
          localQRData = decodedQR.data;
          console.log('[Payment Route] Local QR code decoded from slip:', localQRData);
        } else {
          console.log('[Payment Route] No QR code detected in the slip image locally.');
        }
      } catch (qrErr) {
        console.error('[Payment Route] Error decoding QR code locally:', qrErr.message);
      }

      // Check if this QR payload has already been used in our database
      if (localQRData) {
        const existingQR = await getPrimaryQuery(
          'SELECT * FROM verified_slips WHERE trans_ref = ? OR qr_payload = ?',
          [localQRData, localQRData]
        );
        if (existingQR) {
          console.log('[Payment Route] Error: QR code payload already verified in database');
          // Delete booking to release slot
          await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
          return res.status(400).json({ message: 'This slip has already been used for another booking.' });
        }
      }

      const apiKey = process.env.SLIP_API_KEY;
      console.log('[Payment Route] SLIP_API_KEY present:', !!apiKey);

      // In production mode (when SLIP API Key is present), strictly require a readable QR code
      if (apiKey && !localQRData) {
        console.log('[Payment Route] Error: No QR code found in the uploaded image (Production Mode)');
        // Delete booking to release slot
        await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
        return res.status(400).json({ message: 'No valid QR code detected in the uploaded image. Please ensure you upload a clear bank transfer slip.' });
      }

      if (apiKey) {
        try {
          console.log('[Payment Route] Calling SLIP Solution API...');
          // Real SLIP API verification
          const response = await fetch('https://api.thunder.in.th/v2/verify/bank', {
            method: 'POST',
            headers: {
              'Authorization': `Bearer ${apiKey}`,
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              base64: slip_image,
              checkDuplicate: true
            })
          });

          console.log('[Payment Route] SLIP API response status:', response.status);
          const result = await response.json();
          console.log('[Payment Route] SLIP API response result:', JSON.stringify(result));

          if (!result.success) {
            const errMsg = (result.error && result.error.message) ? result.error.message : 'Slip verification failed';
            console.log('[Payment Route] Error: API returned success=false:', errMsg);
            // Delete booking to release slot
            await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
            return res.status(400).json({ message: errMsg });
          }

          // Check if slip is duplicate (has been used before)
          if (result.data && result.data.isDuplicate === true) {
            console.log('[Payment Route] Error: Duplicate slip detected');
            // Cache it locally so we block it locally next time!
            const transRef = result.data.rawSlip ? result.data.rawSlip.transRef : null;
            const qrPayload = result.data.rawSlip ? (result.data.rawSlip.payload || localQRData) : localQRData;
            if (transRef || qrPayload) {
              try {
                await runQuery(
                  'INSERT OR IGNORE INTO verified_slips (image_hash, trans_ref, qr_payload, booking_id) VALUES (?, ?, ?, ?)',
                  [imageHash, transRef || `DUP-${Date.now()}`, qrPayload, bookingId]
                );
              } catch (dbErr) {
                console.error('[Payment Route] Error saving duplicate slip to cache:', dbErr.message);
              }
            }
            // Delete booking to release slot
            await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
            return res.status(400).json({ message: 'This slip has already been used for another booking.' });
          }

          // Check if transRef is already in verified_slips (just to be extra safe against different image compressions of the same slip)
          const transRef = result.data.rawSlip.transRef;
          if (transRef) {
            const existingTransRef = await getPrimaryQuery(
              'SELECT * FROM verified_slips WHERE trans_ref = ?',
              [transRef]
            );
            if (existingTransRef) {
              console.log('[Payment Route] Error: transRef already verified in database');
              // Delete booking to release slot
              await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
              return res.status(400).json({ message: 'This slip has already been used for another booking.' });
            }
          }

          // Enforce Receiver Check (destination account)
          if (result.data.receiver) {
            const cleanTarget = (process.env.PROMPAY_RECEIVER_ID || '0917291840').replace(/\D/g, '');
            const receiverProxy = result.data.receiver.proxy ? result.data.receiver.proxy.value.replace(/\D/g, '') : '';
            const receiverAccount = result.data.receiver.account ? result.data.receiver.account.value.replace(/\D/g, '') : '';
            
            const isMatch = 
              (receiverProxy && (receiverProxy.endsWith(cleanTarget) || cleanTarget.endsWith(receiverProxy))) ||
              (receiverAccount && (receiverAccount.endsWith(cleanTarget) || cleanTarget.endsWith(receiverAccount)));
            
            if (!isMatch) {
              console.log('[Payment Route] Error: Receiver mismatch. Slip receiver proxy:', receiverProxy, 'account:', receiverAccount, 'expected:', cleanTarget);
              // Delete booking to release slot
              await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
              return res.status(400).json({ message: 'Receiver account in the slip does not match our PromptPay account.' });
            }
          }

          // Enforce Date/Time check (transaction must be recent, within last 30 minutes)
          if (result.data.date) {
            const transTime = new Date(result.data.date);
            const now = new Date();
            const timeDiffMinutes = (now - transTime) / 60000;
            
            // Allow up to 30 minutes of time difference
            if (Math.abs(timeDiffMinutes) > 30) {
              console.log('[Payment Route] Error: Transaction time mismatch. Slip time:', transTime, 'Current server time:', now, 'Difference (min):', timeDiffMinutes);
              // Delete booking to release slot
              await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
              return res.status(400).json({ message: 'Transaction time on the slip is not valid for this recent booking. Please upload a recent slip.' });
            }
          }

          // Verify the amount matches (ignore precision minor errors)
          const slipAmount = result.data.rawSlip.amount.amount;
          console.log('[Payment Route] Comparing amount: slip shows', slipAmount, 'booking needs', booking.price);
          if (Math.abs(slipAmount - booking.price) > 0.01) {
            console.log('[Payment Route] Error: Amount mismatch');
            // Delete booking to release slot
            await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
            return res.status(400).json({ 
              message: `Incorrect payment amount. Slip shows ฿${slipAmount}, but booking requires ฿${booking.price}.` 
            });
          }

          // Save to verified_slips to cache it
          if (transRef) {
            const qrPayload = result.data.rawSlip.payload || localQRData;
            await runQuery(
              'INSERT INTO verified_slips (image_hash, trans_ref, qr_payload, booking_id) VALUES (?, ?, ?, ?)',
              [imageHash, transRef, qrPayload, bookingId]
            );
            console.log('[Payment Route] Slip cached successfully in database.');
          }

          // Complete payment
          const refString = `PromptPay QR (Ref: ${transRef || 'N/A'})`;
          console.log('[Payment Route] Completing payment with ref:', refString);
          await runQuery(
            `UPDATE bookings 
             SET status = 'paid', payment_status = 'completed', payment_method = ? 
             WHERE id = ?`,
            [refString, bookingId]
          );
        } catch (fetchErr) {
          console.error('[Payment Route] SLIP API verification error catch:', fetchErr.message);
          // Delete booking to release slot
          await runQuery('DELETE FROM bookings WHERE id = ?', [bookingId]);
          return res.status(500).json({ message: 'Error verifying slip with SLIP API: ' + fetchErr.message });
        }
      } else {
        // Simulation Fallback Mode
        console.log('[Simulation Mode] Verifying uploaded slip (Fallback)...');
        await new Promise(resolve => setTimeout(resolve, 1500)); // Simulate delay

        // Save simulation mock slip in cache
        const mockTransRef = `SIM-${Date.now()}`;
        const qrPayload = localQRData || `SIM-PAYLOAD-${Date.now()}`;
        await runQuery(
          'INSERT INTO verified_slips (image_hash, trans_ref, qr_payload, booking_id) VALUES (?, ?, ?, ?)',
          [imageHash, mockTransRef, qrPayload, bookingId]
        );
        
        await runQuery(
          `UPDATE bookings 
           SET status = 'paid', payment_status = 'completed', payment_method = ? 
           WHERE id = ?`,
          ['PromptPay QR (Simulated)', bookingId]
        );
        console.log('[Simulation Mode] Slip verified and approved.');
      }
    } else {
      // Fallback for Credit Card (if somehow triggered)
      await runQuery(
        `UPDATE bookings 
         SET status = 'paid', payment_status = 'completed', payment_method = ? 
         WHERE id = ?`,
        [payment_method, bookingId]
      );
    }

    const updatedBooking = await getPrimaryQuery(
      `SELECT b.*, c.name as court_name
       FROM bookings b 
       JOIN courts c ON b.court_id = c.id 
       WHERE b.id = ?`,
      [bookingId]
    );

    res.json({ message: 'Payment successful', booking: updatedBooking });
  } catch (err) {
    res.status(500).json({ message: 'Server error during payment', error: err.message });
  }
});

// --- Admin Panel API Endpoints ---
const requireAdmin = (req, res, next) => {
  if (req.user && req.user.role === 'admin') {
    next();
  } else {
    res.status(403).json({ message: 'Forbidden: Admin access required' });
  }
};

// 1. Get stats
app.get('/api/admin/stats', authenticateToken, requireAdmin, async (req, res) => {
  try {
    const totalBookings = await getPrimaryQuery("SELECT COUNT(*) as count FROM bookings");
    const totalRevenue = await getPrimaryQuery("SELECT SUM(price) as total FROM bookings WHERE status = 'paid'");
    const totalUsers = await getPrimaryQuery("SELECT COUNT(*) as count FROM users");
    const totalCourts = await getPrimaryQuery("SELECT COUNT(*) as count FROM courts");
    
    res.json({
      totalBookings: totalBookings.count,
      totalRevenue: totalRevenue.total || 0,
      totalUsers: totalUsers.count,
      totalCourts: totalCourts.count
    });
  } catch (err) {
    res.status(500).json({ message: 'Server error fetching stats', error: err.message });
  }
});

// 2. Get all bookings
app.get('/api/admin/bookings', authenticateToken, requireAdmin, async (req, res) => {
  try {
    const bookings = await allQuery(`
      SELECT b.*, u.username, u.email, c.name as court_name 
      FROM bookings b 
      JOIN users u ON b.user_id = u.id 
      JOIN courts c ON b.court_id = c.id 
      ORDER BY b.created_at DESC
    `);
    res.json(bookings);
  } catch (err) {
    res.status(500).json({ message: 'Server error fetching bookings', error: err.message });
  }
});

// 3. Delete / Cancel booking
app.delete('/api/admin/bookings/:id', authenticateToken, requireAdmin, async (req, res) => {
  const { id } = req.params;
  try {
    await runQuery("DELETE FROM bookings WHERE id = ?", [id]);
    res.json({ message: 'Booking deleted successfully' });
  } catch (err) {
    res.status(500).json({ message: 'Server error deleting booking', error: err.message });
  }
});

// 4. Add court
app.post('/api/admin/courts', authenticateToken, requireAdmin, async (req, res) => {
  const { name, price_per_hour, description, image_name } = req.body;
  if (!name || !price_per_hour) {
    return res.status(400).json({ message: 'Name and price_per_hour are required' });
  }
  try {
    await runQuery(
      "INSERT INTO courts (name, price_per_hour, description, image_name) VALUES (?, ?, ?, ?)",
      [name, parseFloat(price_per_hour), description || '', image_name || 'court_indoor_a']
    );
    res.status(201).json({ message: 'Court added successfully' });
  } catch (err) {
    res.status(500).json({ message: 'Server error adding court', error: err.message });
  }
});

// 5. Update court
app.put('/api/admin/courts/:id', authenticateToken, requireAdmin, async (req, res) => {
  const { id } = req.params;
  const { name, price_per_hour, description, image_name } = req.body;
  if (!name || !price_per_hour) {
    return res.status(400).json({ message: 'Name and price_per_hour are required' });
  }
  try {
    await runQuery(
      "UPDATE courts SET name = ?, price_per_hour = ?, description = ?, image_name = ? WHERE id = ?",
      [name, parseFloat(price_per_hour), description || '', image_name || 'court_indoor_a', id]
    );
    res.json({ message: 'Court updated successfully' });
  } catch (err) {
    res.status(500).json({ message: 'Server error updating court', error: err.message });
  }
});

// 5. Delete court
app.delete('/api/admin/courts/:id', authenticateToken, requireAdmin, async (req, res) => {
  const { id } = req.params;
  try {
    await runQuery("DELETE FROM courts WHERE id = ?", [id]);
    res.json({ message: 'Court deleted successfully' });
  } catch (err) {
    res.status(500).json({ message: 'Server error deleting court', error: err.message });
  }
});

// 6. Get all users
app.get('/api/admin/users', authenticateToken, requireAdmin, async (req, res) => {
  try {
    const users = await allQuery("SELECT id, username, email, role, created_at FROM users ORDER BY created_at DESC");
    res.json(users);
  } catch (err) {
    res.status(500).json({ message: 'Server error fetching users', error: err.message });
  }
});

// For frontend single page app, redirect all non-API paths to index.html
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Initialize DB and start server
initDb().then(() => {
  app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
  });
});
