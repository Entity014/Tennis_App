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
    const { status, courtId, date, startTime, endTime } = await req.json();

    const booking = await prisma.booking.findUnique({
      where: { id: bookingId }
    });

    if (!booking) {
      return NextResponse.json({ message: 'Booking not found' }, { status: 404 });
    }

    const updateData = {};
    if (status !== undefined) {
      updateData.status = status;
    }

    if (courtId !== undefined || date !== undefined || startTime !== undefined || endTime !== undefined) {
      const newCourtId = courtId !== undefined ? parseInt(courtId) : booking.courtId;
      const newDate = date !== undefined ? date : booking.date;
      const newStartTime = startTime !== undefined ? startTime : booking.startTime;
      const newEndTime = endTime !== undefined ? endTime : booking.endTime;

      if (newStartTime >= newEndTime) {
        return NextResponse.json({ message: 'Start time must be before end time' }, { status: 400 });
      }

      // Validate target court
      const court = await prisma.court.findUnique({
        where: { id: newCourtId }
      });
      if (!court) {
        return NextResponse.json({ message: 'Selected court not found' }, { status: 400 });
      }
      if (court.isMaintenance) {
        return NextResponse.json({ message: 'Selected court is currently under maintenance' }, { status: 400 });
      }

      // Check overlaps with other active bookings
      const conflictingBooking = await prisma.booking.findFirst({
        where: {
          courtId: newCourtId,
          date: newDate,
          id: { not: bookingId }, // exclude current booking
          status: { in: ['paid', 'pending', 'refund_pending'] },
          OR: [
            {
              startTime: { lte: newStartTime },
              endTime: { gt: newStartTime }
            },
            {
              startTime: { lt: newEndTime },
              endTime: { gte: newEndTime }
            },
            {
              startTime: { gte: newStartTime },
              endTime: { lte: newEndTime }
            }
          ]
        }
      });

      if (conflictingBooking) {
        return NextResponse.json({ message: 'Timeslot conflict with an existing booking' }, { status: 400 });
      }

      updateData.courtId = newCourtId;
      updateData.date = newDate;
      updateData.startTime = newStartTime;
      updateData.endTime = newEndTime;
      
      // Auto-restore to paid if conflict is resolved
      if (booking.status === 'refund_pending' || booking.status === 'refunded') {
        updateData.status = 'paid';
      }
    }

    if (Object.keys(updateData).length === 0) {
      return NextResponse.json({ message: 'No fields to update' }, { status: 400 });
    }

    const updatedBooking = await prisma.booking.update({
      where: { id: bookingId },
      data: updateData
    });

    // Broadcast update
    broadcastEvent({ type: 'booking-updated' });

    return NextResponse.json({ success: true, booking: updatedBooking });
  } catch (err) {
    return NextResponse.json({ message: 'Server error updating booking status', error: err.message }, { status: 500 });
  }
}
