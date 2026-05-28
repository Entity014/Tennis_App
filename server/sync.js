const db = require('./database');

async function syncBookings() {
  const serverUrl = process.env.BOOKING_SERVER_URL;
  const syncSecret = process.env.KIOSK_SYNC_SECRET;

  if (!serverUrl || !syncSecret) {
    console.log('[Sync] Sync skipped: BOOKING_SERVER_URL or KIOSK_SYNC_SECRET is not configured.');
    return;
  }

  try {
    const cleanUrl = serverUrl.replace(/\/$/, '');
    const url = `${cleanUrl}/api/kiosk`;
    
    const res = await fetch(url, {
      headers: {
        'Authorization': `Bearer ${syncSecret}`
      }
    });

    if (!res.ok) {
      console.error(`[Sync] Failed to fetch bookings from booking-server: ${res.status} ${res.statusText}`);
      return;
    }

    const data = await res.json();
    const bookings = data.bookings || [];

    // Clean up local sessions whose bookings were deleted/cancelled on the main server
    const fetchedIds = bookings.map(b => b.id);
    const yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    const year = yesterday.getFullYear();
    const month = String(yesterday.getMonth() + 1).padStart(2, '0');
    const day = String(yesterday.getDate()).padStart(2, '0');
    const dateLimitStr = `${year}-${month}-${day}`;

    db.all(
      `SELECT id, booking_id, status, device_id FROM sessions WHERE booking_id IS NOT NULL AND booking_date >= ?`,
      [dateLimitStr],
      (err, localSessions) => {
        if (err) {
          console.error('[Sync] Error querying local sessions for cleanup:', err.message);
          return;
        }

        localSessions.forEach(session => {
          if (!fetchedIds.includes(session.booking_id)) {
            console.log(`[Sync] Booking ${session.booking_id} was deleted/cancelled on the main server. Removing locally.`);
            
            // Force lock the device if the cancelled session was active
            if (session.status === 'ACTIVE' && session.device_id) {
              console.log(`[Sync] Booking ${session.booking_id} was active on device ${session.device_id}. Queuing LOCK command.`);
              db.run(`UPDATE devices SET pending_command = 'LOCK' WHERE device_id = ?`, [session.device_id]);
            }

            db.run(`DELETE FROM sessions WHERE id = ?`, [session.id], (delErr) => {
              if (delErr) {
                console.error(`[Sync] Error deleting session for cancelled booking ${session.booking_id}:`, delErr.message);
              }
            });
          }
        });
      }
    );

    // Sync each booking into local SQLite sessions table
    for (const b of bookings) {
      // Calculate duration in minutes to satisfy duration_minutes NOT NULL constraint in SQLite
      let calculatedDuration = 60; // default 1 hour fallback
      if (b.startTime && b.endTime) {
        const [sh, sm] = b.startTime.split(':').map(Number);
        const [eh, em] = b.endTime.split(':').map(Number);
        calculatedDuration = (eh * 60 + em) - (sh * 60 + sm);
        if (calculatedDuration <= 0) {
          calculatedDuration = 60;
        }
      }

      db.get(`SELECT id, pin, status FROM sessions WHERE booking_id = ?`, [b.id], (err, row) => {
        if (err) {
          console.error(`[Sync] Error checking booking ${b.id}:`, err.message);
          return;
        }

        if (row) {
          // If the booking already exists and is UNUSED/ACTIVE, update it in case details changed.
          if (row.status === 'UNUSED' || row.status === 'ACTIVE') {
            db.run(
              `UPDATE sessions SET pin = ?, court_id = ?, booking_date = ?, start_time = ?, end_time = ?, duration_minutes = ? WHERE booking_id = ?`,
              [b.pinCode, b.courtId, b.date, b.startTime, b.endTime, calculatedDuration, b.id],
              (updateErr) => {
                if (updateErr) {
                  console.error(`[Sync] Error updating session for booking ${b.id}:`, updateErr.message);
                }
              }
            );
          }
        } else {
          // New booking, insert it
          db.run(
            `INSERT INTO sessions (pin, court_id, booking_date, start_time, end_time, booking_id, status, duration_minutes) VALUES (?, ?, ?, ?, ?, ?, 'UNUSED', ?)`,
            [b.pinCode, b.courtId, b.date, b.startTime, b.endTime, b.id, calculatedDuration],
            function(insertErr) {
              if (insertErr) {
                // If it fails because of unique PIN constraint, we catch it
                console.error(`[Sync] Error inserting session for booking ${b.id}:`, insertErr.message);
              } else {
                console.log(`[Sync] Added new booking ${b.id} with PIN ${b.pinCode} for court ${b.courtId} (${calculatedDuration} mins)`);
              }
            }
          );
        }
      });
    }
  } catch (err) {
    console.error('[Sync] Error running sync task:', err.message);
  }
}

function startSyncScheduler() {
  console.log('[Sync] Starting daily sync scheduler (Target: 22:00 / 10 PM everyday)');

  function scheduleNextSync() {
    const now = new Date();
    const next22 = new Date(now);
    next22.setHours(22, 0, 0, 0);

    // If it's already past 22:00 today, schedule for tomorrow 22:00
    if (now >= next22) {
      next22.setDate(next22.getDate() + 1);
    }

    const delay = next22.getTime() - now.getTime();
    const hours = Math.floor(delay / (3600 * 1000));
    const mins = Math.floor((delay % (3600 * 1000)) / (60 * 1000));
    console.log(`[Sync] Next scheduled sync in ${hours}h ${mins}m (at ${next22.toString()})`);

    setTimeout(async () => {
      console.log('[Sync] Triggering scheduled daily sync...');
      await syncBookings();
      scheduleNextSync(); // Schedule next day
    }, delay);
  }

  // Run initial sync on startup
  syncBookings();
  scheduleNextSync();
}

async function updateCloudBookingStatus(bookingId, status) {
  const serverUrl = process.env.BOOKING_SERVER_URL;
  const syncSecret = process.env.KIOSK_SYNC_SECRET;

  if (!serverUrl || !syncSecret) {
    console.log('[Sync] Cloud status update skipped: BOOKING_SERVER_URL or KIOSK_SYNC_SECRET not configured.');
    return;
  }

  try {
    const cleanUrl = serverUrl.replace(/\/$/, '');
    const url = `${cleanUrl}/api/kiosk`;

    console.log(`[Sync] Sending request to update cloud booking ${bookingId} status to '${status}'...`);
    const res = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${syncSecret}`
      },
      body: JSON.stringify({ bookingId, status })
    });

    if (res.ok) {
      console.log(`[Sync] Successfully updated cloud booking ${bookingId} status to '${status}'`);
    } else {
      console.error(`[Sync] Failed to update cloud booking ${bookingId} status: ${res.status} ${res.statusText}`);
    }
  } catch (err) {
    console.error(`[Sync] Error updating cloud booking status for ${bookingId}:`, err.message);
  }
}

module.exports = { startSyncScheduler, syncBookings, updateCloudBookingStatus };
