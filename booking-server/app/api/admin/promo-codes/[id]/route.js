import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export async function PUT(req, { params }) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden' }, { status: 403 });
    }

    const { id } = await params;
    const { code, discountAmount, discountType, validFrom, validUntil, maxUses, isActive } = await req.json();

    const existingPromo = await prisma.promoCode.findUnique({
      where: { id: parseInt(id) }
    });

    if (!existingPromo) {
      return NextResponse.json({ message: 'Promo code not found' }, { status: 404 });
    }

    const finalType = discountType !== undefined ? discountType : existingPromo.discountType;
    const finalAmount = discountAmount !== undefined ? parseFloat(discountAmount) : existingPromo.discountAmount;

    if (isNaN(finalAmount) || finalAmount < 0) {
      return NextResponse.json({ message: 'Discount amount must be a positive number' }, { status: 400 });
    }

    if (finalType === 'percent' && finalAmount > 100) {
      return NextResponse.json({ message: 'Percentage discount cannot exceed 100%' }, { status: 400 });
    }

    if (code && code !== existingPromo.code) {
      if (!/^[a-zA-Z0-9_\-]+$/.test(code.trim())) {
        return NextResponse.json({ message: 'Promo code can only contain English letters, numbers, hyphens, and underscores' }, { status: 400 });
      }
      const checkCode = await prisma.promoCode.findUnique({ where: { code } });
      if (checkCode) {
        return NextResponse.json({ message: 'Promo code already exists' }, { status: 400 });
      }
    }

    const updatedPromo = await prisma.promoCode.update({
      where: { id: parseInt(id) },
      data: {
        code: code ? code.trim().toUpperCase() : existingPromo.code,
        discountAmount: discountAmount !== undefined ? parseFloat(discountAmount) : existingPromo.discountAmount,
        discountType: discountType !== undefined ? discountType : existingPromo.discountType,
        validFrom: validFrom !== undefined ? (validFrom ? new Date(validFrom) : null) : existingPromo.validFrom,
        validUntil: validUntil !== undefined ? (validUntil ? new Date(validUntil) : null) : existingPromo.validUntil,
        maxUses: maxUses !== undefined ? (maxUses ? parseInt(maxUses) : null) : existingPromo.maxUses,
        isActive: isActive !== undefined ? isActive : existingPromo.isActive
      }
    });

    return NextResponse.json({ message: 'Promo code updated successfully', promoCode: updatedPromo });
  } catch (err) {
    return NextResponse.json({ message: 'Server error updating promo code', error: err.message }, { status: 500 });
  }
}

export async function PATCH(req, { params }) {
  return PUT(req, { params });
}

export async function DELETE(req, { params }) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden' }, { status: 403 });
    }

    const { id } = await params;

    await prisma.promoCode.delete({
      where: { id: parseInt(id) }
    });

    return NextResponse.json({ message: 'Promo code deleted successfully' });
  } catch (err) {
    return NextResponse.json({ message: 'Server error deleting promo code', error: err.message }, { status: 500 });
  }
}
