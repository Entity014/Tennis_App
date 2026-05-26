import { NextResponse } from 'next/server';
import nodemailer from 'nodemailer';
import prisma from '@/lib/prisma';

const sendResetPinEmail = async (toEmail, pinCode) => {
  const host = process.env.SMTP_HOST;
  const port = parseInt(process.env.SMTP_PORT || '587');
  const user = process.env.SMTP_USER;
  const pass = process.env.SMTP_PASS;

  if (!host || !user || !pass) {
    console.log('[SMTP] SMTP settings not fully configured in env. Skipping real email send.');
    return false;
  }

  try {
    const transporter = nodemailer.createTransport({
      host,
      port,
      secure: port === 465,
      auth: { user, pass }
    });

    const mailOptions = {
      from: `"Mini Tennis Support" <${user}>`,
      to: toEmail,
      subject: 'Mini Tennis Password Reset PIN',
      html: `
        <div style="font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f7; color: #51545e;">
          <div style="max-width: 570px; margin: 0 auto; background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);">
            <h2 style="color: #2b6cb0; text-align: center;">Mini Tennis Password Reset</h2>
            <p>Hello,</p>
            <p>We received a request to reset your Mini Tennis account password. Please use the following 6-digit PIN code to complete the reset process:</p>
            <div style="text-align: center; margin: 30px 0;">
              <span style="font-size: 32px; font-weight: bold; letter-spacing: 5px; color: #1a202c; background-color: #edf2f7; padding: 10px 20px; border-radius: 5px; border: 1px solid #cbd5e0;">${pinCode}</span>
            </div>
            <p>This PIN is valid for 15 minutes. If you did not request a password reset, please ignore this email or contact support.</p>
            <hr style="border: none; border-top: 1px solid #e2e8f0; margin: 30px 0;" />
            <p style="font-size: 12px; color: #a0aec0; text-align: center;">&copy; 2026 Mini Tennis. All rights reserved.</p>
          </div>
        </div>
      `
    };

    await transporter.sendMail(mailOptions);
    console.log(`[SMTP] Reset PIN email sent successfully to: ${toEmail}`);
    return true;
  } catch (err) {
    console.error('[SMTP] Failed to send reset email via SMTP:', err.message);
    return false;
  }
};

export async function POST(req) {
  try {
    const { email } = await req.json();
    if (!email) return NextResponse.json({ message: 'Email is required' }, { status: 400 });

    const user = await prisma.user.findUnique({
      where: { email }
    });

    if (!user) {
      // Prevent user enumeration by returning the same success response
      return NextResponse.json({ message: 'A password reset PIN has been sent to your email.' });
    }

    const resetToken = Math.floor(100000 + Math.random() * 900000).toString();
    const expiry = new Date(Date.now() + 15 * 60 * 1000);

    await prisma.user.update({
      where: { id: user.id },
      data: {
        resetToken,
        resetTokenExpiry: expiry
      }
    });

    const emailSent = await sendResetPinEmail(email, resetToken);

    if (emailSent) {
      return NextResponse.json({ message: 'A password reset PIN has been sent to your email.' });
    } else {
      return NextResponse.json({ 
        message: 'Failed to send password reset email. Please ensure SMTP configuration is active or try again later.' 
      }, { status: 500 });
    }
  } catch (err) {
    return NextResponse.json({ message: 'Server error', error: err.message }, { status: 500 });
  }
}
