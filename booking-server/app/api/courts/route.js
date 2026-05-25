import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';

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
    const { searchParams } = new URL(req.url);
    const date = searchParams.get('date');

    if (!date) {
      const courts = await prisma.court.findMany();
      return NextResponse.json(courts.map(c => ({
        id: c.id,
        name: c.name,
        price_per_hour: c.pricePerHour,
        description: c.description,
        image_name: c.imageName
      })), {
        headers: {
          'Cache-Control': 'no-store, max-age=0, must-revalidate'
        }
      });
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

    return NextResponse.json(courtList, {
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  } catch (err) {
    return NextResponse.json({ message: 'Server error fetching courts', error: err.message }, {
      status: 500,
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  }
}
