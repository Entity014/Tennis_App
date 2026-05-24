const { PrismaClient } = require('@prisma/client');
const { PrismaPg } = require('@prisma/adapter-pg');
const pg = require('pg');
const bcrypt = require('bcryptjs');

const pool = new pg.Pool({
  connectionString: process.env.DATABASE_URL,
});
const adapter = new PrismaPg(pool);
const prisma = new PrismaClient({ adapter });

async function main() {
  console.log('Seeding initial courts...');

  const initialCourts = [
    {
      name: 'Grand Slam Court A',
      pricePerHour: 600,
      description: 'Premium court with advanced lighting and professional ventilation systems.',
      imageName: 'court_indoor_a'
    },
    {
      name: 'Vanguard Court B',
      pricePerHour: 400,
      description: 'Beautiful outdoor court under floodlights, perfect for standard match play.',
      imageName: 'court_outdoor_b'
    },
    {
      name: 'Wimbledon Arena C',
      pricePerHour: 800,
      description: 'Championship-grade indoor arena with turf feedback and full climate control.',
      imageName: 'court_indoor_c'
    },
    {
      name: 'Apex Court D',
      pricePerHour: 500,
      description: 'Standard outdoor court designed to offer optimal joint safety and ball rebound speed.',
      imageName: 'court_outdoor_d'
    }
  ];

  for (const court of initialCourts) {
    await prisma.court.upsert({
      where: { id: initialCourts.indexOf(court) + 1 },
      update: {},
      create: {
        name: court.name,
        pricePerHour: court.pricePerHour,
        description: court.description,
        imageName: court.imageName
      }
    });
  }

  console.log('Seeding default users...');
  const salt = await bcrypt.genSalt(10);
  const playerPass = await bcrypt.hash('player123', salt);
  const modPass = await bcrypt.hash('@minitennis_mod123', salt);
  const adminPass = await bcrypt.hash('@minitennis_admin123', salt);

  const defaultUsers = [
    {
      username: 'player1',
      email: 'player1@tennis.com',
      passwordHash: playerPass,
      role: 'user'
    },
    {
      username: 'Minitennis_Mod',
      email: 'minitennis_mod@tennis.com',
      passwordHash: modPass,
      role: 'mod'
    },
    {
      username: 'Minitennis_Admin',
      email: 'minitennis_admin@tennis.com',
      passwordHash: adminPass,
      role: 'admin'
    }
  ];

  for (const user of defaultUsers) {
    await prisma.user.upsert({
      where: { email: user.email },
      update: {},
      create: {
        username: user.username,
        email: user.email,
        passwordHash: user.passwordHash,
        role: user.role
      }
    });
  }

  console.log('Database seeding completed successfully.');
}

main()
  .then(async () => {
    await prisma.$disconnect();
  })
  .catch(async (e) => {
    console.error('Error during seeding:', e);
    await prisma.$disconnect();
    process.exit(1);
  });
