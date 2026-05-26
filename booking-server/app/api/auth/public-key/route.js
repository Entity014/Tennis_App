import { NextResponse } from 'next/server';
import { getRsaKeys } from '@/lib/rsa';
import crypto from 'crypto';

export async function GET() {
  try {
    const { publicKey } = getRsaKeys();
    const keyObject = crypto.createPublicKey(publicKey);
    const jwk = keyObject.export({ format: 'jwk' });
    return NextResponse.json(jwk);
  } catch (err) {
    return NextResponse.json({ error: 'Failed to retrieve public key', details: err.message }, { status: 500 });
  }
}
