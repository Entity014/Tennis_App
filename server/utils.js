const db = require('./database');

/**
 * Checks all active and unused sessions and expires them if they are past their validity.
 * For synced bookings, it checks against the booking schedule.
 * For manual local sessions, it checks against duration.
 * If an active session is expired, it queues a LOCK command for that device.
 */
function autoExpireSessions(callback) {
  const nowMs = Date.now();

  db.all(`SELECT * FROM sessions WHERE status IN ('UNUSED', 'ACTIVE')`, [], (err, rows) => {
    if (err) {
      console.error('[AutoExpire] Error fetching sessions:', err.message);
      if (callback) callback();
      return;
    }

    if (!rows || rows.length === 0) {
      if (callback) callback();
      return;
    }

    let pendingUpdates = 0;

    const checkComplete = () => {
      if (pendingUpdates === 0 && callback) {
        callback();
      }
    };

    rows.forEach(session => {
      let shouldExpire = false;

      if (session.booking_date && session.end_time) {
        // Synced scheduled booking (timezone-aware)
        const endTimeStr = `${session.booking_date}T${session.end_time}:00+07:00`;
        const endTimeMs = new Date(endTimeStr).getTime();
        if (nowMs >= endTimeMs) {
          shouldExpire = true;
        }
      } else {
        // Legacy local session
        if (session.status === 'ACTIVE' && session.duration_minutes) {
          const usedTime = new Date(session.used_at.replace(' ', 'T') + 'Z').getTime();
          const diffMinutes = (nowMs - usedTime) / (1000 * 60);
          if (diffMinutes > session.duration_minutes) {
            shouldExpire = true;
          }
        }
      }

      if (shouldExpire) {
        pendingUpdates++;
        db.run(`UPDATE sessions SET status = 'EXPIRED' WHERE id = ?`, [session.id], (updateErr) => {
          if (updateErr) console.error(`[AutoExpire] Failed to expire session ${session.id}:`, updateErr.message);

          if (session.booking_id) {
            try {
              const { updateCloudBookingStatus } = require('./sync');
              updateCloudBookingStatus(session.booking_id, 'completed');
            } catch (syncErr) {
              console.error('[AutoExpire] Error triggering cloud booking status update:', syncErr.message);
            }
          }

          if (session.status === 'ACTIVE' && session.device_id) {
            console.log(`[AutoExpire] Session expired. Queuing LOCK command for device ${session.device_id}`);
            db.run(`UPDATE devices SET pending_command = 'LOCK' WHERE device_id = ?`, [session.device_id], (devErr) => {
              pendingUpdates--;
              checkComplete();
            });
          } else {
            pendingUpdates--;
            checkComplete();
          }
        });
      }
    });

    if (pendingUpdates === 0 && callback) {
      callback();
    }
  });
}

module.exports = { autoExpireSessions };
