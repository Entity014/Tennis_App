import jwt from 'jsonwebtoken';

const JWT_SECRET = process.env.JWT_SECRET || 'tennis_secret_key_123';

export function verifyAuthToken(req) {
  const authHeader = req.headers.get('authorization');
  const token = authHeader && authHeader.split(' ')[1];

  if (!token) {
    return null;
  }

  try {
    return jwt.verify(token, JWT_SECRET);
  } catch (err) {
    return null;
  }
}
