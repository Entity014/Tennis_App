import fs from 'fs';
import path from 'path';
import crypto from 'crypto';

const KEY_DIR = path.join(process.cwd(), '.keys');
const PRIVATE_KEY_PATH = path.join(KEY_DIR, 'private.pem');
const PUBLIC_KEY_PATH = path.join(KEY_DIR, 'public.pem');

export function getRsaKeys() {
  if (!fs.existsSync(KEY_DIR)) {
    fs.mkdirSync(KEY_DIR, { recursive: true });
  }

  if (!fs.existsSync(PRIVATE_KEY_PATH) || !fs.existsSync(PUBLIC_KEY_PATH)) {
    const { publicKey, privateKey } = crypto.generateKeyPairSync('rsa', {
      modulusLength: 2048,
      publicKeyEncoding: { type: 'spki', format: 'pem' },
      privateKeyEncoding: { type: 'pkcs8', format: 'pem' }
    });
    fs.writeFileSync(PRIVATE_KEY_PATH, privateKey);
    fs.writeFileSync(PUBLIC_KEY_PATH, publicKey);
  }

  const privateKey = fs.readFileSync(PRIVATE_KEY_PATH, 'utf8');
  const publicKey = fs.readFileSync(PUBLIC_KEY_PATH, 'utf8');

  return { privateKey, publicKey };
}

export function decryptPayload(encryptedBase64) {
  try {
    const { privateKey } = getRsaKeys();
    const buffer = Buffer.from(encryptedBase64, 'base64');
    const decrypted = crypto.privateDecrypt(
      {
        key: privateKey,
        padding: crypto.constants.RSA_PKCS1_OAEP_PADDING,
        oaepHash: 'sha256'
      },
      buffer
    );
    return decrypted.toString('utf8');
  } catch (err) {
    console.error('Decryption failed:', err);
    throw new Error('Failed to decrypt payload');
  }
}
