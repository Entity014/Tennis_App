import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';
import { broadcastEvent } from '@/lib/sse';

export async function DELETE(req, { params }) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden: Admin access required' }, { status: 403 });
    }

    const { id } = await params;
    const bookingId = parseInt(id);

    // Also delete any associated verified slips first due to foreign keys
    await prisma.verifiedSlip.deleteMany({
      where: { bookingId }
    });

    await prisma.booking.delete({
      where: { id: bookingId }
    });

    // Broadcast update
    broadcastEvent({ type: 'booking-updated' });

    return NextResponse.json({ message: 'Booking deleted successfully' });
  } catch (err) {
    return NextResponse.json({ message: 'Server error deleting booking', error: err.message }, { status: 500 });
  }
}

export async function PUT(req, { params }) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden: Admin access required' }, { status: 403 });
    }

    const { id } = await params;
    const bookingId = parseInt(id);
    const { status } = await req.json();

    if (!status) {
      return NextResponse.json({ message: 'Status is required' }, { status: 400 });
    }

    const updatedBooking = await prisma.booking.update({
      where: { id: bookingId },
      data: { status }
    });

    // Broadcast update
    broadcastEvent({ type: 'booking-updated' });

    return NextResponse.json({ success: true, booking: updatedBooking });
  } catch (err) {
    return NextResponse.json({ message: 'Server error updating booking status', error: err.message }, { status: 500 });
  }
}
