import { NextResponse } from 'next/server';
import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';
import { getJwtSecret } from '@/lib/auth';
import prisma from '@/lib/prisma';
import { decryptPayload } from '@/lib/rsa';

export async function POST(req) {
  try {
    const body = await req.json();
    let username, password;

    if (body.encryptedData) {
      const decrypted = decryptPayload(body.encryptedData);
      const credentials = JSON.parse(decrypted);
      username = credentials.username;
      password = credentials.password;
    } else {
      username = body.username;
      password = body.password;
    }

    if (!username || !password) {
      return NextResponse.json({ message: 'Username and password are required' }, { status: 400 });
    }

    const user = await prisma.user.findUnique({
      where: { username }
    });

    if (!user) {
      return NextResponse.json({ message: 'Invalid credentials' }, { status: 400 });
    }

    const isMatch = await bcrypt.compare(password, user.passwordHash);
    if (!isMatch) {
      return NextResponse.json({ message: 'Invalid credentials' }, { status: 400 });
    }

    const token = jwt.sign(
      { id: user.id, username: user.username, role: user.role },
      getJwtSecret(),
      { expiresIn: '24h' }
    );

    const response = NextResponse.json({
      message: 'Login successful'
    });

    response.cookies.set('token', token, {
      httpOnly: false,
      secure: process.env.NODE_ENV === 'production',
      sameSite: 'lax',
      path: '/',
      maxAge: 60 * 60 * 24 // 24 hours
    });

    return response;
  } catch (err) {
    return NextResponse.json({ message: 'Server error during login', error: err.message }, { status: 500 });
  }
}
