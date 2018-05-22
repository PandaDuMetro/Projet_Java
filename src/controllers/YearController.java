package src.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.Classement;
import src.Player;
import src.sample.Switcher;

public class YearController extends Controller {

    public YearController(Classement menRanking) {
		super(menRanking);
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


   /* @FXML
    public void bob(){
        System.out.println("tournament");

    }*/


}
