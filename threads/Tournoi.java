package threads;
import java.util.ArrayList;
import java.util.List;

import src.Player;

public class Tournoi extends Thread {
	
	private String name;
	private List<Player> players;
	private List<Player> playersNextRound;
	
	public Tournoi(String name, List<Player> players) {
		super();
		this.name = name;
		this.players = players;
	}



	@Override
	public void run() {
	}
}
