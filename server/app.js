const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');
const cors = require('cors');
const mongoose = require('mongoose');
const config = require('./config/database');

// Connect To Database
mongoose.connect(config.database);

// On Connection
mongoose.connection.on('connected', () => {
  console.log('Connected to database '+config.database);
});

// On Error
mongoose.connection.on('error', (err) => {
  console.log('Database error: '+err);
});

var app = express();

const players = require('./routes/players');
const matchs = require('./routes/matchs');

// Port Number
const port = 8080;

// CORS Middleware
app.use(cors());

// Set Static Folder
app.use(express.static(path.join(__dirname, 'public')));

// Body Parser Middleware
app.use(bodyParser.json());

// Set path
app.use('/players', players);
app.use('/matchs', matchs);

// Index Route
app.get('/', (req, res) => {
  res.send('Invalid Endpoint');
});

// Start Server
app.listen(port, () => {
    console.log('Server started on port '+port);
})
