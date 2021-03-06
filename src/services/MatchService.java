package src.services;

import com.google.gson.Gson;
import src.Player;
import src.threads.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchService extends BddService {

    private Match match;

    //recuperer un match grace au tournois, une ronde et le sexe
    public MatchService(Boolean sex, String nameTournament, int ronde){
        this.executePost("http://localhost:8080/matchs/getbytrs", "{\"sex\": "+sex+
                ",\"nameTournament\": \""+nameTournament+ "\",\"nbRonde\" : "+ronde+" }");
    }

    //recupurer tous les matchs d'un joueur
    public MatchService(String name){
        this.executePost("http://localhost:8080/matchs/getbyname", "{\"name\":\""+name+"\"}");
    }

    public MatchService(Match match){
        this.match = match;
    }

    //met en forme la requete
    public String getParameters() {
        MatchToSave toSave = new MatchToSave(this.match.getPlayer1().getName(), this.match.getPlayer2().getName(),
                this.match.getPoints(), this.match.getSets(), this.match.getWinner().getName(),
                this.match.getNbRonde(), this.match.getSex(), this.match.getNameTournament());
        Gson json = new Gson();
        return json.toJson(toSave);
    }

    //ajoute le match a la bdd
    public void addToDb() {
        this.serverResponse = "";
        this.executePost("http://localhost:8080/matchs/newmatch", this.getParameters());
        this.match.set_id(this.serverResponse);
    }

    public void getById(String id) {
        this.executePost("http://localhost:8080/matchs/getmatch", "{\"id\":\"" + id + "\"}");
        Gson json = new Gson();
        MatchToSave elts = json.fromJson(this.serverResponse, MatchToSave.class);
        this.match.setPlayer1(new Player(elts.player1));
        this.match.setPlayer2(new Player(elts.player2));
        this.match.setNbRonde(elts.nbRonde);
        this.match.setPoints(elts.points);
        this.match.setSets(elts.sets);
        this.match.setSex(elts.sex);
        this.match.setNameTournament(elts.nameTournament);
        this.match.set_id(elts._id);
        this.match.setWinner(new Player(elts.winner));
    }

    //permet de convertir la reponse du serveur en objets Match
    public List getMany(){
        Gson json = new Gson();
        MatchToSave[] matchesFromDb = json.fromJson(this.serverResponse, MatchToSave[].class);
        List matches = new ArrayList<Match>();
        for (MatchToSave matchFromDb: matchesFromDb) {
            Match match = new Match(matchFromDb._id);
            matches.add(match);
        }
        return matches;
    }


    //Classe permettant de faire le lien avec la bdd
    class MatchToSave {
        String player1;
        String player2;
        int[] points;
        int[] sets;
        String winner;
        int nbRonde;
        String _id;
        boolean sex;
        String nameTournament;

        public MatchToSave(String player1, String player2, int[] points, int[] sets, String winner, int nbRonde, boolean sex, String nameTournament) {
            this.player1 = player1;
            this.player2 = player2;
            this.points = points;
            this.sets = sets;
            this.winner = winner;
            this.nbRonde = nbRonde;
            this.sex = sex;
            this.nameTournament = nameTournament;
        }
    }

}
