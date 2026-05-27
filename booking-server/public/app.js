// Intercept fetch calls to disable caching for API GET requests
const originalFetch = window.fetch;
window.fetch = function (url, options = {}) {
  let finalUrl = url;
  if (typeof url === 'string' && url.startsWith('/api/')) {
    const method = (options.method || 'GET').toUpperCase();
    if (method === 'GET') {
      const separator = url.includes('?') ? '&' : '?';
      finalUrl = `${url}${separator}_t=${Date.now()}`;
      
      if (!options.headers) {
        options.headers = {};
      }
      if (options.headers instanceof Headers) {
        options.headers.set('Cache-Control', 'no-cache');
        options.headers.set('Pragma', 'no-cache');
      } else if (Array.isArray(options.headers)) {
        options.headers.push(['Cache-Control', 'no-cache']);
        options.headers.push(['Pragma', 'no-cache']);
      } else {
        options.headers['Cache-Control'] = 'no-cache';
        options.headers['Pragma'] = 'no-cache';
      }
    }
  }
  return originalFetch(finalUrl, options);
};

function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
  return null;
}

let cachedPublicKey = null;

async function getPublicKey() {
  if (cachedPublicKey) return cachedPublicKey;
  const res = await fetch('/api/auth/public-key');
  if (!res.ok) throw new Error('Failed to fetch public key');
  const jwk = await res.json();
  cachedPublicKey = await window.crypto.subtle.importKey(
    'jwk',
    jwk,
    {
      name: 'RSA-OAEP',
      hash: 'SHA-256'
    },
    true,
    ['encrypt']
  );
  return cachedPublicKey;
}

async function encryptPayload(payloadObj) {
  try {
    const publicKey = await getPublicKey();
    const plainText = JSON.stringify(payloadObj);
    const encoder = new TextEncoder();
    const dataBuffer = encoder.encode(plainText);
    const encryptedBuffer = await window.crypto.subtle.encrypt(
      {
        name: 'RSA-OAEP'
      },
      publicKey,
      dataBuffer
    );
    const encryptedBase64 = btoa(String.fromCharCode(...new Uint8Array(encryptedBuffer)));
    return { encryptedData: encryptedBase64 };
  } catch (err) {
    console.error('Encryption failed:', err);
    return payloadObj;
  }
}

// --- Internationalization (i18n) ---
const TRANSLATIONS = {
  en: {
    // Navbar
    'nav-home': 'Home',
    'nav-book': 'Book Court',
    'nav-my-bookings': 'My Bookings',
    'nav-admin': 'Admin Panel',
    'nav-signin': 'Sign In',
    'nav-logout': 'Logout',
    
    // Hero
    'hero-title': 'Serve, Volley & <span class="text-neon">Book Instantly</span>',
    'hero-subtitle': 'Experience championship-grade tennis courts equipped with automated smart-lock access systems. Select your timeslot and get on the court in seconds.',
    'btn-book-now': 'Book A Court Now',
    'btn-explore': 'Explore Courts',
    
    // Features Section
    'section-title': 'Our Premium Courts',
    'section-subtitle': 'Professional surfaces designed to match your play style.',
    'court-desc-A': 'Premium hard court with professional lighting and spectator seating.',
    'court-desc-B': 'Traditional clay court offering excellent bounce and slide play.',
    'court-desc-C': 'Fast grass court simulating professional tournament play style.',
    'court-desc-D': 'Modern synthetic turf court suitable for all weather conditions.',
    
    // Search View
    'search-title': 'Find Available Timeslots',
    'search-date-label': 'Date',
    'btn-search': 'Search Available Slots',
    'btn-back-search': 'Back to Search',
    
    // Timeslot View
    'timeslot-title': 'Select Court & Timeslots',
    'legend-select-slots': 'Select Time Slots',
    'legend-hourly-info': 'Bookings must be hourly. Select consecutive blocks.',
    'legend-available': 'Available',
    'legend-booked': 'Booked',
    'legend-selected': 'Selected',
    'legend-expired': 'Expired',
    'lbl-duration': 'Duration',
    'btn-clear': 'Clear Selection',
    'btn-book-selected': 'Confirm & Book',
    
    // Confirmation / Review View
    'review-title': 'Review Your Booking',
    'review-subtitle': 'Please double-check details before proceeding to payment.',
    'lbl-court': 'Selected Court',
    'lbl-date': 'Booking Date',
    'lbl-time': 'Selected Time',
    'lbl-rate': 'Rate per Hour',
    'lbl-total': 'Total Amount',
    'btn-cancel-back': 'Cancel & Back',
    'btn-payment': 'Proceed to Payment',
    'btn-confirm-free': 'Confirm Booking',
    'promo-placeholder': 'Promo / Discount Code',
    'btn-apply-promo': 'Apply',
    
    // Payment View
    'checkout-title': 'Secure Checkout',
    'checkout-subtitle': 'Scan this PromptPay QR Code with any banking app to pay',
    'checkout-subtitle-free': 'Your booking is completely free! Click "Confirm Booking" below to complete.',
    'payment-method': 'Payment Method',
    'method-promptpay': 'PromptPay QR',
    'method-card': 'Credit Card',
    'upload-slip-title': 'Upload Payment Slip',
    'upload-slip-desc': 'Drag & drop your payment slip here or click to browse',
    'amount-due': 'Amount Due',
    'btn-pay-now': 'Pay Now',
    'qr-timer-expires': 'QR Code expires in',
    
    // Confirmation / E-Ticket View
    'confirm-title': 'Booking Confirmed!',
    'confirm-subtitle': 'Show this E-ticket at the court entrance.',
    'ticket-title': 'Booking E-Ticket',
    'ticket-pin': 'COURT ACCESS PIN CODE',
    'ticket-pin-info': 'Enter this 6-digit PIN on the court kiosk screen to unlock system and lights.',
    'btn-go-dashboard': 'Go to My Bookings',
    'btn-go-home': 'Return Home',
    
    // Auth View
    'auth-tab-login': 'Sign In',
    'auth-tab-register': 'Register',
    'auth-login-title': 'Welcome Back',
    'auth-register-title': 'Create Account',
    'auth-forgot-title': 'Reset Password',
    'auth-new-pass-title': 'New Password',
    'auth-username-lbl': 'Username',
    'auth-email-lbl': 'Email Address',
    'auth-password-lbl': 'Password',
    'auth-new-password-lbl': 'New Password',
    'auth-reset-pin-lbl': 'Reset PIN',
    'auth-username-placeholder': 'Enter username',
    'auth-choose-username-placeholder': 'Choose a username',
    'auth-email-placeholder': 'Enter email',
    'auth-forgot-email-placeholder': 'Enter your email',
    'auth-password-placeholder': 'Enter password',
    'auth-choose-password-placeholder': 'Choose a strong password',
    'auth-reset-pin-placeholder': 'Enter 6-digit PIN',
    'auth-forgot-link': 'Forgot Password?',
    'auth-login-btn': 'Sign In',
    'auth-register-btn': 'Register',
    'auth-send-pin-btn': 'Send PIN',
    'auth-update-pass-btn': 'Update Password',
    'auth-or-divider': 'OR',
    'auth-no-account': "Don't have an account?",
    'auth-register-here': 'Register here',
    'auth-has-account': 'Already have an account?',
    'auth-signin-here': 'Sign in here',
    'auth-forgot-desc': 'Enter your email to receive a reset PIN.',
    'auth-forgot-remembered': 'Remembered your password?',
    'auth-forgot-signin': 'Sign in',
    'auth-new-pass-desc': 'Enter the PIN and your new password.',
    'auth-req-username-len': '3 to 20 characters long',
    'auth-req-username-chars': 'Letters, numbers, and underscores only',
    'auth-req-pass-len': 'At least 8 characters long',
    'auth-req-pass-upper': 'At least one uppercase letter (A-Z)',
    'auth-req-pass-lower': 'At least one lowercase letter (a-z)',
    'auth-back-login': 'Back to login',
    // Admin View
    'admin-title': 'Admin Control Panel',
    'admin-subtitle': 'Manage tennis courts, track bookings, view user statistics, and check revenue.',
    'admin-stat-bookings-lbl': 'Total Bookings',
    'admin-stat-revenue-lbl': 'Total Revenue',
    'admin-stat-users-lbl': 'Registered Users',
    'admin-stat-courts-lbl': 'Active Courts',
    'admin-tab-bookings': 'Bookings',
    'admin-tab-courts': 'Courts',
    'admin-tab-users': 'Users',
    'admin-tab-promos': 'Promo Codes',
    'admin-hdr-bookings': 'All Bookings',
    'admin-hdr-courts': 'Active Courts',
    'admin-hdr-users': 'Registered Users',
    'admin-hdr-promos': 'Active Promo Codes',
    'admin-add-court': 'Add New Court',
    'admin-court-name': 'Court Name (English)',
    'admin-court-name-th': 'Court Name (Thai)',
    'admin-court-price': 'Price Per Hour (฿)',
    'admin-court-desc': 'Description (English)',
    'admin-court-desc-th': 'Description (Thai)',
    'admin-court-theme': 'Select Theme Image',
    'admin-save-court': 'Save Court',
    'admin-add-promo': 'Add Promo Code',
    'admin-promo-code': 'Code',
    'admin-promo-amount': 'Amount',
    'admin-promo-type': 'Type',
    'admin-promo-valid': 'Valid Until (Optional)',
    'admin-promo-max': 'Max Uses (Optional)',
    'admin-promo-status': 'Active Status',
    'admin-save-promo': 'Save Promo Code',
    'admin-tbl-id': 'ID',
    'admin-tbl-user': 'User',
    'admin-tbl-court': 'Court',
    'admin-tbl-date': 'Date',
    'admin-tbl-time': 'Time',
    'admin-tbl-price': 'Price',
    'admin-tbl-pin': 'PIN',
    'admin-tbl-status': 'Status',
    'admin-tbl-action': 'Action',
    'admin-tbl-name': 'Name',
    'admin-tbl-price-hr': 'Price/Hour',
    'admin-tbl-desc': 'Description',
    'admin-tbl-username': 'Username',
    'admin-tbl-email': 'Email',
    'admin-tbl-role': 'Role',
    'admin-tbl-reg-at': 'Registered At',
    'admin-tbl-change-role': 'Change Role',
    'admin-tbl-code': 'Code',
    'admin-tbl-discount': 'Discount',
    'admin-tbl-uses': 'Uses',
    'admin-no-bookings': 'No bookings found in database.',
    'admin-no-courts': 'No courts configured.',
    'admin-no-promos': 'No promo codes found.',
    'admin-status-active': 'Active',
    'admin-status-inactive': 'Inactive',
    'admin-status-expired': 'Expired',
    'admin-status-limit': 'Limit Reached',
    'admin-action-cancelling': 'Cancelling...',
    'admin-action-cancel': 'Cancel',
    'admin-action-edit': 'Edit',
    'admin-action-delete': 'Delete',
    'admin-save-court-update': 'Update Court',
    'admin-save-promo-update': 'Update Promo Code',
    'footer-privacy': 'Privacy Policy',
    'footer-terms': 'Terms of Service',
    'footer-support': 'Help Support',
    'dashboard-title': 'Player Dashboard',
    'dashboard-subtitle': 'Manage your bookings, access codes, and profile.',
    'dashboard-new-booking': 'New Booking',
    'dashboard-stat-upcoming': 'Upcoming Bookings',
    'dashboard-stat-total': 'Total Bookings',
    'dashboard-section-title': 'Your Bookings',
    'dashboard-empty-txt': "You don't have any bookings yet.",
    'dashboard-empty-btn': 'Book Your First Court'
  },
  th: {
    // Navbar
    'nav-home': 'หน้าแรก',
    'nav-book': 'จองสนาม',
    'nav-my-bookings': 'การจองของฉัน',
    'nav-admin': 'แผงควบคุม',
    'nav-signin': 'เข้าสู่ระบบ',
    'nav-logout': 'ออกจากระบบ',
    
    // Hero
    'hero-title': 'เสิร์ฟ หวด & <span class="text-neon">จองสนามได้ทันที</span>',
    'hero-subtitle': 'สัมผัสสนามเทนนิสระดับแชมป์เปี้ยนชิพ พร้อมระบบล็อกประตูอัจฉริยะแบบอัตโนมัติ เลือกเวลาของคุณและลงสนามได้ในไม่กี่วินาที',
    'btn-book-now': 'จองสนามเลย',
    'btn-explore': 'สำรวจสนาม',
    
    // Features Section
    'section-title': 'สนามระดับพรีเมียมของเรา',
    'section-subtitle': 'พื้นผิวสนามระดับมืออาชีพที่ออกแบบมาให้เข้ากับสไตล์การเล่นของคุณ',
    'court-desc-A': 'สนามฮาร์ดคอร์ทพรีเมียมพร้อมไฟส่องสว่างระดับอาชีพและที่นั่งผู้ชม',
    'court-desc-B': 'สนามดินเหนียวดั้งเดิมที่ให้แรงกระดอนและการเลื่อนไถลที่ยอดเยี่ยม',
    'court-desc-C': 'สนามหญ้าความเร็วสูงที่จำลองสไตล์การเล่นแบบทัวร์นาเมนต์ระดับอาชีพ',
    'court-desc-D': 'สนามหญ้าเทียมทันสมัยที่เหมาะกับทุกสภาพอากาศ',
    
    // Search View
    'search-title': 'ค้นหาช่วงเวลาที่ว่าง',
    'search-date-label': 'วันที่',
    'btn-search': 'ค้นหาเวลาว่าง',
    'btn-back-search': 'ย้อนกลับเพื่อค้นหา',
    
    // Timeslot View
    'timeslot-title': 'เลือกสนาม & ช่วงเวลา',
    'legend-select-slots': 'เลือกช่วงเวลา',
    'legend-hourly-info': 'การจองต้องเป็นแบบรายชั่วโมง กรุณาเลือกช่วงเวลาที่ต่อเนื่องกัน',
    'legend-available': 'ว่าง',
    'legend-booked': 'จองแล้ว',
    'legend-selected': 'เลือกอยู่',
    'legend-expired': 'หมดเวลา',
    'lbl-duration': 'ระยะเวลา',
    'btn-clear': 'ล้างการเลือก',
    'btn-book-selected': 'ยืนยัน & จองสนาม',
    
    // Confirmation / Review View
    'review-title': 'ตรวจสอบการจองของคุณ',
    'review-subtitle': 'กรุณาตรวจสอบรายละเอียดความถูกต้องก่อนดำเนินการชำระเงิน',
    'lbl-court': 'สนามที่เลือก',
    'lbl-date': 'วันที่จอง',
    'lbl-time': 'เวลาที่เลือก',
    'lbl-rate': 'อัตราต่อชั่วโมง',
    'lbl-total': 'ยอดรวมทั้งหมด',
    'btn-cancel-back': 'ยกเลิก & กลับ',
    'btn-payment': 'ดำเนินการชำระเงิน',
    'btn-confirm-free': 'ยืนยันการจอง',
    'promo-placeholder': 'รหัสโปรโมชั่น / ส่วนลด',
    'btn-apply-promo': 'ใช้ส่วนลด',
    
    // Payment View
    'checkout-title': 'ชำระเงินอย่างปลอดภัย',
    'checkout-subtitle': 'สแกน PromptPay QR Code นี้ด้วยแอปธนาคารใดก็ได้เพื่อชำระเงิน',
    'checkout-subtitle-free': 'การจองของคุณฟรี! คลิก "ยืนยันการจอง" ด้านล่างเพื่อเสร็จสิ้นขั้นตอน',
    'payment-method': 'ช่องทางชำระเงิน',
    'method-promptpay': 'PromptPay QR',
    'method-card': 'บัตรเครดิต',
    'upload-slip-title': 'อัปโหลดสลิปชำระเงิน (รูปภาพ)',
    'upload-slip-desc': 'แตะเพื่อเลือกไฟล์ หรือลากและวางสลิปที่นี่',
    'amount-due': 'ยอดชำระ',
    'btn-pay-now': 'ชำระเงินตอนนี้',
    'qr-timer-expires': 'รหัส QR จะหมดอายุใน',
    
    // Confirmation / E-Ticket View
    'confirm-title': 'ยืนยันการจองเรียบร้อย!',
    'confirm-subtitle': 'กรุณาแสดง E-ticket นี้ที่ประตูทางเข้าสนาม',
    'ticket-title': 'E-Ticket การจองสนาม',
    'ticket-pin': 'รหัสผ่านประตูสนาม',
    'ticket-pin-info': 'กรุณาพิมพ์รหัส PIN 6 หลักนี้บนหน้าจอตู้คีออสหน้าสนามเพื่อปลดล็อกระบบและไฟสนาม',
    'btn-go-dashboard': 'ไปที่การจองของฉัน',
    'btn-go-home': 'กลับหน้าแรก',
    
    // Auth View
    'auth-tab-login': 'เข้าสู่ระบบ',
    'auth-tab-register': 'สมัครสมาชิก',
    'auth-login-title': 'ยินดีต้อนรับกลับมา',
    'auth-register-title': 'สร้างบัญชีผู้ใช้',
    'auth-forgot-title': 'รีเซ็ตรหัสผ่าน',
    'auth-new-pass-title': 'ตั้งรหัสผ่านใหม่',
    'auth-username-lbl': 'ชื่อผู้ใช้',
    'auth-email-lbl': 'ที่อยู่อีเมล',
    'auth-password-lbl': 'รหัสผ่าน',
    'auth-new-password-lbl': 'รหัสผ่านใหม่',
    'auth-reset-pin-lbl': 'รหัส PIN สำหรับรีเซ็ต',
    'auth-username-placeholder': 'กรอกชื่อผู้ใช้',
    'auth-choose-username-placeholder': 'เลือกชื่อผู้ใช้',
    'auth-email-placeholder': 'กรอกอีเมลของคุณ',
    'auth-forgot-email-placeholder': 'กรอกอีเมลของคุณ',
    'auth-password-placeholder': 'กรอกรหัสผ่าน',
    'auth-choose-password-placeholder': 'เลือกตั้งรหัสผ่านที่ปลอดภัย',
    'auth-reset-pin-placeholder': 'กรอกรหัส PIN 6 หลัก',
    'auth-forgot-link': 'ลืมรหัสผ่าน?',
    'auth-login-btn': 'เข้าสู่ระบบ',
    'auth-register-btn': 'สมัครสมาชิก',
    'auth-send-pin-btn': 'ส่งรหัส PIN',
    'auth-update-pass-btn': 'อัปเดตรหัสผ่าน',
    'auth-or-divider': 'หรือ',
    'auth-no-account': 'ยังไม่มีบัญชีผู้ใช้?',
    'auth-register-here': 'สมัครสมาชิกที่นี่',
    'auth-has-account': 'มีบัญชีผู้ใช้อยู่แล้ว?',
    'auth-signin-here': 'เข้าสู่ระบบที่นี่',
    'auth-forgot-desc': 'กรอกอีเมลของคุณเพื่อรับรหัส PIN สำหรับรีเซ็ต',
    'auth-forgot-remembered': 'จำรหัสผ่านได้แล้ว?',
    'auth-forgot-signin': 'เข้าสู่ระบบ',
    'auth-new-pass-desc': 'กรอกรหัส PIN และรหัสผ่านใหม่ของคุณ',
    'auth-req-username-len': 'ความยาว 3 ถึง 20 ตัวอักษร',
    'auth-req-username-chars': 'ตัวอักษร, ตัวเลข และเครื่องหมายขีดล่าง (_) เท่านั้น',
    'auth-req-pass-len': 'ความยาวอย่างน้อย 8 ตัวอักษร',
    'auth-req-pass-upper': 'มีตัวอักษรพิมพ์ใหญ่ (A-Z) อย่างน้อย 1 ตัว',
    'auth-req-pass-lower': 'มีตัวอักษรพิมพ์เล็ก (a-z) อย่างน้อย 1 ตัว',
    'auth-back-login': 'กลับไปหน้าเข้าสู่ระบบ',
    // Admin View
    'admin-title': 'แผงควบคุมระบบ',
    'admin-subtitle': 'จัดการสนามเทนนิส ตรวจสอบประวัติการจอง ดูสถิติผู้ใช้งาน และตรวจสอบรายได้',
    'admin-stat-bookings-lbl': 'รายการจองทั้งหมด',
    'admin-stat-revenue-lbl': 'รายได้ทั้งหมด',
    'admin-stat-users-lbl': 'ผู้ใช้งานที่ลงทะเบียน',
    'admin-stat-courts-lbl': 'สนามที่เปิดให้บริการ',
    'admin-tab-bookings': 'รายการจอง',
    'admin-tab-courts': 'สนาม',
    'admin-tab-users': 'ผู้ใช้งาน',
    'admin-tab-promos': 'รหัสโปรโมชั่น',
    'admin-hdr-bookings': 'รายการจองทั้งหมด',
    'admin-hdr-courts': 'สนามที่เปิดให้บริการ',
    'admin-hdr-users': 'ผู้ใช้งานที่ลงทะเบียน',
    'admin-hdr-promos': 'รหัสโปรโมชั่นที่ใช้งานอยู่',
    'admin-add-court': 'เพิ่มสนามใหม่',
    'admin-court-name': 'ชื่อสนาม (ภาษาอังกฤษ)',
    'admin-court-name-th': 'ชื่อสนาม (ภาษาไทย)',
    'admin-court-price': 'ราคาต่อชั่วโมง (฿)',
    'admin-court-desc': 'คำอธิบายสนาม (ภาษาอังกฤษ)',
    'admin-court-desc-th': 'คำอธิบายสนาม (ภาษาไทย)',
    'admin-court-theme': 'เลือกรูปภาพธีม',
    'admin-save-court': 'บันทึกข้อมูลสนาม',
    'admin-add-promo': 'เพิ่มรหัสโปรโมชั่น',
    'admin-promo-code': 'รหัสโปรโมชั่น',
    'admin-promo-amount': 'จำนวนลด',
    'admin-promo-type': 'ประเภทส่วนลด',
    'admin-promo-valid': 'ใช้งานได้ถึง (ไม่บังคับ)',
    'admin-promo-max': 'สิทธิ์ใช้งานสูงสุด (ไม่บังคับ)',
    'admin-promo-status': 'สถานะเปิดใช้งาน',
    'admin-save-promo': 'บันทึกรหัสโปรโมชั่น',
    'admin-tbl-id': 'ไอดี',
    'admin-tbl-user': 'ผู้ใช้งาน',
    'admin-tbl-court': 'สนาม',
    'admin-tbl-date': 'วันที่',
    'admin-tbl-time': 'เวลา',
    'admin-tbl-price': 'ราคา',
    'admin-tbl-pin': 'รหัส PIN',
    'admin-tbl-status': 'สถานะ',
    'admin-tbl-action': 'การดำเนินการ',
    'admin-tbl-name': 'ชื่อสนาม',
    'admin-tbl-price-hr': 'ราคา/ชั่วโมง',
    'admin-tbl-desc': 'คำอธิบาย',
    'admin-tbl-username': 'ชื่อผู้ใช้',
    'admin-tbl-email': 'อีเมล',
    'admin-tbl-role': 'บทบาท',
    'admin-tbl-reg-at': 'วันที่ลงทะเบียน',
    'admin-tbl-change-role': 'เปลี่ยนบทบาท',
    'admin-tbl-code': 'รหัส',
    'admin-tbl-discount': 'ส่วนลด',
    'admin-tbl-uses': 'จำนวนครั้งที่ใช้',
    'admin-no-bookings': 'ไม่พบประวัติการจองในระบบ',
    'admin-no-courts': 'ยังไม่ได้กำหนดค่าสนาม',
    'admin-no-promos': 'ไม่พบรหัสโปรโมชั่นในระบบ',
    'admin-status-active': 'เปิดใช้งาน',
    'admin-status-inactive': 'ปิดใช้งาน',
    'admin-status-expired': 'หมดอายุ',
    'admin-status-limit': 'สิทธิ์เต็มแล้ว',
    'admin-action-cancelling': 'กำลังยกเลิก...',
    'admin-action-cancel': 'ยกเลิก',
    'admin-action-edit': 'แก้ไข',
    'admin-action-delete': 'ลบ',

    // Notifications & Dialog Translations
    'Successfully logged in with Google!': 'เข้าสู่ระบบด้วย Google สำเร็จ!',
    'Google authentication failed': 'การยืนยันตัวตนด้วย Google ล้มเหลว',
    'Unable to connect to login server': 'ไม่สามารถเชื่อมต่อกับเซิร์ฟเวอร์เข้าสู่ระบบได้',
    'Facebook SDK not loaded yet': 'ยังไม่ได้โหลด Facebook SDK',
    'Successfully logged in with Facebook!': 'เข้าสู่ระบบด้วย Facebook สำเร็จ!',
    'Facebook authentication failed': 'การยืนยันตัวตนด้วย Facebook ล้มเหลว',
    'User cancelled login or did not authorize email.': 'ผู้ใช้ยกเลิกการเข้าสู่ระบบหรือไม่ได้รับอนุญาตอีเมล',
    'Please sign in to view bookings.': 'กรุณาเข้าสู่ระบบเพื่อดูรายการจอง',
    'Logged out successfully.': 'ออกจากระบบเรียบร้อยแล้ว',
    'Welcome back!': 'ยินดีต้อนรับกลับมา!',
    'Registration successful! Please login.': 'สมัครสมาชิกสำเร็จ! กรุณาเข้าสู่ระบบ',
    'Connection error during registration': 'เกิดข้อผิดพลาดในการเชื่อมต่อระหว่างสมัครสมาชิก',
    'A password reset PIN has been sent to your email. Please check your inbox.': 'รหัส PIN สำหรับรีเซ็ตรหัสผ่านถูกส่งไปยังอีเมลของคุณแล้ว กรุณาตรวจสอบกล่องจดหมายของคุณ',
    'Connection error': 'เกิดข้อผิดพลาดในการเชื่อมต่อ',
    'Password updated successfully. You can now login.': 'อัปเดตรหัสผ่านสำเร็จ คุณสามารถเข้าสู่ระบบได้แล้ว',
    'Failed to retrieve user details': 'ไม่สามารถดึงข้อมูลผู้ใช้ได้',
    'Social login failed': 'การเข้าสู่ระบบด้วยโซเชียลมีเดียล้มเหลว',
    'Connection error during social authentication': 'เกิดข้อผิดพลาดในการเชื่อมต่อระหว่างการตรวจสอบสิทธิ์โซเชียล',
    'Please select at least one timeslot.': 'กรุณาเลือกช่วงเวลาอย่างน้อย 1 ช่อง',
    'Sign in required to complete booking.': 'จำเป็นต้องเข้าสู่ระบบเพื่อทำรายการจองให้เสร็จสิ้น',
    'Booking confirmed! Cash payment recorded.': 'ยืนยันการจองเรียบร้อย! บันทึกการชำระเงินด้วยเงินสดแล้ว',
    'Unable to secure slot. It might have been booked by someone else.': 'ไม่สามารถจองช่วงเวลานี้ได้ อาจมีผู้อื่นจองไปแล้ว',
    'Error securing timeslot. Please try again.': 'เกิดข้อผิดพลาดในการจองช่วงเวลา กรุณาลองใหม่อีกครั้ง',
    'Error loading court availability.': 'เกิดข้อผิดพลาดในการโหลดข้อมูลสนามที่ว่าง',
    'This slot is unavailable.': 'ช่วงเวลานี้ไม่สามารถใช้งานได้',
    'Cannot select range: contains unavailable timeslots in between.': 'ไม่สามารถเลือกช่วงนี้ได้: มีช่วงเวลาที่ไม่ว่างคั่นกลางอยู่',
    'Cannot expand range: next slot is unavailable.': 'ไม่สามารถขยายช่วงได้: ช่วงเวลาถัดไปไม่ว่าง',
    'Cannot expand range: preceding slot is unavailable.': 'ไม่สามารถขยายช่วงได้: ช่วงเวลาก่อนหน้าไม่ว่าง',
    'No active booking to apply promo to.': 'ไม่มีรายการจองที่สามารถใช้รหัสโปรโมชั่นได้',
    'Promo code applied successfully!': 'ใช้รหัสโปรโมชั่นสำเร็จ!',
    'Invalid coupon or promo code.': 'คูปองหรือรหัสโปรโมชั่นไม่ถูกต้อง',
    'Error communicating with server': 'เกิดข้อผิดพลาดในการเชื่อมต่อกับเซิร์ฟเวอร์',
    'Please select an image file.': 'กรุณาเลือกไฟล์รูปภาพ',
    'Analyzing slip image...': 'กำลังวิเคราะห์รูปภาพสลิป...',
    'No valid QR code detected. Please ensure you upload a clear PromptPay transfer slip to save API quota.': 'ตรวจไม่พบรหัส QR ที่ถูกต้อง โปรดอัปโหลดสลิป PromptPay ที่ชัดเจนเพื่อประหยัดโควตา API',
    'Slip QR code detected successfully.': 'ตรวจพบรหัส QR ในสลิปสำเร็จ',
    'QR Code expired. Generating a new one...': 'รหัส QR หมดอายุแล้ว กำลังสร้างรหัสใหม่...',
    'Please upload your payment slip first.': 'กรุณาอัปโหลดสลิปการชำระเงินก่อน',
    'Verifying payment details...': 'กำลังตรวจสอบรายละเอียดการชำระเงิน...',
    'Confirming booking details...': 'กำลังยืนยันรายละเอียดการจอง...',
    'Payment verified successfully!': 'ตรวจสอบการชำระเงินเสร็จสมบูรณ์!',
    'Booking confirmed successfully!': 'ยืนยันการจองสนามสำเร็จ!',
    'Payment verification failed': 'การตรวจสอบการชำระเงินล้มเหลว',
    'Promo code deleted successfully': 'ลบรหัสโปรโมชั่นสำเร็จแล้ว',
    'Promo code updated successfully': 'อัปเดตรหัสโปรโมชั่นสำเร็จแล้ว',
    'Promo code added successfully': 'เพิ่มรหัสโปรโมชั่นสำเร็จแล้ว',
    'Court updated successfully': 'อัปเดตข้อมูลสนามสำเร็จแล้ว',
    'Court added successfully': 'เพิ่มสนามสำเร็จแล้ว',
    'Court deleted': 'ลบข้อมูลสนามแล้ว',
    'Booking deleted': 'ลบรายการจองแล้ว',
    'Are you sure you want to cancel this pending booking?': 'คุณแน่ใจหรือไม่ว่าต้องการยกเลิกการจองที่รอดำเนินการนี้?',
    'Are you sure you want to delete/cancel this booking?': 'คุณแน่ใจหรือไม่ว่าต้องการลบ/ยกเลิกการจองนี้?',
    'Are you sure you want to delete this court? This might affect existing bookings!': 'คุณแน่ใจหรือไม่ว่าต้องการลบคอร์ทนี้? การดำเนินการนี้อาจส่งผลกระทบต่อรายการจองที่มีอยู่!',
    'Are you sure you want to delete this promo code?': 'คุณแน่ใจหรือไม่ว่าต้องการลบรหัสโปรโมชั่นนี้?',
    'admin-save-court-update': 'อัปเดตข้อมูลสนาม',
    'admin-save-promo-update': 'อัปเดตรหัสโปรโมชั่น',
    'footer-privacy': 'นโยบายความเป็นส่วนตัว',
    'footer-terms': 'ข้อตกลงการให้บริการ',
    'footer-support': 'ช่วยเหลือและสนับสนุน',
    'dashboard-title': 'แผงควบคุมผู้เล่น',
    'dashboard-subtitle': 'จัดการการจอง รหัสเข้าสนาม และโปรไฟล์ของคุณ',
    'dashboard-new-booking': 'จองสนามใหม่',
    'dashboard-stat-upcoming': 'การจองที่กำลังมาถึง',
    'dashboard-stat-total': 'การจองทั้งหมด',
    'dashboard-section-title': 'การจองของคุณ',
    'dashboard-empty-txt': 'คุณยังไม่มีรายการจองใดๆ',
    'dashboard-empty-btn': 'จองสนามแรกของคุณ',
    'Cannot select a past date': 'ไม่สามารถเลือกวันที่ในอดีตได้',
    'Can only book up to 3 months in advance': 'สามารถจองล่วงหน้าได้ไม่เกิน 3 เดือน',
    'Access denied.': 'ปฏิเสธการเข้าถึง',
    'Error loading admin stats': 'เกิดข้อผิดพลาดในการโหลดข้อมูลสถิติผู้ดูแลระบบ',
    'Error loading bookings': 'เกิดข้อผิดพลาดในการโหลดข้อมูลการจอง',
    'Error loading courts list': 'เกิดข้อผิดพลาดในการโหลดรายการสนาม',
    'Error saving court details': 'เกิดข้อผิดพลาดในการบันทึกรายละเอียดสนาม',
    'Error deleting booking': 'เกิดข้อผิดพลาดในการลบการจอง',
    'Error deleting court': 'เกิดข้อผิดพลาดในการลบสนาม',
    'Error loading users': 'เกิดข้อผิดพลาดในการโหลดข้อมูลผู้ใช้',
    'Failed to update role': 'ล้มเหลวในการอัปเดตบทบาท',
    'Error updating role': 'เกิดข้อผิดพลาดในการอัปเดตบทบาท',
    'Error loading promo codes': 'เกิดข้อผิดพลาดในการโหลดรหัสโปรโมชั่น',
    'Discount amount must be a positive number': 'จำนวนส่วนลดต้องเป็นตัวเลขที่มากกว่า 0',
    'Percentage discount cannot exceed 100%': 'ส่วนลดเป็นเปอร์เซ็นต์ต้องไม่เกิน 100%',
    'Error saving promo code': 'เกิดข้อผิดพลาดในการบันทึกรหัสโปรโมชั่น',
    'Promo code deleted': 'ลบรหัสโปรโมชั่นสำเร็จแล้ว',
    'Error deleting promo code': 'เกิดข้อผิดพลาดในการลบรหัสโปรโมชั่น',
    'Username is required.': 'กรุณากรอกชื่อผู้ใช้',
    'Password is required.': 'กรุณากรอกรหัสผ่าน',
    'Incorrect username or password. Please try again.': 'ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง',
    'Email address is required.': 'กรุณากรอกอีเมล',
    'Please enter a valid email address.': 'กรุณากรอกอีเมลที่ถูกต้อง',
    'Email is required.': 'กรุณากรอกอีเมล',
    'Reset PIN is required.': 'กรุณากรอกรหัส PIN สำหรับรีเซ็ต',
    'New password is required.': 'กรุณากรอกรหัสผ่านใหม่',
    '3 to 20 characters long': 'ความยาว 3 ถึง 20 ตัวอักษร',
    'Letters, numbers, and underscores only': 'ตัวอักษร ตัวเลข และเครื่องหมายขีดล่าง (_) เท่านั้น',
    'At least 8 characters long': 'ความยาวอย่างน้อย 8 ตัวอักษร',
    'At least one uppercase letter (A-Z)': 'มีตัวอักษรพิมพ์ใหญ่ภาษาอังกฤษอย่างน้อย 1 ตัว (A-Z)',
    'At least one lowercase letter (a-z)': 'มีตัวอักษรพิมพ์เล็กภาษาอังกฤษอย่างน้อย 1 ตัว (a-z)',
    'At least one number (0-9)': 'มีตัวเลขอย่างน้อย 1 ตัว (0-9)',
    'Sending...': 'กำลังส่ง...',
    'A password reset PIN has been sent to your email. Please check your inbox.': 'ส่งรหัส PIN สำหรับรีเซ็ตรหัสผ่านไปยังอีเมลของคุณเรียบร้อยแล้ว กรุณาตรวจสอบกล่องจดหมาย',
    'Password updated successfully. You can now login.': 'อัปเดตรหัสผ่านสำเร็จแล้ว คุณสามารถเข้าสู่ระบบได้ทันที',
    'Username already exists': 'ชื่อผู้ใช้นี้ถูกใช้งานแล้ว',
    'Username already exists.': 'ชื่อผู้ใช้นี้ถูกใช้งานแล้ว',
    'Email already exists': 'อีเมลนี้ถูกใช้งานแล้ว',
    'Email already exists.': 'อีเมลนี้ถูกใช้งานแล้ว',
    'User not found.': 'ไม่พบผู้ใช้งานนี้',
    'Invalid or expired token.': 'รหัส PIN ไม่ถูกต้องหรือหมดอายุแล้ว',
    'Registration successful! Please login.': 'สมัครสมาชิกสำเร็จ! กรุณาเข้าสู่ระบบ',
    'Securing timeslot...': 'กำลังบล็อกช่วงเวลา...',
    'Cancelling...': 'กำลังยกเลิก...',
    'Booking...': 'กำลังจองสนาม...',
    'Verifying Payment...': 'กำลังตรวจสอบการชำระเงิน...',
    'Confirming Booking...': 'กำลังยืนยันการจอง...',
    'Applying...': 'กำลังใช้ส่วนลด...',
    'Promo code applied successfully': 'ใช้รหัสโปรโมชั่นสำเร็จ!',
    'Promo code applied successfully.': 'ใช้รหัสโปรโมชั่นสำเร็จ!',
    'Invalid coupon or promo code': 'คูปองหรือรหัสโปรโมชั่นไม่ถูกต้อง',
  }
};

let currentLang = localStorage.getItem('lang') || 'en';

function applyLanguage(lang) {
  currentLang = lang;
  localStorage.setItem('lang', lang);
  
  const label = document.getElementById('lang-label');
  if (label) {
    label.textContent = lang === 'en' ? 'EN' : 'TH';
  }

  document.querySelectorAll('[data-i18n]').forEach(el => {
    const key = el.getAttribute('data-i18n');
    if (TRANSLATIONS[lang] && TRANSLATIONS[lang][key]) {
      const hasIcon = el.querySelector('i');
      if (hasIcon) {
        const iconHTML = hasIcon.outerHTML;
        if (hasIcon.className.includes('ml-')) {
          el.innerHTML = `${TRANSLATIONS[lang][key]} ${iconHTML}`;
        } else {
          el.innerHTML = `${iconHTML} ${TRANSLATIONS[lang][key]}`;
        }
      } else {
        el.innerHTML = TRANSLATIONS[lang][key];
      }
    }
  });

  document.querySelectorAll('[data-i18n-placeholder]').forEach(el => {
    const key = el.getAttribute('data-i18n-placeholder');
    if (TRANSLATIONS[lang] && TRANSLATIONS[lang][key]) {
      el.setAttribute('placeholder', TRANSLATIONS[lang][key]);
    }
  });

  // Re-load courts with the new language descriptions
  if (typeof STATE !== 'undefined' && STATE.courts && STATE.courts.length > 0) {
    renderFeaturedCourts(STATE.courts);
    const modalGrid = document.getElementById('modal-courts-grid');
    if (modalGrid) renderModalCourts(STATE.courts, modalGrid);
  }

  // Re-initialize Flatpickr to apply the new locale
  if (typeof initFlatpickr === 'function') {
    initFlatpickr();
  }

  // Re-load current active views/tabs that are generated dynamically
  if (typeof STATE !== 'undefined') {
    if (STATE.currentView === 'dashboard-view' && typeof loadUserBookings === 'function') {
      loadUserBookings();
    } else if (STATE.currentView === 'admin-view') {
      const activeTabEl = document.querySelector('#admin-sidebar .active');
      const activeTab = activeTabEl ? activeTabEl.dataset.tab : 'dashboard';
      if (activeTab === 'dashboard' && typeof loadAdminDashboard === 'function') loadAdminDashboard();
      else if (activeTab === 'bookings' && typeof loadAdminBookings === 'function') loadAdminBookings();
      else if (activeTab === 'courts' && typeof loadAdminCourts === 'function') loadAdminCourts();
      else if (activeTab === 'users' && typeof loadAdminUsers === 'function') loadAdminUsers();
      else if (activeTab === 'promos' && typeof loadAdminPromoCodes === 'function') loadAdminPromoCodes();
    }

    // Always refresh active timeslots view and details if courts are loaded
    if (STATE.courts && STATE.courts.length > 0) {
      // 1. Re-render court selector tabs
      const tabsContainer = document.getElementById('court-selector-tabs');
      if (tabsContainer) {
        tabsContainer.innerHTML = '';
        STATE.courts.forEach(court => {
          const tab = document.createElement('button');
          tab.className = `court-tab-btn ${court.id === STATE.activeCourtId ? 'active' : ''}`;
          tab.textContent = translateCourtName(court);
          tab.addEventListener('click', () => {
            STATE.activeCourtId = court.id;
            STATE.selectedSlots = [];
            loadCourtsAvailability();
          });
          tabsContainer.appendChild(tab);
        });
      }

      // 2. Re-render Active Court Info Sidebar
      const activeCourt = STATE.courts.find(c => c.id === STATE.activeCourtId);
      if (activeCourt) {
        const nameEl = document.getElementById('detail-court-name');
        const descEl = document.getElementById('detail-court-desc');
        if (nameEl) nameEl.textContent = translateCourtName(activeCourt);
        if (descEl) descEl.textContent = translateCourtDesc(activeCourt);
      }

      // 3. Re-render Timeslot Grid
      if (document.getElementById('time-grid-slots')) {
        renderTimeGrid(activeCourt);
      }

      // 4. Update Selection Summary
      if (typeof updateSelectionSummary === 'function') {
        updateSelectionSummary();
      }
    }

    // Update active booking details on review screen
    if (STATE.activeBooking && typeof updateReviewDetails === 'function') {
      updateReviewDetails();
    }
  }
}

function toggleLanguage() {
  const nextLang = currentLang === 'en' ? 'th' : 'en';
  applyLanguage(nextLang);
}

function t(key, defaultVal) {
  const dict = TRANSLATIONS[currentLang];
  if (dict && dict[key]) {
    return dict[key];
  }
  return defaultVal !== undefined ? defaultVal : key;
}

function translateCourtName(court) {
  if (!court) return '';
  if (currentLang === 'th' && court.name_th) {
    return court.name_th;
  }
  return t(court.name);
}

function translateCourtDesc(court) {
  if (!court) return '';
  if (currentLang === 'th') {
    if (court.description_th) return court.description_th;
    if (court.name.includes('Court A')) return TRANSLATIONS.th['court-desc-A'] || court.description;
    if (court.name.includes('Court B')) return TRANSLATIONS.th['court-desc-B'] || court.description;
    if (court.name.includes('Arena C')) return TRANSLATIONS.th['court-desc-C'] || court.description;
    if (court.name.includes('Court D')) return TRANSLATIONS.th['court-desc-D'] || court.description;
    return t(court.description);
  }
  return court.description;
}

function translateConfirm(message) {
  return t(message, message);
}

// Application State
const STATE = {
  token: getCookie('token') || localStorage.getItem('token') || null,
  user: JSON.parse(localStorage.getItem('user')) || null,
  currentView: 'home-view',
  selectedDate: '',
  courts: [],
  activeCourtId: null,
  selectedSlots: [], // Array of hourly slot strings (e.g. ["08:00", "09:00"])
  appliedPromo: null, // { code: 'ACE10', discount: 0.10 }
  activeBooking: null, // Current booking object during review/checkout
  config: { googleClientId: '', facebookAppId: '', prompayReceiverId: '' }, // Exposed from server
  slipImage: null,
  inBookingFlow: false,
  editingCourtId: null,
  editingPromoId: null,
  promos: [],
  isResumedPayment: false
};

// Date Utility Functions
function getLocalDateString(dateObj = new Date()) {
  const year = dateObj.getFullYear();
  const month = String(dateObj.getMonth() + 1).padStart(2, '0');
  const day = String(dateObj.getDate()).padStart(2, '0');
  return `${year}-${month}-${day}`;
}

function getDefaultDate() {
  const today = new Date();
  if (today.getHours() >= 21) {
    today.setDate(today.getDate() + 1);
  }
  return getLocalDateString(today);
}

// Simulated Social Accounts for Fallback Login Mode
const SIMULATED_ACCOUNTS = {
  google: [
    { id: 'g_112233', name: 'Somchai Jaidee', email: 'somchai.jd@gmail.com', avatar: 'SJ' },
    { id: 'g_445566', name: 'Jane Doe', email: 'jane.doe@gmail.com', avatar: 'JD' }
  ],
  facebook: [
    { id: 'fb_998877', name: 'Wichai Rakthai', email: 'wichai.rt@fb.com', avatar: 'WR' },
    { id: 'fb_665544', name: 'Sarah Connor', email: 'sconnor@fb.com', avatar: 'SC' }
  ]
};

// Initialize Application on DOM Load
let isAppInitialized = false;

function initializeApp() {
  if (isAppInitialized) return;
  isAppInitialized = true;

  initTheme();
  applyLanguage(currentLang);
  
  // Set default search date
  STATE.selectedDate = getDefaultDate();

  // Initialize Flatpickr inputs
  initFlatpickr();

  fetchConfig().then(() => {
    STATE.configLoaded = true;
    initSocialSDKs();
  });
  
  initNavigation();
  initAuthForms();
  initSearchForm();
  initTimeslotHandlers();
  initPromoHandlers();
  initPaymentHandlers();
  checkLoggedInState();
  if (STATE.token) {
    fetchUserInfo();
  }
  loadFeaturedCourts();
  initRealtimeSync();
}

// Listen to custom DOM ready event from React component
window.addEventListener('app-dom-ready', () => {
  initializeApp();
});

// Fallback in case the event was already fired or we are not in React
if (document.getElementById('theme-toggle-btn')) {
  initializeApp();
}


// --- Theme Management (Auto OS & Manual Toggle) ---
function initTheme() {
  const savedTheme = localStorage.getItem('theme');
  if (savedTheme) {
    applyTheme(savedTheme);
  } else {
    // Auto-detect based on OS preference
    const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
    applyTheme(prefersDark ? 'dark' : 'light');
  }

  // Listen for OS preference updates dynamically (if no manual choice exists)
  window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {
    if (!localStorage.getItem('theme')) {
      applyTheme(e.matches ? 'dark' : 'light');
    }
  });

  const toggleBtn = document.getElementById('theme-toggle-btn');
  toggleBtn.addEventListener('click', () => {
    const nextTheme = STATE.theme === 'dark' ? 'light' : 'dark';
    localStorage.setItem('theme', nextTheme); // Save manual override
    applyTheme(nextTheme);
  });
}

function applyTheme(themeName) {
  STATE.theme = themeName;
  const body = document.body;
  const toggleBtn = document.getElementById('theme-toggle-btn');

  if (themeName === 'light') {
    body.classList.remove('dark-theme');
    body.classList.add('light-theme');
    if (toggleBtn) toggleBtn.setAttribute('aria-checked', 'false');
  } else {
    body.classList.remove('light-theme');
    body.classList.add('dark-theme');
    if (toggleBtn) toggleBtn.setAttribute('aria-checked', 'true');
  }

  // Update Flatpickr theme Link element
  const fpThemeLink = document.getElementById('flatpickr-theme');
  if (fpThemeLink) {
    if (themeName === 'light') {
      fpThemeLink.setAttribute('href', 'https://cdn.jsdelivr.net/npm/flatpickr/dist/themes/light.css');
    } else {
      fpThemeLink.setAttribute('href', 'https://cdn.jsdelivr.net/npm/flatpickr/dist/themes/dark.css');
    }
  }

  // Reinitialize Flatpickr config/theme
  if (typeof flatpickr !== 'undefined') {
    initFlatpickr();
  }

  // If live Google button is initialized, we re-render it to match theme colors
  if (STATE.config.googleClientId && typeof google !== 'undefined' && google.accounts && google.accounts.id) {
    renderGoogleNativeButtons();
  }
}

// Flatpickr instances setup
let bookingDatePicker;
let timeslotDatePicker;

function updateAltInputLabel(dateObj, instance) {
  if (!dateObj || !instance.altInput) return;
  
  const d = String(dateObj.getDate()).padStart(2, '0');
  const m = String(dateObj.getMonth() + 1).padStart(2, '0');
  const y = dateObj.getFullYear();
  const dateStrDMY = `${d}/${m}/${y}`;

  instance.altInput.value = dateStrDMY;
}

function updateCustomSearchDateDisplay(dateObj) {
  if (!dateObj) return;

  const today = new Date();
  today.setHours(0, 0, 0, 0);

  const tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  tomorrow.setHours(0, 0, 0, 0);

  const compareDate = new Date(dateObj);
  compareDate.setHours(0, 0, 0, 0);

  const d = String(dateObj.getDate()).padStart(2, '0');
  const m = String(dateObj.getMonth() + 1).padStart(2, '0');
  const y = dateObj.getFullYear();
  const dateStrDMY = `${d}/${m}/${y}`;

  const dayLabelEl = document.getElementById('search-date-day-label');
  const subLabelEl = document.getElementById('search-date-sub-label');

  if (!dayLabelEl || !subLabelEl) return;

  subLabelEl.textContent = dateStrDMY;

  const isThai = currentLang === 'th';
  if (compareDate.getTime() === today.getTime()) {
    dayLabelEl.textContent = isThai ? 'วันนี้' : 'Today';
  } else if (compareDate.getTime() === tomorrow.getTime()) {
    dayLabelEl.textContent = isThai ? 'พรุ่งนี้' : 'Tomorrow';
  } else {
    // Large formatted date, e.g. "Monday, May 25"
    const locale = isThai ? 'th-TH' : 'en-US';
    const options = { weekday: 'long', month: 'short', day: 'numeric' };
    dayLabelEl.textContent = dateObj.toLocaleDateString(locale, options);
  }
}

function initFlatpickr() {
  const isDark = STATE.theme === 'dark';

  const THAI_FLATPICKR_LOCALE = {
    weekdays: {
      shorthand: ["อา", "จ", "อ", "พ", "พฤ", "ศ", "ส"],
      longhand: [
        "อาทิตย์",
        "จันทร์",
        "อังคาร",
        "พุธ",
        "พฤหัสบดี",
        "ศุกร์",
        "เสาร์"
      ]
    },
    months: {
      shorthand: [
        "ม.ค.",
        "ก.พ.",
        "มี.ค.",
        "เม.ย.",
        "พ.ค.",
        "มิ.ย.",
        "ก.ค.",
        "ส.ค.",
        "ก.ย.",
        "ต.ค.",
        "พ.ย.",
        "ธ.ค."
      ],
      longhand: [
        "มกราคม",
        "กุมภาพันธ์",
        "มีนาคม",
        "เมษายน",
        "พฤษภาคม",
        "มิถุนายน",
        "กรกฎาคม",
        "สิงหาคม",
        "กันยายน",
        "ตุลาคม",
        "พฤศจิกายน",
        "ธันวาคม"
      ]
    },
    firstDayOfWeek: 1,
    rangeSeparator: " ถึง ",
    weekAbbreviation: "สัปดาห์",
    scrollTitle: "เลื่อนเพื่อเพิ่มหรือลด",
    toggleTitle: "คลิกเพื่อเปลี่ยน",
    amPM: ["ก่อนเที่ยง", "หลังเที่ยง"],
    yearAriaLabel: "ปี",
    hourAriaLabel: "ชั่วโมง",
    minuteAriaLabel: "นาที",
    time_24hr: true
  };

  const ENGLISH_FLATPICKR_LOCALE = {
    weekdays: {
      shorthand: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
      longhand: [
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
      ]
    },
    months: {
      shorthand: [
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
      ],
      longhand: [
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
      ]
    },
    firstDayOfWeek: 0,
    rangeSeparator: " to ",
    weekAbbreviation: "Wk",
    scrollTitle: "Scroll to increment",
    toggleTitle: "Click to toggle",
    amPM: ["AM", "PM"],
    yearAriaLabel: "Year",
    hourAriaLabel: "Hour",
    minuteAriaLabel: "Minute",
    time_24hr: false
  };

  if (typeof flatpickr !== 'undefined') {
    flatpickr.localize(currentLang === 'th' ? THAI_FLATPICKR_LOCALE : ENGLISH_FLATPICKR_LOCALE);
  }
  
  if (bookingDatePicker) bookingDatePicker.destroy();
  if (timeslotDatePicker) timeslotDatePicker.destroy();

  const maxDate = new Date();
  maxDate.setMonth(maxDate.getMonth() + 3);

  const minDate = getDefaultDate();

  const triggerEl = document.getElementById('search-date-trigger');
  let finalTriggerEl = triggerEl;

  if (triggerEl) {
    const newTrigger = triggerEl.cloneNode(true);
    triggerEl.parentNode.replaceChild(newTrigger, triggerEl);
    finalTriggerEl = newTrigger;
  }

  const activeLocale = currentLang === 'th' ? THAI_FLATPICKR_LOCALE : ENGLISH_FLATPICKR_LOCALE;

  bookingDatePicker = flatpickr("#booking-date", {
    altInput: false,
    dateFormat: "Y-m-d",
    defaultDate: STATE.selectedDate,
    theme: isDark ? "dark" : "light",
    locale: activeLocale,
    minDate: minDate,
    maxDate: maxDate,
    disableMobile: true,
    positionElement: finalTriggerEl || undefined,
    onChange: function(selectedDates, dateStr) {
      if (dateStr) {
        STATE.selectedDate = dateStr;
        if (timeslotDatePicker) {
          timeslotDatePicker.setDate(dateStr, false);
        }
      }
    },
    onValueUpdate: function(selectedDates, dateStr, instance) {
      updateCustomSearchDateDisplay(selectedDates[0]);
    },
    onReady: function(selectedDates, dateStr, instance) {
      updateCustomSearchDateDisplay(selectedDates[0]);
    }
  });

  if (finalTriggerEl) {
    finalTriggerEl.addEventListener('click', () => {
      if (bookingDatePicker) {
        bookingDatePicker.open();
      }
    });
  }

  timeslotDatePicker = flatpickr("#timeslot-date-picker", {
    altInput: true,
    altFormat: "d/m/Y",
    dateFormat: "Y-m-d",
    defaultDate: STATE.selectedDate,
    theme: isDark ? "dark" : "light",
    locale: activeLocale,
    minDate: minDate,
    maxDate: maxDate,
    disableMobile: true,
    appendTo: document.querySelector('.timeslot-date-header-wrapper') || undefined,
    onChange: function(selectedDates, dateStr) {
      if (dateStr) {
        STATE.selectedDate = dateStr;
        if (bookingDatePicker) {
          bookingDatePicker.setDate(dateStr, false);
        }
        loadCourtsAvailability();
      }
    },
    onValueUpdate: function(selectedDates, dateStr, instance) {
      updateAltInputLabel(selectedDates[0], instance);
    },
    onReady: function(selectedDates, dateStr, instance) {
      updateAltInputLabel(selectedDates[0], instance);
      if (instance.calendarContainer) {
        instance.calendarContainer.classList.add('timeslot-calendar-popup');
      }
    }
  });

  const promoDateEl = document.getElementById('promo-valid-until');
  if (promoDateEl) {
    flatpickr(promoDateEl, {
      altInput: true,
      altFormat: "d/m/Y",
      dateFormat: "Y-m-d",
      theme: isDark ? "dark" : "light",
      locale: activeLocale,
      minDate: "today",
      disableMobile: true
    });
  }

  const timeslotRow = document.querySelector('.timeslot-date-header-row');
  if (timeslotRow) {
    timeslotRow.addEventListener('click', () => {
      if (timeslotDatePicker) {
        timeslotDatePicker.open();
      }
    });
  }
}

// Fetch Server Configurations (Client IDs)
async function fetchConfig() {
  try {
    const response = await fetch('/api/config');
    if (response.ok) {
      STATE.config = await response.json();
    }
  } catch (err) {
    console.error('Error fetching server config:', err);
  }
}

// Google GIS SDK onload callback — fires when accounts.google.com/gsi/client finishes loading
function onGoogleScriptLoaded() {
  STATE.googleGisLoaded = true;
  if (STATE.configLoaded && STATE.config.googleClientId) {
    initGoogleGis();
    return;
  }
  // Config not ready yet — poll until it is (max 3s)
  let waited = 0;
  const poll = setInterval(() => {
    waited += 200;
    if (STATE.configLoaded) {
      clearInterval(poll);
      if (STATE.config.googleClientId) initGoogleGis();
    } else if (waited >= 3000) {
      clearInterval(poll);
    }
  }, 200);
}

// Assign to callback hook
window.onGoogleGisLoadCallback = onGoogleScriptLoaded;

// Run if Google GIS script finished loading before app.js executed
if (window.googleGisLoaded) {
  onGoogleScriptLoaded();
}

function initGoogleGis() {
  if (typeof google === 'undefined' || !google.accounts || !google.accounts.id) {
    console.warn('Google GIS SDK not loaded yet.');
    return;
  }
  try {
    google.accounts.id.initialize({
      client_id: STATE.config.googleClientId,
      callback: handleGoogleLiveCredentialResponse
    });

    const liveAuth = document.getElementById('google-live-auth');
    if (liveAuth) liveAuth.style.display = 'flex';

    const liveAuthReg = document.getElementById('google-live-auth-reg');
    if (liveAuthReg) liveAuthReg.style.display = 'flex';

    // Show OR dividers for Google Auth
    document.querySelectorAll('.google-auth-divider').forEach(el => {
      el.style.display = 'flex';
    });

    renderGoogleNativeButtons();
  } catch (e) {
    console.error('Error initializing Google accounts:', e);
  }
}

// Initialize official SDKs for Google Sign In & Facebook
function initSocialSDKs() {
  // Google: if SDK already loaded (either via callback or already globally present in window)
  const isGoogleLoaded = STATE.googleGisLoaded || (typeof google !== 'undefined' && google.accounts && google.accounts.id);
  if (isGoogleLoaded && STATE.config.googleClientId) {
    initGoogleGis();
  }

  // 2. Live Facebook Sign In
  if (STATE.config.facebookAppId) {
    console.log('Facebook App ID found. Loading Facebook SDK...');
    window.fbAsyncInit = function() {
      FB.init({
        appId      : STATE.config.facebookAppId,
        cookie     : true,
        xfbml      : true,
        version    : 'v18.0'
      });
      
      const fbLiveLogin = document.getElementById('facebook-live-login');
      if (fbLiveLogin) fbLiveLogin.style.display = 'block';
      const fbSimLogin = document.getElementById('facebook-sim-login');
      if (fbSimLogin) fbSimLogin.style.display = 'none';

      const fbLiveReg = document.getElementById('facebook-live-reg');
      if (fbLiveReg) fbLiveReg.style.display = 'block';
      const fbSimReg = document.getElementById('facebook-sim-reg');
      if (fbSimReg) fbSimReg.style.display = 'none';
    };

    (function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) return;
      js = d.createElement(s); js.id = id;
      js.src = "https://connect.facebook.net/en_US/sdk.js";
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
  }
}

function renderGoogleNativeButtons() {
  if (typeof google === 'undefined' || !google.accounts || !google.accounts.id) {
    console.warn('Google GIS SDK not loaded yet for button rendering.');
    return;
  }
  const isDark = STATE.theme === 'dark';
  const btnTheme = isDark ? 'filled_black' : 'outline';

  const loginBtnContainer = document.getElementById('google-signin-btn-login');
  if (loginBtnContainer) {
    google.accounts.id.renderButton(loginBtnContainer, {
      theme: btnTheme,
      size: 'large',
      width: '320'
    });
  }

  const regBtnContainer = document.getElementById('google-signin-btn-reg');
  if (regBtnContainer) {
    google.accounts.id.renderButton(regBtnContainer, {
      theme: btnTheme,
      size: 'large',
      width: '320'
    });
  }
}

// --- Live Social Sign-In Callbacks ---
function handleGoogleLiveCredentialResponse(response) {
  encryptPayload({ credential: response.credential }).then(encryptedBody => {
    fetch('/api/auth/google-login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(encryptedBody)
    })
    .then(res => {
      if (!res.ok) throw new Error();
      return res.json();
    })
    .then(data => {
      const token = getCookie('token');
      if (token) {
        handleAuthSuccess(data);
        showNotification('Successfully logged in with Google!', 'success');
      } else {
        showNotification('Google authentication failed', 'error');
      }
    })
    .catch(err => {
      console.error('Google Sign In Error:', err);
      showNotification('Unable to connect to login server', 'error');
    });
  });
}

function handleFacebookLiveLogin() {
  if (typeof FB === 'undefined') {
    showNotification('Facebook SDK not loaded yet', 'error');
    return;
  }

  FB.login((response) => {
    if (response.authResponse) {
      const accessToken = response.authResponse.accessToken;
      fetch('/api/auth/facebook-login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ accessToken })
      })
      .then(res => res.json())
      .then(data => {
        if (data.token) {
          handleAuthSuccess(data);
          showNotification('Successfully logged in with Facebook!', 'success');
        } else {
          showNotification(data.message || 'Facebook authentication failed', 'error');
        }
      })
      .catch(err => {
        console.error('Facebook Sign In Error:', err);
        showNotification('Unable to connect to login server', 'error');
      });
    } else {
      showNotification('User cancelled login or did not authorize email.', 'error');
    }
  }, { scope: 'email' });
}

// --- Navigation & Routing ---
function initNavigation() {
  const links = document.querySelectorAll('.nav-link, #nav-logo');
  links.forEach(link => {
    link.addEventListener('click', (e) => {
      e.preventDefault();
      const target = link.getAttribute('data-target') || 'home-view';
      
      // If auth is required
      if (link.classList.contains('auth-required') && !STATE.token) {
        showNotification('Please sign in to view bookings.', 'error');
        navigateTo('auth-view');
        return;
      }
      
      navigateTo(target.replace('-view', ''));
    });
  });

  // Mobile Menu Toggle
  const mobileToggle = document.getElementById('mobile-menu-toggle');
  const navLinks = document.querySelector('.nav-links');
  if (mobileToggle) {
    mobileToggle.addEventListener('click', () => {
      navLinks.classList.toggle('active');
    });
  }

  // User Profile Dropdown Toggle
  const profileTrigger = document.getElementById('user-profile-trigger');
  const profileDropdown = document.getElementById('user-display');
  if (profileTrigger && profileDropdown) {
    profileTrigger.addEventListener('click', (e) => {
      e.stopPropagation();
      profileDropdown.classList.toggle('open');
    });

    document.addEventListener('click', (e) => {
      if (!profileDropdown.contains(e.target)) {
        profileDropdown.classList.remove('open');
      }
    });
  }

  // Logout Button
  document.getElementById('logout-btn').addEventListener('click', () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    document.cookie = 'token=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    STATE.token = null;
    STATE.user = null;
    STATE.activeBooking = null;
    STATE.appliedPromo = null;
    STATE.selectedSlots = [];
    STATE.slipImage = null;
    STATE.inBookingFlow = false;
    checkLoggedInState();
    showNotification('Logged out successfully.', 'success');
    navigateTo('home-view');
  });
}

function navigateTo(viewId) {
  const baseId = viewId.replace('-view', '');

  // Hide active views
  document.querySelectorAll('.view').forEach(view => {
    view.classList.remove('active-view');
  });

  const nextView = document.getElementById(`${baseId}-view`);
  if (nextView) {
    nextView.classList.add('active-view');
    STATE.currentView = `${baseId}-view`;
    
    // Update navbar active state
    document.querySelectorAll('.nav-link').forEach(link => {
      if (link.getAttribute('data-target') === `${baseId}-view`) {
        link.classList.add('active');
      } else {
        link.classList.remove('active');
      }
    });

    // View specific hooks
    if (baseId === 'auth') {
      if (!STATE.inBookingFlow) {
        STATE.activeBooking = null;
        STATE.appliedPromo = null;
        STATE.selectedSlots = [];
        STATE.slipImage = null;
      }
      toggleAuthTab('login');
    } else {
      if (baseId !== 'review') {
        STATE.inBookingFlow = false;
      }
    }

    if (baseId === 'dashboard') {
      loadUserBookings();
    } else if (baseId === 'admin') {
      loadAdminDashboard();
    } else if (baseId === 'timeslot') {
      document.getElementById('timeslot-date-picker').value = STATE.selectedDate;
      loadCourtsAvailability();
    }
  }

  // Close mobile menu if open
  const navLinks = document.querySelector('.nav-links');
  if (navLinks) navLinks.classList.remove('active');
  window.scrollTo(0, 0);
}

// --- Notification Banner Helper ---
function showNotification(message, type = 'success') {
  message = t(message, message);
  const container = document.getElementById('notification-container');
  const notification = document.createElement('div');
  notification.className = `notification ${type === 'error' ? 'error' : ''}`;
  
  const icon = type === 'error' ? 'fa-circle-exclamation text-red' : 'fa-circle-check text-neon';
  
  notification.innerHTML = `
    <i class="fa-solid ${icon} notification-icon"></i>
    <div class="notification-content">${message}</div>
  `;
  
  container.appendChild(notification);
  
  setTimeout(() => {
    notification.style.animation = 'slideIn 0.3s ease reverse forwards';
    setTimeout(() => notification.remove(), 300);
  }, 3500);
}

// --- Authentication UI Flow (Login, Register & simulated/live logins) ---
function initAuthForms() {
  const loginForm = document.getElementById('login-form');
  const registerForm = document.getElementById('register-form');

  const loginUsernameInput = document.getElementById('login-username');
  const loginPasswordInput = document.getElementById('login-password');
  const loginUsernameError = document.getElementById('login-username-error');
  const loginPasswordError = document.getElementById('login-password-error');

  const clearLoginErrors = () => {
    loginUsernameError.style.display = 'none';
    loginPasswordError.style.display = 'none';
  };

  loginUsernameInput.addEventListener('input', () => loginUsernameError.style.display = 'none');
  loginPasswordInput.addEventListener('input', () => loginPasswordError.style.display = 'none');

  // Submit Login
  loginForm.addEventListener('submit', (e) => {
    e.preventDefault();
    clearLoginErrors();

    const username = loginUsernameInput.value.trim();
    const password = loginPasswordInput.value;
    let isValid = true;

    if (!username) {
      loginUsernameError.textContent = t('Username is required.');
      loginUsernameError.style.display = 'block';
      isValid = false;
    }

    if (!password) {
      loginPasswordError.textContent = t('Password is required.');
      loginPasswordError.style.display = 'block';
      isValid = false;
    }

    if (!isValid) return;

    encryptPayload({ username, password }).then(encryptedBody => {
      fetch('/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(encryptedBody)
      })
      .then(async res => {
        if (!res.ok) throw new Error();
        return res.json();
      })
      .then(data => {
        const token = getCookie('token');
        if (token) {
          handleAuthSuccess(data);
          showNotification('Welcome back!', 'success');
        } else {
          loginPasswordError.textContent = t('Incorrect username or password. Please try again.');
          loginPasswordError.style.display = 'block';
        }
      })
      .catch(() => {
        loginPasswordError.textContent = t('Incorrect username or password. Please try again.');
        loginPasswordError.style.display = 'block';
      });
    });
  });

  // Form elements for validation
  const registerUsernameInput = document.getElementById('register-username');
  const registerEmailInput = document.getElementById('register-email');
  const registerPasswordInput = document.getElementById('register-password');
  
  const registerUsernameError = document.getElementById('register-username-error');
  const registerEmailError = document.getElementById('register-email-error');
  const registerPasswordError = document.getElementById('register-password-error');

  const clearErrors = () => {
    registerUsernameError.style.display = 'none';
    registerEmailError.style.display = 'none';
    registerPasswordError.style.display = 'none';
  };

  const registerUsernameReqs = document.getElementById('register-username-requirements');
  const registerPasswordReqs = document.getElementById('register-password-requirements');

  // Username requirements elements
  const reqUsernameLength = document.getElementById('username-req-length');
  const reqUsernameChars = document.getElementById('username-req-chars');

  // Password requirements elements
  const reqPassLength = document.getElementById('pass-req-length');
  const reqPassUpper = document.getElementById('pass-req-upper');
  const reqPassLower = document.getElementById('pass-req-lower');
  const reqPassNumber = document.getElementById('pass-req-number');

  // Show requirements on focus
  registerUsernameInput.addEventListener('focus', () => {
    registerUsernameReqs.style.display = 'block';
  });
  registerPasswordInput.addEventListener('focus', () => {
    registerPasswordReqs.style.display = 'block';
  });

  // Helper to update requirement item style
  function updateReqItem(el, isValid, text) {
    if (isValid) {
      el.className = 'text-success';
      el.innerHTML = `<i class="fa-solid fa-circle-check mr-1"></i> ${text}`;
    } else {
      el.className = 'text-danger';
      el.innerHTML = `<i class="fa-solid fa-circle-xmark mr-1"></i> ${text}`;
    }
  }

  // Reset to neutral state when empty
  function resetReqItem(el, text) {
    el.className = 'text-muted';
    el.innerHTML = `<i class="fa-solid fa-circle mr-1" style="font-size: 0.5rem; vertical-align: middle;"></i> ${text}`;
  }

  // Real-time username check
  registerUsernameInput.addEventListener('input', () => {
    registerUsernameError.style.display = 'none';
    registerUsernameReqs.style.display = 'block';
    const val = registerUsernameInput.value;
    
    if (val === '') {
      resetReqItem(reqUsernameLength, t('3 to 20 characters long'));
      resetReqItem(reqUsernameChars, t('Letters, numbers, and underscores only'));
      return;
    }

    const hasValidLength = val.length >= 3 && val.length <= 20;
    const hasValidChars = /^[a-zA-Z0-9_]*$/.test(val);

    updateReqItem(reqUsernameLength, hasValidLength, t('3 to 20 characters long'));
    updateReqItem(reqUsernameChars, hasValidChars, t('Letters, numbers, and underscores only'));
  });

  registerEmailInput.addEventListener('input', () => registerEmailError.style.display = 'none');

  // Real-time password check
  registerPasswordInput.addEventListener('input', () => {
    registerPasswordError.style.display = 'none';
    registerPasswordReqs.style.display = 'block';
    const val = registerPasswordInput.value;

    if (val === '') {
      resetReqItem(reqPassLength, t('At least 8 characters long'));
      resetReqItem(reqPassUpper, t('At least one uppercase letter (A-Z)'));
      resetReqItem(reqPassLower, t('At least one lowercase letter (a-z)'));
      resetReqItem(reqPassNumber, t('At least one number (0-9)'));
      return;
    }

    const hasLength = val.length >= 8;
    const hasUpper = /[A-Z]/.test(val);
    const hasLower = /[a-z]/.test(val);
    const hasNumber = /\d/.test(val);

    updateReqItem(reqPassLength, hasLength, t('At least 8 characters long'));
    updateReqItem(reqPassUpper, hasUpper, t('At least one uppercase letter (A-Z)'));
    updateReqItem(reqPassLower, hasLower, t('At least one lowercase letter (a-z)'));
    updateReqItem(reqPassNumber, hasNumber, t('At least one number (0-9)'));
  });

  // Submit Register
  registerForm.addEventListener('submit', (e) => {
    e.preventDefault();
    clearErrors();
    
    const username = registerUsernameInput.value.trim();
    const email = registerEmailInput.value.trim();
    const password = registerPasswordInput.value;
    let isValid = true;

    const usernameRegex = /^[a-zA-Z0-9_]{3,20}$/;
    if (!username) {
      registerUsernameError.textContent = t('Username is required.');
      registerUsernameError.style.display = 'block';
      isValid = false;
    } else if (!usernameRegex.test(username)) {
      registerUsernameReqs.style.display = 'block';
      registerUsernameInput.dispatchEvent(new Event('input'));
      isValid = false;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email) {
      registerEmailError.textContent = t('Email address is required.');
      registerEmailError.style.display = 'block';
      isValid = false;
    } else if (!emailRegex.test(email)) {
      registerEmailError.textContent = t('Please enter a valid email address.');
      registerEmailError.style.display = 'block';
      isValid = false;
    }

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    if (!password) {
      registerPasswordError.textContent = t('Password is required.');
      registerPasswordError.style.display = 'block';
      isValid = false;
    } else if (!passwordRegex.test(password)) {
      registerPasswordReqs.style.display = 'block';
      registerPasswordInput.dispatchEvent(new Event('input'));
      isValid = false;
    }

    if (!isValid) return;

    fetch('/api/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, email, password })
    })
    .then(res => {
      if (!res.ok) {
        return res.json().then(errData => { throw new Error(errData.message || 'Registration failed'); });
      }
      return res.json();
    })
    .then(data => {
      showNotification('Registration successful! Please login.', 'success');
      toggleAuthTab('login');
      document.getElementById('login-username').value = username;
    })
    .catch((err) => {
      const errMsg = err.message.toLowerCase();
      if (errMsg.includes('username')) {
        registerUsernameError.textContent = t(err.message);
        registerUsernameError.style.display = 'block';
      } else if (errMsg.includes('email')) {
        registerEmailError.textContent = t(err.message);
        registerEmailError.style.display = 'block';
      } else {
        showNotification(err.message ? t(err.message) : t('Connection error during registration'), 'error');
      }
    });
  });

  // Forgot Password Submit
  const forgotForm = document.getElementById('forgot-password-form');
  const forgotEmailInput = document.getElementById('forgot-email');
  const forgotEmailError = document.getElementById('forgot-email-error');
  const forgotSubmitBtn = document.getElementById('forgot-submit-btn');
  let originalBtnHtml = forgotSubmitBtn ? forgotSubmitBtn.innerHTML : 'Send PIN';

  forgotEmailInput?.addEventListener('input', () => forgotEmailError.style.display = 'none');

  forgotForm?.addEventListener('submit', (e) => {
    e.preventDefault();
    const email = forgotEmailInput.value.trim();
    if (!email) {
      forgotEmailError.textContent = t('Email is required.');
      forgotEmailError.style.display = 'block';
      return;
    }

    if (forgotSubmitBtn) {
      forgotSubmitBtn.disabled = true;
      forgotSubmitBtn.innerHTML = `${t('Sending...')} <i class="fa-solid fa-spinner fa-spin ml-1"></i>`;
    }

    fetch('/api/auth/forgot-password', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email })
    })
    .then(res => res.json().then(data => ({ ok: res.ok, data })))
    .then(({ ok, data }) => {
      if (!ok) {
        forgotEmailError.textContent = data.message ? t(data.message) : t('Error sending PIN');
        forgotEmailError.style.display = 'block';
        if (forgotSubmitBtn) {
          forgotSubmitBtn.disabled = false;
          forgotSubmitBtn.innerHTML = originalBtnHtml;
        }
        return;
      }
      showNotification('A password reset PIN has been sent to your email. Please check your inbox.', 'success');
      document.getElementById('reset-token').value = '';
      
      if (forgotSubmitBtn) {
        let cooldown = 30;
        forgotSubmitBtn.innerHTML = `Wait ${cooldown}s <i class="fa-solid fa-clock ml-1"></i>`;
        const interval = setInterval(() => {
          cooldown--;
          if (cooldown <= 0) {
            clearInterval(interval);
            forgotSubmitBtn.disabled = false;
            forgotSubmitBtn.innerHTML = originalBtnHtml;
          } else {
            forgotSubmitBtn.innerHTML = `Wait ${cooldown}s <i class="fa-solid fa-clock ml-1"></i>`;
          }
        }, 1000);
      }

      toggleAuthTab('reset');
    })
    .catch(() => {
      showNotification('Connection error', 'error');
      if (forgotSubmitBtn) {
        forgotSubmitBtn.disabled = false;
        forgotSubmitBtn.innerHTML = originalBtnHtml;
      }
    });
  });

  // Reset Password Submit
  const resetForm = document.getElementById('reset-password-form');
  const resetTokenInput = document.getElementById('reset-token');
  const resetPasswordInput = document.getElementById('reset-password');
  const resetTokenError = document.getElementById('reset-token-error');
  const resetPasswordError = document.getElementById('reset-password-error');
  
  resetTokenInput?.addEventListener('input', () => resetTokenError.style.display = 'none');
  resetPasswordInput?.addEventListener('input', () => resetPasswordError.style.display = 'none');

  resetForm?.addEventListener('submit', (e) => {
    e.preventDefault();
    const email = forgotEmailInput.value.trim();
    const token = resetTokenInput.value.trim();
    const newPassword = resetPasswordInput.value;
    let isValid = true;

    if (!token) {
      resetTokenError.textContent = t('Reset PIN is required.');
      resetTokenError.style.display = 'block';
      isValid = false;
    }

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    if (!newPassword) {
      resetPasswordError.textContent = t('New password is required.');
      resetPasswordError.style.display = 'block';
      isValid = false;
    } else if (!passwordRegex.test(newPassword)) {
      resetPasswordError.innerHTML = `
        <ul style="margin: 0; padding-left: 1.2rem; text-align: left;">
          <li>${t('At least 8 characters long')}</li>
          <li>${t('At least one uppercase letter (A-Z)')}</li>
          <li>${t('At least one lowercase letter (a-z)')}</li>
          <li>${t('At least one number (0-9)')}</li>
        </ul>
      `;
      resetPasswordError.style.display = 'block';
      isValid = false;
    }

    if (!isValid) return;

    fetch('/api/auth/reset-password', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, token, newPassword })
    })
    .then(res => res.json().then(data => ({ ok: res.ok, data })))
    .then(({ ok, data }) => {
      if (!ok) {
        resetTokenError.textContent = data.message ? t(data.message) : t('Error resetting password');
        resetTokenError.style.display = 'block';
        return;
      }
      showNotification('Password updated successfully. You can now login.', 'success');
      toggleAuthTab('login');
      document.getElementById('login-password').value = '';
    })
    .catch(() => showNotification('Connection error', 'error'));
  });
}

function handleAuthSuccess(data) {
  STATE.token = getCookie('token') || (data && data.token);
  localStorage.setItem('token', STATE.token || '');

  if (data && data.user) {
    STATE.user = data.user;
    localStorage.setItem('user', JSON.stringify(data.user));
    checkLoggedInState();

    // Redirect based on previous workflow target
    if (STATE.inBookingFlow && STATE.activeBooking) {
      createPendingBooking();
    } else {
      navigateTo('dashboard');
    }
  } else {
    // Fetch user details from /api/auth/me since they weren't in the response body
    fetch('/api/auth/me', {
      headers: { 'Authorization': `Bearer ${STATE.token}` }
    })
    .then(res => {
      if (!res.ok) throw new Error();
      return res.json();
    })
    .then(user => {
      STATE.user = user;
      localStorage.setItem('user', JSON.stringify(user));
      checkLoggedInState();

      // Redirect based on previous workflow target
      if (STATE.inBookingFlow && STATE.activeBooking) {
        createPendingBooking();
      } else {
        navigateTo('dashboard');
      }
    })
    .catch(() => {
      showNotification('Failed to retrieve user details', 'error');
    });
  }
}

function checkLoggedInState() {
  const loginNavBtn = document.getElementById('login-nav-btn');
  const userDisplay = document.getElementById('user-display');
  const usernameSpan = document.getElementById('username-span');
  const dropdownUsername = document.getElementById('dropdown-username');
  const authRequiredLinks = document.querySelectorAll('.auth-required');
  const adminRequiredLinks = document.querySelectorAll('.admin-required');

  if (STATE.token && STATE.user) {
    loginNavBtn.style.display = 'none';
    userDisplay.style.display = 'flex';
    const displayName = STATE.user.display_name || STATE.user.username;
    usernameSpan.textContent = displayName;
    if (dropdownUsername) {
      dropdownUsername.textContent = displayName;
    }
    authRequiredLinks.forEach(link => link.style.display = 'block');

    // Only admin can see the Admin Panel nav link
    if (STATE.user.role === 'admin') {
      adminRequiredLinks.forEach(link => link.style.display = 'block');
    } else {
      adminRequiredLinks.forEach(link => link.style.display = 'none');
    }
  } else {
    loginNavBtn.style.display = 'block';
    userDisplay.style.display = 'none';
    authRequiredLinks.forEach(link => link.style.display = 'none');
    adminRequiredLinks.forEach(link => link.style.display = 'none');
  }
}

function fetchUserInfo() {
  fetch('/api/auth/me', {
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => {
    if (!res.ok) {
      if (res.status === 401 || res.status === 403) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        STATE.token = null;
        STATE.user = null;
        checkLoggedInState();
      }
      throw new Error();
    }
    return res.json();
  })
  .then(user => {
    STATE.user = user;
    localStorage.setItem('user', JSON.stringify(user));
    checkLoggedInState();
  })
  .catch(() => {});
}

function toggleAuthTab(tab) {
  const tabLogin = document.getElementById('tab-login');
  const tabRegister = document.getElementById('tab-register');
  const loginForm = document.getElementById('login-form');
  const registerForm = document.getElementById('register-form');
  const forgotForm = document.getElementById('forgot-password-form');
  const resetForm = document.getElementById('reset-password-form');
  const authTabsContainer = document.querySelector('.auth-tabs');

  // Reset all
  loginForm.style.display = 'none';
  registerForm.style.display = 'none';
  if (forgotForm) forgotForm.style.display = 'none';
  if (resetForm) resetForm.style.display = 'none';
  
  loginForm.classList.remove('active-form');
  registerForm.classList.remove('active-form');
  if (forgotForm) forgotForm.classList.remove('active-form');
  if (resetForm) resetForm.classList.remove('active-form');

  if (tab === 'login') {
    authTabsContainer.style.display = 'flex';
    tabLogin.classList.add('active');
    tabRegister.classList.remove('active');
    loginForm.style.display = 'block';
    loginForm.classList.add('active-form');
  } else if (tab === 'register') {
    authTabsContainer.style.display = 'flex';
    tabLogin.classList.remove('active');
    tabRegister.classList.add('active');
    registerForm.style.display = 'block';
    registerForm.classList.add('active-form');
  } else if (tab === 'forgot' && forgotForm) {
    authTabsContainer.style.display = 'none';
    forgotForm.style.display = 'block';
    forgotForm.classList.add('active-form');
  } else if (tab === 'reset' && resetForm) {
    authTabsContainer.style.display = 'none';
    resetForm.style.display = 'block';
    resetForm.classList.add('active-form');
  }

  // Force Google native button re-render now that the containers are visible!
  if (STATE.config.googleClientId && typeof google !== 'undefined' && google.accounts && google.accounts.id) {
    setTimeout(() => {
      renderGoogleNativeButtons();
    }, 50);
  }
}

// --- Fallback Simulated Social Authentication Handlers ---
function handleSocialAuth(provider) {
  const modal = document.getElementById('social-modal');
  const modalTitle = document.getElementById('social-modal-title');
  const accountsList = document.getElementById('social-accounts-list');

  modalTitle.innerHTML = `<i class="fa-brands fa-${provider} mr-2"></i> Sign In with ${provider === 'google' ? 'Google' : 'Facebook'}`;
  accountsList.innerHTML = '';

  const accounts = SIMULATED_ACCOUNTS[provider];
  accounts.forEach(acc => {
    const row = document.createElement('div');
    row.className = 'social-account-row';
    row.innerHTML = `
      <div class="social-account-avatar ${provider}">${acc.avatar}</div>
      <div class="social-account-info">
        <span class="social-account-name">${acc.name}</span>
        <span class="social-account-email">${acc.email}</span>
      </div>
    `;
    row.addEventListener('click', () => {
      submitSimulatedSocialLogin(provider, acc);
    });
    accountsList.appendChild(row);
  });

  modal.style.display = 'flex';
}

function closeSocialModal() {
  document.getElementById('social-modal').style.display = 'none';
}

function closePolicyModal() {
  document.getElementById('policy-modal').style.display = 'none';
}

// Close modals when clicking on the overlay background
window.addEventListener('click', (event) => {
  const socialModal = document.getElementById('social-modal');
  const policyModal = document.getElementById('policy-modal');
  
  if (event.target === socialModal) {
    closeSocialModal();
  }
  if (event.target === policyModal) {
    closePolicyModal();
  }
});


function showPolicy(type) {
  const modal = document.getElementById('policy-modal');
  const title = document.getElementById('policy-modal-title');
  const body = document.getElementById('policy-modal-body');
  
  if (!modal || !title || !body) return;
  
  let contentHtml = '';
  const isThai = currentLang === 'th';
  
  if (type === 'privacy') {
    if (isThai) {
      title.innerHTML = '<i class="fa-solid fa-shield-halved mr-2 text-neon"></i> นโยบายความเป็นส่วนตัว (Privacy Policy)';
      contentHtml = `
        <div style="font-family: var(--font-body); font-size: 0.95rem; line-height: 1.6; color: var(--text-main);">
          <p class="mb-3">พวกเราที่ <strong>Mini Tennis</strong> ให้ความสำคัญต่อความเป็นส่วนตัวของคุณเป็นอันดับแรก นโยบายนี้ระบุหลักเกณฑ์การเก็บรวบรวมข้อมูลส่วนบุคคลของท่าน</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-circle-info mr-1 text-neon"></i> 1. ข้อมูลที่เราจัดเก็บ</h5>
          <ul style="padding-left: 20px;" class="mb-3">
            <li><strong>ข้อมูลลงทะเบียน:</strong> ชื่อ-นามสกุล, อีเมล, เบอร์โทรศัพท์ และรหัสผ่านที่ผ่านการเข้ารหัส</li>
            <li><strong>ข้อมูลการทำรายการ:</strong> ประวัติการจองคอร์ท, วันเวลาจอง, ยอดค่าบริการ, และแฮชประวัติของรูปภาพสลิปที่ใช้ชำระเงิน</li>
            <li><strong>Social Login:</strong> รูปโปรไฟล์ และอีเมลที่ได้รับจากระบบ Google OAuth</li>
          </ul>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-circle-check mr-1 text-neon"></i> 2. การนำข้อมูลไปใช้</h5>
          <p class="mb-3">ข้อมูลของท่านจะถูกใช้เฉพาะการบริหารจัดการการจองสนาม, ตรวจสอบยอดชำระเงินโดยอัตโนมัติผ่านทาง API ตรวจสอบสลิป, นำส่ง PIN 6 หลักเพื่อปลดล็อกใช้งานสนาม, และติดต่อช่วยเหลือผู้ใช้</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-lock mr-1 text-neon"></i> 3. ความปลอดภัยของข้อมูล</h5>
          <p class="mb-3">ข้อมูลทั้งหมดจะได้รับการจัดเก็บในระบบ PostgreSQL ที่ปลอดภัย รหัสผ่านได้รับการเข้าแฮชผ่านกระบวนการ Cryptographic (bcrypt) เสมอ ข้อมูลรูปภาพสลิปจะไม่มีการส่งต่อไปที่ใดนอกเหนือจาก API ตรวจสอบสลิปอย่างปลอดภัย</p>
        </div>
      `;
    } else {
      title.innerHTML = '<i class="fa-solid fa-shield-halved mr-2 text-neon"></i> Privacy Policy';
      contentHtml = `
        <div style="font-family: var(--font-body); font-size: 0.95rem; line-height: 1.6; color: var(--text-main);">
          <p class="mb-3">At <strong>Mini Tennis</strong>, your privacy is our top priority. This policy outlines how we collect and use your personal information.</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-circle-info mr-1 text-neon"></i> 1. Information We Collect</h5>
          <ul style="padding-left: 20px;" class="mb-3">
            <li><strong>Registration Details:</strong> First and last name, email address, phone number, and encrypted password.</li>
            <li><strong>Transaction Details:</strong> Booking history, dates and times, fee totals, and hash history of uploaded payment slips.</li>
            <li><strong>Social Login:</strong> Profile image and email retrieved from Google OAuth.</li>
          </ul>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-circle-check mr-1 text-neon"></i> 2. How We Use Information</h5>
          <p class="mb-3">Your information is used solely for managing court bookings, automatically verifying payments via our slip verification API, generating and sending the 6-digit access PIN, and providing customer support.</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-lock mr-1 text-neon"></i> 3. Data Security</h5>
          <p class="mb-3">All data is securely stored in a PostgreSQL database. Passwords are encrypted using a cryptographic hashing algorithm (bcrypt). Slip images are never shared outside our secure verification API.</p>
        </div>
      `;
    }
  } else if (type === 'terms') {
    if (isThai) {
      title.innerHTML = '<i class="fa-solid fa-file-contract mr-2 text-neon"></i> ข้อตกลงการให้บริการ (Terms of Service)';
      contentHtml = `
        <div style="font-family: var(--font-body); font-size: 0.95rem; line-height: 1.6; color: var(--text-main);">
          <p class="mb-3">การเข้าใช้งานระบบ <strong>Mini Tennis</strong> ถือว่าท่านยอมรับข้อตกลงและเงื่อนไขการใช้บริการดังต่อไปนี้:</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-circle-exclamation mr-1 text-neon"></i> 1. กติกาการจองและใช้สนาม</h5>
          <ul style="padding-left: 20px;" class="mb-3">
            <li>ผู้ใช้งานจะต้องเลือกช่วงเวลาที่ว่างและลงทะเบียนจองพร้อมโอนเงินชำระค่าบริการให้ถูกต้องเต็มจำนวนตามใบจอง</li>
            <li>เมื่อการชำระเงินและตรวจสอบสลิปเสร็จสมบูรณ์ ท่านจะได้รับรหัส PIN 6 หลักทางหน้าเว็บและประวัติการจอง</li>
            <li>รหัส PIN จะเปิดใช้งานได้เฉพาะคอร์ทและช่วงเวลาที่ท่านได้ระบุจองไว้เท่านั้น และจะสิ้นสุดลงทันทีเมื่อหมดรอบการจอง</li>
          </ul>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-ban mr-1 text-neon"></i> 2. การปฏิเสธและคืนเงิน</h5>
          <p class="mb-3">รายการจองที่ได้รับการอนุมัติและออกรหัส PIN แล้ว **ไม่สามารถยกเลิกหรือขอคืนเงินได้** หากท่านต้องการเลื่อนเวลา กรุณาประสานงานแอดมินล่วงหน้าอย่างน้อย 24 ชั่วโมงก่อนเริ่มเวลาการจอง</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-triangle-exclamation mr-1 text-neon"></i> 3. มาตรการป้องกันการทุจริตสลิป</h5>
          <p class="mb-3">ระบบของเราติดตั้งระบบดักจับสลิปซ้ำและสลิปปลอมอัตโนมัติ หากผู้ใช้พยายามอัปโหลดสลิปที่ผ่านการตรวจแล้ว สลิปปลอม หรือสลิปที่แก้ไขตัวเลข ระบบจะทำการระงับบัญชีผู้ใช้งานทันที</p>
        </div>
      `;
    } else {
      title.innerHTML = '<i class="fa-solid fa-file-contract mr-2 text-neon"></i> Terms of Service';
      contentHtml = `
        <div style="font-family: var(--font-body); font-size: 0.95rem; line-height: 1.6; color: var(--text-main);">
          <p class="mb-3">By using <strong>Mini Tennis</strong>, you agree to the following terms and conditions:</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-circle-exclamation mr-1 text-neon"></i> 1. Booking & Court Rules</h5>
          <ul style="padding-left: 20px;" class="mb-3">
            <li>Users must select an available timeslot, register their booking, and pay the full balance shown on the booking details.</li>
            <li>Once payment verification is complete, a 6-digit PIN will be displayed on-screen and added to your booking history.</li>
            <li>The PIN is valid only for the specific court and timeslot selected, expiring automatically at the end of the booking session.</li>
          </ul>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-ban mr-1 text-neon"></i> 2. Cancellations & Refunds</h5>
          <p class="mb-3">Bookings that have been approved and assigned a PIN **cannot be cancelled or refunded**. For rescheduling requests, please contact the admin team at least 24 hours prior to the booking start time.</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-triangle-exclamation mr-1 text-neon"></i> 3. Anti-Fraud Measures</h5>
          <p class="mb-3">Our system automatically detects duplicate or forged slips. Attempting to upload a previously processed slip, a fake slip, or modified transaction documents will result in an immediate and permanent account suspension.</p>
        </div>
      `;
    }
  } else if (type === 'support') {
    if (isThai) {
      title.innerHTML = '<i class="fa-solid fa-circle-question mr-2 text-neon"></i> ช่วยเหลือและสนับสนุน (Help Support)';
      contentHtml = `
        <div style="font-family: var(--font-body); font-size: 0.95rem; line-height: 1.6; color: var(--text-main);">
          <p class="mb-3">ยินดีต้อนรับสู่ศูนย์ช่วยเหลือของ <strong>Mini Tennis Court</strong> หากท่านมีข้อสงสัยหรือพบปัญหาในการจอง/ใช้งานสนาม สามารถศึกษาคำแนะนำหรือติดต่อทีมงานด้านล่างนี้:</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-list-check mr-1 text-neon"></i> ขั้นตอนการเข้าสนาม</h5>
          <ol style="padding-left: 20px;" class="mb-3">
            <li>หลังจากทำการจองและอัปโหลดรูปภาพสลิปชำระเงินสำเร็จแล้ว ให้คัดลอกหรือแคปหน้าจอ **PIN 6 หลัก**</li>
            <li>เดินทางมายังหน้าสนามเทนนิส และนำรหัส PIN ไปป้อนลงที่หน้าจออุปกรณ์ Android Kiosk ด้านหน้าประตู</li>
            <li>เมื่อรหัสผ่านถูกต้อง ระบบ Kiosk จะปลดล็อกประตูและเปิดไฟฟ้าสนามให้ทำงานทันที</li>
          </ol>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-phone mr-1 text-neon"></i> ช่องทางติดต่อทีมงานแอดมิน</h5>
          <ul style="padding-left: 20px; list-style: none;" class="mb-3">
            <li class="mb-1"><i class="fa-solid fa-phone-volume mr-2 text-neon"></i> <strong>เบอร์โทรศัพท์ด่วน:</strong> 02-123-4567</li>
            <li class="mb-1"><i class="fa-solid fa-envelope mr-2 text-neon"></i> <strong>อีเมลฝ่ายซัพพอร์ต:</strong> support@minitennis.com</li>
            <li class="mb-1"><i class="fa-brands fa-line mr-2 text-neon"></i> <strong>Official Line:</strong> @MiniTennis</li>
          </ul>
          
          <p style="font-size: 0.85rem; color: var(--text-muted);" class="mt-4 text-center">เปิดทำการและให้บริการช่วยเหลือลูกค้าเวลา 08:00 น. - 22:00 น. ทุกวัน</p>
        </div>
      `;
    } else {
      title.innerHTML = '<i class="fa-solid fa-circle-question mr-2 text-neon"></i> Help Support';
      contentHtml = `
        <div style="font-family: var(--font-body); font-size: 0.95rem; line-height: 1.6; color: var(--text-main);">
          <p class="mb-3">Welcome to the <strong>Mini Tennis Court</strong> Help Center. If you have questions or encounter issues while booking or accessing the courts, please read the guide below or contact our team:</p>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-list-check mr-1 text-neon"></i> Accessing the Court</h5>
          <ol style="padding-left: 20px;" class="mb-3">
            <li>After successful booking and uploading your payment slip, copy or screenshot your **6-digit PIN**.</li>
            <li>Go to your designated tennis court and enter this PIN on the Android Kiosk device next to the gate.</li>
            <li>Once the correct PIN is entered, the kiosk will unlock the gate and activate the court floodlights automatically.</li>
          </ol>
          
          <h5 class="text-white mt-3 mb-2" style="font-size: 1.05rem;"><i class="fa-solid fa-phone mr-1 text-neon"></i> Contact Support Team</h5>
          <ul style="padding-left: 20px; list-style: none;" class="mb-3">
            <li class="mb-1"><i class="fa-solid fa-phone-volume mr-2 text-neon"></i> <strong>Hotline:</strong> 02-123-4567</li>
            <li class="mb-1"><i class="fa-solid fa-envelope mr-2 text-neon"></i> <strong>Support Email:</strong> support@minitennis.com</li>
            <li class="mb-1"><i class="fa-brands fa-line mr-2 text-neon"></i> <strong>Official Line:</strong> @MiniTennis</li>
          </ul>
          
          <p style="font-size: 0.85rem; color: var(--text-muted);" class="mt-4 text-center">Support hours: 08:00 AM - 10:00 PM daily</p>
        </div>
      `;
    }
  }
  
  body.innerHTML = contentHtml;
  modal.style.display = 'flex';
}


function submitSimulatedSocialLogin(provider, account) {
  closeSocialModal();
  const endpoint = provider === 'google' ? '/api/auth/google-login' : '/api/auth/facebook-login';
  
  const payload = {
    isSimulation: true,
    email: account.email,
    name: account.name,
    id: account.id
  };

  const sendRequest = (bodyObj) => {
    fetch(endpoint, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(bodyObj)
    })
    .then(res => {
      if (!res.ok) throw new Error();
      return res.json();
    })
    .then(data => {
      const token = getCookie('token');
      if (token) {
        handleAuthSuccess(data);
        showNotification(currentLang === 'th' ? `เข้าสู่ระบบเป็น ${account.name} (จำลอง)` : `Logged in as ${account.name} (Simulated)`, 'success');
      } else {
        showNotification('Social login failed', 'error');
      }
    })
    .catch(() => showNotification('Connection error during social authentication', 'error'));
  };

  if (provider === 'google') {
    encryptPayload(payload).then(sendRequest);
  } else {
    sendRequest(payload);
  }
}

// --- Home / Featured Courts Section ---
function loadFeaturedCourts() {
  fetch(`/api/courts?date=${getDefaultDate()}`)
  .then(res => res.json())
  .then(courts => {
    STATE.courts = courts;
    renderFeaturedCourts(courts);
  })
  .catch(() => {
    const grid = document.getElementById('featured-courts-grid');
    if (grid) {
      grid.innerHTML = '<p class="text-center w-100 text-muted">Unable to load courts. Please check server connection.</p>';
    }
  });
}

function renderFeaturedCourts(courts) {
  const grid = document.getElementById('featured-courts-grid');
  if (!grid) return;
  grid.innerHTML = '';

  courts.forEach(court => {
    const card = document.createElement('div');
    card.className = 'court-card';
    
    let desc = translateCourtDesc(court);
    const isMaintenance = court.is_maintenance;
    const btnText = isMaintenance 
      ? (currentLang === 'en' ? 'Maintenance' : 'ปิดปรับปรุง') 
      : (currentLang === 'en' ? 'Book Now' : 'จองตอนนี้');
    const btnClass = isMaintenance ? 'btn btn-outline btn-sm btn-disabled' : 'btn btn-primary btn-sm btn-book';
    
    card.innerHTML = `
      <div class="court-img-container">
        <i class="fa-solid fa-baseball court-img-svg"></i>
        <div class="court-img-bg-shape"></div>
        ${isMaintenance ? `
          <div class="court-maintenance-overlay" style="position: absolute; top: 12px; right: 12px; background: rgba(239, 68, 68, 0.9); color: #fff; padding: 4px 8px; border-radius: 4px; font-size: 0.75rem; font-weight: bold; z-index: 2;">
            <i class="fa-solid fa-screwdriver-wrench mr-1"></i> ${currentLang === 'en' ? 'Maintenance' : 'ปิดปรับปรุง'}
          </div>
        ` : ''}
      </div>
      <div class="court-card-body">
        <h3 class="court-card-title">${translateCourtName(court)}</h3>
        <p class="court-card-desc">${desc}</p>
        <div class="court-card-footer">
          <div class="court-card-price" style="${isMaintenance ? 'opacity: 0.5;' : ''}">
            <span class="price">฿${court.price_per_hour}</span>
            <span class="label">${currentLang === 'en' ? 'per hour' : 'ต่อชั่วโมง'}</span>
          </div>
          <button class="${btnClass}" data-id="${court.id}" ${isMaintenance ? 'disabled' : ''}>${btnText}</button>
        </div>
      </div>
    `;
    
    if (!isMaintenance) {
      card.style.cursor = 'pointer';
      card.addEventListener('click', () => {
        STATE.activeCourtId = court.id;
        navigateTo('timeslot');
      });
    } else {
      card.style.cursor = 'default';
    }

    grid.appendChild(card);
  });
}

function openExploreCourtsModal() {
  const modal = document.getElementById('explore-courts-modal');
  if (modal) {
    modal.style.display = 'flex';
    populateModalCourts();
  }
}

function closeExploreCourtsModal() {
  const modal = document.getElementById('explore-courts-modal');
  if (modal) {
    modal.style.display = 'none';
  }
}

function populateModalCourts() {
  const grid = document.getElementById('modal-courts-grid');
  if (!grid) return;
  grid.innerHTML = '<p class="text-center w-100 text-muted">Loading courts...</p>';
  
  if (!STATE.courts || STATE.courts.length === 0) {
    fetch(`/api/courts?date=${getDefaultDate()}`)
    .then(res => res.json())
    .then(courts => {
      STATE.courts = courts;
      renderModalCourts(courts, grid);
    })
    .catch(() => {
      grid.innerHTML = '<p class="text-center w-100 text-muted">Unable to load courts.</p>';
    });
  } else {
    renderModalCourts(STATE.courts, grid);
  }
}

function renderModalCourts(courts, grid) {
  grid.innerHTML = '';
  courts.forEach(court => {
    const card = document.createElement('div');
    card.className = 'court-card';
    
    let desc = translateCourtDesc(court);
    const isMaintenance = court.is_maintenance;
    const btnText = isMaintenance 
      ? (currentLang === 'en' ? 'Maintenance' : 'ปิดปรับปรุง') 
      : (currentLang === 'en' ? 'Book Now' : 'จองตอนนี้');
    const btnClass = isMaintenance ? 'btn btn-outline btn-sm btn-disabled' : 'btn btn-primary btn-sm btn-book';

    card.innerHTML = `
      <div class="court-img-container">
        <i class="fa-solid fa-baseball court-img-svg"></i>
        <div class="court-img-bg-shape"></div>
        ${isMaintenance ? `
          <div class="court-maintenance-overlay" style="position: absolute; top: 12px; right: 12px; background: rgba(239, 68, 68, 0.9); color: #fff; padding: 4px 8px; border-radius: 4px; font-size: 0.75rem; font-weight: bold; z-index: 2;">
            <i class="fa-solid fa-screwdriver-wrench mr-1"></i> ${currentLang === 'en' ? 'Maintenance' : 'ปิดปรับปรุง'}
          </div>
        ` : ''}
      </div>
      <div class="court-card-body">
        <h3 class="court-card-title">${translateCourtName(court)}</h3>
        <p class="court-card-desc">${desc}</p>
        <div class="court-card-footer">
          <div class="court-card-price" style="${isMaintenance ? 'opacity: 0.5;' : ''}">
            <span class="price">฿${court.price_per_hour}</span>
            <span class="label">${currentLang === 'en' ? 'per hour' : 'ต่อชั่วโมง'}</span>
          </div>
          <button class="${btnClass}" data-id="${court.id}" ${isMaintenance ? 'disabled' : ''}>${btnText}</button>
        </div>
      </div>
    `;
    
    if (!isMaintenance) {
      card.style.cursor = 'pointer';
      card.addEventListener('click', () => {
        closeExploreCourtsModal();
        STATE.activeCourtId = court.id;
        navigateTo('timeslot');
      });
    } else {
      card.style.cursor = 'default';
    }
    grid.appendChild(card);
  });
}

// --- Search Form Handler ---
function initSearchForm() {
  const form = document.getElementById('search-form');
  form.addEventListener('submit', (e) => {
    e.preventDefault();
    const dateInput = document.getElementById('booking-date').value;
    if (!dateInput) return;

    STATE.selectedDate = dateInput;
    document.getElementById('timeslot-date-picker').value = dateInput; // Keep in sync
    
    // Choose first court as default active tab if none is selected
    if (STATE.courts.length > 0 && !STATE.activeCourtId) {
      STATE.activeCourtId = STATE.courts[0].id;
    }
    
    navigateTo('timeslot');
  });
}

// --- Court & Timeslot Selection Flow ---
function initTimeslotHandlers() {
  // Bind change event to inline date picker on timeslot page
  document.getElementById('timeslot-date-picker').addEventListener('change', (e) => {
    const newDate = e.target.value;
    if (newDate) {
      STATE.selectedDate = newDate;
      document.getElementById('booking-date').value = newDate; // Keep search date in sync
      loadCourtsAvailability();
    }
  });

  // Clear selection button
  document.getElementById('clear-selection-btn').addEventListener('click', () => {
    STATE.selectedSlots = [];
    refreshTimeGridSelection();
    updateSelectionSummary();
  });

  // Confirm booking button
  document.getElementById('confirm-selection-btn').addEventListener('click', () => {
    if (STATE.selectedSlots.length === 0) {
      showNotification('Please select at least one timeslot.', 'error');
      return;
    }

    // Prepare active booking draft
    const court = STATE.courts.find(c => c.id === STATE.activeCourtId);
    const startHour = STATE.selectedSlots[0];
    const endHour = calculateEndHour(STATE.selectedSlots);
    const duration = STATE.selectedSlots.length;
    const price = court.price_per_hour * duration;

    STATE.activeBooking = {
      court_id: court.id,
      court_name: court.name,
      date: STATE.selectedDate,
      start_time: `${startHour}:00`,
      end_time: `${endHour}:00`,
      price: price,
      rate: court.price_per_hour,
      duration: duration
    };

    STATE.appliedPromo = null; // Clear old promos
    document.getElementById('promo-input').value = '';

    STATE.inBookingFlow = true;
    STATE.isResumedPayment = false;

    if (!STATE.token) {
      showNotification('Sign in required to complete booking.', 'error');
      navigateTo('auth');
    } else {
      createPendingBooking();
    }
  });
}

function createPendingBooking() {
  const b = STATE.activeBooking;
  if (!b) return;

  const confirmBtn = document.getElementById('confirm-selection-btn');
  const originalHTML = confirmBtn.innerHTML;
  confirmBtn.disabled = true;
  confirmBtn.innerHTML = `<i class="fa-solid fa-circle-notch fa-spin mr-2"></i> ${t('Securing timeslot...')}`;

  fetch('/api/bookings', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${STATE.token}`
    },
    body: JSON.stringify({
      court_id: b.court_id,
      date: b.date,
      start_time: b.start_time,
      end_time: b.end_time
    })
  })
  .then(res => res.json().then(data => ({ ok: res.ok, data })))
  .then(({ ok, data }) => {
    confirmBtn.disabled = false;
    confirmBtn.innerHTML = originalHTML;

    if (ok && data.id) {
      STATE.activeBooking = {
        ...STATE.activeBooking,
        ...data
      };
      STATE.inBookingFlow = false;
      
      if (data._cashBooking) {
        showNotification('Booking confirmed! Cash payment recorded.', 'success');
        renderETicket(data);
        navigateTo('confirmation');
      } else {
        updateReviewDetails();
        navigateTo('review');
      }
    } else {
      showNotification(data.message || 'Unable to secure slot. It might have been booked by someone else.', 'error');
      STATE.activeBooking = null;
      STATE.selectedSlots = [];
      refreshTimeGridSelection();
      updateSelectionSummary();
      navigateTo('timeslot');
    }
  })
  .catch(() => {
    confirmBtn.disabled = false;
    confirmBtn.innerHTML = originalHTML;
    showNotification('Error securing timeslot. Please try again.', 'error');
  });
}

function calculateEndHour(slots) {
  // slots are sorted like ["08", "09"]
  const lastStart = parseInt(slots[slots.length - 1]);
  const end = lastStart + 1;
  return end < 10 ? `0${end}` : `${end}`;
}

function loadCourtsAvailability() {
  if (!STATE.selectedDate) return;

  fetch(`/api/courts?date=${STATE.selectedDate}`)
  .then(res => res.json())
  .then(courts => {
    STATE.courts = courts;
    STATE.selectedSlots = [];
    updateSelectionSummary();

    // Render Court Selector Tabs
    const tabsContainer = document.getElementById('court-selector-tabs');
    tabsContainer.innerHTML = '';

    if (!STATE.activeCourtId && courts.length > 0) {
      STATE.activeCourtId = courts[0].id;
    }

    courts.forEach(court => {
      const tab = document.createElement('button');
      if (court.is_maintenance) {
        tab.className = `court-tab-btn maintenance-tab ${court.id === STATE.activeCourtId ? 'active' : ''}`;
        tab.innerHTML = `<i class="fa-solid fa-screwdriver-wrench mr-1"></i> ${translateCourtName(court)}`;
      } else {
        tab.className = `court-tab-btn ${court.id === STATE.activeCourtId ? 'active' : ''}`;
        tab.textContent = translateCourtName(court);
      }
      tab.addEventListener('click', () => {
        STATE.activeCourtId = court.id;
        STATE.selectedSlots = [];
        loadCourtsAvailability();
      });
      tabsContainer.appendChild(tab);
    });

    // Render Active Court Info Sidebar
    const activeCourt = courts.find(c => c.id === STATE.activeCourtId);
    if (activeCourt) {
      document.getElementById('detail-court-name').textContent = translateCourtName(activeCourt);
      
      let desc = translateCourtDesc(activeCourt);
      document.getElementById('detail-court-desc').textContent = desc;
      document.getElementById('detail-court-price').textContent = `฿${activeCourt.price_per_hour}`;
    }

    // Render Timeslot Grid
    renderTimeGrid(activeCourt);
  })
  .catch(() => showNotification('Error loading court availability.', 'error'));
}

function refreshTimeGridSelection() {
  const gridSlots = document.querySelectorAll('#time-grid-slots .time-slot');
  gridSlots.forEach(slotDiv => {
    if (slotDiv.classList.contains('booked')) return;
    
    const timeText = slotDiv.querySelector('.time').textContent;
    const startHourStr = timeText.split(':')[0];
    
    const isSelected = STATE.selectedSlots.includes(startHourStr);
    if (isSelected) {
      slotDiv.classList.add('selected');
      slotDiv.querySelector('.status-lbl').textContent = currentLang === 'en' ? 'Selected' : 'เลือกอยู่';
    } else {
      slotDiv.classList.remove('selected');
      slotDiv.querySelector('.status-lbl').textContent = currentLang === 'en' ? 'Available' : 'ว่าง';
    }
  });
}

function switchToAvailableCourt() {
  const availableCourt = STATE.courts.find(c => !c.is_maintenance);
  if (availableCourt) {
    STATE.activeCourtId = availableCourt.id;
    STATE.selectedSlots = [];
    loadCourtsAvailability();
  } else {
    showNotification(currentLang === 'en' ? 'No other courts available.' : 'ไม่มีสนามอื่นที่พร้อมให้บริการในขณะนี้', 'info');
  }
}
window.switchToAvailableCourt = switchToAvailableCourt;

function renderTimeGrid(court) {
  const grid = document.getElementById('time-grid-slots');
  grid.innerHTML = '';

  if (!court) return;

  if (court.is_maintenance) {
    grid.innerHTML = `
      <div class="maintenance-container">
        <div class="maintenance-card-premium">
          <div class="maintenance-icon-wrapper">
            <i class="fa-solid fa-screwdriver-wrench"></i>
          </div>
          <h4 class="maintenance-title-premium">${currentLang === 'en' ? 'Court Closed for Maintenance' : 'สนามปิดปรับปรุงชั่วคราว'}</h4>
          <p class="maintenance-desc-premium">
            ${currentLang === 'en' 
              ? 'This court is currently undergoing scheduled maintenance to improve your experience. We apologize for the inconvenience.' 
              : 'สนามนี้กำลังอยู่ระหว่างการปิดปรับปรุงระบบตามรอบการบำรุงรักษาเพื่อความปลอดภัย ขออภัยในความไม่สะดวก'}
          </p>
          <div class="maintenance-action-buttons">
            <button class="btn btn-primary btn-sm" onclick="switchToAvailableCourt()">
              <i class="fa-solid fa-circle-chevron-right mr-1"></i>
              ${currentLang === 'en' ? 'Switch to Available Court' : 'สลับไปยังสนามอื่นที่ว่าง'}
            </button>
          </div>
        </div>
      </div>
    `;
    return;
  }

  // Render slots from 06:00 to 22:00
  for (let hour = 6; hour < 22; hour++) {
    const startStr = hour < 10 ? `0${hour}:00` : `${hour}:00`;
    const nextHour = hour + 1;
    const endStr = nextHour < 10 ? `0${nextHour}:00` : `${nextHour}:00`;
    const slotKey = hour < 10 ? `0${hour}` : `${hour}`;

    const isBooked = court.booked_slots.some(b => {
      const bStart = parseInt(b.start.split(':')[0]);
      const bEnd = parseInt(b.end.split(':')[0]);
      return hour >= bStart && hour < bEnd;
    });

    const now = new Date();
    const slotDateTime = new Date(`${STATE.selectedDate}T${startStr}`);
    const isExpired = slotDateTime < now;

    const isSelected = STATE.selectedSlots.includes(slotKey);

    const slotDiv = document.createElement('div');
    if (isBooked) {
      slotDiv.className = 'time-slot booked';
    } else if (isExpired) {
      slotDiv.className = 'time-slot expired';
    } else {
      slotDiv.className = `time-slot ${isSelected ? 'selected' : ''}`;
    }

    let statusText = currentLang === 'en' ? 'Available' : 'ว่าง';
    if (isBooked) {
      statusText = currentLang === 'en' ? 'Unavailable' : 'ไม่ว่าง';
    } else if (isExpired) {
      statusText = currentLang === 'en' ? 'Expired' : 'หมดเวลา';
    } else if (isSelected) {
      statusText = currentLang === 'en' ? 'Selected' : 'เลือกอยู่';
    }

    slotDiv.innerHTML = `
      <span class="time">${startStr} - ${endStr}</span>
      <span class="status-lbl">${statusText}</span>
    `;

    if (!isBooked && !isExpired) {
      slotDiv.addEventListener('click', () => handleSlotClick(slotKey, slotDiv));
    }

    grid.appendChild(slotDiv);
  }
}

function handleSlotClick(slotKey, element) {
  const clickHour = parseInt(slotKey, 10);
  const activeCourt = STATE.courts.find(c => c.id === STATE.activeCourtId);

  // Helper to format hour integer as key string e.g. 7 -> "07", 12 -> "12"
  const getSlotStr = h => h < 10 ? `0${h}` : `${h}`;

  // Helper to check if a specific hour block is booked/unavailable
  const isSlotBooked = h => {
    return activeCourt.booked_slots.some(b => {
      const bStart = parseInt(b.start.split(':')[0], 10);
      const bEnd = parseInt(b.end.split(':')[0], 10);
      return h >= bStart && h < bEnd;
    });
  };

  const isSlotExpired = h => {
    const startStr = h < 10 ? `0${h}:00` : `${h}:00`;
    const slotDateTime = new Date(`${STATE.selectedDate}T${startStr}`);
    return slotDateTime < new Date();
  };

  // Helper to get range keys if all slots in the range [start, end] (inclusive) are available
  const getAvailableRange = (start, end) => {
    const minH = Math.min(start, end);
    const maxH = Math.max(start, end);
    const rangeKeys = [];
    for (let h = minH; h <= maxH; h++) {
      if (isSlotBooked(h) || isSlotExpired(h)) {
        return null; // Contains booked or expired slot
      }
      rangeKeys.push(getSlotStr(h));
    }
    return rangeKeys.sort();
  };

  // Get current sorted selected hours as integers
  const selectedHours = STATE.selectedSlots.map(s => parseInt(s, 10)).sort((a, b) => a - b);

  if (selectedHours.length === 0) {
    // Case 1: Nothing selected yet. Select click slot if not booked.
    if (isSlotBooked(clickHour)) {
      showNotification('This slot is unavailable.', 'error');
      return;
    }
    STATE.selectedSlots = [slotKey];
  } else if (selectedHours.length === 1) {
    // Case 2: Exactly 1 slot selected.
    const soleHour = selectedHours[0];
    if (clickHour === soleHour) {
      STATE.selectedSlots = [];
    } else {
      const range = getAvailableRange(soleHour, clickHour);
      if (range) {
        STATE.selectedSlots = range;
      } else {
        showNotification('Cannot select range: contains unavailable timeslots in between.', 'error');
        if (!isSlotBooked(clickHour)) {
          STATE.selectedSlots = [slotKey];
        }
      }
    }
  } else {
    // Case 3: A range of slots is already selected (> 1).
    const minHour = selectedHours[0];
    const maxHour = selectedHours[selectedHours.length - 1];

    if (clickHour === maxHour + 1) {
      // Case 3a: Expand range to the right
      const range = getAvailableRange(minHour, clickHour);
      if (range) {
        STATE.selectedSlots = range;
      } else {
        showNotification('Cannot expand range: next slot is unavailable.', 'error');
      }
    } else if (clickHour === minHour - 1) {
      // Case 3b: Expand range to the left
      const range = getAvailableRange(clickHour, maxHour);
      if (range) {
        STATE.selectedSlots = range;
      } else {
        showNotification('Cannot expand range: preceding slot is unavailable.', 'error');
      }
    } else if (clickHour === maxHour) {
      // Case 3c: Shrink range from the right (remove the end slot)
      const newRange = [];
      for (let h = minHour; h < maxHour; h++) {
        newRange.push(getSlotStr(h));
      }
      STATE.selectedSlots = newRange.sort();
    } else if (clickHour === minHour) {
      // Case 3d: Shrink range from the left (remove the start slot)
      const newRange = [];
      for (let h = minHour + 1; h <= maxHour; h++) {
        newRange.push(getSlotStr(h));
      }
      STATE.selectedSlots = newRange.sort();
    } else if (clickHour > minHour && clickHour < maxHour) {
      // Case 3e: Clicked inside the range. Shrink to [minHour, ..., clickHour]
      const newRange = [];
      for (let h = minHour; h <= clickHour; h++) {
        newRange.push(getSlotStr(h));
      }
      STATE.selectedSlots = newRange.sort();
    } else {
      // Case 3f: Clicked outside the range entirely. Reset range to this single slot.
      if (isSlotBooked(clickHour)) {
        showNotification('This slot is unavailable.', 'error');
        return;
      }
      STATE.selectedSlots = [slotKey];
    }
  }

  refreshTimeGridSelection();
  updateSelectionSummary();
}

function updateSelectionSummary() {
  const panel = document.getElementById('selection-summary');
  if (STATE.selectedSlots.length === 0) {
    panel.style.display = 'none';
    return;
  }

  panel.style.display = 'flex';
  const startHour = STATE.selectedSlots[0];
  const endHour = calculateEndHour(STATE.selectedSlots);
  const duration = STATE.selectedSlots.length;
  const activeCourt = STATE.courts.find(c => c.id === STATE.activeCourtId);
  const total = activeCourt.price_per_hour * duration;

  document.getElementById('sum-date').textContent = formatDateDMY(STATE.selectedDate);
  document.getElementById('sum-time').textContent = currentLang === 'en'
    ? `${startHour}:00 to ${endHour}:00`
    : `${startHour}:00 ถึง ${endHour}:00`;
  document.getElementById('sum-duration').textContent = currentLang === 'en'
    ? `${duration} hour${duration > 1 ? 's' : ''}`
    : `${duration} ชั่วโมง`;
  document.getElementById('sum-total-price').textContent = `฿${total.toLocaleString()}`;
}

// --- Review Booking Flow (Promo / Promo card calculations) ---
function initPromoHandlers() {
  // Apply Promo
  document.getElementById('promo-apply-btn').addEventListener('click', () => {
    const code = document.getElementById('promo-input').value.trim().toUpperCase();
    if (!code) return;

    const b = STATE.activeBooking;
    if (!b) {
      showNotification('No active booking to apply promo to.', 'error');
      return;
    }

    const applyBtn = document.getElementById('promo-apply-btn');
    const origText = applyBtn.textContent;
    applyBtn.disabled = true;
    applyBtn.textContent = t('Applying...');

    fetch('/api/promo/validate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ code, price: b.price })
    })
    .then(res => {
      if (!res.ok) {
        return res.json().then(err => { throw new Error(err.message || 'Invalid coupon or promo code.'); });
      }
      return res.json();
    })
    .then(data => {
      applyBtn.disabled = false;
      applyBtn.textContent = origText;

      if (data.isValid) {
        // Calculate dynamic discount ratio for compatibility
        const discountFraction = b.price > 0 ? (b.price - data.price) / b.price : 0;

        STATE.appliedPromo = {
          code: code,
          price: data.price,
          discount: discountFraction
        };
        showNotification(data.message ? t(data.message) : t('Promo code applied successfully!'), 'success');
        updateReviewDetails();
      } else {
        showNotification(data.message ? t(data.message) : t('Invalid coupon or promo code.'), 'error');
      }
    })
    .catch(err => {
      applyBtn.disabled = false;
      applyBtn.textContent = origText;
      showNotification(err.message ? t(err.message) : t('Invalid coupon or promo code.'), 'error');
    });
  });

  // Cancel and Back button on Review page
  const reviewCancelBtn = document.getElementById('review-cancel-btn');
  if (reviewCancelBtn) {
    reviewCancelBtn.addEventListener('click', () => {
      if (STATE.activeBooking && STATE.activeBooking.id) {
        const cancelId = STATE.activeBooking.id;
        reviewCancelBtn.disabled = true;
        reviewCancelBtn.innerHTML = `<i class="fa-solid fa-circle-notch fa-spin mr-1"></i> ${t('Cancelling...')}`;
        
        fetch(`/api/bookings/${cancelId}/cancel`, {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${STATE.token}`
          }
        })
        .then(() => {
          STATE.activeBooking = null;
          STATE.inBookingFlow = false;
          loadCourtsAvailability();
          navigateTo('timeslot');
          reviewCancelBtn.disabled = false;
          reviewCancelBtn.innerHTML = t('btn-cancel-back');
        })
        .catch(err => {
          console.error('Error cancelling pending booking:', err);
          STATE.activeBooking = null;
          STATE.inBookingFlow = false;
          navigateTo('timeslot');
          reviewCancelBtn.disabled = false;
          reviewCancelBtn.innerHTML = t('btn-cancel-back');
        });
      } else {
        navigateTo('timeslot');
      }
    });
  }

  // Proceed to payment gateway (or instant cash booking for mod/admin)
  document.getElementById('proceed-to-payment-btn').addEventListener('click', () => {
    if (!STATE.activeBooking) return;

    // mod / admin → instant cash booking, no payment page
    const role = STATE.user && STATE.user.role;
    if (role === 'mod' || role === 'admin') {
      const btn = document.getElementById('proceed-to-payment-btn');
      const origHTML = btn.innerHTML;
      btn.disabled = true;
      btn.innerHTML = `<i class="fa-solid fa-circle-notch fa-spin mr-2"></i> ${t('Booking...')}`;

      const b = STATE.activeBooking;
      fetch('/api/bookings', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${STATE.token}`
        },
        body: JSON.stringify({
          court_id: b.court_id,
          date: b.date,
          start_time: b.start_time,
          end_time: b.end_time,
          promo_code: STATE.appliedPromo ? STATE.appliedPromo.code : null
        })
      })
      .then(res => res.json())
      .then(bookingData => {
        btn.disabled = false;
        btn.innerHTML = origHTML;
        if (bookingData.id) {
          showNotification('Booking confirmed! Cash payment recorded.', 'success');
          renderETicket(bookingData);
          navigateTo('confirmation');
        } else {
          showNotification(bookingData.message || 'Unable to secure slot.', 'error');
        }
      })
      .catch(() => {
        btn.disabled = false;
        btn.innerHTML = origHTML;
        showNotification('Error communicating with server', 'error');
      });
      return;
    }

    // Regular user → go to payment page
    let finalPrice = STATE.activeBooking.price;
    if (STATE.appliedPromo) {
      if (STATE.appliedPromo.price !== undefined) {
        finalPrice = STATE.appliedPromo.price;
      } else {
        finalPrice = finalPrice - (finalPrice * STATE.appliedPromo.discount);
      }
    }

    if (finalPrice === 0) {
      submitBookingToDatabase();
      return;
    }

    // Update payment displays
    document.getElementById('payment-amount-display').textContent = `฿${finalPrice.toLocaleString()}`;

    navigateTo('payment');
    togglePaymentMethod('qr');
  });
}

function updateReviewDetails() {
  const b = STATE.activeBooking;
  if (!b) return;

  document.getElementById('rev-court-name').textContent = b.court_name;
  document.getElementById('rev-date').textContent = formatDateDMY(b.date);
  document.getElementById('rev-time').textContent = currentLang === 'en'
    ? `${b.start_time} - ${b.end_time} (${b.duration} hrs)`
    : `${b.start_time} - ${b.end_time} (${b.duration} ชม.)`;
  document.getElementById('rev-rate').textContent = currentLang === 'en'
    ? `฿${b.rate}/hr`
    : `฿${b.rate}/ชม.`;

  let finalPrice = b.price;
  if (STATE.appliedPromo) {
    if (STATE.appliedPromo.price !== undefined) {
      finalPrice = STATE.appliedPromo.price;
    } else {
      finalPrice = finalPrice - (finalPrice * STATE.appliedPromo.discount);
    }
  }
  document.getElementById('rev-total').textContent = `฿${finalPrice.toLocaleString()}`;

  const proceedBtn = document.getElementById('proceed-to-payment-btn');
  if (proceedBtn) {
    if (finalPrice === 0) {
      proceedBtn.innerHTML = currentLang === 'en'
        ? 'Confirm Booking <i class="fa-solid fa-circle-check ml-1"></i>'
        : 'ยืนยันการจอง <i class="fa-solid fa-circle-check ml-1"></i>';
    } else {
      proceedBtn.innerHTML = currentLang === 'en'
        ? 'Proceed to Payment <i class="fa-solid fa-credit-card ml-1"></i>'
        : 'ดำเนินการชำระเงิน <i class="fa-solid fa-credit-card ml-1"></i>';
    }
  }
}

// Intercept review navigation to update values on entry
const originalNav = navigateTo;
navigateTo = function(viewId) {
  if (viewId === 'review') {
    updateReviewDetails();
  }
  originalNav.apply(this, arguments);
};

// --- Payment Handlers ---
let countdownTimer;

function crc16(data) {
  let crc = 0xffff;
  for (let i = 0; i < data.length; i++) {
    let x = ((crc >> 8) ^ data.charCodeAt(i)) & 0xff;
    x ^= x >> 4;
    crc = ((crc << 8) ^ (x << 12) ^ (x << 5) ^ x) & 0xffff;
  }
  return crc.toString(16).toUpperCase().padStart(4, '0');
}

function generatePromptPayPayload(target, amount) {
  const sanitized = target.replace(/\D/g, '');
  
  const aid = 'A000000677010111';
  let merchantInfo = '0016' + aid;
  
  if (sanitized.length === 13) {
    // National ID / Tax ID (tag 02, length 13)
    merchantInfo += '0213' + sanitized;
  } else {
    // Mobile Number (tag 01, length 13, prefixed with 66 instead of leading 0)
    let mobile = sanitized;
    if (mobile.length === 10 && mobile.startsWith('0')) {
      mobile = '66' + mobile.substring(1);
    }
    merchantInfo += '0113' + mobile.padStart(13, '0');
  }
  
  let payload = '000201' +
                (amount ? '010212' : '010211') +
                '29' + merchantInfo.length.toString().padStart(2, '0') + merchantInfo +
                '5802TH' +
                '5303764';
                
  if (amount) {
    const amountStr = parseFloat(amount).toFixed(2);
    payload += '54' + amountStr.length.toString().padStart(2, '0') + amountStr;
  }
  
  payload += '6304';
  
  const checksum = crc16(payload);
  return payload + checksum;
}

function initPaymentHandlers() {
  // Card input listeners to update preview card in real-time
  const cardNum = document.getElementById('card-number');
  const cardName = document.getElementById('card-name');
  const cardExpiry = document.getElementById('card-expiry');

  cardNum.addEventListener('input', (e) => {
    let val = e.target.value.replace(/\D/g, '');
    val = val.replace(/(.{4})/g, '$1 ').trim();
    e.target.value = val;
    document.getElementById('card-num-preview').textContent = val || '•••• •••• •••• ••••';
  });

  cardExpiry.addEventListener('input', (e) => {
    let val = e.target.value.replace(/\D/g, '');
    if (val.length > 2) {
      val = `${val.substring(0,2)}/${val.substring(2,4)}`;
    }
    e.target.value = val;
    document.getElementById('card-expiry-preview').textContent = val || 'MM/YY';
  });

  cardName.addEventListener('input', (e) => {
    document.getElementById('card-name-preview').textContent = e.target.value.toUpperCase() || 'YOUR NAME';
  });

  // Slip upload handlers
  const slipInput = document.getElementById('slip-file-input');
  const slipUploadLabel = document.getElementById('slip-upload-label');
  const slipPreviewContainer = document.getElementById('slip-preview-container');
  const slipPreviewImg = document.getElementById('slip-preview-img');
  const removeSlipBtn = document.getElementById('remove-slip-btn');

  function processAndValidateSlip(file) {
    if (!file.type.startsWith('image/')) {
      showNotification('Please select an image file.', 'error');
      slipInput.value = '';
      return;
    }
    
    showNotification('Analyzing slip image...', 'info');
    
    const reader = new FileReader();
    reader.onload = (event) => {
      const base64Data = event.target.result;
      
      // Client-side QR Code verification using jsQR
      const img = new Image();
      img.onload = () => {
        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');
        canvas.width = img.width;
        canvas.height = img.height;
        ctx.drawImage(img, 0, 0);
        
        try {
          const imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
          const decodedQR = jsQR(imageData.data, imageData.width, imageData.height);
          
          if (!decodedQR || !decodedQR.data) {
            showNotification('No valid QR code detected. Please ensure you upload a clear PromptPay transfer slip to save API quota.', 'error');
            slipInput.value = '';
            STATE.slipImage = null;
            slipPreviewImg.src = '';
            slipPreviewContainer.style.display = 'none';
            slipUploadLabel.style.display = 'flex';
            return;
          }
          
          // Valid QR code found! Save slip image state.
          STATE.slipImage = base64Data;
          slipPreviewImg.src = base64Data;
          slipUploadLabel.style.display = 'none';
          slipPreviewContainer.style.display = 'flex';
          showNotification('Slip QR code detected successfully.', 'success');
        } catch (err) {
          console.error('Error decoding QR code on client side:', err);
          // Fallback: if browser sandbox/canvas context fails, allow server validation
          STATE.slipImage = base64Data;
          slipPreviewImg.src = base64Data;
          slipUploadLabel.style.display = 'none';
          slipPreviewContainer.style.display = 'flex';
        }
      };
      img.src = base64Data;
    };
    reader.readAsDataURL(file);
  }

  slipInput.addEventListener('change', (e) => {
    const file = e.target.files[0];
    if (file) {
      processAndValidateSlip(file);
    }
  });

  removeSlipBtn.addEventListener('click', () => {
    slipInput.value = '';
    STATE.slipImage = null;
    slipPreviewImg.src = '';
    slipPreviewContainer.style.display = 'none';
    slipUploadLabel.style.display = 'flex';
  });

  // Drag & drop file upload support
  ['dragenter', 'dragover'].forEach(eventName => {
    slipUploadLabel.addEventListener(eventName, (e) => {
      e.preventDefault();
      e.stopPropagation();
      slipUploadLabel.style.borderColor = 'var(--neon-green)';
      slipUploadLabel.style.background = 'var(--neon-green-alpha)';
    }, false);
  });

  ['dragleave', 'drop'].forEach(eventName => {
    slipUploadLabel.addEventListener(eventName, (e) => {
      e.preventDefault();
      e.stopPropagation();
      slipUploadLabel.style.borderColor = '';
      slipUploadLabel.style.background = '';
    }, false);
  });

  slipUploadLabel.addEventListener('drop', (e) => {
    const dt = e.dataTransfer;
    const file = dt.files[0];
    if (file) {
      processAndValidateSlip(file);
    }
  });

  // Test mode toggle handler
  const testModeToggle = document.getElementById('test-mode-toggle');
  if (testModeToggle) {
    testModeToggle.addEventListener('change', () => {
      let finalPrice = STATE.activeBooking ? STATE.activeBooking.price : 0;
      if (STATE.appliedPromo && STATE.activeBooking) {
        if (STATE.appliedPromo.price !== undefined) {
          finalPrice = STATE.appliedPromo.price;
        } else {
          finalPrice = finalPrice - (finalPrice * STATE.appliedPromo.discount);
        }
      }
      
      if (testModeToggle.checked) {
        finalPrice = 0.1;
      }
      
      document.getElementById('payment-amount-display').textContent = `฿${finalPrice.toFixed(2)}`;
      
      // Regenerate QR Code / visibility based on finalPrice
      const receiver = STATE.config.prompayReceiverId || '0917291840';
      const qrImg = document.getElementById('qr-code-img');
      const qrPlaceholderIcon = document.getElementById('qr-code-placeholder-icon');
      const slipUploadBox = document.getElementById('slip-upload-label');
      const qrCountdownBox = document.querySelector('.qr-timer');
      const qrInstruction = document.querySelector('.qr-info-text');

      if (finalPrice > 0) {
        const payload = generatePromptPayPayload(receiver, finalPrice);
        qrImg.src = `https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=${encodeURIComponent(payload)}`;
        qrImg.style.display = 'block';
        qrPlaceholderIcon.style.display = 'none';
        if (slipUploadBox) slipUploadBox.style.display = 'flex';
        if (qrCountdownBox) qrCountdownBox.style.display = 'block';
        if (qrInstruction) qrInstruction.textContent = 'Scan this PromptPay QR Code with any banking app to pay';
      } else {
        qrImg.style.display = 'none';
        qrPlaceholderIcon.style.display = 'block';
        if (slipUploadBox) slipUploadBox.style.display = 'none';
        if (qrCountdownBox) qrCountdownBox.style.display = 'none';
        if (qrInstruction) qrInstruction.textContent = 'Your booking is completely free! Click "Pay Now" below to confirm.';
      }
    });
  }

  // Submit payment action
  document.getElementById('pay-submit-btn').addEventListener('click', () => {
    submitBookingToDatabase();
  });
}

function togglePaymentMethod(method) {
  const tabCard = document.getElementById('pay-tab-card');
  const tabQr = document.getElementById('pay-tab-qr');
  const formCard = document.getElementById('payment-card-form');
  const formQr = document.getElementById('payment-qr-form');

  // Stop previous QR timer
  clearInterval(countdownTimer);

  if (method === 'card') {
    tabCard.classList.add('active');
    tabQr.classList.remove('active');
    formCard.classList.add('active-form');
    formQr.classList.remove('active-form');
  } else {
    tabCard.classList.remove('active');
    tabQr.classList.add('active');
    formCard.classList.remove('active-form');
    formQr.classList.add('active-form');
    
    // Generate and display dynamic PromptPay QR code
    const receiver = STATE.config.prompayReceiverId || '0917291840';
    
    // Update PromptPay display text
    let formattedReceiver = receiver;
    if (receiver.length === 10) {
      formattedReceiver = receiver.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
    }
    const receiverDisplay = document.getElementById('prompay-receiver-display');
    if (receiverDisplay) {
      receiverDisplay.textContent = formattedReceiver;
    }

    let finalPrice = STATE.activeBooking ? STATE.activeBooking.price : 0;
    if (STATE.appliedPromo && STATE.activeBooking) {
      if (STATE.appliedPromo.price !== undefined) {
        finalPrice = STATE.appliedPromo.price;
      } else {
        finalPrice = finalPrice - (finalPrice * STATE.appliedPromo.discount);
      }
    }
    
    const testModeToggle = document.getElementById('test-mode-toggle');
    if (testModeToggle && testModeToggle.checked) {
      finalPrice = 0.1;
    }
    
    const qrImg = document.getElementById('qr-code-img');
    const qrPlaceholderIcon = document.getElementById('qr-code-placeholder-icon');
    const slipUploadBox = document.getElementById('slip-upload-label');
    const qrCountdownBox = document.querySelector('.qr-timer');
    const qrInstruction = document.querySelector('.qr-info-text');

    if (finalPrice > 0) {
      const payload = generatePromptPayPayload(receiver, finalPrice);
      qrImg.src = `https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=${encodeURIComponent(payload)}`;
      qrImg.style.display = 'block';
      qrPlaceholderIcon.style.display = 'none';
      if (slipUploadBox) slipUploadBox.style.display = 'flex';
      if (qrCountdownBox) qrCountdownBox.style.display = 'block';
      if (qrInstruction) qrInstruction.textContent = 'Scan this PromptPay QR Code with any banking app to pay';
      startQrCountdown();
    } else {
      qrImg.style.display = 'none';
      qrPlaceholderIcon.style.display = 'block';
      if (slipUploadBox) slipUploadBox.style.display = 'none';
      if (qrCountdownBox) qrCountdownBox.style.display = 'none';
      if (qrInstruction) qrInstruction.textContent = 'Your booking is completely free! Click "Pay Now" below to confirm.';
      
      const display = document.getElementById('qr-countdown');
      if (display) display.textContent = '--:--';
    }
  }
}

function startQrCountdown() {
  let time = 299; // 5 mins
  const display = document.getElementById('qr-countdown');
  
  countdownTimer = setInterval(() => {
    const mins = Math.floor(time / 60);
    const secs = time % 60;
    display.textContent = `0${mins}:${secs < 10 ? '0' : ''}${secs}`;
    
    if (--time < 0) {
      clearInterval(countdownTimer);
      showNotification('QR Code expired. Generating a new one...', 'error');
      startQrCountdown();
    }
  }, 1000);
}

function submitBookingToDatabase() {
  const b = STATE.activeBooking;
  if (!b || !STATE.token) return;

  let finalPrice = b.price;
  if (STATE.appliedPromo) {
    if (STATE.appliedPromo.price !== undefined) {
      finalPrice = STATE.appliedPromo.price;
    } else {
      finalPrice = finalPrice - (finalPrice * STATE.appliedPromo.discount);
    }
  }

  const testModeToggle = document.getElementById('test-mode-toggle');
  if (testModeToggle && testModeToggle.checked) {
    finalPrice = 0.1;
  }

  const method = document.getElementById('pay-tab-card').classList.contains('active') ? 'Credit Card' : 'PromptPay QR';

  if (finalPrice > 0 && method === 'PromptPay QR' && !STATE.slipImage) {
    showNotification('Please upload your payment slip first.', 'error');
    return;
  }

  // Show loading state
  const payBtn = (STATE.currentView === 'review-view') ? document.getElementById('proceed-to-payment-btn') : document.getElementById('pay-submit-btn');
  const originalBtnHTML = payBtn.innerHTML;
  payBtn.disabled = true;
  payBtn.innerHTML = finalPrice > 0 
    ? `<i class="fa-solid fa-circle-notch fa-spin mr-2"></i> ${t('Verifying Payment...')}` 
    : `<i class="fa-solid fa-circle-notch fa-spin mr-2"></i> ${t('Confirming Booking...')}`;

  // Perform Payment processing on that Booking
  showNotification(finalPrice > 0 ? 'Verifying payment details...' : 'Confirming booking details...', 'info');

  fetch(`/api/payment/${b.id}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${STATE.token}`
    },
    body: JSON.stringify({
      payment_method: method,
      slip_image: finalPrice > 0 ? STATE.slipImage : null,
      promo_code: STATE.appliedPromo ? STATE.appliedPromo.code : null
    })
  })
  .then(payRes => {
    return payRes.json().then(data => {
      if (!payRes.ok) {
        throw new Error(data.message || 'Verification failed');
      }
      return data;
    });
  })
  .then(paymentResult => {
    // Reset loading state
    payBtn.disabled = false;
    payBtn.innerHTML = originalBtnHTML;

    if (paymentResult.booking) {
      // Clear active booking state and display E-ticket
      clearInterval(countdownTimer);
      showNotification(finalPrice > 0 ? 'Payment verified successfully!' : 'Booking confirmed successfully!', 'success');

      // Clear uploaded slip state
      STATE.slipImage = null;
      document.getElementById('slip-file-input').value = '';
      document.getElementById('slip-preview-container').style.display = 'none';
      document.getElementById('slip-upload-label').style.display = 'flex';

      renderETicket(paymentResult.booking);
      navigateTo('confirmation');
    } else {
      showNotification(paymentResult.message || 'Payment verification failed', 'error');
    }
  })
  .catch((err) => {
    payBtn.disabled = false;
    payBtn.innerHTML = originalBtnHTML;
    showNotification(err.message || 'Error verifying payment', 'error');
  });
}

function renderETicket(booking) {
  document.getElementById('ticket-court').textContent = booking.court_name;
  document.getElementById('ticket-date').textContent = formatDateDMY(booking.date);
  document.getElementById('ticket-time').textContent = `${booking.start_time} - ${booking.end_time}`;
  
  const isPaid = booking.payment_status === 'completed';
  const pinEl = document.getElementById('ticket-pin');
  const barcodeEl = document.getElementById('ticket-barcode');
  
  if (isPaid) {
    const pin = booking.pin_code;
    const formattedPin = `${pin.substring(0,3)} ${pin.substring(3,6)}`;
    pinEl.textContent = formattedPin;
    pinEl.style.fontSize = '2.2rem';
    pinEl.style.letterSpacing = 'normal';
    
    // Check if booking has expired (end_time passed or marked completed/expired on server)
    const bookingEndStr = `${booking.date}T${booking.end_time}:00+07:00`;
    const isExpired = booking.status === 'completed' || booking.status === 'expired' || (new Date().getTime() >= new Date(bookingEndStr).getTime());
    
    if (booking.court_is_maintenance) {
      pinEl.style.color = '#ef4444';
      pinEl.style.textDecoration = 'line-through';
      document.querySelector('.pin-instruction').innerHTML = currentLang === 'th'
        ? `<i class="fa-solid fa-triangle-exclamation mr-1" style="color: #ef4444;"></i> สนามปิดปรับปรุงชั่วคราวในช่วงเวลานี้ กรุณาติดต่อพนักงานเพื่อเลื่อนรอบหรือรับเงินคืน`
        : `<i class="fa-solid fa-triangle-exclamation mr-1" style="color: #ef4444;"></i> Court is under maintenance. Please contact staff to reschedule or request a refund.`;
      if (barcodeEl) barcodeEl.style.display = 'none';
    } else if (isExpired) {
      pinEl.style.color = '#64748b';
      pinEl.style.textDecoration = 'line-through';
      document.querySelector('.pin-instruction').innerHTML = currentLang === 'th'
        ? `<i class="fa-solid fa-circle-xmark mr-1 text-muted"></i> การจองนี้เสร็จสิ้นแล้ว รหัส PIN นี้หมดอายุแล้ว`
        : `<i class="fa-solid fa-circle-xmark mr-1 text-muted"></i> This booking is completed. The PIN has expired.`;
      if (barcodeEl) barcodeEl.style.display = 'none';
    } else {
      pinEl.style.color = '';
      pinEl.style.textDecoration = '';
      document.querySelector('.pin-instruction').innerHTML = currentLang === 'th'
        ? `<i class="fa-solid fa-circle-info mr-1 text-neon"></i> กรุณาพิมพ์รหัส PIN 6 หลักนี้บนหน้าจอตู้คีออสหน้าสนามเพื่อปลดล็อกระบบและไฟสนาม`
        : `<i class="fa-solid fa-circle-info mr-1 text-neon"></i> Enter this 6-digit PIN on the court kiosk screen to unlock system and lights.`;
      
      // Generate barcode from PIN
      if (typeof JsBarcode !== 'undefined' && barcodeEl) {
        try {
          JsBarcode("#ticket-barcode", pin, {
            format: "CODE128",
            lineColor: "#000000",
            width: 1.8,
            height: 38,
            displayValue: false,
            margin: 0
          });
          barcodeEl.style.display = 'block';
        } catch (err) {
          console.error('Error generating barcode:', err);
        }
      }
    }
  } else {
    pinEl.textContent = currentLang === 'th' ? 'รอการชำระเงิน' : 'PENDING PAYMENT';
    pinEl.style.fontSize = '1.4rem';
    pinEl.style.letterSpacing = '1px';
    document.querySelector('.pin-instruction').style.display = 'none';
    if (barcodeEl) {
      barcodeEl.style.display = 'none';
    }
  }
  
  document.getElementById('ticket-ref-id').textContent = `REF: AP-${booking.date.replace(/-/g, '')}-00${booking.id}`;
}

// --- Player Dashboard Management ---
function loadUserBookings() {
  if (!STATE.token) return;

  const upcomingCountEl = document.getElementById('stat-upcoming-count');
  const totalCountEl = document.getElementById('stat-total-count');
  const bookingsList = document.getElementById('dashboard-bookings-list');

  fetch('/api/bookings/my-bookings', {
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => res.json())
  .then(bookings => {
    totalCountEl.textContent = bookings.length;
    
    // Filter upcoming (date >= today)
    const todayStr = getLocalDateString();
    const upcoming = bookings.filter(b => b.date >= todayStr);
    upcomingCountEl.textContent = upcoming.length;

    if (bookings.length === 0) {
      bookingsList.innerHTML = `
        <div class="empty-state text-center py-5">
          <i class="fa-solid fa-calendar-minus text-muted mb-3 size-xl"></i>
          <p data-i18n="dashboard-empty-txt">${currentLang === 'th' ? 'คุณยังไม่มีรายการจองใดๆ' : "You don't have any bookings yet."}</p>
          <button class="btn btn-outline mt-3" onclick="navigateTo('search-view')" data-i18n="dashboard-empty-btn">${currentLang === 'th' ? 'จองสนามแรกของคุณ' : 'Book Your First Court'}</button>
        </div>
      `;
      return;
    }

    bookingsList.innerHTML = '';
    bookings.forEach(b => {
      const row = document.createElement('div');
      row.className = 'booking-item';
      
      const isPaid = b.payment_status === 'completed';
      const pin = b.pin_code;
      const formattedPin = isPaid && pin !== 'PENDING' ? `${pin.substring(0,3)} ${pin.substring(3,6)}` : (currentLang === 'th' ? 'รอตรวจสอบ' : 'Pending');

      // Check if booking has expired (end_time passed or marked completed/expired on server)
      const bookingEndStr = `${b.date}T${b.end_time}:00+07:00`;
      const isExpired = b.status === 'completed' || b.status === 'expired' || (new Date().getTime() >= new Date(bookingEndStr).getTime());
      const showMaintenanceWarning = b.court_is_maintenance && !isExpired;

      row.innerHTML = `
        <div class="booking-info-main">
          <div class="booking-court-avatar">
            <i class="fa-solid fa-baseball"></i>
          </div>
          <div class="booking-details-txt">
            <h4>${b.court_name}</h4>
            <div class="booking-meta-row">
              <span><i class="fa-regular fa-calendar mr-1"></i> ${formatDateDMY(b.date)}</span>
              <span><i class="fa-regular fa-clock mr-1"></i> ${b.start_time} - ${b.end_time}</span>
              <span>฿${b.price}</span>
            </div>
          </div>
        </div>
        <div class="booking-actions">
          ${showMaintenanceWarning
            ? `<span class="booking-status-badge badge-danger" style="background: rgba(239, 68, 68, 0.15); color: #ef4444; border: 1px solid rgba(239, 68, 68, 0.3); font-weight:600; white-space:nowrap; padding: 4px 10px; border-radius: 50px; font-size: 0.72rem; text-transform: uppercase;"><i class="fa-solid fa-screwdriver-wrench mr-1"></i> ${currentLang === 'th' ? 'สนามปิดปรับปรุง' : 'Court Maint.'}</span>`
            : ''
          }
          ${isPaid 
            ? (isExpired
                ? `<span class="badge" style="background: rgba(148, 163, 184, 0.1); color: #94a3b8; border: 1px solid rgba(148, 163, 184, 0.2);">PIN: ${formattedPin}</span>`
                : `<span class="badge badge-neon">PIN: ${formattedPin}</span>`
              )
            : ''
          }
          <span class="booking-status-badge ${isPaid ? (isExpired ? 'status-completed' : 'status-paid') : 'status-pending'}" style="${
            !isPaid 
              ? 'background: rgba(245, 158, 11, 0.15); color: #f59e0b; border: 1px solid rgba(245, 158, 11, 0.3);' 
              : (isExpired
                  ? 'background: rgba(148, 163, 184, 0.15); color: #94a3b8; border: 1px solid rgba(148, 163, 184, 0.3);'
                  : ''
                )
          }">
            ${isPaid ? (isExpired ? (currentLang === 'th' ? 'เสร็จสิ้น' : 'Completed') : (currentLang === 'th' ? 'ชำระเงินแล้ว' : 'Paid')) : (currentLang === 'th' ? 'รอดำเนินการ' : 'PENDING')}
          </span>
          ${isPaid 
            ? `<button class="btn btn-outline btn-sm btn-ticket-view">${currentLang === 'th' ? 'ดูตั๋วการจอง' : 'View Ticket'}</button>` 
            : `<button class="btn btn-danger btn-sm btn-cancel-booking" style="background: rgba(239, 68, 68, 0.15); color: #ef4444; border: 1px solid rgba(239, 68, 68, 0.3);">${currentLang === 'th' ? 'ยกเลิก' : 'Cancel'}</button>`
          }
        </div>
      `;
      
      row.style.cursor = 'pointer';
      row.addEventListener('click', () => {
        if (isPaid) {
          renderETicket(b);
          navigateTo('confirmation');
        } else {
          // Resume payment flow for pending booking
          STATE.isResumedPayment = true;
          STATE.activeBooking = b;
          document.getElementById('payment-amount-display').textContent = `฿${b.price.toLocaleString()}`;
          navigateTo('payment');
          togglePaymentMethod('qr');
        }
      });

      // Handle View Ticket button click separately
      const ticketBtn = row.querySelector('.btn-ticket-view');
      if (ticketBtn) {
        ticketBtn.addEventListener('click', (e) => {
          e.stopPropagation();
          renderETicket(b);
          navigateTo('confirmation');
        });
      }

      // Handle Cancel booking button click separately
      const cancelBtn = row.querySelector('.btn-cancel-booking');
      if (cancelBtn) {
        cancelBtn.addEventListener('click', (e) => {
          e.stopPropagation();
          if (confirm(translateConfirm('Are you sure you want to cancel this pending booking?'))) {
            cancelBtn.disabled = true;
            cancelBtn.innerHTML = currentLang === 'th' ? '<i class="fa-solid fa-circle-notch fa-spin mr-1"></i> กำลังยกเลิก...' : '<i class="fa-solid fa-circle-notch fa-spin mr-1"></i> Cancelling...';
            
            fetch(`/api/bookings/${b.id}/cancel`, {
              method: 'POST',
              headers: {
                'Authorization': `Bearer ${STATE.token}`
              }
            })
            .then(res => {
              if (res.ok) {
                showNotification(currentLang === 'th' ? 'ยกเลิกรายการจองสำเร็จแล้ว' : 'Booking cancelled successfully.', 'success');
                loadUserBookings();
              } else {
                showNotification(currentLang === 'th' ? 'ล้มเหลวในการยกเลิกการจอง กรุณาลองใหม่อีกครั้ง' : 'Failed to cancel booking. Please try again.', 'error');
                cancelBtn.disabled = false;
                cancelBtn.innerHTML = currentLang === 'th' ? 'ยกเลิก' : 'Cancel';
              }
            })
            .catch(() => {
              showNotification(currentLang === 'th' ? 'เกิดข้อผิดพลาดในการยกเลิกการจอง' : 'Error cancelling booking.', 'error');
              cancelBtn.disabled = false;
              cancelBtn.innerHTML = currentLang === 'th' ? 'ยกเลิก' : 'Cancel';
            });
          }
        });
      }

      bookingsList.appendChild(row);
    });
  })
  .catch(() => showNotification(currentLang === 'th' ? 'เกิดข้อผิดพลาดในการโหลดรายการจอง' : 'Error loading dashboard bookings.', 'error'));
}

// --- Date Formatter Helper (d/m/Y) ---
function formatDateDMY(dateStr) {
  if (!dateStr) return '';
  const parts = dateStr.split('-');
  return parts.length === 3 ? `${parts[2]}/${parts[1]}/${parts[0]}` : dateStr;
}

// Helper to slide dates left/right by an offset
function changeDateByOffset(offset) {
  if (!timeslotDatePicker) return;
  const currentDate = new Date(STATE.selectedDate);
  currentDate.setDate(currentDate.getDate() + offset);

  const minSelectable = new Date(getDefaultDate());
  minSelectable.setHours(0, 0, 0, 0);

  const maxBookingDate = new Date();
  maxBookingDate.setMonth(maxBookingDate.getMonth() + 3);
  maxBookingDate.setHours(23, 59, 59, 999);

  if (currentDate < minSelectable) {
    showNotification('Cannot select a past date', 'info');
    return;
  }
  if (currentDate > maxBookingDate) {
    showNotification('Can only book up to 3 months in advance', 'info');
    return;
  }

  const yyyy = currentDate.getFullYear();
  const mm = String(currentDate.getMonth() + 1).padStart(2, '0');
  const dd = String(currentDate.getDate()).padStart(2, '0');
  const newDateStr = `${yyyy}-${mm}-${dd}`;

  timeslotDatePicker.setDate(newDateStr, true);
}

function changeSearchDateByOffset(offset) {
  if (!bookingDatePicker) return;
  const currentDate = new Date(STATE.selectedDate);
  currentDate.setDate(currentDate.getDate() + offset);

  const minSelectable = new Date(getDefaultDate());
  minSelectable.setHours(0, 0, 0, 0);

  const maxBookingDate = new Date();
  maxBookingDate.setMonth(maxBookingDate.getMonth() + 3);
  maxBookingDate.setHours(23, 59, 59, 999);

  if (currentDate < minSelectable) {
    showNotification('Cannot select a past date', 'info');
    return;
  }
  if (currentDate > maxBookingDate) {
    showNotification('Can only book up to 3 months in advance', 'info');
    return;
  }

  const yyyy = currentDate.getFullYear();
  const mm = String(currentDate.getMonth() + 1).padStart(2, '0');
  const dd = String(currentDate.getDate()).padStart(2, '0');
  const newDateStr = `${yyyy}-${mm}-${dd}`;

  bookingDatePicker.setDate(newDateStr, true);
}
window.changeSearchDateByOffset = changeSearchDateByOffset;

// --- Admin Panel Functions ---
let activeAdminTab = 'bookings';

function switchAdminTab(tabName) {
  activeAdminTab = tabName;
  
  // Update buttons
  document.querySelectorAll('.admin-tabs button').forEach(btn => {
    btn.classList.remove('active');
  });
  const activeBtn = document.getElementById(`btn-admin-tab-${tabName}`);
  if (activeBtn) activeBtn.classList.add('active');

  // Update sections
  document.querySelectorAll('.admin-tab-content').forEach(section => {
    section.style.display = 'none';
  });
  const activeSection = document.getElementById(`admin-tab-${tabName}-content`);
  if (activeSection) activeSection.style.display = 'block';

  // Load content if needed
  if (tabName === 'bookings') {
    loadAdminBookings();
  } else if (tabName === 'courts') {
    loadAdminCourts();
  } else if (tabName === 'users') {
    loadAdminUsers();
  } else if (tabName === 'promocodes') {
    loadAdminPromoCodes();
  }
}

function loadAdminDashboard() {
  if (!STATE.token || STATE.user.role !== 'admin') {
    showNotification('Access denied.', 'error');
    navigateTo('home-view');
    return;
  }

  // Load Stats
  fetch('/api/admin/stats', {
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => {
    if (!res.ok) throw new Error();
    return res.json();
  })
  .then(stats => {
    document.getElementById('admin-stat-bookings').textContent = stats.totalBookings;
    document.getElementById('admin-stat-revenue').textContent = `฿${stats.totalRevenue.toLocaleString()}`;
    document.getElementById('admin-stat-users').textContent = stats.totalUsers;
    document.getElementById('admin-stat-courts').textContent = stats.totalCourts;
  })
  .catch(() => showNotification('Error loading admin stats', 'error'));

  // Trigger loading for currently active tab
  switchAdminTab(activeAdminTab);
}

function loadAdminBookings() {
  fetch('/api/admin/bookings', {
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => res.json())
  .then(bookings => {
    const tbody = document.getElementById('admin-bookings-table-body');
    tbody.innerHTML = '';
    
    if (bookings.length === 0) {
      tbody.innerHTML = `<tr><td colspan="9" class="text-center text-muted">${t('admin-no-bookings', 'No bookings found in database.')}</td></tr>`;
      return;
    }

    bookings.forEach(b => {
      const tr = document.createElement('tr');
      const statusClass = b.status === 'paid' ? 'status-paid' : 'status-pending';
      tr.innerHTML = `
        <td>${b.id}</td>
        <td><strong>${b.username}</strong><br><small class="text-muted">${b.email}</small></td>
        <td>${b.court_name}</td>
        <td>${formatDateDMY(b.date)}</td>
        <td>${b.start_time.substring(0, 5)} - ${b.end_time.substring(0, 5)}</td>
        <td>฿${b.price}</td>
        <td><code>${b.pin_code}</code></td>
        <td><span class="booking-status-badge ${statusClass}">${t(b.status, b.status)}</span></td>
        <td>
          <button class="btn btn-danger-sm" onclick="deleteAdminBooking(${b.id}, this)" data-i18n="admin-action-delete">
            <i class="fa-solid fa-trash"></i> ${t('admin-action-delete', 'Delete')}
          </button>
        </td>
      `;
      tbody.appendChild(tr);
    });
  })
  .catch(() => showNotification('Error loading bookings', 'error'));
}

function loadAdminCourts() {
  const todayStr = getDefaultDate();
  fetch(`/api/courts?date=${STATE.selectedDate || todayStr}`)
  .then(res => res.json())
  .then(courts => {
    const tbody = document.getElementById('admin-courts-table-body');
    tbody.innerHTML = '';

    if (courts.length === 0) {
      tbody.innerHTML = `<tr><td colspan="5" class="text-center text-muted">${t('admin-no-courts', 'No courts configured.')}</td></tr>`;
      return;
    }

    courts.forEach(c => {
      const tr = document.createElement('tr');
      // Escape single quotes for HTML injection safely
      const escapedName = c.name.replace(/'/g, "\\'");
      const escapedNameTh = (c.name_th || '').replace(/'/g, "\\'");
      const escapedDesc = (c.description || '').replace(/'/g, "\\'");
      const escapedDescTh = (c.description_th || '').replace(/'/g, "\\'");
      tr.innerHTML = `
        <td>${c.id}</td>
        <td>
          <div style="display: inline-flex; align-items: center; gap: 8px; flex-wrap: nowrap; white-space: nowrap;">
            <strong>${translateCourtName(c)}</strong>
            ${c.is_maintenance ? `<span class="badge badge-danger" style="font-size:0.7rem; background: rgba(239, 68, 68, 0.15); color: #ef4444; border: 1px solid rgba(239, 68, 68, 0.3); padding: 2px 8px; line-height: 1.2; display: inline-block; white-space: nowrap;">${currentLang === 'th' ? 'ปิดปรับปรุง' : 'Maintenance'}</span>` : ''}
          </div>
        </td>
        <td>฿${c.price_per_hour}/hr</td>
        <td><small class="text-muted">${translateCourtDesc(c) || '-'}</small></td>
        <td>
          <button class="btn btn-outline btn-sm text-neon mr-2" onclick="startEditCourt(${c.id}, '${escapedName}', '${escapedNameTh}', ${c.price_per_hour}, '${escapedDesc}', '${escapedDescTh}', '${c.image_name}', ${c.is_maintenance})" data-i18n="admin-action-edit">
            <i class="fa-solid fa-pen-to-square"></i> ${t('admin-action-edit', 'Edit')}
          </button>
          <button class="btn btn-outline btn-sm text-red" onclick="deleteAdminCourt(${c.id})" data-i18n="admin-action-delete">
            <i class="fa-solid fa-trash"></i> ${t('admin-action-delete', 'Delete')}
          </button>
        </td>
      `;
      tbody.appendChild(tr);
    });
  })
  .catch(() => showNotification('Error loading courts list', 'error'));
}

function handleAdminAddCourt(event) {
  event.preventDefault();
  
  const name = document.getElementById('court-name-input').value;
  const name_th = document.getElementById('court-name-th-input').value;
  const price = document.getElementById('court-price-input').value;
  const desc = document.getElementById('court-desc-input').value;
  const desc_th = document.getElementById('court-desc-th-input').value;
  const img = document.getElementById('court-image-input').value;
  const is_maintenance = document.getElementById('court-is-maintenance').checked;

  const url = STATE.editingCourtId ? `/api/admin/courts/${STATE.editingCourtId}` : '/api/admin/courts';
  const method = STATE.editingCourtId ? 'PUT' : 'POST';

  fetch(url, {
    method: method,
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${STATE.token}`
    },
    body: JSON.stringify({
      name,
      name_th,
      price_per_hour: price,
      description: desc,
      description_th: desc_th,
      image_name: img,
      is_maintenance
    })
  })
  .then(async res => {
    const data = await res.json();
    if (!res.ok) throw new Error(data.message || 'Error saving court details');
    return data;
  })
  .then(() => {
    const actionText = STATE.editingCourtId ? 'updated' : 'added';
    showNotification(`Court ${actionText} successfully`, 'success');
    resetCourtForm();
    // Refresh admin courts table
    loadAdminCourts();
    // Also refresh STATE.courts so home/timeslot views reflect changes in this session
    const todayStr = getDefaultDate();
    fetch(`/api/courts?date=${STATE.selectedDate || todayStr}`)
    .then(r => r.json())
    .then(courts => {
      STATE.courts = courts;
      // Re-render home featured courts if visible
      const featuredGrid = document.getElementById('featured-courts-grid');
      if (featuredGrid) {
        featuredGrid.innerHTML = '';
        courts.forEach(court => {
          const card = document.createElement('div');
          card.className = 'court-card';
          card.innerHTML = `
            <div class="court-img-container">
              <i class="fa-solid fa-baseball court-img-svg"></i>
              <div class="court-img-bg-shape"></div>
            </div>
            <div class="court-card-body">
              <h3 class="court-card-title">${court.name}</h3>
              <p class="court-card-desc">${court.description}</p>
              <div class="court-card-footer">
                <div class="court-card-price">
                  <span class="price">฿${court.price_per_hour}</span>
                </div>
                <button class="btn btn-primary btn-sm btn-book" data-id="${court.id}">Book Now</button>
              </div>
            </div>
          `;
          card.querySelector('.btn-book').addEventListener('click', () => {
            STATE.activeCourtId = court.id;
            navigateTo('timeslot');
          });
          featuredGrid.appendChild(card);
        });
      }
    })
    .catch(() => {});
    // Refresh stats
    fetch('/api/admin/stats', {
      headers: { 'Authorization': `Bearer ${STATE.token}` }
    })
    .then(res => res.json())
    .then(stats => {
      document.getElementById('admin-stat-courts').textContent = stats.totalCourts;
    })
    .catch(() => {});
  })
  .catch(err => showNotification(err.message || 'Error saving court details', 'error'));
}

function startEditCourt(id, name, nameTh, price, desc, descTh, img, isMaintenance) {
  STATE.editingCourtId = id;
  
  document.getElementById('court-name-input').value = name;
  document.getElementById('court-name-th-input').value = nameTh || '';
  document.getElementById('court-price-input').value = price;
  document.getElementById('court-desc-input').value = desc || '';
  document.getElementById('court-desc-th-input').value = descTh || '';
  document.getElementById('court-image-input').value = img || 'court_indoor_a';
  document.getElementById('court-is-maintenance').checked = !!isMaintenance;
  
  const formHeader = document.querySelector('.add-court-section h3');
  if (formHeader) {
    formHeader.textContent = `${t('admin-action-edit', 'Edit')} ${t('admin-tbl-court', 'Court')}: ${name}`;
    formHeader.classList.remove('text-neon');
    formHeader.style.color = '#38bdf8'; // Sky blue in edit mode
  }
  
  const submitBtn = document.querySelector('#admin-add-court-form button[type="submit"]');
  if (submitBtn) {
    submitBtn.innerHTML = `<i class="fa-solid fa-check mr-1"></i> ${t('admin-save-court-update', 'Update Court')}`;
    submitBtn.style.backgroundColor = '#0284c7';
    submitBtn.style.color = '#ffffff';
  }
  
  if (!document.getElementById('cancel-edit-court-btn')) {
    const cancelBtn = document.createElement('button');
    cancelBtn.type = 'button';
    cancelBtn.id = 'cancel-edit-court-btn';
    cancelBtn.className = 'btn btn-outline w-100 mt-2';
    cancelBtn.innerHTML = `<i class="fa-solid fa-xmark mr-1"></i> ${t('admin-action-cancel', 'Cancel')} ${t('admin-action-edit', 'Edit')}`;
    cancelBtn.addEventListener('click', resetCourtForm);
    document.getElementById('admin-add-court-form').appendChild(cancelBtn);
  }
  
  document.getElementById('admin-add-court-form').scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

function resetCourtForm() {
  STATE.editingCourtId = null;
  document.getElementById('admin-add-court-form').reset();
  document.getElementById('court-is-maintenance').checked = false;
  
  const formHeader = document.querySelector('.add-court-section h3');
  if (formHeader) {
    formHeader.textContent = t('admin-add-court', 'Add New Court');
    formHeader.classList.add('text-neon');
    formHeader.style.color = '';
  }
  
  const submitBtn = document.querySelector('#admin-add-court-form button[type="submit"]');
  if (submitBtn) {
    submitBtn.innerHTML = `<i class="fa-solid fa-plus mr-1"></i> ${t('admin-save-court', 'Save Court')}`;
    submitBtn.style.backgroundColor = '';
    submitBtn.style.color = '';
  }
  
  const cancelBtn = document.getElementById('cancel-edit-court-btn');
  if (cancelBtn) cancelBtn.remove();
}

function deleteAdminBooking(id, btn) {
  if (!confirm(translateConfirm('Are you sure you want to delete/cancel this booking?'))) return;

  // Optimistic UI: remove the row immediately
  const row = btn ? btn.closest('tr') : null;
  if (row) row.style.opacity = '0.4';

  fetch(`/api/admin/bookings/${id}`, {
    method: 'DELETE',
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => {
    if (!res.ok) throw new Error();
    return res.json();
  })
  .then(() => {
    // Remove from DOM instantly — no replication lag issue
    if (row) row.remove();

    // Check if table is now empty
    const tbody = document.getElementById('admin-bookings-table-body');
    if (tbody && tbody.children.length === 0) {
      tbody.innerHTML = `<tr><td colspan="9" class="text-center text-muted">No bookings found in database.</td></tr>`;
    }

    showNotification('Booking deleted', 'success');

    // Update stats counter
    fetch('/api/admin/stats', {
      headers: { 'Authorization': `Bearer ${STATE.token}` }
    })
    .then(res => res.json())
    .then(stats => {
      document.getElementById('admin-stat-bookings').textContent = stats.totalBookings;
      document.getElementById('admin-stat-revenue').textContent = `฿${stats.totalRevenue.toLocaleString()}`;
    })
    .catch(() => {});
  })
  .catch(() => {
    if (row) row.style.opacity = '1'; // revert fade on error
    showNotification('Error deleting booking', 'error');
  });
}

function deleteAdminCourt(id) {
  if (!confirm(translateConfirm('Are you sure you want to delete this court? This might affect existing bookings!'))) return;

  fetch(`/api/admin/courts/${id}`, {
    method: 'DELETE',
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => {
    if (!res.ok) throw new Error();
    return res.json();
  })
  .then(() => {
    showNotification('Court deleted', 'success');
    loadAdminCourts();
    fetch('/api/admin/stats', {
      headers: { 'Authorization': `Bearer ${STATE.token}` }
    })
    .then(res => res.json())
    .then(stats => {
      document.getElementById('admin-stat-courts').textContent = stats.totalCourts;
    })
    .catch(() => {});
  })
  .catch(() => showNotification('Error deleting court', 'error'));
}

function loadAdminUsers() {
  fetch('/api/admin/users', {
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => res.json())
  .then(users => {
    const tbody = document.getElementById('admin-users-table-body');
    tbody.innerHTML = '';

    users.forEach(u => {
      const tr = document.createElement('tr');
      const roleColors = { admin: 'badge-neon', mod: 'badge-blue', user: '' };
      const roleClass = roleColors[u.role] || '';

      // Don't allow changing own role
      const isSelf = STATE.user && u.id === STATE.user.id;

      tr.innerHTML = `
        <td>${u.id}</td>
        <td><strong>${u.username}</strong>${isSelf ? ` <span class="badge" style="font-size:0.7rem;">${t('You', 'You')}</span>` : ''}</td>
        <td>${u.email}</td>
        <td><span class="badge ${roleClass}">${t(u.role, u.role)}</span></td>
        <td>${new Date(u.created_at).toLocaleString(currentLang === 'th' ? 'th-TH' : 'en-US')}</td>
        <td>
          ${isSelf ? '<span class="text-muted" style="font-size:0.8rem;">—</span>' : `
          <select class="role-select-dropdown"
            data-original="${u.role}"
            onchange="changeUserRole(${u.id}, this.value, this)">
            <option value="user" ${u.role === 'user' ? 'selected' : ''}>${t('user', 'user')}</option>
            <option value="mod" ${u.role === 'mod' ? 'selected' : ''}>${t('mod', 'mod')}</option>
            <option value="admin" ${u.role === 'admin' ? 'selected' : ''}>${t('admin', 'admin')}</option>
          </select>`}
        </td>
      `;
      tbody.appendChild(tr);
    });
  })
  .catch(() => showNotification('Error loading users', 'error'));
}

function changeUserRole(userId, newRole, selectEl) {
  // Save original value BEFORE any changes (data-original is set in HTML template)
  const originalValue = selectEl.dataset.original || newRole;

  fetch(`/api/admin/users/${userId}/role`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${STATE.token}`
    },
    body: JSON.stringify({ role: newRole })
  })
  .then(res => res.json().then(data => ({ ok: res.ok, data })))
  .then(({ ok, data }) => {
    if (!ok) {
      showNotification(data.message || 'Failed to update role', 'error');
      selectEl.value = originalValue;
      return;
    }
    showNotification(data.message, 'success');
    selectEl.dataset.original = newRole;
    // Refresh the badge next to the dropdown
    const badge = selectEl.closest('tr').querySelector('.badge');
    if (badge) {
      badge.textContent = t(newRole, newRole);
      badge.className = 'badge ' + ({ admin: 'badge-neon', mod: 'badge-blue', user: '' }[newRole] || '');
    }
  })
  .catch(() => {
    showNotification('Error updating role', 'error');
    selectEl.value = originalValue;
  });
}

function loadAdminPromoCodes() {
  fetch('/api/admin/promo-codes', {
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => res.json())
  .then(promos => {
    STATE.promos = promos;
    const tbody = document.getElementById('admin-promocodes-table-body');
    if (!tbody) return;
    tbody.innerHTML = '';

    if (!Array.isArray(promos) || promos.length === 0) {
      tbody.innerHTML = `<tr><td colspan="7" class="text-center text-muted">${t('admin-no-promos', 'No promo codes found.')}</td></tr>`;
      return;
    }

    promos.forEach(p => {
      const tr = document.createElement('tr');
      
      // Calculate realistic status
      const isExpired = p.validUntil && new Date() > new Date(p.validUntil);
      const isLimitReached = p.maxUses !== null && p.currentUses >= p.maxUses;
      
      let statusClass = 'badge-danger';
      let statusText = t('admin-status-inactive', 'Inactive');
      if (p.isActive) {
        if (isExpired) {
          statusText = t('admin-status-expired', 'Expired');
        } else if (isLimitReached) {
          statusText = t('admin-status-limit', 'Limit Reached');
        } else {
          statusClass = 'badge-neon';
          statusText = t('admin-status-active', 'Active');
        }
      }

      const validUntilStr = p.validUntil ? formatDateDMY(p.validUntil.substring(0, 10)) : t('Never', 'Never');
      const discountText = p.discountType === 'percent' ? `${p.discountAmount}%` : `฿${p.discountAmount}`;

      tr.innerHTML = `
        <td>${p.id}</td>
        <td><strong>${p.code}</strong></td>
        <td>${discountText}</td>
        <td>${validUntilStr}</td>
        <td>${p.currentUses} / ${p.maxUses || '∞'}</td>
        <td><span class="badge ${statusClass}">${statusText}</span></td>
        <td>
          <button class="btn btn-outline btn-sm text-neon mr-1" onclick="editAdminPromoCode(${p.id})">
            <i class="fa-solid fa-pen"></i>
          </button>
          <button class="btn btn-outline btn-sm text-red" onclick="deleteAdminPromoCode(${p.id})">
            <i class="fa-solid fa-trash"></i>
          </button>
        </td>
      `;
      tbody.appendChild(tr);
    });
  })
  .catch(() => showNotification('Error loading promo codes', 'error'));
}

function editAdminPromoCode(id) {
  const promo = STATE.promos.find(p => p.id === id);
  if (!promo) return;

  STATE.editingPromoId = id;
  
  // Set form values
  document.getElementById('promo-code-input').value = promo.code;
  document.getElementById('promo-discount-amount').value = promo.discountAmount;
  document.getElementById('promo-discount-type').value = promo.discountType;
  handlePromoTypeChange();
  document.getElementById('promo-max-uses').value = promo.maxUses || '';
  
  const promoDateEl = document.getElementById('promo-valid-until');
  if (promoDateEl && promoDateEl._flatpickr) {
    promoDateEl._flatpickr.setDate(promo.validUntil ? promo.validUntil.substring(0, 10) : '');
  }

  // Show status active checkbox
  document.getElementById('promo-active-group').style.display = 'flex';
  document.getElementById('promo-is-active').checked = promo.isActive;

  // Change headers / button texts
  document.getElementById('promo-form-title').textContent = `${t('admin-action-edit', 'Edit')} ${t('admin-tbl-code', 'Code')}: ${promo.code}`;
  const submitBtn = document.querySelector('#admin-add-promocode-form button[type="submit"]');
  submitBtn.innerHTML = `<i class="fa-solid fa-check mr-1"></i> ${t('admin-save-promo-update', 'Update Promo Code')}`;

  // Show Cancel Button if not already there
  let cancelBtn = document.getElementById('promo-cancel-edit-btn');
  if (!cancelBtn) {
    cancelBtn = document.createElement('button');
    cancelBtn.type = 'button';
    cancelBtn.id = 'promo-cancel-edit-btn';
    cancelBtn.className = 'btn btn-outline w-100 mt-2';
    cancelBtn.innerHTML = `<i class="fa-solid fa-xmark mr-1"></i> ${t('admin-action-cancel', 'Cancel')} ${t('admin-action-edit', 'Edit')}`;
    cancelBtn.addEventListener('click', cancelPromoEdit);
    document.getElementById('admin-add-promocode-form').appendChild(cancelBtn);
  }
}

function cancelPromoEdit() {
  STATE.editingPromoId = null;
  document.getElementById('admin-add-promocode-form').reset();
  handlePromoTypeChange();
  
  const promoDateEl = document.getElementById('promo-valid-until');
  if (promoDateEl && promoDateEl._flatpickr) {
    promoDateEl._flatpickr.setDate('');
  }

  document.getElementById('promo-active-group').style.display = 'none';
  document.getElementById('promo-form-title').textContent = t('admin-add-promo', 'Add Promo Code');
  
  const submitBtn = document.querySelector('#admin-add-promocode-form button[type="submit"]');
  submitBtn.innerHTML = `<i class="fa-solid fa-plus mr-1"></i> ${t('admin-save-promo', 'Save Promo Code')}`;

  const cancelBtn = document.getElementById('promo-cancel-edit-btn');
  if (cancelBtn) {
    cancelBtn.remove();
  }
}

function handlePromoTypeChange() {
  const type = document.getElementById('promo-discount-type').value;
  const amountInput = document.getElementById('promo-discount-amount');
  if (type === 'percent') {
    amountInput.setAttribute('max', '100');
  } else {
    amountInput.removeAttribute('max');
  }
}

function handleAdminAddPromoCode(event) {
  event.preventDefault();
  
  const code = document.getElementById('promo-code-input').value;
  const amount = document.getElementById('promo-discount-amount').value;
  const type = document.getElementById('promo-discount-type').value;
  const validUntil = document.getElementById('promo-valid-until').value;
  const maxUses = document.getElementById('promo-max-uses').value;
  const isActive = document.getElementById('promo-is-active').checked;

  const parsedAmount = parseFloat(amount);
  if (isNaN(parsedAmount) || parsedAmount < 0) {
    showNotification('Discount amount must be a positive number', 'error');
    return;
  }

  if (type === 'percent' && parsedAmount > 100) {
    showNotification('Percentage discount cannot exceed 100%', 'error');
    return;
  }

  const isEdit = STATE.editingPromoId !== null;
  const url = isEdit ? `/api/admin/promo-codes/${STATE.editingPromoId}` : '/api/admin/promo-codes';
  const method = isEdit ? 'PATCH' : 'POST';

  fetch(url, {
    method: method,
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${STATE.token}`
    },
    body: JSON.stringify({
      code,
      discountAmount: amount,
      discountType: type,
      validUntil: validUntil || null,
      maxUses: maxUses || null,
      isActive: isEdit ? isActive : true
    })
  })
  .then(async res => {
    const data = await res.json();
    if (!res.ok) throw new Error(data.message || 'Error saving promo code');
    return data;
  })
  .then(() => {
    showNotification(isEdit ? 'Promo code updated successfully' : 'Promo code added successfully', 'success');
    cancelPromoEdit();
    loadAdminPromoCodes();
  })
  .catch(err => showNotification(err.message || 'Error saving promo code', 'error'));
}

function deleteAdminPromoCode(id) {
  if (!confirm(translateConfirm('Are you sure you want to delete this promo code?'))) return;

  fetch(`/api/admin/promo-codes/${id}`, {
    method: 'DELETE',
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => {
    if (!res.ok) throw new Error();
    return res.json();
  })
  .then(() => {
    showNotification('Promo code deleted', 'success');
    loadAdminPromoCodes();
  })
  .catch(() => showNotification('Error deleting promo code', 'error'));
}

function initRealtimeSync() {
  const evtSource = new EventSource('/api/events');
  evtSource.onmessage = (event) => {
    try {
      const data = JSON.parse(event.data);
      if (data.type === 'booking-updated') {
        const view = STATE.currentView ? STATE.currentView.replace('-view', '') : '';
        if (view === 'timeslot') {
          loadCourtsAvailability();
        }
      }
    } catch (e) {
      // Ignore heartbeat parses
    }
  };
}

