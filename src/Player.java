package src;

import src.services.PlayerService;

public class Player extends PlayerService {


    public Player(){

    }

    public Player(int id){
        this.url = "http://localhost:8080/players";
        this.getFromDb(id);
    }
    public Player(int id, String name, int stamina, int power,  boolean sex) {
        this._id = id;
        this.stamina = stamina;
        this.staminaMatch = stamina;
        this.power = power;
        this.name = name;
        this.points = 1;
        this.sex = sex;
    }



    public void setRank(int points) {
        this.points = points;
    }

    public void setStaminaMatch(int staminaMatch) {

        this.staminaMatch = staminaMatch;
    }

    public int getStamina() {
        return stamina;
    }

    public int getStaminaMatch() {
        return staminaMatch;
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public boolean getSex() {
        return sex;
    }

    public int getId() {
        return _id;
    }
}
