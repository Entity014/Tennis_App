import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export async function GET(req, { params }) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload) {
      return NextResponse.json({ message: 'Access token required' }, { status: 401 });
    }

    const { id } = await params;
    const bookingId = parseInt(id);

    const booking = await prisma.booking.findFirst({
      where: {
        id: bookingId,
        userId: userPayload.id
      },
      include: {
        court: true
      }
    });

    if (!booking) {
      return NextResponse.json({ message: 'Booking not found' }, { status: 404 });
    }

    const formattedBooking = {
      id: booking.id,
      user_id: booking.userId,
      court_id: booking.courtId,
      date: booking.date,
      start_time: booking.startTime,
      end_time: booking.endTime,
      price: booking.price,
      status: booking.status,
      pin_code: booking.paymentStatus === 'completed' ? booking.pinCode : 'PENDING',
      payment_method: booking.paymentMethod,
      payment_status: booking.paymentStatus,
      created_at: booking.createdAt,
      court_name: booking.court.name,
      image_name: booking.court.imageName
    };

    return NextResponse.json(formattedBooking);
  } catch (err) {
    return NextResponse.json({ message: 'Server error fetching booking details', error: err.message }, { status: 500 });
  }
}
