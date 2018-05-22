package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Classement {
    private List<Player> players;

    public Classement(){
        this.players = new ArrayList<Player>();
        String thisLine;
        File f = new File("src/players.txt");
        int id=0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
            while((thisLine = br.readLine()) != null){
                String[] carac = thisLine.split(":");
                this.players.add(new Player(id,carac[0], Integer.parseInt(carac[1]),
                        Integer.parseInt(carac[2]), Boolean.getBoolean(carac[3])));
                id++;
            }
        }
        catch(IOException e){
            System.err.println("Error : "+e);
        }
    }

    public List<Player> getPlayers() {
        return players.stream()
                .sorted(Comparator.comparingInt(Player::getPoints).reversed())
                .collect(Collectors.toList());
    }

    public void setPlayers(ArrayList<Player> p) {
        this.players = p;
    }

    public void consoleLog(){
        for (Player elt: this.players) {
            System.out.println("id : "+elt.getId()+" Nom : "+elt.getName()+" Puissance : "+ elt.getPower()+" Endurance : "+ elt.getStamina()+" Points : "+ elt.getPoints());
        }
    }
    
    public Player getPlayerByName(String name) {
    	System.out.println(name+"|");
    	for(int i = 0; i < 128; i++) {
    		if(name == this.players.get(i).getName()) {
    	    	System.out.println("found : "+i);
    			return this.players.get(i);
    		}
    	}
    	return null;
    }
}
