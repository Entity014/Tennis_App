const express = require('express');
const cors = require('cors');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());

// Serve static files for Web Dashboard
app.use(express.static(path.join(__dirname, 'public')));

// API Routes
app.use('/api/device', require('./routes/device'));
app.use('/api/admin', require('./routes/admin'));

// Fallback to Dashboard
app.use((req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Start Server
const server = app.listen(PORT, () => {
  console.log(`[Server] Running on http://localhost:${PORT}`);
});

server.on('error', (err) => {
  if (err.code === 'EADDRINUSE') {
    console.error(`[Error] Port ${PORT} is already in use. Please kill the process using it or choose another port.`);
  } else {
    console.error(`[Error] Server error:`, err);
  }
  process.exit(1);
});
