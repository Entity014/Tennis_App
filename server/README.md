# ⚙️ TennisLock Kiosk API Server

This is the backend API server for the TennisLock Kiosk system. It acts as the local control server, tracking the states of all connected Android Kiosks at the tennis courts, managing PIN verification, and providing a dashboard for local administration.

## ✨ Features

- **Device Management**: Registers devices and tracks their real-time heartbeat, battery level, and current state (`ONLINE`, `IN_USE`, `LOCKED`, etc.).
- **PIN Verification**: Validates the 6-digit PINs entered on the Android Kiosk against the generated list.
- **Auto-Expiration**: Unused PINs expire automatically after 10 minutes to maintain security.
- **Remote Force Controls**: Sends commands (e.g., `LOCK` or `UNLOCK`) to connected devices during their heartbeat sync.
- **Admin Dashboard**: A local Tailwind CSS-based web dashboard to monitor devices, manage sessions, generate new PINs, and configure target auto-launch apps (e.g., PusunTennis) for the Kiosk.
- **Secure Admin API**: All admin endpoints are protected by an authentication middleware requiring a `Bearer <KIOSK_SYNC_SECRET>`.

## 🛠️ Tech Stack

- **Runtime**: Node.js
- **Framework**: Express.js
- **Database**: SQLite3
- **Frontend Dashboard**: HTML + Tailwind CSS
- **Containerization**: Docker & Docker Compose

## 🚀 Installation & Setup

### Method A: Docker (Recommended for Production)

1. Ensure Docker and Docker Compose are installed.
2. Run the build and startup command:
   ```bash
   docker-compose up -d --build
   ```
The server will run on port `3000`, and the SQLite database will be mounted in `./data`.

### Method B: Native Node.js

1. Install dependencies:
   ```bash
   npm install
   ```
2. Run the server:
   ```bash
   node index.js
   ```

## 📡 API Endpoints Overview

### Device API (`/api/device/*`)
*Used by the Android App to communicate with the server.*
- `POST /register`: Register a new device.
- `POST /verify-pin`: Validate an entered PIN.
- `POST /heartbeat`: Send real-time device status and retrieve pending remote commands.

### Admin API (`/api/admin/*`)
*Protected endpoints used by the Admin Dashboard. Requires `Authorization: Bearer <KIOSK_SYNC_SECRET>`.*
- `GET /devices`: List all registered devices.
- `POST /generate-pin`: Create a new PIN with a specified duration.
- `POST /device/:id/force`: Send a remote lock/unlock command to a specific device.
- `GET /sessions`: View active and past sessions.

## 🔗 Connection to Web App
While the AcePoint Booking Web App handles payment and generates the PINs logic conceptually, this Kiosk Server is the source of truth for the local devices at the physical court location. Admins can sync PINs between the online Web App and this local Kiosk Server manually or through secure API integration.
