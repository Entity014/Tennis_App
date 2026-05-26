# 🌐 Mini Tennis Booking Web App

This is the Booking Server component for the Mini Tennis Court Booking system, built with [Next.js](https://nextjs.org/) (App Router).

## 🚀 Features

- **Real-time Court Booking**: Users can view court availability and book slots in real-time.
- **Next-Day Date Rollover**: Automatically shifts the minimum booking date to the next day after 21:00.
- **Secure Transactions**: Built with Prisma and PostgreSQL, utilizing `$transaction` with `Serializable` isolation level to prevent double-booking race conditions.
- **Payment & Slip Verification**: Integrated with PromptPay QR and Thunder Slip Verify API, featuring local `jsQR` verification to save API quotas.
- **Promo Code Management**: Admin panel to create and manage alphanumeric promo codes with strict date restrictions.
- **Admin Dashboard**: Comprehensive dashboard for managing bookings, courts, users, and system configurations.
- **PIN Code Generation**: Generates a 6-digit PIN upon successful booking for integration with the TennisLock Kiosk system.

## 🛠️ Technologies Used

- **Framework**: Next.js (App Router)
- **Database**: PostgreSQL
- **ORM**: Prisma
- **Styling**: Vanilla CSS (Custom styling, Dark mode, Glassmorphism)
- **Authentication**: JWT, bcryptjs, Google OAuth
- **Email**: Nodemailer

## 📦 Getting Started

### Prerequisites

- Node.js v18+
- PostgreSQL database
- `npm` or `yarn`

### Installation

1. Install dependencies:
   ```bash
   npm install
   ```

2. Copy `.env.example` to `.env` and fill in your variables:
   ```bash
   cp .env.example .env
   ```
   *Make sure to set `DATABASE_URL`, `JWT_SECRET`, `SLIP_API_KEY`, etc.*

3. Push the Prisma schema to your database:
   ```bash
   npx prisma db push
   ```

4. Seed the database with initial data (admin/user accounts):
   ```bash
   npx prisma db seed
   ```

5. Run the development server:
   ```bash
   npm run dev
   ```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

## 🔐 Security Considerations

- **Booking Concurrency**: All booking insertions use strict database locks.
- **Validation**: Strict validation prevents negative duration bugs and ensures past dates cannot be selected.
- **Admin Authentication**: All `/api/admin/*` routes require proper JWT authentication and role validation.

## 🔗 Integration with Kiosk System

This web app generates the 6-digit PIN code which the user then inputs into the `TennisLockApp` kiosk system located at the courts. The two systems remain loosely coupled through this generated PIN.
