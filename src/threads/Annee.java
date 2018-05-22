package src.threads;

import java.util.ArrayList;

import src.Player;

public class Annee extends Thread {

	private ArrayList<Tournoi> tournois = new ArrayList<Tournoi>();
	private ArrayList<Player> players;
	
	public Annee(ArrayList<Player> players) {
		super();
		this.players = players; //on cree 6 tournois pour tout le long de l'annee
		this.tournois.add(new Tournoi("tournoi 1",this.players)); //fait a l'arrache
		this.tournois.add(new Tournoi("tournoi 2",this.players)); //voir si ca peut pas etre plus propre
		this.tournois.add(new Tournoi("tournoi 3",this.players));
		this.tournois.add(new Tournoi("tournoi 4",this.players));
		this.tournois.add(new Tournoi("tournoi 5",this.players));
		this.tournois.add(new Tournoi("tournoi 6",this.players));
	}



	@Override
	public void run() {
		//remettre les classements a 1 pour le debut d'annee
		for (Player elt: this.players) {
            elt.setRank(1);
        }
		//lancer tout les tournois 1 a 1
		//requete nouveau tournoi
		//attendre fin dU tournoi pour lancer le prochain
		for(int i = 0; i < 6; i++) {
			this.tournois.get(i).start();
			try {
				this.tournois.get(i).join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
