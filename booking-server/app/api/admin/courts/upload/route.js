import { NextResponse } from 'next/server';
import { verifyAuthToken } from '@/lib/auth';
import { writeFile, mkdir } from 'fs/promises';
import { join } from 'path';

export async function POST(req) {
  try {
    // 1. Verify Authentication & Role
    const userPayload = verifyAuthToken(req);
    if (!userPayload || userPayload.role !== 'admin') {
      return NextResponse.json({ message: 'Forbidden: Admin access required' }, { status: 403 });
    }

    // 2. Parse Multipart Form Data
    const formData = await req.formData();
    const file = formData.get('file');

    if (!file) {
      return NextResponse.json({ message: 'No file uploaded' }, { status: 400 });
    }

    // 3. Validate File Type (must be an image)
    if (!file.type.startsWith('image/')) {
      return NextResponse.json({ message: 'Only image files are allowed' }, { status: 400 });
    }

    // 4. Validate File Size (Max 2MB)
    const MAX_SIZE = 2 * 1024 * 1024; // 2MB
    if (file.size > MAX_SIZE) {
      return NextResponse.json({ message: 'Image size exceeds the 2MB limit' }, { status: 400 });
    }

    // 5. Ensure Uploads Directory Exists
    const uploadDir = join(process.cwd(), 'public', 'uploads');
    await mkdir(uploadDir, { recursive: true });

    // 6. Generate Unique Filename & Save
    const bytes = await file.arrayBuffer();
    const buffer = Buffer.from(bytes);
    
    // Clean filename to prevent path traversal or issues with spaces
    const safeName = file.name.replace(/[^a-zA-Z0-9.\-_]/g, '_');
    const uniqueFilename = `${Date.now()}-${safeName}`;
    const filePath = join(uploadDir, uniqueFilename);
    
    await writeFile(filePath, buffer);

    // 7. Return the relative URL path
    const fileUrl = `/uploads/${uniqueFilename}`;
    return NextResponse.json({ 
      message: 'Image uploaded successfully', 
      url: fileUrl 
    }, { status: 201 });

  } catch (err) {
    return NextResponse.json({ 
      message: 'Server error uploading image', 
      error: err.message 
    }, { status: 500 });
  }
}
