import prisma from './prisma';

let lastCleanup = 0;
const CLEANUP_INTERVAL = 60 * 1000; // 1 minute throttle

let last30DaysCleanup = 0;
const ONE_DAY_MS = 24 * 60 * 60 * 1000; // 24 hours throttle for old bookings cleanup

/**
 * Deletes all bookings (and their associated verified slips) older than 30 days.
 * Throttled to run at most once every 24 hours.
 */
async function cleanupOldBookings() {
  const now = Date.now();
  if (now - last30DaysCleanup < ONE_DAY_MS) {
    return;
  }
  
  last30DaysCleanup = now;
  console.log('[Cleanup] Starting 30-day database cleanup for old bookings...');

  try {
    // Nullify booking_id on verified_slips to detach them safely while preserving slip hashes (preventing reuse)
    await prisma.$executeRaw`
      UPDATE verified_slips
      SET booking_id = NULL
      WHERE booking_id IN (
        SELECT id FROM bookings
        WHERE created_at < NOW() - INTERVAL '30 days'
      )
    `;

    const deletedCount = await prisma.$executeRaw`
      DELETE FROM bookings
      WHERE created_at < NOW() - INTERVAL '30 days'
    `;
    if (deletedCount > 0) {
      console.log(`[Cleanup] Successfully deleted ${deletedCount} booking(s) older than 30 days (slips preserved).`);
    }
  } catch (err) {
    console.error('[Cleanup] Error during 30-day cleanup:', err.message);
  }
}

/**
 * Periodically deletes pending bookings that were created more than 5 minutes ago.
 * Executed at most once per minute to prevent DB lock contention under high load.
 */
export async function throttledCleanupExpiredBookings() {
  const now = Date.now();
  if (now - lastCleanup < CLEANUP_INTERVAL) {
    return;
  }

  // Prevent concurrent calls from passing the check before the delete query finishes
  lastCleanup = now;

  try {
    const deletedCount = await prisma.$executeRaw`
      DELETE FROM bookings
      WHERE status = 'pending' AND created_at < NOW() - INTERVAL '5 minutes'
    `;
    if (deletedCount > 0) {
      console.log(`[Cleanup] Deleted ${deletedCount} expired pending booking(s).`);
    }

    // Trigger the daily 30-day old bookings cleanup task
    await cleanupOldBookings();
  } catch (err) {
    console.error('Error cleaning up expired bookings:', err.message);
  }
}
