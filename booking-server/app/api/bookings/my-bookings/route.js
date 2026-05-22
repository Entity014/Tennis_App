import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

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
      return NextResponse.json({ message: 'Access token required' }, { status: 401 });
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
      image_name: b.court.imageName
    }));

    return NextResponse.json(formattedBookings);
  } catch (err) {
    return NextResponse.json({ message: 'Server error fetching user bookings', error: err.message }, { status: 500 });
  }
}
