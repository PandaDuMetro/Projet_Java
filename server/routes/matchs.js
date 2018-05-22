const express = require('express');
const router = express.Router();
const config = require('../config/database');
const Match = require('../models/matchs');

router.post('/newmatch', (req, res, next) => {
  console.log('New Match');
  console.log(req.body);
  let match = new Match({
    player1: req.body.player1,
    player2: req.body.player2,
    winner: req.body.winner,
    tournois: req.body.tournois,
    ronde: req.body.ronde,
    points: req.body.points,
    sets: req.body.sets
  });
  Match.addMatch(match, (err, match) => {
    if(err) {
      res.json('Error');
    } else {
      res.json(match);
    }
  })
});

router.post('/getmatch', (req, res, next) => {
  console.log('Get Match');
  console.log(req.body);
  Match.getMatchById(req.body.id, (err, match) => {
    if(err) {
      res.json('Error : '+err);
    } else {
      res.json(match);
    }
  })
});

// Test function
router.post('/test', (req, res, next) => {
  console.log('test function');
  //console.log(req.body);
  res.send('Connection Sucess');
})

module.exports = router;