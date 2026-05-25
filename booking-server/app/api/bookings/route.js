import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';
import { broadcastEvent } from '@/lib/sse';
import { validateAndApplyPromo } from '@/lib/promo';

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

    const startHour = parseInt(start_time.split(':')[0]);
    const endHour = parseInt(end_time.split(':')[0]);
    const duration = endHour - startHour;

    if (duration <= 0) {
      return NextResponse.json({ message: 'Invalid booking duration.' }, { status: 400 });
    }

    let price = court.pricePerHour * duration;

    // Apply Promo Code validation on the server using database
    if (promo_code) {
      const promoResult = await validateAndApplyPromo(promo_code, price);
      if (promoResult.isValid) {
        price = promoResult.price;
      } else {
        return NextResponse.json({ message: promoResult.message }, { status: 400 });
      }
    }

    const pin_code = Math.floor(100000 + Math.random() * 900000).toString();

    // mod and admin get instant paid booking (cash on-site)
    const isPrivileged = userRole === 'mod' || userRole === 'admin';

    try {
      const newBooking = await prisma.$transaction(async (tx) => {
        // Check collision (double booking overlap check) inside transaction
        const collision = await tx.booking.findFirst({
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
          throw new Error('Timeslot is already booked');
        }

        if (isPrivileged) {
          return await tx.booking.create({
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
        }

        return await tx.booking.create({
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
      }, {
        isolationLevel: 'Serializable'
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
        _cashBooking: isPrivileged ? true : undefined
      }, { status: 201 });
    } catch (txErr) {
      if (txErr.message === 'Timeslot is already booked') {
        return NextResponse.json({ message: txErr.message }, { status: 409 });
      }
      throw txErr;
    }
  } catch (err) {
    return NextResponse.json({ message: 'Server error placing booking', error: err.message }, { status: 500 });
  }
}
