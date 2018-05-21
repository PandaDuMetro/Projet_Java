package src.services;

import com.google.gson.Gson;
import src.Player;
import src.threads.Match;

public class MatchService extends BddService {

    private Match match;
    protected String url = "http://localhost:8080/matchs";

    public MatchService(Match match){
        this.match = match;
    }

    public String getParameters() {
        MatchToSave toSave = new MatchToSave(this.match.getPlayer1().getName(), this.match.getPlayer2().getName(),
                this.match.getPoints(), this.match.getSets(), "jeancule", this.match.getNbRonde());
        Gson json = new Gson();
        return json.toJson(toSave);

    }

    public void addToDb() {
        this.executePost(this.url + "/newmatch", this.getParameters());
        System.out.println(this.serverResponse);
    }

    public void getFromDb(String id) {
        this.executePost(this.url + "/getmatch", "{\"id\":\"" + id + "\"}");
        System.out.println(this.serverResponse);
        Gson json = new Gson();
        MatchToSave elts = json.fromJson(this.serverResponse, MatchToSave.class);
        this.match.setPlayer1(new Player(elts.player1));
        this.match.setPlayer2(new Player(elts.player2));
        this.match.setNbRonde(elts.nbRonde);
        this.match.setPoints(elts.points);
        this.match.setSets(elts.sets);
        if(elts.winner == elts.player1){
            this.match.setWinner(this.match.getPlayer1());
        }
        else {
            this.match.setWinner(this.match.getPlayer2());
        }
    }

    class MatchToSave {
        String player1;
        String player2;
        int[] points;
        int[] sets;
        String winner;
        int nbRonde;

        public MatchToSave(String player1, String player2, int[] points, int[] sets, String winner, int nbRonde) {
            this.player1 = player1;
            this.player2 = player2;
            this.points = points;
            this.sets = sets;
            this.winner = winner;
            this.nbRonde = nbRonde;


        }
    }

}
