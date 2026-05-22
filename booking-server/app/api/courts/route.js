import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';

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
    const { searchParams } = new URL(req.url);
    const date = searchParams.get('date');

    if (!date) {
      return NextResponse.json({ message: 'Date query parameter is required (format: YYYY-MM-DD)' }, { status: 400 });
    }

    await cleanupExpiredBookings();

    const courts = await prisma.court.findMany();
    const bookings = await prisma.booking.findMany({
      where: {
        date: date,
        status: {
          in: ['paid', 'pending']
        }
      },
      select: {
        courtId: true,
        startTime: true,
        endTime: true
      }
    });

    const courtList = courts.map((court) => {
      const bookedSlots = bookings
        .filter((b) => b.courtId === court.id)
        .map((b) => ({ start: b.startTime, end: b.endTime }));

      return {
        id: court.id,
        name: court.name,
        price_per_hour: court.pricePerHour,
        description: court.description,
        image_name: court.imageName,
        booked_slots: bookedSlots
      };
    });

    return NextResponse.json(courtList);
  } catch (err) {
    return NextResponse.json({ message: 'Server error fetching courts', error: err.message }, { status: 500 });
  }
}
