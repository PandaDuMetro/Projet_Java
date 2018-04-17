package threads;
import java.util.ArrayList;

import src.Player;

public class Tournoi extends Thread {
	
	private String name;
	private ArrayList<Player> players;
	private ArrayList<Player> playersNextRound;
	
	public Tournoi(String name, ArrayList<Player> players) {
		super();
		this.name = name;
		this.players = players;
	}



	@Override
	public void run() {
	}
}
