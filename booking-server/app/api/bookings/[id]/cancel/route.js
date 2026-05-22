import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';
import { broadcastEvent } from '@/lib/sse';

export async function POST(req, { params }) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload) {
      return NextResponse.json({ message: 'Access token required' }, { status: 401 });
    }

    const { id } = await params;
    const bookingId = parseInt(id);

    // Only allow cancelling pending bookings
    const booking = await prisma.booking.findFirst({
      where: {
        id: bookingId,
        userId: userPayload.id,
        status: 'pending'
      }
    });

    if (!booking) {
      return NextResponse.json({ message: 'Booking not found or not in pending status' }, { status: 404 });
    }

    await prisma.booking.delete({
      where: { id: bookingId }
    });

    // Broadcast update
    broadcastEvent({ type: 'booking-updated' });

    return NextResponse.json({ message: 'Booking cancelled successfully' });
  } catch (err) {
    return NextResponse.json({ message: 'Server error cancelling booking', error: err.message }, { status: 500 });
  }
}
