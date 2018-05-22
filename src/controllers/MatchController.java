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

    public MatchController(Classement menRanking, String id) {
		super(menRanking);
		this.id = id;
	}
    
    public MatchController(Classement menRanking, Match match) {
		super(menRanking);
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
    private void initialize() {
        winnerName.setText("Name of the Winner");
        tournamentName.setText("Tournament 1");
        roundNumber.setText("3");
        firstPlayerScoreSet1.setText("3");
        secondPlayerScoreSet1.setText("6");
        firstPlayerScoreSet2.setText("6");
        secondPlayerScoreSet2.setText("4");
        firstPlayerScoreSet3.setText("5");
        secondPlayerScoreSet3.setText("7");
        namePlayer1.setText("Joueur 1");
        namePlayer2.setText("Joueur 2");
        firstPlayerName.setText("Joueur 1");
        secondPlayerName.setText("Joueur 2");


    }
}

