package src;

public class Joueurs {

    private final int stamina;
    private int staminaMatch;
    private int power;
    private String name;
    private int rank;
    private boolean sex; //0 = homme 1 = femme

    public Joueurs(int stamina, int power, String name, int rank, boolean sex) {
        this.stamina = stamina;
        this.staminaMatch = stamina;
        this.power = power;
        this.name = name;
        this.rank = rank;
        this.sex = sex;
    }




    public void setRank(int rank) {
        this.rank = rank;
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

    public int getRank() {
        return rank;
    }

    public boolean isSex() {
        return sex;
    }
}
