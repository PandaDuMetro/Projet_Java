package src.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import src.Classement;
import src.sample.Switcher;

public class TournamentController extends Controller {

    public TournamentController(Classement menRanking) {
		super(menRanking);
		// TODO Auto-generated constructor stub
	}

    @FXML
    public javafx.scene.control.Button returnButton;
    public javafx.scene.control.Button rankingButton;



    @FXML
    public void returnButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)returnButton.getScene().getWindow(),"Scenes/Menu.fxml",800, 550,new Controller(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void rankingButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)rankingButton.getScene().getWindow(),"Scenes/Ranking.fxml",800, 550,new RankingController(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
