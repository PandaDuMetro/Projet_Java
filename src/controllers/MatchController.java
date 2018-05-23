package src.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import src.Classement;
import src.Player;
import src.sample.Switcher;
import src.threads.Match;

import java.awt.*;

public class MatchController extends Controller {
	
	protected String id;
	protected Match match;

    public MatchController(Classement menRanking, Classement womenRanking, String id) {
		super(menRanking, womenRanking);
		this.id = id;
		this.match = new Match(id);
	}
    
    public MatchController(Classement menRanking, Classement womenRanking, Match match) {
		super(menRanking, womenRanking);
		this.match = match;
	}

	@FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Label winnerName;
    public javafx.scene.control.Label tournamentName;
    public javafx.scene.control.Label roundNumber;
    public javafx.scene.control.Label firstPlayerScoreSet1;
    public javafx.scene.control.Label firstPlayerScoreSet2;
    public javafx.scene.control.Label firstPlayerScoreSet3;
    public javafx.scene.control.Label secondPlayerScoreSet1;
    public javafx.scene.control.Label secondPlayerScoreSet2;
    public javafx.scene.control.Label secondPlayerScoreSet3;
    public javafx.scene.control.Label namePlayer1;
    public javafx.scene.control.Label namePlayer2;
    public javafx.scene.control.Label secondPlayerName;
    public javafx.scene.control.Label firstPlayerName;







    @FXML
    public void closeButtonAction(){
    	Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() { //remplit les champs selon le match
        winnerName.setText(this.match.getWinner().getName());
        tournamentName.setText(this.match.getNameTournament());
        roundNumber.setText(this.match.getNbRonde()+"");
        firstPlayerScoreSet1.setText(this.match.getPoints(0)+"");
        secondPlayerScoreSet1.setText(this.match.getPoints(1)+"");
        firstPlayerScoreSet2.setText(this.match.getPoints(2)+"");
        secondPlayerScoreSet2.setText(this.match.getPoints(3)+"");
        firstPlayerScoreSet3.setText(this.match.getPoints(4)+"");
        secondPlayerScoreSet3.setText(this.match.getPoints(5)+"");
        namePlayer1.setText(this.match.getPlayer1().getName());
        namePlayer2.setText(this.match.getPlayer2().getName());
        firstPlayerName.setText(this.match.getPlayer1().getName());
        secondPlayerName.setText(this.match.getPlayer2().getName());


    }
}

