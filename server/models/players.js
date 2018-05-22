const mongoose = require('mongoose');
const config = require('../config/database');

// User Schema
const PlayerSchema = mongoose.Schema({
  name: {
    type: String
  },
  power: {
    type: Number
  },
  stamina: {
    type: Number
  },
  sex: {
    type: Boolean
  },
  points: {
    type: Number
  }
});

const Player = module.exports = mongoose.model('Player', PlayerSchema);


module.exports.getPlayerByName = function(name, callback){
  const query = {name: name};
  Player.findOne(query, callback);
};

module.exports.addPlayer = function(newPlayer, callback){
  newPlayer.save(callback);
};
