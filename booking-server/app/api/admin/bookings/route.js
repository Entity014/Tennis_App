import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export const dynamic = 'force-dynamic';

export async function GET(req) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden: Admin access required' }, {
        status: 403,
        headers: {
          'Cache-Control': 'no-store, max-age=0, must-revalidate'
        }
      });
    }

    const bookings = await prisma.booking.findMany({
      include: {
        user: {
          select: {
            username: true,
            email: true
          }
        },
        court: {
          select: {
            name: true
          }
        }
      },
      orderBy: {
        createdAt: 'desc'
      }
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
      pin_code: b.pinCode,
      payment_method: b.paymentMethod,
      payment_status: b.paymentStatus,
      created_at: b.createdAt,
      username: b.user.username,
      email: b.user.email,
      court_name: b.court.name
    }));

    return NextResponse.json(formattedBookings, {
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  } catch (err) {
    return NextResponse.json({ message: 'Server error fetching bookings', error: err.message }, {
      status: 500,
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  }
}
