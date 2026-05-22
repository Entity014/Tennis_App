import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';
import { broadcastEvent } from '@/lib/sse';

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

export async function POST(req) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload) {
      return NextResponse.json({ message: 'Access token required' }, { status: 401 });
    }

    const { court_id, date, start_time, end_time, promo_code } = await req.json();
    const user_id = userPayload.id;
    const userRole = userPayload.role;

    if (!court_id || !date || !start_time || !end_time) {
      return NextResponse.json({ message: 'Missing booking details' }, { status: 400 });
    }

    await cleanupExpiredBookings();

    // Prevent booking slots in the past (Thailand timezone offset +07:00)
    const slotDateTime = new Date(`${date}T${start_time}:00+07:00`);
    if (slotDateTime < new Date()) {
      return NextResponse.json({ message: 'Cannot book a timeslot in the past.' }, { status: 400 });
    }

    // Prevent bookings more than 3 months in advance
    const maxDate = new Date();
    maxDate.setMonth(maxDate.getMonth() + 3);
    if (slotDateTime > maxDate) {
      return NextResponse.json({ message: 'Cannot book more than 3 months in advance.' }, { status: 400 });
    }

    const court = await prisma.court.findUnique({
      where: { id: parseInt(court_id) }
    });
    if (!court) {
      return NextResponse.json({ message: 'Court not found' }, { status: 404 });
    }

    // Check collision (double booking overlap check)
    const collision = await prisma.booking.findFirst({
      where: {
        courtId: parseInt(court_id),
        date: date,
        status: {
          in: ['paid', 'pending']
        },
        startTime: {
          lt: end_time
        },
        endTime: {
          gt: start_time
        }
      }
    });

    if (collision) {
      return NextResponse.json({ message: 'Timeslot is already booked' }, { status: 409 });
    }

    const startHour = parseInt(start_time.split(':')[0]);
    const endHour = parseInt(end_time.split(':')[0]);
    const duration = endHour - startHour;
    let price = court.pricePerHour * duration;

    // Apply Promo Code validation on the server
    if (promo_code === 'ACE10') {
      price = price * 0.90; // 10% discount
    }

    const pin_code = Math.floor(100000 + Math.random() * 900000).toString();

    // mod and admin get instant paid booking (cash on-site)
    const isPrivileged = userRole === 'mod' || userRole === 'admin';

    let newBooking;
    if (isPrivileged) {
      newBooking = await prisma.booking.create({
        data: {
          userId: user_id,
          courtId: parseInt(court_id),
          date,
          startTime: start_time,
          endTime: end_time,
          price,
          pinCode: pin_code,
          status: 'paid',
          paymentStatus: 'completed',
          paymentMethod: 'Cash (Staff)'
        },
        include: {
          court: true
        }
      });

      // Broadcast update
      broadcastEvent({ type: 'booking-updated' });

      return NextResponse.json({
        id: newBooking.id,
        user_id: newBooking.userId,
        court_id: newBooking.courtId,
        date: newBooking.date,
        start_time: newBooking.startTime,
        end_time: newBooking.endTime,
        price: newBooking.price,
        status: newBooking.status,
        pin_code: newBooking.paymentStatus === 'completed' ? newBooking.pinCode : 'PENDING',
        payment_method: newBooking.paymentMethod,
        payment_status: newBooking.paymentStatus,
        created_at: newBooking.createdAt,
        court_name: newBooking.court.name,
        _cashBooking: true
      }, { status: 201 });
    }

    newBooking = await prisma.booking.create({
      data: {
        userId: user_id,
        courtId: parseInt(court_id),
        date,
        startTime: start_time,
        endTime: end_time,
        price,
        pinCode: pin_code
      },
      include: {
        court: true
      }
    });

    // Broadcast update
    broadcastEvent({ type: 'booking-updated' });

    return NextResponse.json({
      id: newBooking.id,
      user_id: newBooking.userId,
      court_id: newBooking.courtId,
      date: newBooking.date,
      start_time: newBooking.startTime,
      end_time: newBooking.endTime,
      price: newBooking.price,
      status: newBooking.status,
      pin_code: newBooking.paymentStatus === 'completed' ? newBooking.pinCode : 'PENDING',
      payment_method: newBooking.paymentMethod,
      payment_status: newBooking.paymentStatus,
      created_at: newBooking.createdAt,
      court_name: newBooking.court.name
    }, { status: 201 });
  } catch (err) {
    return NextResponse.json({ message: 'Server error placing booking', error: err.message }, { status: 500 });
  }
}
