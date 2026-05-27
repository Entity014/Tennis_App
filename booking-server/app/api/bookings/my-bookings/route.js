import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export const dynamic = 'force-dynamic';

async function cleanupExpiredBookings() {
  try {
    await prisma.$executeRaw`
      DELETE FROM bookings
      WHERE status = 'pending' AND created_at < NOW() - INTERVAL '5 minutes'
    `;
  } catch (err) {
    console.error('Error cleaning up expired bookings:', err.message);
  }
}

export async function GET(req) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload) {
      return NextResponse.json({ message: 'Access token required' }, {
        status: 401,
        headers: {
          'Cache-Control': 'no-store, max-age=0, must-revalidate'
        }
      });
    }

    await cleanupExpiredBookings();

    const bookings = await prisma.booking.findMany({
      where: {
        userId: userPayload.id
      },
      include: {
        court: true
      },
      orderBy: [
        { date: 'desc' },
        { startTime: 'desc' }
      ]
    });

    const formattedBookings = bookings.map((b) => ({
      id: b.id,
      user_id: b.userId,
      court_id: b.courtId,
      date: b.date,
      start_time: b.startTime,
      end_time: b.endTime,
      price: b.price,
      status: b.status,
      pin_code: b.paymentStatus === 'completed' ? b.pinCode : 'PENDING',
      payment_method: b.paymentMethod,
      payment_status: b.paymentStatus,
      created_at: b.createdAt,
      court_name: b.court.name,
      image_name: b.court.imageName,
      court_is_maintenance: b.court.isMaintenance
    }));

    return NextResponse.json(formattedBookings, {
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  } catch (err) {
    return NextResponse.json({ message: 'Server error fetching user bookings', error: err.message }, {
      status: 500,
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  }
}
