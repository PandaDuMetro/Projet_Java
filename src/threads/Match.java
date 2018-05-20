package src.threads;

import java.util.Random;

import src.Player;

public class Match extends Thread {
	private Player player1;
	private Player player2;
	private int[] points = new int[6]; //contient tout les points de sets (2 à 2), pour la BDD
	private int[] sets = new int[2];
	private Player winner;
	private int nbRonde;

	public Match(Player player1, Player player2, int nbRonde) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.nbRonde = nbRonde;
	}
	
	public Player getWinner() {
		return this.winner;
	}
	
	public int setIsOver(int points1, int points2) { //renvoie 1 ou 2 selon le joueur qui gagne le set, ou 0 si 
		if((points1 == 21 && points2 < 20) || (points1 == 30) || (points1 > 21 && points1-points2 == 2)) {	//le set est toujours en cours
			return 1;
		}
		else if((points2 == 21 && points1 < 20) || (points2 == 30) || (points2 > 21 && points2-points1 == 2)) {
			return 2;
		}
		else {
			return 0;
		}
	}

	@Override
	public void run() {
		Random rand = new Random();
		int set = 0;
		while(this.sets[0] != 2 || this.sets[1] != 2 || this.player1.getStaminaMatch() < 1 || this.player2.getStaminaMatch() < 1) {
			while(this.setIsOver(this.points[set+0], this.points[set+1]) == 0) {
				int val1 = (this.player1.getPower()/(100-this.player1.getStaminaMatch()))*(rand.nextInt(100)); //calcul valeurs
				int val2 = (this.player2.getPower()/(100-this.player2.getStaminaMatch()))*(rand.nextInt(100));
				if(val1 > val2) { //joueur 1 gagne le point
					this.points[set+0]++;
				}
				else if(val2 > val1) { //joueur 2 gagne le point
					this.points[set+1]++;
				}
				this.player1.setStaminaMatch(this.player1.getStaminaMatch() - (this.player1.getPower()/100)); //retrait de stamina
				this.player2.setStaminaMatch(this.player2.getStaminaMatch() - (this.player2.getPower()/100));
			}
			this.sets[this.setIsOver(this.points[set+0], this.points[set+1]) - 1]++;
			set++;
		}
		if(this.sets[0] == 2 || this.player2.getStaminaMatch() < 1) {
			this.winner = this.player1;
			this.player1.setRank((this.nbRonde/7)*
					(this.player2.getPoints()/this.player1.getPoints())*
					(this.sets[0] - this.sets[1])); 						//calcul des nouveaux points au classement
			//requete victoire match										
		}
		else if(this.sets[1] == 2 || this.player1.getStaminaMatch() < 1) {
			this.winner = this.player2;
			this.player2.setRank((this.nbRonde/7)*
					(this.player1.getPoints()/this.player2.getPoints())*
					(this.sets[1] - this.sets[0]));
			//requete victoire match
		}
		this.player1.setStaminaMatch(this.player1.getStamina()); //on remet les staminas a la normale
		this.player2.setStaminaMatch(this.player2.getStamina());
	}
}
