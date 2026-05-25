import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export const dynamic = 'force-dynamic';

export async function GET(req) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden: Admin access required' }, {
        status: 403,
        headers: {
          'Cache-Control': 'no-store, max-age=0, must-revalidate'
        }
      });
    }

    const totalBookings = await prisma.booking.count();
    
    const revenueAggregate = await prisma.booking.aggregate({
      where: { status: 'paid' },
      _sum: { price: true }
    });
    const totalRevenue = revenueAggregate._sum.price || 0;

    const totalUsers = await prisma.user.count();
    const totalCourts = await prisma.court.count();

    return NextResponse.json({
      totalBookings,
      totalRevenue,
      totalUsers,
      totalCourts
    }, {
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  } catch (err) {
    return NextResponse.json({ message: 'Server error fetching stats', error: err.message }, {
      status: 500,
      headers: {
        'Cache-Control': 'no-store, max-age=0, must-revalidate'
      }
    });
  }
}
