import { NextResponse } from 'next/server';
import crypto from 'crypto';
import { Jimp } from 'jimp';
import jsQR from 'jsqr';
import prisma from '@/lib/prisma';
import { verifyAuthToken } from '@/lib/auth';
import { broadcastEvent } from '@/lib/sse';

async function cleanupExpiredBookings() {
  try {
    await prisma.$executeRaw`
      DELETE FROM bookings
      WHERE status = 'pending' AND created_at < NOW() - INTERVAL '5 minutes'
    `;
  } catch (err) {
    console.error('Error cleaning up expired bookings:', err.message);
  }
}

async function saveFailedSlip(imageHash, transRef, qrPayload, bookingId) {
  try {
    const dummyRef = transRef || `FAILED-${imageHash}`;
    const dummyPayload = qrPayload || `FAILED-${imageHash}`;
    
    // Check if it already exists to avoid unique constraint violations
    const exists = await prisma.verifiedSlip.findFirst({
      where: {
        OR: [
          { imageHash },
          { transRef: dummyRef }
        ]
      }
    });
    
    if (!exists) {
      await prisma.verifiedSlip.create({
        data: {
          imageHash,
          transRef: dummyRef,
          qrPayload: dummyPayload,
          bookingId
        }
      });
      console.log('[Payment Route] Cached failed slip hash in database.');
    }
  } catch (dbErr) {
    console.error('[Payment Route] Error caching failed slip:', dbErr.message);
  }
}

export async function POST(req, { params }) {
  try {
    const userPayload = verifyAuthToken(req);
    if (!userPayload) {
      return NextResponse.json({ message: 'Access token required' }, { status: 401 });
    }

    const { payment_method, promo_code, slip_image } = await req.json();
    const { bookingId } = await params;
    const bId = parseInt(bookingId);

    if (!payment_method) {
      return NextResponse.json({ message: 'Payment method is required' }, { status: 400 });
    }

    await cleanupExpiredBookings();

    const booking = await prisma.booking.findFirst({
      where: {
        id: bId,
        userId: userPayload.id
      }
    });

    if (!booking) {
      return NextResponse.json({ message: 'Booking not found' }, { status: 404 });
    }

    if (booking.status === 'paid') {
      return NextResponse.json({ message: 'Booking is already paid' }, { status: 400 });
    }

    let finalPrice = booking.price;
    // Apply Promo Code if valid and update price in DB
    if (promo_code === 'ACE10') {
      const court = await prisma.court.findUnique({
        where: { id: booking.courtId }
      });
      if (court) {
        const startHour = parseInt(booking.startTime.split(':')[0]);
        const endHour = parseInt(booking.endTime.split(':')[0]);
        const duration = endHour - startHour;
        finalPrice = court.pricePerHour * duration * 0.90;
        
        await prisma.booking.update({
          where: { id: bId },
          data: { price: finalPrice }
        });
      }
    }

    if (payment_method === 'PromptPay QR') {
      console.log('[Payment Route] Request received for bookingId:', bId);
      if (!slip_image) {
        console.log('[Payment Route] Error: No slip image provided in request body');
        return NextResponse.json({ message: 'Payment slip image is required' }, { status: 400 });
      }

      // Calculate SHA-256 hash of the base64 slip image
      const imageHash = crypto.createHash('sha256').update(slip_image).digest('hex');
      console.log('[Payment Route] Calculated slip image hash:', imageHash);

      // Check if this exact slip image was already verified before
      const existingSlip = await prisma.verifiedSlip.findUnique({
        where: { imageHash }
      });
      if (existingSlip) {
        console.log('[Payment Route] Error: Slip image hash already verified in database');
        return NextResponse.json({ message: 'This slip has already been used for another booking.' }, { status: 400 });
      }

      // Read and decode QR code from the base64 slip image locally
      let localQRData = null;
      try {
        const base64Data = slip_image.replace(/^data:image\/\w+;base64,/, "");
        const imageBuffer = Buffer.from(base64Data, 'base64');
        const jimpImage = await Jimp.read(imageBuffer);
        
        const imageData = {
          data: new Uint8ClampedArray(jimpImage.bitmap.data),
          width: jimpImage.bitmap.width,
          height: jimpImage.bitmap.height
        };
        
        const decodedQR = jsQR(imageData.data, imageData.width, imageData.height);
        if (decodedQR && decodedQR.data) {
          localQRData = decodedQR.data;
          console.log('[Payment Route] Local QR code decoded from slip:', localQRData);
        } else {
          console.log('[Payment Route] No QR code detected in the slip image locally.');
        }
      } catch (qrErr) {
        console.error('[Payment Route] Error decoding QR code locally:', qrErr.message);
      }

      // Check if this QR payload has already been used in our database
      if (localQRData) {
        const existingQR = await prisma.verifiedSlip.findFirst({
          where: {
            OR: [
              { transRef: localQRData },
              { qrPayload: localQRData }
            ]
          }
        });
        if (existingQR) {
          console.log('[Payment Route] Error: QR code payload already verified in database');
          return NextResponse.json({ message: 'This slip has already been used for another booking.' }, { status: 400 });
        }
      }

      const apiKey = process.env.SLIP_API_KEY;
      console.log('[Payment Route] SLIP_API_KEY present:', !!apiKey);

      // In production mode (when SLIP API Key is present), strictly require a readable QR code
      if (apiKey && !localQRData) {
        console.log('[Payment Route] Error: No QR code found in the uploaded image (Production Mode)');
        await saveFailedSlip(imageHash, null, null, bId);
        return NextResponse.json({ message: 'No valid QR code detected in the uploaded image. Please ensure you upload a clear bank transfer slip.' }, { status: 400 });
      }

      if (apiKey) {
        try {
          console.log('[Payment Route] Calling Slip2Go API...');

          const base64Data = slip_image.replace(/^data:image\/\w+;base64,/, '');
          const imageBuffer = Buffer.from(base64Data, 'base64');
          const formData = new FormData();
          formData.append('file', new Blob([imageBuffer], { type: 'image/jpeg' }), 'slip.jpg');

          const response = await fetch('https://connect.slip2go.com/api/verify-slip/qr-image/info', {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${apiKey}` },
            body: formData
          });

          console.log('[Payment Route] Slip2Go API response status:', response.status);
          const result = await response.json();
          console.log('[Payment Route] Slip2Go API response result:', JSON.stringify(result));

          if (result.code !== '200000' || !result.data) {
            const errMsg = result.message || 'Slip verification failed';
            console.log('[Payment Route] Error: Slip2Go returned non-success code:', result.code, errMsg);
            await saveFailedSlip(imageHash, null, localQRData, bId);
            return NextResponse.json({ message: errMsg }, { status: 400 });
          }

          const transRef = result.data.transRef;
          if (transRef) {
            const existingTransRef = await prisma.verifiedSlip.findUnique({
              where: { transRef }
            });
            if (existingTransRef) {
              console.log('[Payment Route] Error: transRef already verified in database');
              await saveFailedSlip(imageHash, transRef, localQRData, bId);
              return NextResponse.json({ message: 'This slip has already been used for another booking.' }, { status: 400 });
            }
          }

          // Enforce Receiver Check
          if (result.data.receiver) {
            const cleanTarget = (process.env.PROMPAY_RECEIVER_ID || '0917291840').replace(/\D/g, '');
            const proxyAccount = result.data.receiver.account && result.data.receiver.account.proxy
              ? (result.data.receiver.account.proxy.account || '').replace(/\D/g, '')
              : '';
            const bankAccount = result.data.receiver.account && result.data.receiver.account.bank
              ? (result.data.receiver.account.bank.account || '').replace(/\D/g, '')
              : '';

            const isMatch =
              (proxyAccount && (proxyAccount.endsWith(cleanTarget) || cleanTarget.endsWith(proxyAccount))) ||
              (bankAccount && (bankAccount.endsWith(cleanTarget) || cleanTarget.endsWith(bankAccount)));

            if (!isMatch) {
              console.log('[Payment Route] Error: Receiver mismatch. proxy:', proxyAccount, 'bank:', bankAccount, 'expected:', cleanTarget);
              await saveFailedSlip(imageHash, transRef, localQRData, bId);
              return NextResponse.json({ message: 'Receiver account in the slip does not match our PromptPay account.' }, { status: 400 });
            }
          }

          // Enforce Date/Time check (transaction must be recent, within last 30 minutes)
          if (result.data.dateTime) {
            const transTime = new Date(result.data.dateTime);
            const now = new Date();
            const timeDiffMinutes = (now - transTime) / 60000;

            if (Math.abs(timeDiffMinutes) > 30) {
              console.log('[Payment Route] Error: Transaction time mismatch. Slip time:', transTime, 'Current server time:', now, 'Difference (min):', timeDiffMinutes);
              await saveFailedSlip(imageHash, transRef, localQRData, bId);
              return NextResponse.json({ message: 'Transaction time on the slip is not valid for this recent booking. Please upload a recent slip.' }, { status: 400 });
            }
          }

          // Verify the amount matches
          const slipAmount = result.data.amount;
          console.log('[Payment Route] Comparing amount: slip shows', slipAmount, 'booking needs', finalPrice);
          if (Math.abs(slipAmount - finalPrice) > 0.01) {
            console.log('[Payment Route] Error: Amount mismatch');
            await saveFailedSlip(imageHash, transRef, localQRData, bId);
            return NextResponse.json({
              message: `Incorrect payment amount. Slip shows ฿${slipAmount}, but booking requires ฿${finalPrice}.`
            }, { status: 400 });
          }

          // Save to verified_slips
          if (transRef) {
            const qrPayload = result.data.decode || localQRData;
            await prisma.verifiedSlip.create({
              data: {
                imageHash,
                transRef,
                qrPayload,
                bookingId: bId
              }
            });
            console.log('[Payment Route] Slip cached successfully in database.');
          }

          // Complete payment
          const refString = `PromptPay QR (Ref: ${transRef || 'N/A'})`;
          console.log('[Payment Route] Completing payment with ref:', refString);
          await prisma.booking.update({
            where: { id: bId },
            data: {
              status: 'paid',
              paymentStatus: 'completed',
              paymentMethod: refString
            }
          });
        } catch (fetchErr) {
          console.error('[Payment Route] Slip2Go API verification error catch:', fetchErr.message);
          await saveFailedSlip(imageHash, null, localQRData, bId);
          return NextResponse.json({ message: 'Error verifying slip with Slip2Go API: ' + fetchErr.message }, { status: 500 });
        }
      } else {
        // Simulation Fallback Mode
        console.log('[Simulation Mode] Verifying uploaded slip (Fallback)...');
        await new Promise(resolve => setTimeout(resolve, 1500)); // Simulate delay

        const mockTransRef = `SIM-${Date.now()}`;
        const qrPayload = localQRData || `SIM-PAYLOAD-${Date.now()}`;
        
        await prisma.verifiedSlip.create({
          data: {
            imageHash,
            transRef: mockTransRef,
            qrPayload,
            bookingId: bId
          }
        });
        
        await prisma.booking.update({
          where: { id: bId },
          data: {
            status: 'paid',
            paymentStatus: 'completed',
            paymentMethod: 'PromptPay QR (Simulated)'
          }
        });
        console.log('[Simulation Mode] Slip verified and approved.');
      }
    } else {
      // Fallback for Credit Card
      await prisma.booking.update({
        where: { id: bId },
        data: {
          status: 'paid',
          paymentStatus: 'completed',
          paymentMethod: payment_method
        }
      });
    }

    const updatedBooking = await prisma.booking.findUnique({
      where: { id: bId },
      include: { court: true }
    });

    // Broadcast update
    broadcastEvent({ type: 'booking-updated' });

    const formattedBooking = {
      id: updatedBooking.id,
      user_id: updatedBooking.userId,
      court_id: updatedBooking.courtId,
      date: updatedBooking.date,
      start_time: updatedBooking.startTime,
      end_time: updatedBooking.endTime,
      price: updatedBooking.price,
      status: updatedBooking.status,
      pin_code: updatedBooking.pinCode,
      payment_method: updatedBooking.paymentMethod,
      payment_status: updatedBooking.paymentStatus,
      created_at: updatedBooking.createdAt,
      court_name: updatedBooking.court.name
    };

    return NextResponse.json({ message: 'Payment successful', booking: formattedBooking });
  } catch (err) {
    return NextResponse.json({ message: 'Server error during payment', error: err.message }, { status: 500 });
  }
}
