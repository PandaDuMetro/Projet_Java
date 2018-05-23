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
import src.threads.Tournoi;

public class YearController extends Controller {

    public YearController(Classement menRanking, Classement womenRanking) {
		super(menRanking, womenRanking);
	}

    @FXML
    public javafx.scene.control.Button returnButton;
    public javafx.scene.control.Button rankingButton;
    public javafx.scene.control.Button tournament1;
    public javafx.scene.control.Button tournament2;
    public javafx.scene.control.Button tournament3;
    public javafx.scene.control.Button tournament4;
    public javafx.scene.control.Button tournament5;
    public javafx.scene.control.Button tournament6;
    

   
    @FXML
    public void returnButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)returnButton.getScene().getWindow(),"Scenes/Menu.fxml",
                    1000, 750,new Controller(this.menRanking, this.womenRanking));
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
            newStage.setScene(new Scene(root, 1000, 750));
            root.getStylesheets().add("src/sample/CSS/style.css");
            newStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void tourn1Open() {
    	try{
            newSwitch.uploadNewScene((Stage)tournament1.getScene().getWindow(),"Scenes/Tournament.fxml",
                    1000, 750,new TournamentController(this.menRanking, this.womenRanking, "tournoi1"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void tourn2Open() {
    	try{
            newSwitch.uploadNewScene((Stage)tournament2.getScene().getWindow(),"Scenes/Tournament.fxml",
                    1000, 750,new TournamentController(this.menRanking, this.womenRanking, "tournoi2"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void tourn3Open() {
    	try{
            newSwitch.uploadNewScene((Stage)tournament3.getScene().getWindow(),"Scenes/Tournament.fxml",
                    1000, 750,new TournamentController(this.menRanking, this.womenRanking, "tournoi3"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void tourn4Open() {
    	try{
            newSwitch.uploadNewScene((Stage)tournament4.getScene().getWindow(),"Scenes/Tournament.fxml",
                    1000, 750,new TournamentController(this.menRanking, this.womenRanking, "tournoi4"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void tourn5Open() {
    	try{
            newSwitch.uploadNewScene((Stage)tournament5.getScene().getWindow(),"Scenes/Tournament.fxml",
                    1000, 750,new TournamentController(this.menRanking, this.womenRanking, "tournoi5"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void tourn6Open() {
    	try{
            newSwitch.uploadNewScene((Stage)tournament6.getScene().getWindow(),"Scenes/Tournament.fxml",
                    1000, 750,new TournamentController(this.menRanking, this.womenRanking, "tournoi6"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


   /* @FXML
    public void bob(){
        System.out.println("tournament");

    }*/


}
