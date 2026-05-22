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
