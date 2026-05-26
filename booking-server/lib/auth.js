import jwt from 'jsonwebtoken';

export function getJwtSecret() {
  const secret = process.env.JWT_SECRET;
  if (!secret) {
    throw new Error('JWT_SECRET environment variable is missing.');
  }
  return secret;
}

export function verifyAuthToken(req) {
  const authHeader = req.headers.get('authorization');
  let token = authHeader && authHeader.split(' ')[1];

  if (!token) {
    // Try to get token from NextRequest cookies if present
    if (req.cookies && typeof req.cookies.get === 'function') {
      const cookieVal = req.cookies.get('token');
      if (cookieVal) {
        token = typeof cookieVal === 'string' ? cookieVal : cookieVal.value;
      }
    }
    // Fallback to manual parsing from the raw Cookie header
    if (!token) {
      const cookieHeader = req.headers.get('cookie');
      if (cookieHeader) {
        const match = cookieHeader.match(/(?:^|;)\s*token\s*=\s*([^;]+)/);
        if (match) {
          token = match[1];
        }
      }
    }
  }

  if (!token) {
    return null;
  }

  try {
    const secret = getJwtSecret();
    return jwt.verify(token, secret);
  } catch (err) {
    return null;
  }
}
