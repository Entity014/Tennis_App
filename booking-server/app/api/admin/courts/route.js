import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export async function POST(req) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden: Admin access required' }, { status: 403 });
    }

    const { name, name_th, price_per_hour, description, description_th, image_name, is_maintenance } = await req.json();

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
        }
      }
    });

    if (existingCourt) {
      return NextResponse.json({ message: 'Court name already exists' }, { status: 400 });
    }

    await prisma.court.create({
      data: {
        name,
        nameTh: name_th || '',
        pricePerHour: parseFloat(price_per_hour),
        description: description || '',
        descriptionTh: description_th || '',
        imageName: image_name || 'court_indoor_a',
        isMaintenance: !!is_maintenance
      }
    });

    return NextResponse.json({ message: 'Court added successfully' }, { status: 201 });
  } catch (err) {
    return NextResponse.json({ message: 'Server error adding court', error: err.message }, { status: 500 });
  }
}
