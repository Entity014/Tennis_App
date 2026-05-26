import prisma from '@/lib/prisma';

export async function validateAndApplyPromo(code, basePrice) {
  if (!code) {
    return { isValid: false, price: basePrice, message: 'No promo code provided' };
  }

  const cleanCode = code.trim().toUpperCase();
  let promo = await prisma.promoCode.findUnique({
    where: { code: cleanCode }
  });

  // Self-healing: if the code is 'ACE10' and it doesn't exist in the database yet, create it.
  if (!promo && cleanCode === 'ACE10') {
    try {
      promo = await prisma.promoCode.create({
        data: {
          code: 'ACE10',
          discountAmount: 10,
          discountType: 'percent',
          isActive: true
        }
      });
      console.log('[Promo Service] Automatically created missing default promo code: ACE10');
    } catch (err) {
      // In case of concurrent request creation
      promo = await prisma.promoCode.findUnique({
        where: { code: 'ACE10' }
      });
    }
  }

  if (!promo) {
    return { isValid: false, price: basePrice, message: 'Promo code not found' };
  }

  if (!promo.isActive) {
    return { isValid: false, price: basePrice, message: 'Promo code is inactive' };
  }

  const now = new Date();
  if (promo.validFrom && now < new Date(promo.validFrom)) {
    return { isValid: false, price: basePrice, message: 'Promo code is not active yet' };
  }

  if (promo.validUntil && now > new Date(promo.validUntil)) {
    return { isValid: false, price: basePrice, message: 'Promo code has expired' };
  }

  if (promo.maxUses !== null && promo.currentUses >= promo.maxUses) {
    return { isValid: false, price: basePrice, message: 'Promo code use limit reached' };
  }

  let finalPrice = basePrice;
  if (promo.discountType === 'percent') {
    finalPrice = basePrice * (1 - promo.discountAmount / 100);
  } else if (promo.discountType === 'fixed' || promo.discountType === 'amount') {
    finalPrice = Math.max(0, basePrice - promo.discountAmount);
  }

  // Round price to 2 decimal places
  finalPrice = Math.round(finalPrice * 100) / 100;

  return {
    isValid: true,
    price: finalPrice,
    promoId: promo.id,
    promoCode: promo.code,
    message: 'Promo code applied successfully'
  };
}
