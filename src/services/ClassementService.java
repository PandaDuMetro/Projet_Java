package src.services;

import com.google.gson.Gson;
import src.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class ClassementService extends BddService {

    protected List<Player> players;

    public ClassementService() {
        this.url = "http://localhost:8080/players";
        if(!this.isInitiated()){
            String thisLine;
            File f = new File("src/players.txt");
            try{
                BufferedReader br = new BufferedReader(new FileReader(f));
                while((thisLine = br.readLine()) != null){
                    String[] carac = thisLine.split(":");
                    Player tmp = new Player(carac[0], Integer.parseInt(carac[1]),
                            Integer.parseInt(carac[2]), Boolean.getBoolean(carac[3]));
                    //System.out.println(tmp.toString());
                    tmp.addToDb();
                }
            }
            catch(IOException e){
                System.err.println("Error : "+e);
            }
        }
    }

    public void getAllPlayers(boolean sex){
        this.executePost(this.url+"/getall", "{\"sex\":"+sex+"}");
        Gson json = new Gson();
        Player[] elts = json.fromJson(this.serverResponse, Player[].class);
        for (Player player: elts) {
            this.players.add(player);
        }
    }

    public boolean isInitiated(){
        this.executePost(this.url+"/isinitiated", "");
        Gson json = new Gson();
        ServerResponse isSet = json.fromJson(this.serverResponse, ServerResponse.class);
        if(isSet.success){
            return true;
        } else {
            System.out.println(isSet.msg);
            return false;
        }
    }

    class ServerResponse {
        private boolean success;
        private String msg;

        public boolean getSuccess() {
            return success;
        }

        public String getMsg() {
            return msg;
        }
    }
}
