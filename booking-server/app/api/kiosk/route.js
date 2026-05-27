import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';

export async function GET(req) {
  const authHeader = req.headers.get('authorization');
  const secret = process.env.KIOSK_SYNC_SECRET;

  if (!secret) {
    console.error('[Kiosk API] KIOSK_SYNC_SECRET is not configured on the booking server.');
    return NextResponse.json({ error: 'KIOSK_SYNC_SECRET is not configured on the booking server.' }, { status: 500 });
  }

  if (!authHeader || authHeader !== `Bearer ${secret}`) {
    return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
  }

  try {
    // Sync bookings from yesterday onwards to capture any timezone/rollover edge cases
    const yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);

    const year = yesterday.getFullYear();
    const month = String(yesterday.getMonth() + 1).padStart(2, '0');
    const day = String(yesterday.getDate()).padStart(2, '0');
    const dateLimitStr = `${year}-${month}-${day}`;

    // Get bookings that are confirmed/paid
    const bookings = await prisma.booking.findMany({
      where: {
        status: 'paid',
        date: {
          gte: dateLimitStr
        },
        court: {
          isMaintenance: false
        }
      },
      select: {
        id: true,
        courtId: true,
        date: true,
        startTime: true,
        endTime: true,
        pinCode: true,
        status: true,
        paymentStatus: true
      }
    });

    return NextResponse.json({ bookings });
  } catch (err) {
    console.error('[Kiosk API] Error fetching bookings:', err.message);
    return NextResponse.json({ error: 'Server error', details: err.message }, { status: 500 });
  }
}

export async function POST(req) {
  const authHeader = req.headers.get('authorization');
  const secret = process.env.KIOSK_SYNC_SECRET;

  if (!secret) {
    console.error('[Kiosk API] KIOSK_SYNC_SECRET is not configured on the booking server.');
    return NextResponse.json({ error: 'KIOSK_SYNC_SECRET is not configured on the booking server.' }, { status: 500 });
  }

  if (!authHeader || authHeader !== `Bearer ${secret}`) {
    return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
  }

  try {
    const { bookingId, status } = await req.json();
    if (!bookingId || !status) {
      return NextResponse.json({ error: 'bookingId and status are required' }, { status: 400 });
    }

    const updatedBooking = await prisma.booking.update({
      where: { id: parseInt(bookingId) },
      data: { status: status }
    });

    return NextResponse.json({ success: true, booking: updatedBooking });
  } catch (err) {
    console.error('[Kiosk API] Error updating booking status:', err.message);
    return NextResponse.json({ error: 'Server error', details: err.message }, { status: 500 });
  }
}
