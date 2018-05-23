package src.threads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import src.Classement;
import src.Player;

public class Annee extends Thread {

	private ArrayList<Tournoi> tournois = new ArrayList<Tournoi>();
	private Classement players;
	private Classement fPlayers;
	
	public Annee(Classement players, Classement fPlayers) {
		super();
		this.players = players; //on cree 6 tournois pour tout le long de l'annee
		this.fPlayers = fPlayers;
		this.tournois.add(new Tournoi("tournoi 1",this.players, this.fPlayers));
		this.tournois.add(new Tournoi("tournoi 2",this.players, this.fPlayers));
		this.tournois.add(new Tournoi("tournoi 3",this.players, this.fPlayers));
		this.tournois.add(new Tournoi("tournoi 4",this.players, this.fPlayers));
		this.tournois.add(new Tournoi("tournoi 5",this.players, this.fPlayers));
		this.tournois.add(new Tournoi("tournoi 6",this.players, this.fPlayers));

		//on vide la base de donnée
		HttpURLConnection connection = null;
		try {
			//Create connection
			URL url = new URL("http://localhost:8080/matchs/remiseazero");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/json");
			connection.setDoOutput(true);

			//Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes("");
			wr.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}



	@Override
	public void run() {
		//lancer tout les tournois 1 a 1
		//requete nouveau tournoi
		//attendre fin dU tournoi pour lancer le prochain
		for(int i = 0; i < 6; i++) {
			this.tournois.get(i).run();
			System.out.println("tournoi "+i+"fini");
		}
	}
}
