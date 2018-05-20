package src.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import src.Classement;
import src.sample.Switcher;
import java.awt.*;

public class MatchController extends Controller {

    public MatchController(Classement menRanking) {
		super(menRanking);
		// TODO Auto-generated constructor stub
	}

	@FXML
    public javafx.scene.control.Button returnButton;
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
    public void returnButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)returnButton.getScene().getWindow(),"Scenes/Menu.fxml",800, 500,new Controller(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
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

