import { NextResponse } from 'next/server';
import bcrypt from 'bcryptjs';
import prisma from '@/lib/prisma';

export async function POST(req) {
  try {
    const { email, token, newPassword } = await req.json();

    if (!email || !token || !newPassword) {
      return NextResponse.json({ message: 'All fields are required' }, { status: 400 });
    }

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    if (!passwordRegex.test(newPassword)) {
      return NextResponse.json({ message: 'Password does not meet complexity requirements.' }, { status: 400 });
    }

    const user = await prisma.user.findFirst({
      where: {
        email,
        resetToken: token
      }
    });

    if (!user) {
      return NextResponse.json({ message: 'Invalid token or email' }, { status: 400 });
    }

    if (new Date() > new Date(user.resetTokenExpiry)) {
      return NextResponse.json({ message: 'Reset token has expired' }, { status: 400 });
    }

    const salt = await bcrypt.genSalt(10);
    const passwordHash = await bcrypt.hash(newPassword, salt);

    await prisma.user.update({
      where: { id: user.id },
      data: {
        passwordHash,
        resetToken: null,
        resetTokenExpiry: null
      }
    });

    return NextResponse.json({ message: 'Password has been reset successfully. You can now login.' });
  } catch (err) {
    return NextResponse.json({ message: 'Server error', error: err.message }, { status: 500 });
  }
}
