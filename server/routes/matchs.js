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
    nameTournament: req.body.nameTournament,
    ronde: req.body.ronde,
    points: req.body.points,
    sets: req.body.sets,
    sex: req.body.sex
  });
  Match.addMatch(match, (err, match) => {
    if(err) {
      res.json('Error');
    } else {
      res.send(match._id);
    }
  })
});

router.post('/getmatch', (req, res, next) => {
  Match.findById(req.body.id, (err, match) => {
    if(err) {
      res.json('Error : '+err);
    } else {
      res.json(match);
    }
  })
});

router.post('/getbytrs', (req, res, next) => {
  var query = {sex: req.body.sex, nameTournament: req.body.nameTournament, ronde: req.body.ronde}
  Match.find(query, (err, data) => {
    if(err) throw err;
    res.json(data);
  });
});

router.post('/getbyname', (req, res, next) => {
  Match.find({$or:[
    {player1: req.body.name},
    {player2: req.body.name}
  ]}, (err, data) => {
    if(err) throw err;
    res.json(data);
  });
});


router.get('/remiseazero', (req, res, body) => {
  Match.remove({}, (err, res) => {
    if (err) throw err;
    console.log(res);
  })
});

// Test function
router.post('/test', (req, res, next) => {
  console.log('test function');
  //console.log(req.body);
  res.send('Connection Sucess');
})

module.exports = router;
