package src.threads;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import src.Classement;
import src.Player;

public class Tournoi extends Thread {
	
	private String name;
	private Classement players;
	private Classement fPlayers;
	
	public Tournoi(String name, Classement players, Classement fPlayers) {
		super();
		this.name = name;
		this.players = players;
		this.fPlayers = fPlayers;
	}



	@Override
	public void run() {
		ExecutorService exec;
		List<Player> menPlayers = this.players.getPlayers();
		List<Match> matchs = new ArrayList<Match>();
		//random sur le tableau des joueurs
		Collections.shuffle(menPlayers);
		//boucle while jusqu'a 1 joueur restant
		int ronde = 0;
		while(menPlayers.size() != 1) {
			ronde++;
			//chaque ronde : tableau de matchs joueurs 2 a deux
			for(int i = 0; i < 64/((int)Math.pow(2,ronde)); i++) { //on cree les matchs avec deux joueurs
				matchs.add(new Match(menPlayers.get(0), menPlayers.get(1), ronde, false, this.name));
				menPlayers.remove(0); //puis on retire les joueurs du tableau pour ne pas les reutiliser avant la prochaine ronde
				menPlayers.remove(1);
			}
			//lancer tout les matchs via un executor
			exec = Executors.newCachedThreadPool();
			for(int j = 0; j < matchs.size(); j++) {
				exec.execute(matchs.get(j));
			}
			exec.shutdown(); //empecher d'autres de se lancer
			while(!exec.isTerminated()) {} //attente des threads
			System.out.println("Ronde terminée");
			//on recupere les vainqueurs pour la prochaine ronde
			menPlayers.clear();
			menPlayers = matchs.stream().map(x -> x.getWinner()).collect(Collectors.toList());
			matchs.clear();
		}
		//puis pour les femmes : 
		List<Player> womenPlayers = this.fPlayers.getPlayers();
		Collections.shuffle(womenPlayers);
		ronde = 0;
		while(womenPlayers.size() != 1) {
			ronde++;
			for(int i = 0; i < 64/((int)Math.pow(2,ronde)); i++) {
				matchs.add(new Match(womenPlayers.get(0), womenPlayers.get(1), ronde, true, this.name));
				womenPlayers.remove(0);
				womenPlayers.remove(1);
			}
			exec = Executors.newCachedThreadPool();
			for(int j = 0; j < matchs.size(); j++) {
				exec.execute(matchs.get(j));
			}
			exec.shutdown();
			while(!exec.isTerminated()) {}
			System.out.println("ronde f terminée");
			womenPlayers.clear();
			womenPlayers = matchs.stream().map(x -> x.getWinner()).collect(Collectors.toList()); 
			matchs.clear();
		}
		System.out.println("Tournoi terminé");
	}
}
