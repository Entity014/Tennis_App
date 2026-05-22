import { NextResponse } from 'next/server';

export async function GET() {
  return NextResponse.json({
    googleClientId: process.env.GOOGLE_CLIENT_ID || '',
    facebookAppId: process.env.FACEBOOK_APP_ID || '',
    prompayReceiverId: process.env.PROMPAY_RECEIVER_ID || '0917291840'
  });
}
