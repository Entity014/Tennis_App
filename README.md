# 🎾 AcePoint — Tennis Court Booking & Kiosk Lock System

ระบบจองสนามเทนนิสออนไลน์และระบบล็อก Kiosk สำหรับอุปกรณ์ Android แบบครบวงจร ประกอบด้วย 2 ระบบหลักที่ทำงานประสานกัน:

| ระบบ                                | คำอธิบาย                                                |
| ----------------------------------- | ------------------------------------------------------- |
| **🌐 AcePoint Booking Web App**     | เว็บแอปจองคอร์ตสำหรับผู้ใช้งานทั่วไปและแผงควบคุม Admin  |
| **📱 TennisLock Kiosk Android App** | แอป Android ล็อก Kiosk สำหรับหน้าจอติดตั้งที่สนามเทนนิส |

---

## 🏗️ สถาปัตยกรรมรวมของระบบ (System Architecture)

```mermaid
graph TD
    subgraph "🌐 System 1: AcePoint Booking Platform (Next.js)"
        User["👤 ผู้ใช้/ลูกค้า\n(Web Browser)"]
        BookingWeb["💻 AcePoint Web App\n(HTML + Vanilla JS + CSS)"]
        BookingServer["⚙️ Next.js App Router API\n(Next.js Server Actions/Routes)"]
        Database[("🗄️ PostgreSQL DB\n(Prisma ORM)")]
        SlipAPI["💳 SLIP Verify API\n(Thunder / Slip2Go)"]
        SMTP["📧 SMTP Server\n(Nodemailer / Gmail)"]
    end

    subgraph "📱 System 2: TennisLock Kiosk"
        KioskDevice["📱 Android Tablet/Phone\n(Kiosk Device at Court)"]
        LockServer["⚙️ Kiosk API Server\n(Node.js + Express)"]
        KioskDB[("🗄️ Kiosk DB\n(SQLite)")]
        AdminDash["💻 Kiosk Admin Dashboard\n(Tailwind CSS UI)"]
    end

    User --> BookingWeb
    BookingWeb <--> BookingServer
    BookingServer --> Database
    BookingServer --> SlipAPI
    BookingServer --> SMTP

    AdminDash <--> LockServer
    LockServer <--> KioskDB
    KioskDevice <-->|"Heartbeat every 15s"| LockServer
```

---

## 📂 โครงสร้างโปรเจกต์ (Project Structure)

```
Tennis_App/
├── README.md                   # เอกสารหลักของโปรเจกต์
├── .env                        # ตัวแปรสภาพแวดล้อมหลัก (root-level)
├── .gitignore
│
├── booking-server/             # ⭐ ระบบที่ 1: AcePoint Booking Web App (Next.js)
│   ├── app/                    # Next.js App Router (API Endpoints, Layouts, Pages)
│   ├── lib/                    # Helper libraries (Prisma, Auth, SSE)
│   ├── prisma/                 # Prisma schema & seed script สำหรับ PostgreSQL
│   │   ├── schema.prisma
│   │   └── seed.js
│   ├── package.json            # Node.js dependencies
│   ├── .env                    # ตัวแปรสภาพแวดล้อมของ Booking Server (Next.js)
│   └── public/                 # Static frontend files
│       ├── index.html          # หน้าเว็บหลัก (SPA)
│       ├── app.js              # JavaScript logic ทั้งหมดของ Web App
│       └── style.css           # Stylesheet (Dark mode / Glassmorphism)
│
├── booking-server-express/     # ⭐ (Legacy Backup) ระบบที่ 1 เดิม (Express.js & SQLite)
│
├── server/                     # ⭐ ระบบที่ 2: TennisLock Kiosk Backend
│   ├── index.js                # Entry point ของ Kiosk API Server
│   ├── database.js             # SQLite schema และ helper functions
│   ├── package.json
│   ├── Dockerfile              # Docker image สำหรับ deploy
│   ├── docker-compose.yml      # Docker Compose config
│   ├── public/                 # Kiosk Admin Web Dashboard (Tailwind CSS)
│   └── routes/
│       ├── admin.js            # Admin endpoints (generate PIN, force lock)
│       └── device.js           # Device endpoints (register, heartbeat, verify-pin)
│
└── TennisLockApp/              # ⭐ ระบบที่ 2: TennisLock Kiosk Android App
    └── app/src/main/
        ├── AndroidManifest.xml
        └── java/com/example/tennislockapp/
            ├── MainActivity.java             # หน้าจอ PIN lock หลัก
            ├── LockForegroundService.java    # Countdown timer + Heartbeat (15s)
            ├── LockAccessibilityService.java # บล็อก Settings, Notification Shade
            ├── LockDeviceAdminReceiver.java  # Device Owner / Reboot handler
            └── MyHttpClient.java             # HTTP client สำหรับ API calls
```

---

# 🌐 ระบบที่ 1: AcePoint Booking Web App

เว็บแอปจองสนามเทนนิสออนไลน์แบบ Single Page Application รองรับการจองคอร์ต, ชำระเงินผ่าน PromptPay QR, ยืนยันสลิปอัตโนมัติ และแผงควบคุม Admin

## ✨ Features หลัก

### สำหรับผู้ใช้ทั่วไป

- **ค้นหาคอร์ตว่าง** — เลือกวันที่และดูช่องเวลาว่างแบบ Real-time
- **รองรับสิทธิ์การเลือกคอร์ตและเวลาของ Guest** — ผู้ที่ยังไม่ได้ล็อกอินสามารถเลือกคอร์ต ตารางเวลา และดูคำนวณยอดชำระเงินรวมได้ทันทีก่อนหน้าล็อกอินเพื่อชำระเงิน
- **ระบบปัดวันเริ่มต้นอัตโนมัติ (Next-Day Date Rollover)** — หากเปิดจองหลังเวลา 21:00 น. (เลยเวลาเริ่มสล็อตสุดท้ายของวัน) ระบบจะตั้งค่าเริ่มต้นการค้นหาและจำกัดปฏิทิน (minDate) ไปเป็นวันพรุ่งนี้อัตโนมัติ
- **จองคอร์ตได้สูงสุด 3 เดือนล่วงหน้า** — ป้องกันการจองย้อนหลัง
- **ชำระเงินผ่าน PromptPay QR** — สร้าง QR Code ตามราคาจริงอัตโนมัติ
- **ยืนยันสลิปอัตโนมัติ** — ตรวจสอบ QR code ในสลิปด้วย `jsQR` แบบ local และ Thunder Slip API
- **โปรโมชั่นโค้ด** — รองรับ promo code (เช่น `ACE10` = ลด 10%)
- **E-Ticket พร้อม PIN Code** — รับ PIN 6 หลักหลังการชำระเงิน สำหรับปลดล็อก Kiosk ที่สนาม
- **Dashboard ประวัติการจอง** — ดูและ reprint E-Ticket ได้
- **Social Login** — รองรับ Google OAuth
- **ลืมรหัสผ่าน (พร้อมการป้องกัน)** — รีเซ็ตผ่านอีเมล PIN 6 หลัก โดยมีการยืนยันตัวตนของอีเมลในระบบ และปุ่มสแปมล็อก Cooldown ป้องกันการกดซ้ำเป็นเวลา 30 วินาทีเมื่อส่งสำเร็จ

### สำหรับ Admin

- **แผงควบคุม Admin** — ดู stats, จัดการการจอง, จัดการคอร์ต, ดูผู้ใช้
- **ระบบจัดการโปรโมชั่นโค้ด (Promo Code Setting)** — จัดการโค้ดส่วนลด (จำกัดเฉพาะตัวอักษรภาษาอังกฤษและตัวเลข) พร้อมระบบเลือกวันที่แบบจำกัดไม่ให้เลือกอดีต
- **CRUD Courts** — เพิ่ม / แก้ไข / ลบคอร์ตแบบ Real-time
- **ดูรายการจองทั้งหมด** — พร้อม PIN code และสถานะการชำระเงิน
- **ลบ/ยกเลิกการจอง** — จัดการ bookings ได้โดยตรง

## 🛠️ Tech Stack

| Layer                | Technology                                                                               |
| -------------------- | ---------------------------------------------------------------------------------------- |
| **Frontend**         | HTML5, Vanilla JavaScript (ES6+), CSS3 (Custom Scrollbars / Theme Dropdown / Glow Icons) |
| **Backend**          | Next.js API Routes (App Router)                                                          |
| **Database**         | PostgreSQL + Prisma ORM (พร้อม Concurrency & Transaction Control)                        |
| **Auth**             | JWT (jsonwebtoken), bcryptjs                                                             |
| **Payment**          | PromptPay QR (EMV payload), Slip2Go Verify API, Client-Side QR Detection (jsQR)          |
| **Email**            | Nodemailer (SMTP / Gmail)                                                                |
| **Anti-Abuse Cache** | Slip Hash Cache (ป้องกันการใช้สลิปเดิมยึดตรวจซ้ำใน DB เพื่อเซฟโควตา API)                 |

## 🚀 การติดตั้งและรัน

### ข้อกำหนดเบื้องต้น

- Node.js v18 ขึ้นไป
- PostgreSQL database (พร้อมใช้สำหรับต่อ Prisma)
- npm

### ขั้นตอน

```bash
# 1. เข้าโฟลเดอร์ booking-server
cd booking-server

# 2. ติดตั้ง dependencies
npm install

# 3. คัดลอกและแก้ไขไฟล์ config (.env)
cp .env.example .env
# กรอกค่า DATABASE_URL และตัวแปรอื่นๆ ตามต้องการ

# 4. ซิงค์โครงสร้างฐานข้อมูลผ่าน Prisma
npx prisma db push

# 5. ใส่ข้อมูลเริ่มต้น (Seed Database)
npx prisma db seed

# 6. รันเซิร์ฟเวอร์สำหรับพัฒนา (Development Mode)
npm run dev

# 7. หรือทำการบิลด์และรันโหมดจริง (Production Mode)
npm run build
npm start
```

เว็บแอปจะเปิดที่: **http://localhost:3000** (หรือตามพอร์ตที่กำหนดใน Next.js)

### ตัวแปรสภาพแวดล้อม (`.env`)

```env
PORT=3000
DATABASE_URL="postgresql://user:password@localhost:5432/tennis_db?schema=public"
JWT_SECRET=your_jwt_secret_here

# Google OAuth (ถ้าต้องการใช้ Social Login)
GOOGLE_CLIENT_ID=your_google_client_id

# Thunder / Slip2Go Verify API (ถ้าไม่ใส่จะเป็น Simulation Mode อัตโนมัติ)
SLIP_API_KEY=your_slip_api_key

# PromptPay รับเงิน (เบอร์โทรหรือเลขบัตรประชาชน)
PROMPAY_RECEIVER_ID=0912345678

# SMTP สำหรับส่งอีเมลรีเซ็ตรหัสผ่าน
SMTP_HOST=smtp.gmail.com
SMTP_PORT=587
SMTP_USER=your_email@gmail.com
SMTP_PASS=your_app_password
```

### บัญชีเริ่มต้น (Seed Accounts)

ระบบจะสร้างบัญชีเริ่มต้นเมื่อกดรันคำสั่ง Seed:

| Username  | Password    | Role  |
| --------- | ----------- | ----- |
| `player1` | `player123` | user  |
| `admin`   | `admin123`  | admin |

> [!WARNING]
> เปลี่ยนรหัสผ่านเหล่านี้ทันทีก่อนใช้งานจริงบนอินเทอร์เน็ต

## 📡 Booking API Reference

### Public Endpoints

| Method | Endpoint                      | Description                                    |
| ------ | ----------------------------- | ---------------------------------------------- |
| `GET`  | `/api/config`                 | ดึงค่า config (Google Client ID, PromptPay ID) |
| `GET`  | `/api/courts?date=YYYY-MM-DD` | ดึงรายการคอร์ตพร้อม booked slots               |
| `POST` | `/api/auth/register`          | ลงทะเบียนผู้ใช้ใหม่                            |
| `POST` | `/api/auth/login`             | เข้าสู่ระบบ                                    |
| `POST` | `/api/auth/google-login`      | เข้าสู่ระบบด้วย Google                         |
| `POST` | `/api/auth/forgot-password`   | ขอรีเซ็ตรหัสผ่าน (ส่ง PIN ทางอีเมล)            |
| `POST` | `/api/auth/reset-password`    | รีเซ็ตรหัสผ่านด้วย PIN                         |

### Protected Endpoints (ต้องใช้ JWT Token)

| Method | Endpoint                    | Description                        |
| ------ | --------------------------- | ---------------------------------- |
| `GET`  | `/api/auth/me`              | ดึงข้อมูลผู้ใช้ปัจจุบัน            |
| `POST` | `/api/bookings`             | สร้างการจองใหม่                    |
| `GET`  | `/api/bookings/my-bookings` | ดูประวัติการจองของตัวเอง           |
| `GET`  | `/api/bookings/:id`         | ดูรายละเอียดการจอง                 |
| `POST` | `/api/payment/:bookingId`   | ชำระเงิน (พร้อม slip verification) |

### Admin Endpoints (ต้องมี role = `admin`)

| Method   | Endpoint                  | Description            |
| -------- | ------------------------- | ---------------------- |
| `GET`    | `/api/admin/stats`        | ดู stats ภาพรวม        |
| `GET`    | `/api/admin/bookings`     | ดูการจองทั้งหมด        |
| `DELETE` | `/api/admin/bookings/:id` | ลบการจอง               |
| `GET`    | `/api/admin/courts`       | ดูรายการคอร์ตทั้งหมด   |
| `POST`   | `/api/admin/courts`       | เพิ่มคอร์ตใหม่         |
| `PUT`    | `/api/admin/courts/:id`   | แก้ไขคอร์ต             |
| `DELETE` | `/api/admin/courts/:id`   | ลบคอร์ต                |
| `GET`    | `/api/admin/users`        | ดูรายชื่อผู้ใช้ทั้งหมด |

## 🔄 Database Concurrency & Architecture

ระบบที่ 1 (Next.js) ได้รับการอัปเกรดระบบฐานข้อมูลจาก SQLite เดิมเป็น **PostgreSQL** โดยสื่อสารผ่าน **Prisma ORM** เพื่อตอบโจทย์ความคงเส้นคงวาและความปลอดภัยในการจองสนาม:

```
[Web UI Request] ──► [Next.js API Route] ──► [Prisma Client] ──► [PostgreSQL Database]
                                                                        │
                                                         (Serializable Transaction & Locks ป้องกันการจองซ้ำซ้อน)
```

ส่วนเซิร์ฟเวอร์ระบบที่ 2 (Kiosk Server) ยังคงใช้ SQLite เพื่อความเบาตัวและง่ายต่อการรันบนอุปกรณ์ Kiosk หน้าสนาม (Dockerized)

## 🔒 ความปลอดภัยและระบบป้องกัน (Security & Concurrency)

เราให้ความสำคัญกับความปลอดภัยและเสถียรภาพของระบบ ได้มีการอุดช่องโหว่และเพิ่มการป้องกันดังนี้:

- **ป้องกันการจองซ้ำซ้อน (Double Booking / Race Condition):** ระบบใช้ `Prisma $transaction` ในระดับ `isolationLevel: 'Serializable'` เพื่อล็อกข้อมูล ป้องกันกรณีผู้ใช้หลายคนกดจองคอร์ตและเวลาเดียวกันพร้อมๆ กัน
- **ป้องกันการโจมตีค่าธรรมเนียมติดลบ (Negative Duration Bug):** ระบบตรวจสอบและบังคับให้เวลาสิ้นสุด (`end_time`) ต้องมากกว่าเวลาเริ่มต้นเสมอ (`duration > 0`) ก่อนนำไปคำนวณราคา
- **ป้องกันการเจาะระบบแผงควบคุม Kiosk (API Authentication):** ทุก Endpoint ของ Kiosk Admin API (`/api/admin/*`) ถูกล็อกด้วย Middleware Authentication โดยต้องแนบ Header `Authorization: Bearer <KIOSK_SYNC_SECRET>` ป้องกันผู้ไม่หวังดีในเครือข่าย Network เข้ามายึดเครื่อง หรือสั่ง Force Unlock

## 🔐 Payment & Slip Verification Flow

```mermaid
sequenceDiagram
    actor User
    participant Web as Web App (Client)
    participant Server as Next.js Server
    participant DB as PostgreSQL (DB)
    participant SlipAPI as Thunder / Slip2Go API

    User->>Web: เลือกคอร์ท + เวลา + กด Book
    Web->>Server: POST /api/bookings
    Server-->>Web: booking ID + ยอดเงินสุทธิ
    Web->>User: แสดงหน้าสแกนจ่าย PromptPay QR Code
    User->>User: สแกนโอนเงินและบันทึกรูปสลิป
    User->>Web: อัปโหลดรูปสลิป
    Note over Web: ตรวจหา QR Code ในสลิปก่อนส่งด้วย jsQR
    alt ไม่พบ QR Code ในรูปภาพ
        Web->>User: แจ้งเตือน: "รูปสลิปนี้ไม่มีคิวอาร์โค้ด" (ไม่ยิง API ประหยัดโควตา)
    else พบ QR Code ในรูปภาพ
        Web->>Server: POST /api/payment/:id + ข้อมูลรูป (Base64)
        Note over Server: คำนวณ Hash (SHA-256) ของไฟล์สลิป
        Server->>DB: ตรวจสอบประวัติสลิปจาก Hash
        alt สลิปเคยใช้แล้ว (Duplicate Slip Hash)
            DB-->>Server: พบแฮชซ้ำในระบบ
            Server-->>Web: ส่งข้อผิดพลาดกลับ (ไม่อนุญาต/ไม่ตรวจซ้ำ)
        else สลิปใหม่
            Server->>SlipAPI: ยิงตรวจสอบสลิปผ่าน API
            SlipAPI-->>Server: ผลตอบรับ (จำนวนเงิน, ผู้รับโอน, หมายเลขอ้างอิง)
            alt ข้อมูลสลิปถูกต้อง
                Server->>DB: บันทึกประวัติสลิป (บันทึกแฮชกันการสปัม) + อัปเดต Booking
                Server-->>Web: ยืนยันสำเร็จ (Payment Success) + PIN 6 หลัก
                Web->>User: แสดง E-Ticket พร้อม PIN ปลดล็อก Kiosk หน้าสนาม
            else สลิปไม่ถูกต้อง (แต่ยิงแล้ว)
                Server->>DB: บันทึกแฮชสลิปนี้ (เพื่อแบนรูปเดิมไม่ให้กดสปัมตรวจสอบซ้ำ)
                Server-->>Web: ส่งข้อผิดพลาดกลับ (ข้อมูลสลิปไม่ตรงเงื่อนไข)
            end
        end
    end
```

---

# 📱 ระบบที่ 2: TennisLock Kiosk System

ระบบ Kiosk ล็อกหน้าจออุปกรณ์ Android สำหรับติดตั้งที่สนามเทนนิส ผู้เช่าสนามจะปลดล็อกหน้าจอด้วย PIN 6 หลักที่ได้จากระบบจองในระบบที่ 1

## ✨ Features หลัก

### Android Kiosk App (`TennisLockApp`)

- **Tablet Optimization** — รองรับและปรับแต่งหน้าต่าง UI ให้เหมาะสมกับอุปกรณ์จอใหญ่ (Tablet) เช่น sw600dp
- **Relentless Launcher Lock** — ตั้งตัวเองเป็น Default Launcher, หน้าจอล็อก Kiosk เปิดขึ้นทันทีเมื่อเครื่อง boot หากผู้ใช้ปัดแอปออก ระบบจะดึงกลับมาภายใน **200ms**
- **Accessibility Service Guard** — บล็อกการเข้า Settings, Package Installer และการลากแถบ Notification Shade / Quick Settings
- **Persistent Foreground Service** — นับถอยหลังเวลาใช้งาน (Countdown Timer) และส่ง Heartbeat ไปยังเซิร์ฟเวอร์ทุก **15 วินาที**
- **Device Owner Integration** — รองรับสิทธิ์สูงสุด (Device Owner) สำหรับ Lock Task Mode ระดับฮาร์ดแวร์
- **App Auto-Launch** — เปิดแอปพลิเคชันเป้าหมาย (เช่น YouTube) อัตโนมัติทันทีที่กรอก PIN ปลดล็อกสำเร็จ
- **Remote Command** — รับคำสั่ง Force Lock / Force Unlock จากเซิร์ฟเวอร์ผ่าน Heartbeat response

### Kiosk Backend Server (`server/`)

- **PIN Generator** — สร้าง PIN 6 หลักพร้อมกำหนดเวลาหมดอายุ
- **Auto-Expiration** — PIN ที่ไม่ได้ใช้ใน **10 นาที** จะ expire อัตโนมัติ
- **Real-time Status** — ติดตามสถานะอุปกรณ์ (ONLINE/OFFLINE/IN_USE/LOCKED) และแบตเตอรี่
- **Admin Dashboard** — หน้าจอควบคุม Tailwind CSS สำหรับ Admin พร้อมระบบตั้งค่า **Target App Package** (เช่น `com.google.android.youtube`) สำหรับแอปหน้า Kiosk

## 🛠️ Tech Stack

| Layer                | Technology                                                   |
| -------------------- | ------------------------------------------------------------ |
| **Android App**      | Java, Android SDK (minSdk 24 / targetSdk 36)                 |
| **Android Services** | AccessibilityService, DeviceAdminReceiver, ForegroundService |
| **Kiosk Backend**    | Node.js, Express.js, SQLite3                                 |
| **Admin Dashboard**  | HTML + Tailwind CSS                                          |
| **Deployment**       | Docker + Docker Compose                                      |

## 🚀 การติดตั้ง Kiosk Backend Server

### วิธี A: ผ่าน Docker (แนะนำสำหรับ Production)

```bash
cd server
docker-compose up -d --build
```

ระบบจะรันที่พอร์ต `3000` และ database จะถูกบันทึกไว้ที่ `./data/database.sqlite`

### วิธี B: รันตรงด้วย Node.js

```bash
cd server
npm install
node index.js
```

Admin Dashboard จะเปิดที่: **http://localhost:3000**

## 📲 การติดตั้ง Android Kiosk App

### ขั้นตอนที่ 1: Build และติดตั้งแอป

1. เปิดโฟลเดอร์ `TennisLockApp` ใน **Android Studio**
2. รอ Gradle sync เสร็จสิ้น
3. เชื่อมต่ออุปกรณ์ Android ผ่าน USB (เปิด USB Debugging)
4. กด **Run** หรือ `Shift+F10` เพื่อ build และติดตั้ง

### ขั้นตอนที่ 2: ลงทะเบียนสิทธิ์ Device Owner

> [!IMPORTANT]
> ก่อนรันคำสั่ง Device Owner ให้ตรวจสอบว่า:
>
> - **ไม่มีบัญชี Google** ล็อกอินอยู่ในเครื่อง (ลบออกชั่วคราวได้ใน Settings > Accounts)
> - ไม่มี PIN/Pattern ล็อกหน้าจอตั้งไว้

```bash
adb shell dpm set-device-owner com.example.tennislockapp/.LockDeviceAdminReceiver
```

### ขั้นตอนที่ 3: เปิดใช้งาน Accessibility Service

บนอุปกรณ์ Android:

```
Settings > Accessibility > Tennis Lock Accessibility Guard > เปิดใช้งาน
```

### ขั้นตอนที่ 4: ตั้งค่า Server IP

เปิดแอป `TennisLockApp` บนเครื่อง → เข้าหน้า Admin Settings → กรอก IP ของ Kiosk Server เช่น:

```
http://192.168.1.100:3000
```

> [!NOTE]
> อุปกรณ์ Android และ Kiosk Server ต้องอยู่ใน Wi-Fi วงเดียวกัน

## 📡 Kiosk API Reference

### Device Endpoints (เรียกจากแอป Android)

| Method | Endpoint                 | Description              | Payload                                                         |
| ------ | ------------------------ | ------------------------ | --------------------------------------------------------------- |
| `POST` | `/api/device/register`   | ลงทะเบียนอุปกรณ์ครั้งแรก | `{"device_id": "unique-id"}`                                    |
| `POST` | `/api/device/verify-pin` | ตรวจสอบ PIN ปลดล็อก      | `{"device_id": "...", "pin": "123456"}`                         |
| `POST` | `/api/device/heartbeat`  | รายงานสถานะทุก 15 วินาที | `{"device_id": "...", "battery_level": 85, "status": "IN_USE"}` |

### Admin Endpoints (เรียกจาก Web Dashboard)

> [!WARNING]
> ทุก Endpoint ของ Admin ได้รับการปกป้องด้วย Authentication Middleware ผู้เรียกต้องแนบ Header `Authorization: Bearer <KIOSK_SYNC_SECRET>` เสมอ เพื่อป้องกันผู้ไม่หวังดีเข้ามาควบคุมระบบในเครือข่ายเดียวกัน

| Method   | Endpoint                      | Description               | Payload / Params                      |
| -------- | ----------------------------- | ------------------------- | ------------------------------------- |
| `GET`    | `/api/admin/devices`          | ดูรายการอุปกรณ์ทั้งหมด    | —                                     |
| `POST`   | `/api/admin/generate-pin`     | สร้าง PIN ใหม่            | `{"duration_minutes": 60}`            |
| `GET`    | `/api/admin/sessions`         | ดูประวัติ PIN/เซสชัน      | `?limit=10&offset=0`                  |
| `POST`   | `/api/admin/device/:id/force` | สั่งการอุปกรณ์ด่วน        | `{"command": "LOCK"}` หรือ `"UNLOCK"` |
| `DELETE` | `/api/admin/devices`          | ล้างรายชื่ออุปกรณ์ทั้งหมด | —                                     |
| `DELETE` | `/api/admin/sessions`         | ล้างประวัติ PIN ทั้งหมด   | —                                     |

## 🔄 Workflow Scenarios

### Scenario A: ผู้เช่าสนามปลดล็อก Kiosk

```mermaid
sequenceDiagram
    autonumber
    actor Admin as 👨‍💼 Admin (Web Dashboard)
    actor User as 🏸 ผู้เช่าสนาม
    participant Server as ⚙️ Kiosk Server
    participant Device as 📱 Android Kiosk

    Admin->>Server: สร้าง PIN (ระบุเวลาเช่า เช่น 60 นาที)
    Server-->>Admin: ส่ง PIN กลับ (เช่น "582910")
    Admin->>User: มอบ PIN ให้ผู้เช่า
    User->>Device: กรอก PIN ที่หน้าจอ Kiosk
    Device->>Server: POST /api/device/verify-pin
    Server-->>Device: ยืนยันผ่าน! ส่งค่าเวลาใช้งาน (60 mins)
    Device->>Device: สถานะ IN_USE, ปลดบล็อก, เริ่มนับถอยหลัง
```

### Scenario B: หมดเวลา หรือ Admin สั่ง Force Lock

```mermaid
sequenceDiagram
    autonumber
    actor Admin as 👨‍💼 Admin (Web Dashboard)
    participant Server as ⚙️ Kiosk Server
    participant Device as 📱 Android Kiosk

    Note over Device: Countdown ครบ 60 นาที
    Device->>Device: stopSession() → ล็อกหน้าจอทันที

    Note over Admin,Device: หรือแอดมินสั่ง Force Lock
    Admin->>Server: POST /api/admin/device/:id/force {"command":"LOCK"}
    Server->>Server: บันทึก pending_command = 'LOCK'
    Device->>Server: Heartbeat (วินาทีที่ 15)
    Server-->>Device: ตรวจพบ pending_command LOCK
    Device->>Device: ปิดเซสชัน → กลับสู่หน้าจอล็อก Kiosk
```

---

## 🔗 ความสัมพันธ์ระหว่าง 2 ระบบ

ทั้ง 2 ระบบทำงานแยกกันแต่เชื่อมกันผ่าน **PIN Code**:

```
[AcePoint Booking Web App]          [TennisLock Kiosk App]
         |                                    |
  ผู้ใช้จองคอร์ต                      หน้าจอติดที่สนาม
  ชำระเงินสลิป ✅                     รอรับ PIN
         |                                    |
  ได้รับ E-Ticket                           |
  + PIN 6 หลัก  ────────────────────► กรอก PIN ปลดล็อก
                                       ใช้สนามได้ตามเวลา
```

> [!NOTE]
> PIN ที่แสดงใน E-Ticket ของระบบจองสามารถนำไปใช้กับหน้าจอ Kiosk ได้โดยตรง โดย Admin เป็นผู้เชื่อมข้อมูลระหว่าง 2 ระบบ

---

## 🛠️ Troubleshooting

### Booking Web App

| ปัญหา                               | แนวทางแก้ไข                                                                   |
| ----------------------------------- | ----------------------------------------------------------------------------- |
| แก้ไข court แล้วไม่อัพเดท           | ทำ Hard Refresh (`Ctrl+Shift+R`) ครั้งแรกหลัง restart server ล่าสุด           |
| ยืนยันสลิปไม่ผ่าน                   | ตรวจสอบว่า `SLIP_API_KEY` ใน `.env` ถูกต้อง หรือลบออกเพื่อใช้ Simulation Mode |
| ไม่ได้รับอีเมล reset PIN            | ตรวจสอบค่า SMTP ใน `.env` และ Gmail App Password                              |
| Error: "Timeslot is already booked" | มีการจองซ้อนในช่วงเวลานั้นแล้ว ลองเลือกช่วงอื่น                               |

### TennisLock Kiosk

| ปัญหา                          | แนวทางแก้ไข                                                |
| ------------------------------ | ---------------------------------------------------------- |
| คำสั่ง Device Owner ขึ้น error | ลบบัญชี Google และ PIN ล็อกหน้าจอออกก่อน แล้วรันใหม่       |
| แอปไม่บล็อก Notification Shade | ตรวจสอบว่า Accessibility Service เปิดใช้งานอยู่            |
| Heartbeat ส่งไม่ผ่าน           | ตรวจสอบ Server IP ในแอป และ Wi-Fi ให้อยู่วงเดียวกับ Server |
| ผู้ใช้ปัดแอปออกได้ชั่วคราว     | ตรวจสอบว่าตั้ง `TennisLockApp` เป็น Default Launcher แล้ว  |

---

## 📄 License

MIT License — © 2026 AcePoint Tennis Platform
