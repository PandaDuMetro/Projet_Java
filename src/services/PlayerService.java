package src.services;

import com.google.gson.Gson;
import src.Player;

public abstract class PlayerService extends BddService {

    protected int stamina;
    protected int staminaMatch;
    protected int power;
    protected String name;
    protected int points;
    protected boolean sex; //0 = homme,  1 = femme
    protected String _id;
    protected int[] histPoints;


    public void addToDb(){
        Gson json = new Gson();
        this.executePost("http://localhost:8080/players/newplayer", json.toJson(this));
    }

    public void getFromDb(String name){
        this.executePost("http://localhost:8080/players/getplayer", "{\"name\":\""+name+"\"}");
        Gson json = new Gson();
        PlayerService elts = json.fromJson(this.serverResponse, Player.class);
        this.name = ((Player) elts).getName();
        this._id = ((Player) elts).getId();
        this.stamina = ((Player) elts).getStamina();
        this.power = ((Player) elts).getPower();
        this.points = ((Player) elts).getPoints();
        this.sex = ((Player) elts).getSex();
        this.histPoints = ((Player) elts).getHistPoints();
    }

    public void updateDb(){
        this.executePost("http://localhost:8080/players/updatepoints", "{\"id\": \""+this._id+"\",\"points\": "+this.points+"}");
    }
}
