import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export const dynamic = 'force-dynamic';

export async function GET(req) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden' }, {
        status: 403,
        headers: {
          'Cache-Control': 'no-store, max-age=0, must-revalidate'
        }
      });
    }

    const promoCodes = await prisma.promoCode.findMany({
      orderBy: { createdAt: 'desc' }
    });
    return NextResponse.json(promoCodes, {
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  } catch (err) {
    return NextResponse.json({ message: 'Server error fetching promo codes', error: err.message }, {
      status: 500,
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  }
}

export async function POST(req) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden' }, { status: 403 });
    }

    const { code, discountAmount, discountType, validFrom, validUntil, maxUses, isActive } = await req.json();

    if (!code || discountAmount === undefined) {
      return NextResponse.json({ message: 'Code and discount amount are required' }, { status: 400 });
    }

    const parsedAmount = parseFloat(discountAmount);
    if (isNaN(parsedAmount) || parsedAmount < 0) {
      return NextResponse.json({ message: 'Discount amount must be a positive number' }, { status: 400 });
    }

    if ((discountType || 'percent') === 'percent' && parsedAmount > 100) {
      return NextResponse.json({ message: 'Percentage discount cannot exceed 100%' }, { status: 400 });
    }

    if (!/^[a-zA-Z0-9_\-]+$/.test(code.trim())) {
      return NextResponse.json({ message: 'Promo code can only contain English letters, numbers, hyphens, and underscores' }, { status: 400 });
    }

    const existingCode = await prisma.promoCode.findUnique({
      where: { code }
    });

    if (existingCode) {
      return NextResponse.json({ message: 'Promo code already exists' }, { status: 400 });
    }

    const newPromoCode = await prisma.promoCode.create({
      data: {
        code: code.trim().toUpperCase(),
        discountAmount: parseFloat(discountAmount),
        discountType: discountType || 'percent',
        validFrom: validFrom ? new Date(validFrom) : null,
        validUntil: validUntil ? new Date(validUntil) : null,
        maxUses: maxUses ? parseInt(maxUses) : null,
        isActive: isActive !== undefined ? isActive : true
      }
    });

    return NextResponse.json({ message: 'Promo code created successfully', promoCode: newPromoCode }, { status: 201 });
  } catch (err) {
    return NextResponse.json({ message: 'Server error creating promo code', error: err.message }, { status: 500 });
  }
}
