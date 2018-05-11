package src.services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import src.Player;

import java.lang.reflect.Type;
import java.util.List;

public abstract class PlayerService extends BddService {

    protected int stamina;
    protected int staminaMatch;
    protected int power;
    protected String name;
    protected int points;
    protected boolean sex; //0 = homme,  1 = femme
    protected int _id;



    public void addToDb(){
        Gson json = new Gson();
        this.executePost(this.url+"/test", json.toJson(this));
        System.out.println(this.serverResponse);

    }
    public void getFromDb(int id){
        Type listType = new TypeToken<List<String>>() {}.getType();
        this.executePost(this.url+"/getplayer", "{\"id\":\""+id+"\"}");
        Gson json = new Gson();
        PlayerService elts = json.fromJson(this.serverResponse, Player.class);
        this.serverResponse = this.serverResponse.substring(1);
        this.name = ((Player) elts).getName();
        this._id = ((Player) elts).getId();
        this.stamina = ((Player) elts).getStamina();
        this.power = ((Player) elts).getPower();
        this.points = ((Player) elts).getPoints();
        this.sex = ((Player) elts).getSex();

    }
    public void updateDb(){
        this.executePost(this.url+"/updatepoints", "{\"id\": \""+this._id+ "\"points\": \""+this.points+"\" ]");
    }


}
