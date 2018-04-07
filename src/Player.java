package src;

public class Player {

    private final int stamina;
    private int staminaMatch;
    private int power;
    private String name;
    private int points;
    private boolean sex; //0 = homme,  1 = femme
    private int id;

    public Player(int id, String name, int stamina, int power,  boolean sex) {
        this.id = id;
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
        return id;
    }
}
