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
    nbRonde: req.body.nbRonde,
    points: req.body.points,
    sets: req.body.sets,
    sex: req.body.sex
  });
  Match.addMatch(match, (err, match) => {
    if(err) {
      res.sendStatus(400);
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
  console.log(req.body);
  var query = {sex: req.body.sex, nameTournament: req.body.nameTournament, nbRonde: req.body.nbRonde}
  Match.find(query, (err, data) => {
    console.log(data);
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


router.post('/remiseazero', (req, res, body) => {
  console.log("supprimer les matchs");
  Match.remove({}, (err, data) => {
    if (err) throw err;
    res.sendStatus(200);
    console.log(data);
  })
});


module.exports = router;
