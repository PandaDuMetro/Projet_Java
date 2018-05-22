package src;

import src.services.PlayerService;

public class Player extends PlayerService {


    public Player(String name) {
        super();
        this.getFromDb(name);
    }

    public Player( String name, int stamina, int power, boolean sex) {
        super();
        this.stamina = stamina;
        this.staminaMatch = stamina;
        this.power = power;
        this.name = name;
        this.points = 1;
        this.sex = sex;
    }


    public void setRank(int points) {

        this.points = points;
        this.updateDb();
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

    public String getId() {
        return _id;
    }
    @Override
    public String toString() {
        return "Player{" +
                "stamina=" + stamina +
                ", staminaMatch=" + staminaMatch +
                ", power=" + power +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", sex=" + sex +
                ", _id='" + _id + '\'' +
                '}';
    }
}
