package threads;

import java.util.ArrayList;
import java.util.List;

import src.Player;

public class Annee extends Thread {

	private List<Tournoi> tournois = new ArrayList<Tournoi>();
	private List<Player> players;
	
	public Annee(List<Player> players) {
		super();
		this.players = players;
	}



	@Override
	public void run() {
	}
}
