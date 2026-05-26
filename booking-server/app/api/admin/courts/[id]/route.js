import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export async function PUT(req, { params }) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden: Admin access required' }, { status: 403 });
    }

    const { id } = await params;
    const courtId = parseInt(id);
    const { name, name_th, price_per_hour, description, description_th, image_name } = await req.json();

    if (!name || !price_per_hour) {
      return NextResponse.json({ message: 'Name and price_per_hour are required' }, { status: 400 });
    }

    const price = parseFloat(price_per_hour);
    if (isNaN(price) || price < 0) {
      return NextResponse.json({ message: 'Price per hour must be a non-negative number' }, { status: 400 });
    }

    const existingCourt = await prisma.court.findFirst({
      where: {
        name: {
          equals: name.trim(),
          mode: 'insensitive'
        },
        id: {
          not: courtId
        }
      }
    });

    if (existingCourt) {
      return NextResponse.json({ message: 'Court name already exists' }, { status: 400 });
    }

    await prisma.court.update({
      where: { id: courtId },
      data: {
        name,
        nameTh: name_th || '',
        pricePerHour: parseFloat(price_per_hour),
        description: description || '',
        descriptionTh: description_th || '',
        imageName: image_name || 'court_indoor_a'
      }
    });

    return NextResponse.json({ message: 'Court updated successfully' });
  } catch (err) {
    return NextResponse.json({ message: 'Server error updating court', error: err.message }, { status: 500 });
  }
}

export async function DELETE(req, { params }) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden: Admin access required' }, { status: 403 });
    }

    const { id } = await params;
    const courtId = parseInt(id);

    // Delete associated bookings and verified slips first to satisfy foreign keys
    const bookings = await prisma.booking.findMany({
      where: { courtId }
    });
    const bookingIds = bookings.map((b) => b.id);

    await prisma.verifiedSlip.deleteMany({
      where: {
        bookingId: { in: bookingIds }
      }
    });

    await prisma.booking.deleteMany({
      where: { courtId }
    });

    await prisma.court.delete({
      where: { id: courtId }
    });

    return NextResponse.json({ message: 'Court deleted successfully' });
  } catch (err) {
    return NextResponse.json({ message: 'Server error deleting court', error: err.message }, { status: 500 });
  }
}
