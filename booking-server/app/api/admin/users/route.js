import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export async function GET(req) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden: Admin access required' }, { status: 403 });
    }

    const users = await prisma.user.findMany({
      select: {
        id: true,
        username: true,
        email: true,
        role: true,
        createdAt: true
      },
      orderBy: {
        createdAt: 'desc'
      }
    });

    const formattedUsers = users.map((u) => ({
      id: u.id,
      username: u.username,
      email: u.email,
      role: u.role,
      created_at: u.createdAt
    }));

    return NextResponse.json(formattedUsers);
  } catch (err) {
    return NextResponse.json({ message: 'Server error fetching users', error: err.message }, { status: 500 });
  }
}
