package src;

import src.services.ClassementService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Classement extends ClassementService {

    public Classement(boolean sex){
        super();
        this.players = new ArrayList<Player>();
        this.getAllPlayers(sex);

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
}
