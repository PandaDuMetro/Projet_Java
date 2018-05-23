const express = require('express');
const router = express.Router();
const config = require('../config/database');
const Player = require('../models/players');

// Add Player
router.post('/newplayer', (req, res, next) => {
  console.log(req.body);
  let player = new Player({
    _id: req.body._id,
    name: req.body.name,
    stamina: req.body.stamina,
    power: req.body.power,
    sex: req.body.sex,
    points: req.body.points
  });

  Player.addPlayer(player, (err, player) => {
    if (err) {
      res.json({success: false, msg: err});
    } else {
      res.json(player);
    }
  });
});

//Remove Player
router.post('/removeplayer', (req, res, next) => {
  Player.remove({_id: req.body.id}, (err) => {
    if(err) res.send(err);
    else res.send("true")
  })
});

// Profile
router.post('/getplayer', (req, res, next) => {
  Player.getPlayerByName(req.body.name, (err, user) => {
    if(err) {
      res.json({success: false, msg: 'User not found'});
    }
    res.json(user);
  });
});

router.post('/getall', (req, res, next) => {
  Player.find({sex: req.body.sex}, (err, next) => {
    if(err) throw err;
    res.json(next);
  })
});

router.post('/updatepoints', (req, res, next) => {
  console.log('update points ');
  console.log(req.body);
  Player.findById(req.body.id, (err, player) => {
      player.points = req.body.points;
      player.histPoints.push(req.body.points);
      player.save(err => {
        if(err) throw err;
        res.sendStatus(200);
      });
  });
});

router.post('/isinitiated', (req, res, next) => {
  Player.find({}, (err, all) => {
    if(err){
      res.json({success: false, msg: err});
    } else if(all.length >=128){
      res.json({success: true, msg: 'DB initiated'});
    } else res.json({success: false, msg: 'DB not initiated'});
  })
});

router.post('/remiseazero', (req, res, body) => {
  Player.remove({}, (err, data) => {
    if (err) throw err;
    res.json(data);
  });
});


module.exports = router;
