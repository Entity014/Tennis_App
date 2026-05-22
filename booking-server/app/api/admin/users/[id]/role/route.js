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
    const userId = parseInt(id);
    const { role } = await req.json();

    const validRoles = ['user', 'mod', 'admin'];
    if (!validRoles.includes(role)) {
      return NextResponse.json({ message: `Invalid role. Must be one of: ${validRoles.join(', ')}` }, { status: 400 });
    }

    // Prevent admin from demoting themselves
    if (userId === userPayload.id) {
      return NextResponse.json({ message: 'You cannot change your own role.' }, { status: 400 });
    }

    await prisma.user.update({
      where: { id: userId },
      data: { role }
    });

    return NextResponse.json({ message: `User role updated to "${role}" successfully.` });
  } catch (err) {
    return NextResponse.json({ message: 'Server error updating role', error: err.message }, { status: 500 });
  }
}
