import { NextResponse } from 'next/server';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';

export async function GET(req) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload) {
      return NextResponse.json({ message: 'Access token required' }, { status: 401 });
    }

    const user = await prisma.user.findUnique({
      where: { id: userPayload.id },
      select: {
        id: true,
        username: true,
        email: true,
        role: true,
        avatar: true,
        displayName: true
      }
    });

    if (!user) {
      return NextResponse.json({ message: 'User not found' }, { status: 404 });
    }

    // Map displayName back to display_name as expected by the frontend
    const mappedUser = {
      id: user.id,
      username: user.username,
      email: user.email,
      role: user.role,
      avatar: user.avatar,
      display_name: user.displayName
    };

    return NextResponse.json(mappedUser);
  } catch (err) {
    return NextResponse.json({ message: 'Server error', error: err.message }, { status: 500 });
  }
}
