# 📱 TennisLock Kiosk Android App

This is the Android Kiosk application for the AcePoint Tennis Court system. It is designed to run on dedicated Android devices (tablets or phones) installed at the tennis courts, acting as a digital lock.

## ✨ Features

- **Relentless Launcher Lock**: Sets itself as the default Home app. If the app is swiped away, it immediately restores itself to the foreground within 200ms.
- **Accessibility Service Guard**: Blocks access to the Notification Shade, Quick Settings, and Android Settings.
- **Device Owner Mode**: Integrates with Android's Device Policy Manager (DPM) to enforce hardware-level Lock Task Mode.
- **PIN Unlock & Countdown Timer**: Users input a 6-digit PIN obtained from the AcePoint Web App. Upon success, a countdown timer starts, unlocking the screen for the duration of the booking.
- **Tablet Optimization**: Fully optimized UI layouts for larger screens and tablets (`sw600dp`).
- **Auto-Launch External App**: Automatically launches a specified target application (e.g., YouTube) immediately upon successful PIN entry.
- **Persistent Heartbeat**: A foreground service continuously sends a heartbeat (every 15 seconds) to the Kiosk Server, reporting battery level and current state (LOCKED/IN_USE).
- **Remote Force Control**: Can receive remote "LOCK" or "UNLOCK" commands from the admin server via the heartbeat response.

## 🛠️ Tech Stack

- **Language**: Java
- **SDK**: Android SDK (minSdk 24 / targetSdk 36)
- **Core Services**: 
  - `AccessibilityService`
  - `DeviceAdminReceiver`
  - `ForegroundService`

## 🚀 Installation & Setup

### 1. Build and Install
1. Open the `TennisLockApp` folder in **Android Studio**.
2. Build the APK and install it on the target device via USB (USB Debugging enabled).

### 2. Grant Device Owner Permissions
**Important**: The device must NOT have any Google accounts logged in, and no screen lock (PIN/Pattern) should be set before running this command.
```bash
adb shell dpm set-device-owner com.example.tennislockapp/.LockDeviceAdminReceiver
```

### 3. Enable Accessibility Service
On the Android device:
Go to `Settings > Accessibility > Tennis Lock Accessibility Guard` and turn it **ON**.

### 4. Configure Kiosk Server IP
Open the `TennisLockApp`, access the hidden Admin Settings menu, and enter the local IP address of your Kiosk Server.
Example: `http://192.168.1.100:3000`

*(Note: The Android device and the Kiosk Server must be on the same local network).*

## 🔗 How It Works

1. User books a court on the Web App and receives a 6-digit PIN.
2. User goes to the court, types the PIN into this Android Kiosk app.
3. The app verifies the PIN with the local Kiosk Server via `/api/device/verify-pin`.
4. If valid, the app unlocks the screen, auto-launches the configured target app, and begins counting down the session time.
5. Once the time expires, the app forcibly locks the screen and resets the session.
