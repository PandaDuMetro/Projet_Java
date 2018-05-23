package src.threads;

import java.util.Random;

import src.Player;
import src.services.MatchService;

public class Match extends Thread {
	private Player player1;
	private Player player2;
	private int[] points = {0,0,0,0,0,0}; //contient tout les points de sets (2 a 2), pour la BDD
	private int[] sets = {0,0};
	private Player winner;
	private int nbRonde;
	private String _id;
	private boolean sex;
	private String nameTournament;

	private MatchService service;

	public Match(String id) {
		this.service = new MatchService(this);
		this.service.getFromDb(id);
	}

	public Match(Player player1, Player player2, int nbRonde, boolean sex, String nameTournament) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.nbRonde = nbRonde;
		this.service = new MatchService(this);
		this.sex = sex;
		this.nameTournament = nameTournament;
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
		this.player1.setStaminaMatch(this.player1.getStamina());
		this.player2.setStaminaMatch(this.player2.getStamina());
		Random rand = new Random();
		int set = 0;
		while(this.sets[0] != 2 && this.sets[1] != 2 && this.player1.getStaminaMatch() > 0 && this.player2.getStaminaMatch() > 0) {
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
			set = set + 2;
		}
		if(this.sets[0] == 2 || this.player2.getStaminaMatch() < 1) {
			this.winner = this.player1;
			this.player1.setRank((this.nbRonde/7)*
					(this.player2.getPoints()/this.player1.getPoints())*
					(this.sets[0] - this.sets[1])); 						//calcul des nouveaux points au classement
			this.service.addToDb();
			//requete victoire match
		}
		else if(this.sets[1] == 2 || this.player1.getStaminaMatch() < 1) {
			this.winner = this.player2;
			this.player2.setRank((this.nbRonde/7)*
					(this.player1.getPoints()/this.player2.getPoints())*
					(this.sets[1] - this.sets[0]));
			this.service.addToDb();
			//requete victoire match
		}
		this.player1.setStaminaMatch(this.player1.getStamina()); //on remet les staminas a la normale
		this.player2.setStaminaMatch(this.player2.getStamina());
	}

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int[] getPoints() {
        return this.points;
    }
    
    public int getPoints(int i) {
        return this.points[i];
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    public int[] getSets() {
        return sets;
    }

    public void setSets(int[] sets) {
        this.sets = sets;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNbRonde() {
        return nbRonde;
    }

    public void setNbRonde(int nbRonde) {
        this.nbRonde = nbRonde;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
    
    public String getNameTournament() {
    	return this.nameTournament;
    }
}
