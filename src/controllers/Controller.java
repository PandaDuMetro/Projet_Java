package src.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.Classement;
import src.Player;
import src.sample.Switcher;
import src.threads.Annee;
import src.threads.Tournoi;


public class Controller {

    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button matchButton;
    public javafx.scene.control.Button yearButton;
    public javafx.scene.control.Button rankingButton;
    public javafx.scene.control.Button tournamentButton;



    protected Switcher newSwitch = new Switcher();
    protected Classement menRanking = null;
    protected Classement womenRanking = null;
    
    public Controller(Classement menRanking, Classement womenRanking) {
    	this.menRanking = menRanking;
    	this.womenRanking = womenRanking;
    }
    
    @FXML
    public void closeButtonAction(){
      Stage stage = (Stage) closeButton.getScene().getWindow();
      stage.close();
  }

    @FXML
    public void matchButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)matchButton.getScene().getWindow(),"Scenes/FriendlyMatch.fxml",1000, 750,new FriendlyMatchController(this.menRanking, this.womenRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void tournamentButtonAction(){
        try{
        	Tournoi tournament = new Tournoi("tournoi0",this.menRanking, this.womenRanking);
        	tournament.run();
            newSwitch.uploadNewScene((Stage)tournamentButton.getScene().getWindow(),"Scenes/Tournament.fxml",
                    1000, 750,new TournamentController(this.menRanking, this.womenRanking, "tournoi0"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void yearButtonAction(){
        try{
        	Annee year = new Annee(this.menRanking, this.womenRanking);
        	year.start();
        	try {
        		year.join();
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
            newSwitch.uploadNewScene((Stage)yearButton.getScene().getWindow(),"Scenes/Year.fxml",1000, 750,new YearController(this.menRanking, this.womenRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void rankingButtonAction() throws IOException{
        try{
        	Stage newStage = new Stage();
        	FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/src/sample/Scenes/Ranking.fxml"));
    		loader2.setController(new RankingController(this.menRanking, this.womenRanking));
    		Parent root = loader2.load();
            newStage.setTitle("Ranking window");
            newStage.setScene(new Scene(root, 1000, 750));
            root.getStylesheets().add("src/sample/CSS/style.css");
            newStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
