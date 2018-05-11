package threads;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import src.Player;

public class Tournoi extends Thread {
	
	private String name;
	private List<Player> players;
	
	public Tournoi(String name, ArrayList<Player> players) {
		super();
		this.name = name;
		this.players = players;
	}



	@Override
	public void run() {
		ExecutorService exec;
		//random sur le tableau des joueurs
		Collections.shuffle(this.players);
		//boucle while jusqu'à 1 joueur restant
		int ronde = 0;
		while(this.players.size() != 1) {
			ronde++;
			//chaque ronde : tableau de matchs joueurs 2 à deux
			List<Match> matchs = new ArrayList<Match>();
			for(int i = 0; i < 64/((int)Math.pow(2,ronde)); i++) { //on crée les matchs avec deux joueurs
				matchs.add(new Match(this.players.get(0), this.players.get(1), ronde));
				this.players.remove(0); //puis on retire les joueurs du tableau pour ne pas les réutiliser avant la prochaine ronde
				this.players.remove(1);
			}
			//lancer tout les matchs via un executor
			exec = Executors.newCachedThreadPool();
			for(int j = 0; j < matchs.size(); j++) {
				exec.execute(matchs.get(j));
			}
			exec.shutdown(); //empecher d'autres de se lancer
			while(!exec.isTerminated()) {
				System.out.println("running"); //attente des threads
			}
			//on récupère les vainqueurs pour la prochaine ronde
			this.players = matchs.stream().map(x -> x.getWinner()).collect(Collectors.toList()); 
		}
		//envoyer requete gagnant tournoi
		
	}
}
