package src.threads;

import java.util.Arrays;
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
		this.service.getById(id);
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
		float cent = (float) 100;
		int set = 0;
		while(this.sets[0] != 2 && this.sets[1] != 2 && this.player1.getStaminaMatch() > 0 && this.player2.getStaminaMatch() > 0) {
			while(this.setIsOver(this.points[set+0], this.points[set+1]) == 0) {
				float val1 = (this.player1.getPower()/(cent-this.player1.getStaminaMatch()))*(rand.nextInt(100)+1); //calcul valeurs
				float val2 = (this.player2.getPower()/(cent-this.player2.getStaminaMatch()))*(rand.nextInt(100)+1);
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
			set = set+2;
		}
		if(this.sets[0] == 2 || this.player2.getStaminaMatch() < 1) {
			this.winner = this.player1;
			int diff = this.sets[0] - this.sets[1];
			if(diff == 0) {
				diff = 1;
			}
			double newRank = ((double)this.nbRonde/7)*
                    ((double)this.player2.getPoints()/(double)this.player1.getPoints())*
                    diff;
            if(newRank < 1 || newRank != newRank ){newRank=1;}
            newRank = newRank+this.player2.getPoints();
			this.player1.setRank((int)newRank);						//calcul des nouveaux points au classement
			this.service.addToDb();
			this.toString();
		}
		else if(this.sets[1] == 2 || this.player1.getStaminaMatch() < 1) {
			this.winner = this.player2;
			int diff = this.sets[0] - this.sets[1];
			if(diff == 0) {
				diff = 1;
			}
            double newRank = ((double)this.nbRonde/7)*
                    ((double)this.player1.getPoints()/(double)this.player2.getPoints())*
                    (diff);
            if(newRank < 1 || newRank != newRank ){newRank=1;}
			newRank = newRank+this.player2.getPoints();
			this.player2.setRank((int)newRank);					//calcul des nouveaux points au classement
			this.service.addToDb();
			this.toString();
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

	public boolean getSex() {
		return sex;
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

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public void setNameTournament(String nameTournament) {
		this.nameTournament = nameTournament;
	}

	@Override
	public String toString() {
		return "Match{" +
				"player1=" + player1 +
				", player2=" + player2 +
				", points=" + Arrays.toString(points) +
				", sets=" + Arrays.toString(sets) +
				", winner=" + winner +
				", nbRonde=" + nbRonde +
				", _id='" + _id + '\'' +
				", sex=" + sex +
				", nameTournament='" + nameTournament + '\'' +
				'}';
	}
}
