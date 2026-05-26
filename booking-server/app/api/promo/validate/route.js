import { NextResponse } from 'next/server';
import { validateAndApplyPromo } from '@/lib/promo';

export async function POST(req) {
  try {
    const { code, price } = await req.json();
    if (!code) {
      return NextResponse.json({ message: 'Promo code is required' }, { status: 400 });
    }
    if (price === undefined || isNaN(price)) {
      return NextResponse.json({ message: 'Price is required and must be a number' }, { status: 400 });
    }

    const result = await validateAndApplyPromo(code, parseFloat(price));
    if (result.isValid) {
      return NextResponse.json({
        isValid: true,
        price: result.price,
        message: result.message
      });
    } else {
      return NextResponse.json({
        isValid: false,
        message: result.message
      }, { status: 400 });
    }
  } catch (err) {
    return NextResponse.json({ message: 'Error validating promo code', error: err.message }, { status: 500 });
  }
}
