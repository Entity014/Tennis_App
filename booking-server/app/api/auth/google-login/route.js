import { NextResponse } from 'next/server';
import bcrypt from 'bcryptjs';
import jwt from 'jsonwebtoken';
import { getJwtSecret } from '@/lib/auth';
import prisma from '@/lib/prisma';
import { decryptPayload } from '@/lib/rsa';

export async function POST(req) {
  try {
    let body = await req.json();

    if (body.encryptedData) {
      const decrypted = decryptPayload(body.encryptedData);
      body = JSON.parse(decrypted);
    }

    const { credential, isSimulation, email: simEmail, name: simName, id: simId } = body;

    let email, name, googleId;

    if (isSimulation) {
      email = simEmail;
      name = simName || 'Google User';
      googleId = simId || 'dummy_google_id';
    } else {
      if (!credential) {
        return NextResponse.json({ message: 'Google Credential token is missing' }, { status: 400 });
      }

      console.log('Verifying live Google ID Token...');
      const tokenResponse = await fetch(`https://oauth2.googleapis.com/tokeninfo?id_token=${credential}`);
      const googleTokenInfo = await tokenResponse.json();

      if (googleTokenInfo.error || !googleTokenInfo.email) {
        return NextResponse.json({ message: 'Invalid Google Credential token' }, { status: 400 });
      }

      email = googleTokenInfo.email;
      name = googleTokenInfo.name || googleTokenInfo.given_name || 'Google User';
      googleId = googleTokenInfo.sub;
    }

    let user = await prisma.user.findUnique({
      where: { email }
    });

    if (!user) {
      const suffix = Math.floor(100 + Math.random() * 900);
      const socialUsername = `${name.replace(/\s+/g, '').toLowerCase()}${suffix}`;

      const salt = await bcrypt.genSalt(10);
      const dummyPassword = await bcrypt.hash(`google_pass_dummy_${googleId}`, salt);

      user = await prisma.user.create({
        data: {
          username: socialUsername,
          email,
          passwordHash: dummyPassword,
          role: 'user',
          avatar: 'google',
          displayName: name
        }
      });
    } else {
      user = await prisma.user.update({
        where: { id: user.id },
        data: {
          displayName: name,
          avatar: 'google'
        }
      });
    }

    const token = jwt.sign(
      { id: user.id, username: user.username, role: user.role },
      getJwtSecret(),
      { expiresIn: '24h' }
    );

    const response = NextResponse.json({
      message: 'Google login successful'
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
    return NextResponse.json({ message: 'Google login server error', error: err.message }, { status: 500 });
  }
}

