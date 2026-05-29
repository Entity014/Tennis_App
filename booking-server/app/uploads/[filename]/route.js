import { NextResponse } from 'next/server';
import { readFile } from 'fs/promises';
import { join } from 'path';

export async function GET(req, { params }) {
  try {
    const { filename } = await params;

    // Prevent path traversal
    if (filename.includes('..') || filename.includes('/') || filename.includes('\\')) {
      return NextResponse.json({ message: 'Invalid filename' }, { status: 400 });
    }

    const filePath = join(process.cwd(), 'public', 'uploads', filename);
    const fileBuffer = await readFile(filePath);

    // Determine content type based on extension
    const ext = filename.split('.').pop().toLowerCase();
    let contentType = 'image/jpeg';
    if (ext === 'png') contentType = 'image/png';
    else if (ext === 'webp') contentType = 'image/webp';
    else if (ext === 'gif') contentType = 'image/gif';
    else if (ext === 'svg') contentType = 'image/svg+xml';

    return new NextResponse(fileBuffer, {
      headers: {
        'Content-Type': contentType,
        'Cache-Control': 'public, max-age=31536000, immutable'
      }
    });
  } catch (err) {
    return NextResponse.json({ message: 'Image not found' }, { status: 404 });
  }
}
