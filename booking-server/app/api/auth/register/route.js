import { NextResponse } from 'next/server';
import bcrypt from 'bcryptjs';
import prisma from '@/lib/prisma';

export async function POST(req) {
  try {
    const { username, email, password } = await req.json();

    if (!username || !email || !password) {
      return NextResponse.json({ message: 'All fields are required' }, { status: 400 });
    }

    // Username complexity validation
    const usernameRegex = /^[a-zA-Z0-9_]{3,20}$/;
    if (!usernameRegex.test(username)) {
      return NextResponse.json({ 
        message: 'Username must be between 3 and 20 characters long and contain only letters, numbers, and underscores.' 
      }, { status: 400 });
    }

    // Password complexity validation
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    if (!passwordRegex.test(password)) {
      return NextResponse.json({ 
        message: 'Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one number.' 
      }, { status: 400 });
    }

    // Check if user already exists
    const existingUser = await prisma.user.findFirst({
      where: {
        OR: [
          { username: username },
          { email: email }
        ]
      }
    });

    if (existingUser) {
      return NextResponse.json({ message: 'Username or email already exists' }, { status: 400 });
    }

    const salt = await bcrypt.genSalt(10);
    const passwordHash = await bcrypt.hash(password, salt);

    await prisma.user.create({
      data: {
        username,
        email,
        passwordHash
      }
    });

    return NextResponse.json({ message: 'User registered successfully' }, { status: 201 });
  } catch (err) {
    return NextResponse.json({ message: 'Server error during registration', error: err.message }, { status: 500 });
  }
}
