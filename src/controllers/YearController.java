package src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.Classement;
import src.Player;
import src.sample.Switcher;

public class YearController extends Controller {

    public YearController(Classement menRanking, Classement womenRanking) {
		super(menRanking, womenRanking);
	}

    @FXML
    public javafx.scene.control.Button returnButton;
    public javafx.scene.control.Button rankingButton;
    

   
    @FXML
    public void returnButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)returnButton.getScene().getWindow(),"Scenes/Menu.fxml",1000, 750,new Controller(this.menRanking, this.womenRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void rankingButtonAction(){
    	try{
        	Stage newStage = new Stage();
        	FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/src/sample/Scenes/Ranking.fxml"));
    		loader2.setController(new RankingController(this.menRanking,this.womenRanking));
    		Parent root = loader2.load();
            newStage.setTitle("Ranking window");
            newStage.setScene(new Scene(root, 800, 500));
            root.getStylesheets().add("src/sample/CSS/style.css");
            newStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


   /* @FXML
    public void bob(){
        System.out.println("tournament");

    }*/


}
