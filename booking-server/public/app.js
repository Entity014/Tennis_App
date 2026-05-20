// Application State
const STATE = {
  token: localStorage.getItem('token') || null,
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
  editingCourtId: null
};

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
document.addEventListener('DOMContentLoaded', () => {
  initTheme();
  
  // Set default search date to today
  const today = new Date();
  const formattedToday = today.toISOString().split('T')[0];
  STATE.selectedDate = formattedToday;

  // Initialize Flatpickr inputs
  initFlatpickr();

  fetchConfig().then(() => {
    initSocialSDKs();
    if (typeof google !== 'undefined' && google.accounts && google.accounts.id) {
      initGoogleGis();
    }
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
});

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
    toggleBtn.innerHTML = '<i class="fa-solid fa-moon"></i>';
  } else {
    body.classList.remove('light-theme');
    body.classList.add('dark-theme');
    toggleBtn.innerHTML = '<i class="fa-solid fa-sun"></i>';
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

function initFlatpickr() {
  const isDark = STATE.theme === 'dark';
  
  if (bookingDatePicker) bookingDatePicker.destroy();
  if (timeslotDatePicker) timeslotDatePicker.destroy();

  const maxDate = new Date();
  maxDate.setMonth(maxDate.getMonth() + 3);

  bookingDatePicker = flatpickr("#booking-date", {
    altInput: true,
    altFormat: "d/m/Y",
    dateFormat: "Y-m-d",
    defaultDate: STATE.selectedDate,
    theme: isDark ? "dark" : "light",
    minDate: "today",
    maxDate: maxDate,
    onChange: function(selectedDates, dateStr) {
      if (dateStr) {
        STATE.selectedDate = dateStr;
        if (timeslotDatePicker) {
          timeslotDatePicker.setDate(dateStr, false);
        }
      }
    }
  });

  timeslotDatePicker = flatpickr("#timeslot-date-picker", {
    altInput: true,
    altFormat: "d/m/Y",
    dateFormat: "Y-m-d",
    defaultDate: STATE.selectedDate,
    theme: isDark ? "dark" : "light",
    minDate: "today",
    maxDate: maxDate,
    onChange: function(selectedDates, dateStr) {
      if (dateStr) {
        STATE.selectedDate = dateStr;
        if (bookingDatePicker) {
          bookingDatePicker.setDate(dateStr, false);
        }
        loadCourtsAvailability();
      }
    }
  });
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

// Google GIS Onload callback defined globally
window.onGoogleGisLoad = function() {
  STATE.googleGisLoaded = true;
  if (STATE.config.googleClientId) {
    initGoogleGis();
  }
};

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
  // If Google script has already loaded and loaded callback fired
  if (typeof google !== 'undefined' && google.accounts && google.accounts.id) {
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
  fetch('/api/auth/google-login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ credential: response.credential })
  })
  .then(res => res.json())
  .then(data => {
    if (data.token) {
      handleAuthSuccess(data);
      showNotification('Successfully logged in with Google!', 'success');
    } else {
      showNotification(data.message || 'Google authentication failed', 'error');
    }
  })
  .catch(err => {
    console.error('Google Sign In Error:', err);
    showNotification('Unable to connect to login server', 'error');
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

  // Logout Button
  document.getElementById('logout-btn').addEventListener('click', () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
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
      loginUsernameError.textContent = 'Username is required.';
      loginUsernameError.style.display = 'block';
      isValid = false;
    }

    if (!password) {
      loginPasswordError.textContent = 'Password is required.';
      loginPasswordError.style.display = 'block';
      isValid = false;
    }

    if (!isValid) return;

    fetch('/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    })
    .then(res => res.json())
    .then(data => {
      if (data.token) {
        handleAuthSuccess(data);
        showNotification('Welcome back!', 'success');
      } else {
        loginPasswordError.textContent = 'Incorrect username or password. Please try again.';
        loginPasswordError.style.display = 'block';
      }
    })
    .catch(() => showNotification('Unable to connect to auth server', 'error'));
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

  registerUsernameInput.addEventListener('input', () => registerUsernameError.style.display = 'none');
  registerEmailInput.addEventListener('input', () => registerEmailError.style.display = 'none');
  registerPasswordInput.addEventListener('input', () => registerPasswordError.style.display = 'none');

  // Submit Register
  registerForm.addEventListener('submit', (e) => {
    e.preventDefault();
    clearErrors();
    
    const username = registerUsernameInput.value.trim();
    const email = registerEmailInput.value.trim();
    const password = registerPasswordInput.value;
    let isValid = true;

    if (!username) {
      registerUsernameError.textContent = 'Username is required.';
      registerUsernameError.style.display = 'block';
      isValid = false;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email) {
      registerEmailError.textContent = 'Email address is required.';
      registerEmailError.style.display = 'block';
      isValid = false;
    } else if (!emailRegex.test(email)) {
      registerEmailError.textContent = 'Please enter a valid email address.';
      registerEmailError.style.display = 'block';
      isValid = false;
    }

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    if (!password) {
      registerPasswordError.textContent = 'Password is required.';
      registerPasswordError.style.display = 'block';
      isValid = false;
    } else if (!passwordRegex.test(password)) {
      registerPasswordError.innerHTML = `
        <ul style="margin: 0; padding-left: 1.2rem; text-align: left;">
          <li>At least 8 characters long</li>
          <li>At least one uppercase letter (A-Z)</li>
          <li>At least one lowercase letter (a-z)</li>
          <li>At least one number (0-9)</li>
        </ul>
      `;
      registerPasswordError.style.display = 'block';
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
        registerUsernameError.textContent = err.message;
        registerUsernameError.style.display = 'block';
      } else if (errMsg.includes('email')) {
        registerEmailError.textContent = err.message;
        registerEmailError.style.display = 'block';
      } else {
        showNotification(err.message || 'Connection error during registration', 'error');
      }
    });
  });

  // Forgot Password Submit
  const forgotForm = document.getElementById('forgot-password-form');
  const forgotEmailInput = document.getElementById('forgot-email');
  const forgotEmailError = document.getElementById('forgot-email-error');

  forgotEmailInput?.addEventListener('input', () => forgotEmailError.style.display = 'none');

  forgotForm?.addEventListener('submit', (e) => {
    e.preventDefault();
    const email = forgotEmailInput.value.trim();
    if (!email) {
      forgotEmailError.textContent = 'Email is required.';
      forgotEmailError.style.display = 'block';
      return;
    }

    fetch('/api/auth/forgot-password', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email })
    })
    .then(res => res.json().then(data => ({ ok: res.ok, data })))
    .then(({ ok, data }) => {
      if (!ok) {
        forgotEmailError.textContent = data.message || 'Error sending PIN';
        forgotEmailError.style.display = 'block';
        return;
      }
      showNotification('A password reset PIN has been sent to your email. Please check your inbox.', 'success');
      document.getElementById('reset-token').value = '';
      toggleAuthTab('reset');
    })
    .catch(() => showNotification('Connection error', 'error'));
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
      resetTokenError.textContent = 'Reset PIN is required.';
      resetTokenError.style.display = 'block';
      isValid = false;
    }

    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
    if (!newPassword) {
      resetPasswordError.textContent = 'New password is required.';
      resetPasswordError.style.display = 'block';
      isValid = false;
    } else if (!passwordRegex.test(newPassword)) {
      resetPasswordError.innerHTML = `
        <ul style="margin: 0; padding-left: 1.2rem; text-align: left;">
          <li>At least 8 characters long</li>
          <li>At least one uppercase letter (A-Z)</li>
          <li>At least one lowercase letter (a-z)</li>
          <li>At least one number (0-9)</li>
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
        resetTokenError.textContent = data.message || 'Error resetting password';
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
  STATE.token = data.token;
  STATE.user = data.user;
  localStorage.setItem('token', data.token);
  localStorage.setItem('user', JSON.stringify(data.user));
  checkLoggedInState();

  // Redirect based on previous workflow target
  if (STATE.activeBooking) {
    // If logging in during court booking flow, proceed to review
    navigateTo('review');
  } else {
    navigateTo('dashboard');
  }
}

function checkLoggedInState() {
  const loginNavBtn = document.getElementById('login-nav-btn');
  const userDisplay = document.getElementById('user-display');
  const usernameSpan = document.getElementById('username-span');
  const authRequiredLinks = document.querySelectorAll('.auth-required');
  const adminRequiredLinks = document.querySelectorAll('.admin-required');

  if (STATE.token && STATE.user) {
    loginNavBtn.style.display = 'none';
    userDisplay.style.display = 'flex';
    usernameSpan.textContent = STATE.user.display_name || STATE.user.username;
    authRequiredLinks.forEach(link => link.style.display = 'block');
    
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

function submitSimulatedSocialLogin(provider, account) {
  closeSocialModal();
  const endpoint = provider === 'google' ? '/api/auth/google-login' : '/api/auth/facebook-login';
  
  fetch(endpoint, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      isSimulation: true,
      email: account.email,
      name: account.name,
      id: account.id
    })
  })
  .then(res => res.json())
  .then(data => {
    if (data.token) {
      handleAuthSuccess(data);
      showNotification(`Logged in as ${account.name} (Simulated)`, 'success');
    } else {
      showNotification('Social login failed', 'error');
    }
  })
  .catch(() => showNotification('Connection error during social authentication', 'error'));
}

// --- Home / Featured Courts Section ---
function loadFeaturedCourts() {
  fetch(`/api/courts?date=${new Date().toISOString().split('T')[0]}`)
  .then(res => res.json())
  .then(courts => {
    STATE.courts = courts;
    const grid = document.getElementById('featured-courts-grid');
    grid.innerHTML = '';

    courts.forEach(court => {
      const card = document.createElement('div');
      card.className = 'court-card';
      
      const imageClass = court.image_name || 'court_indoor_a';
      card.innerHTML = `
        <div class="court-img-container">
          <i class="fa-solid fa-tennis-ball court-img-svg"></i>
          <div class="court-img-bg-shape"></div>
        </div>
        <div class="court-card-body">
          <h3 class="court-card-title">${court.name}</h3>
          <p class="court-card-desc">${court.description}</p>
          <div class="court-card-footer">
            <div class="court-card-price">
              <span class="price">฿${court.price_per_hour}</span>
              <span class="label">per hour</span>
            </div>
            <button class="btn btn-primary btn-sm btn-book" data-id="${court.id}">Book Now</button>
          </div>
        </div>
      `;
      
      card.style.cursor = 'pointer';
      card.addEventListener('click', () => {
        STATE.activeCourtId = court.id;
        navigateTo('timeslot');
      });

      grid.appendChild(card);
    });
  })
  .catch(() => {
    document.getElementById('featured-courts-grid').innerHTML = '<p class="text-center w-100 text-muted">Unable to load courts. Please check server connection.</p>';
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

    if (!STATE.token) {
      showNotification('Sign in required to complete booking.', 'error');
      navigateTo('auth');
    } else {
      navigateTo('review');
    }
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
      tab.className = `court-tab-btn ${court.id === STATE.activeCourtId ? 'active' : ''}`;
      tab.textContent = court.name;
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
      document.getElementById('detail-court-name').textContent = activeCourt.name;
      document.getElementById('detail-court-desc').textContent = activeCourt.description;
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
      slotDiv.querySelector('.status-lbl').textContent = 'Selected';
    } else {
      slotDiv.classList.remove('selected');
      slotDiv.querySelector('.status-lbl').textContent = 'Available';
    }
  });
}

function renderTimeGrid(court) {
  const grid = document.getElementById('time-grid-slots');
  grid.innerHTML = '';

  if (!court) return;

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

    let statusText = 'Available';
    if (isBooked) {
      statusText = 'Unavailable';
    } else if (isExpired) {
      statusText = 'Expired';
    } else if (isSelected) {
      statusText = 'Selected';
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
  document.getElementById('sum-time').textContent = `${startHour}:00 to ${endHour}:00`;
  document.getElementById('sum-duration').textContent = `${duration} hour${duration > 1 ? 's' : ''}`;
  document.getElementById('sum-total-price').textContent = `฿${total.toLocaleString()}`;
}

// --- Review Booking Flow (Promo / Promo card calculations) ---
function initPromoHandlers() {
  // Apply Promo
  document.getElementById('promo-apply-btn').addEventListener('click', () => {
    const code = document.getElementById('promo-input').value.trim().toUpperCase();
    if (!code) return;

    if (code === 'ACE10') {
      STATE.appliedPromo = { code: 'ACE10', discount: 0.10 };
      showNotification('Promo card applied successfully! 10% Discount.', 'success');
      updateReviewDetails();
    } else {
      showNotification('Invalid coupon or promo code.', 'error');
    }
  });

  // Proceed to payment gateway
  document.getElementById('proceed-to-payment-btn').addEventListener('click', () => {
    if (!STATE.activeBooking) return;
    
    // Reset test mode checkbox
    const testModeToggle = document.getElementById('test-mode-toggle');
    if (testModeToggle) testModeToggle.checked = false;

    // Save final price
    let finalPrice = STATE.activeBooking.price;
    if (STATE.appliedPromo) {
      finalPrice = finalPrice - (finalPrice * STATE.appliedPromo.discount);
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
  document.getElementById('rev-date').textContent = b.date;
  document.getElementById('rev-time').textContent = `${b.start_time} - ${b.end_time} (${b.duration} hrs)`;
  document.getElementById('rev-rate').textContent = `฿${b.rate}/hr`;

  let finalPrice = b.price;
  if (STATE.appliedPromo) {
    finalPrice = finalPrice - (finalPrice * STATE.appliedPromo.discount);
  }
  document.getElementById('rev-total').textContent = `฿${finalPrice.toLocaleString()}`;
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

  slipInput.addEventListener('change', (e) => {
    const file = e.target.files[0];
    if (file) {
      if (!file.type.startsWith('image/')) {
        showNotification('Please select an image file.', 'error');
        slipInput.value = '';
        return;
      }
      
      const reader = new FileReader();
      reader.onload = (event) => {
        STATE.slipImage = event.target.result;
        slipPreviewImg.src = event.target.result;
        slipUploadLabel.style.display = 'none';
        slipPreviewContainer.style.display = 'flex';
      };
      reader.readAsDataURL(file);
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
      if (!file.type.startsWith('image/')) {
        showNotification('Please select an image file.', 'error');
        return;
      }
      const reader = new FileReader();
      reader.onload = (event) => {
        STATE.slipImage = event.target.result;
        slipPreviewImg.src = event.target.result;
        slipUploadLabel.style.display = 'none';
        slipPreviewContainer.style.display = 'flex';
      };
      reader.readAsDataURL(file);
    }
  });

  // Test mode toggle handler
  const testModeToggle = document.getElementById('test-mode-toggle');
  if (testModeToggle) {
    testModeToggle.addEventListener('change', () => {
      let finalPrice = STATE.activeBooking ? STATE.activeBooking.price : 0;
      if (STATE.appliedPromo && STATE.activeBooking) {
        finalPrice = finalPrice - (finalPrice * STATE.appliedPromo.discount);
      }
      
      if (testModeToggle.checked) {
        finalPrice = 0.1;
      }
      
      document.getElementById('payment-amount-display').textContent = `฿${finalPrice.toFixed(2)}`;
      
      // Regenerate QR Code matching the test amount
      if (finalPrice > 0) {
        const receiver = STATE.config.prompayReceiverId || '0917291840';
        const payload = generatePromptPayPayload(receiver, finalPrice);
        const qrImg = document.getElementById('qr-code-img');
        qrImg.src = `https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=${encodeURIComponent(payload)}`;
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
      finalPrice = finalPrice - (finalPrice * STATE.appliedPromo.discount);
    }
    
    const testModeToggle = document.getElementById('test-mode-toggle');
    if (testModeToggle && testModeToggle.checked) {
      finalPrice = 0.1;
    }
    
    if (finalPrice > 0) {
      const payload = generatePromptPayPayload(receiver, finalPrice);
      const qrImg = document.getElementById('qr-code-img');
      const qrPlaceholderIcon = document.getElementById('qr-code-placeholder-icon');
      
      qrImg.src = `https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=${encodeURIComponent(payload)}`;
      qrImg.style.display = 'block';
      qrPlaceholderIcon.style.display = 'none';
    }
    
    startQrCountdown();
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

  const method = document.getElementById('pay-tab-card').classList.contains('active') ? 'Credit Card' : 'PromptPay QR';
  
  if (method === 'PromptPay QR' && !STATE.slipImage) {
    showNotification('Please upload your payment slip first.', 'error');
    return;
  }

  // Show loading state
  const payBtn = document.getElementById('pay-submit-btn');
  const originalBtnHTML = payBtn.innerHTML;
  payBtn.disabled = true;
  payBtn.innerHTML = '<i class="fa-solid fa-circle-notch fa-spin mr-2"></i> Verifying Slip...';

  const isTest = document.getElementById('test-mode-toggle') ? document.getElementById('test-mode-toggle').checked : false;

  // 1. Create Booking in Database
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
      is_test: isTest,
      promo_code: STATE.appliedPromo ? STATE.appliedPromo.code : null
    })
  })
  .then(res => res.json())
  .then(bookingData => {
    if (bookingData.id) {
      // 2. Perform Payment processing on that Booking
      showNotification('Verifying payment slip...', 'info');
      
      fetch(`/api/payment/${bookingData.id}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${STATE.token}`
        },
        body: JSON.stringify({ 
          payment_method: method,
          slip_image: STATE.slipImage
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
          showNotification('Payment verified successfully!', 'success');
          
          // Clear uploaded slip state
          STATE.slipImage = null;
          document.getElementById('slip-file-input').value = '';
          document.getElementById('slip-preview-container').style.display = 'none';
          document.getElementById('slip-upload-label').style.display = 'flex';
          
          renderETicket(paymentResult.booking);
          navigateTo('confirmation');
        } else {
          showNotification(paymentResult.message || 'Slip verification failed', 'error');
        }
      })
      .catch((err) => {
        payBtn.disabled = false;
        payBtn.innerHTML = originalBtnHTML;
        showNotification(err.message || 'Error verifying payment slip', 'error');
      });
    } else {
      payBtn.disabled = false;
      payBtn.innerHTML = originalBtnHTML;
      showNotification(bookingData.message || 'Unable to secure slot. Collision detected.', 'error');
    }
  })
  .catch(() => {
    payBtn.disabled = false;
    payBtn.innerHTML = originalBtnHTML;
    showNotification('Error communicating with checkout server', 'error');
  });
}

function renderETicket(booking) {
  document.getElementById('ticket-court').textContent = booking.court_name;
  document.getElementById('ticket-date').textContent = formatDateDMY(booking.date);
  document.getElementById('ticket-time').textContent = `${booking.start_time} - ${booking.end_time}`;
  
  // Format PIN code with space (e.g. 829104 -> 829 104)
  const pin = booking.pin_code;
  const formattedPin = `${pin.substring(0,3)} ${pin.substring(3,6)}`;
  document.getElementById('ticket-pin').textContent = formattedPin;
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
    const todayStr = new Date().toISOString().split('T')[0];
    const upcoming = bookings.filter(b => b.date >= todayStr);
    upcomingCountEl.textContent = upcoming.length;

    if (bookings.length === 0) {
      bookingsList.innerHTML = `
        <div class="empty-state text-center py-5">
          <i class="fa-solid fa-calendar-minus text-muted mb-3 size-xl"></i>
          <p>You don't have any bookings yet.</p>
          <button class="btn btn-outline mt-3" onclick="navigateTo('search-view')">Book Your First Court</button>
        </div>
      `;
      return;
    }

    bookingsList.innerHTML = '';
    bookings.forEach(b => {
      const row = document.createElement('div');
      row.className = 'booking-item';
      
      const pin = b.pin_code;
      const formattedPin = `${pin.substring(0,3)} ${pin.substring(3,6)}`;

      row.innerHTML = `
        <div class="booking-info-main">
          <div class="booking-court-avatar">
            <i class="fa-solid fa-tennis-ball"></i>
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
          <span class="badge badge-neon">PIN: ${formattedPin}</span>
          <span class="booking-status-badge status-paid">Paid</span>
          <button class="btn btn-outline btn-sm btn-ticket-view">View Ticket</button>
        </div>
      `;
      
      row.querySelector('.btn-ticket-view').addEventListener('click', () => {
        renderETicket(b);
        navigateTo('confirmation');
      });
      bookingsList.appendChild(row);
    });
  })
  .catch(() => showNotification('Error loading dashboard bookings.', 'error'));
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

  const today = new Date();
  today.setHours(0, 0, 0, 0);

  const maxBookingDate = new Date();
  maxBookingDate.setMonth(maxBookingDate.getMonth() + 3);
  maxBookingDate.setHours(23, 59, 59, 999);

  if (currentDate < today) {
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
      tbody.innerHTML = `<tr><td colspan="9" class="text-center text-muted">No bookings found in database.</td></tr>`;
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
        <td><span class="booking-status-badge ${statusClass}">${b.status}</span></td>
        <td>
          <button class="btn btn-outline btn-sm text-red" onclick="deleteAdminBooking(${b.id})">
            <i class="fa-solid fa-trash"></i> Delete
          </button>
        </td>
      `;
      tbody.appendChild(tr);
    });
  })
  .catch(() => showNotification('Error loading bookings', 'error'));
}

function loadAdminCourts() {
  const todayStr = new Date().toISOString().split('T')[0];
  fetch(`/api/courts?date=${STATE.selectedDate || todayStr}`)
  .then(res => res.json())
  .then(courts => {
    const tbody = document.getElementById('admin-courts-table-body');
    tbody.innerHTML = '';

    if (courts.length === 0) {
      tbody.innerHTML = `<tr><td colspan="5" class="text-center text-muted">No courts configured.</td></tr>`;
      return;
    }

    courts.forEach(c => {
      const tr = document.createElement('tr');
      // Escape single quotes for HTML injection safely
      const escapedName = c.name.replace(/'/g, "\\'");
      const escapedDesc = (c.description || '').replace(/'/g, "\\'");
      tr.innerHTML = `
        <td>${c.id}</td>
        <td><strong>${c.name}</strong></td>
        <td>฿${c.price_per_hour}/hr</td>
        <td><small class="text-muted">${c.description || '-'}</small></td>
        <td>
          <button class="btn btn-outline btn-sm text-neon mr-2" onclick="startEditCourt(${c.id}, '${escapedName}', ${c.price_per_hour}, '${escapedDesc}', '${c.image_name}')">
            <i class="fa-solid fa-pen-to-square"></i> Edit
          </button>
          <button class="btn btn-outline btn-sm text-red" onclick="deleteAdminCourt(${c.id})">
            <i class="fa-solid fa-trash"></i> Delete
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
  const price = document.getElementById('court-price-input').value;
  const desc = document.getElementById('court-desc-input').value;
  const img = document.getElementById('court-image-input').value;

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
      price_per_hour: price,
      description: desc,
      image_name: img
    })
  })
  .then(res => {
    if (!res.ok) throw new Error();
    return res.json();
  })
  .then(() => {
    const actionText = STATE.editingCourtId ? 'updated' : 'added';
    showNotification(`Court ${actionText} successfully`, 'success');
    resetCourtForm();
    // Refresh admin courts table
    loadAdminCourts();
    // Also refresh STATE.courts so home/timeslot views reflect changes in this session
    const todayStr = new Date().toISOString().split('T')[0];
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
              <i class="fa-solid fa-tennis-ball court-img-svg"></i>
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
  .catch(() => showNotification('Error saving court details', 'error'));
}

function startEditCourt(id, name, price, desc, img) {
  STATE.editingCourtId = id;
  
  document.getElementById('court-name-input').value = name;
  document.getElementById('court-price-input').value = price;
  document.getElementById('court-desc-input').value = desc || '';
  document.getElementById('court-image-input').value = img || 'court_indoor_a';
  
  const formHeader = document.querySelector('.add-court-section h3');
  if (formHeader) {
    formHeader.textContent = `Edit Court: ${name}`;
    formHeader.classList.remove('text-neon');
    formHeader.style.color = '#38bdf8'; // Sky blue in edit mode
  }
  
  const submitBtn = document.querySelector('#admin-add-court-form button[type="submit"]');
  if (submitBtn) {
    submitBtn.innerHTML = `<i class="fa-solid fa-check mr-1"></i> Update Court`;
    submitBtn.style.backgroundColor = '#0284c7';
    submitBtn.style.color = '#ffffff';
  }
  
  if (!document.getElementById('cancel-edit-court-btn')) {
    const cancelBtn = document.createElement('button');
    cancelBtn.type = 'button';
    cancelBtn.id = 'cancel-edit-court-btn';
    cancelBtn.className = 'btn btn-outline w-100 mt-2';
    cancelBtn.innerHTML = `<i class="fa-solid fa-xmark mr-1"></i> Cancel Edit`;
    cancelBtn.addEventListener('click', resetCourtForm);
    document.getElementById('admin-add-court-form').appendChild(cancelBtn);
  }
  
  document.getElementById('admin-add-court-form').scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

function resetCourtForm() {
  STATE.editingCourtId = null;
  document.getElementById('admin-add-court-form').reset();
  
  const formHeader = document.querySelector('.add-court-section h3');
  if (formHeader) {
    formHeader.textContent = 'Add New Court';
    formHeader.classList.add('text-neon');
    formHeader.style.color = '';
  }
  
  const submitBtn = document.querySelector('#admin-add-court-form button[type="submit"]');
  if (submitBtn) {
    submitBtn.innerHTML = `<i class="fa-solid fa-plus mr-1"></i> Save Court`;
    submitBtn.style.backgroundColor = '';
    submitBtn.style.color = '';
  }
  
  const cancelBtn = document.getElementById('cancel-edit-court-btn');
  if (cancelBtn) cancelBtn.remove();
}

function deleteAdminBooking(id) {
  if (!confirm('Are you sure you want to delete/cancel this booking?')) return;

  fetch(`/api/admin/bookings/${id}`, {
    method: 'DELETE',
    headers: { 'Authorization': `Bearer ${STATE.token}` }
  })
  .then(res => {
    if (!res.ok) throw new Error();
    return res.json();
  })
  .then(() => {
    showNotification('Booking deleted', 'success');
    loadAdminBookings();
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
  .catch(() => showNotification('Error deleting booking', 'error'));
}

function deleteAdminCourt(id) {
  if (!confirm('Are you sure you want to delete this court? This might affect existing bookings!')) return;

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
      const roleClass = u.role === 'admin' ? 'badge-neon' : '';
      tr.innerHTML = `
        <td>${u.id}</td>
        <td><strong>${u.username}</strong></td>
        <td>${u.email}</td>
        <td><span class="badge ${roleClass}">${u.role}</span></td>
        <td>${new Date(u.created_at).toLocaleString('th-TH')}</td>
      `;
      tbody.appendChild(tr);
    });
  })
  .catch(() => showNotification('Error loading users', 'error'));
}
