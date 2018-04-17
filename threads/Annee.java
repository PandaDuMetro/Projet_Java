package threads;

import java.util.ArrayList;

import src.Player;

public class Annee extends Thread {

	private ArrayList<Tournoi> tournois = new ArrayList<Tournoi>();
	private ArrayList<Player> players;
	
	public Annee(ArrayList<Player> players) {
		super();
		this.players = players;
	}



	@Override
	public void run() {
	}
}
