package src.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import src.Classement;
import src.sample.Switcher;


public class Controller {

    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button matchButton;
    public javafx.scene.control.Button yearButton;
    public javafx.scene.control.Button rankingButton;
    public javafx.scene.control.Button tournamentButton;



    protected Switcher newSwitch = new Switcher();
    protected Classement menRanking = null;
    
    public Controller(Classement menRanking) {
    	this.menRanking = menRanking;
    }
    
    @FXML
    public void closeButtonAction(){
      Stage stage = (Stage) closeButton.getScene().getWindow();
      stage.close();
  }

    @FXML
    public void matchButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)matchButton.getScene().getWindow(),"Scenes/FriendlyMatch.fxml",800, 550,new FriendlyMatchController(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void tournamentButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)tournamentButton.getScene().getWindow(),"Scenes/Tournament.fxml",800, 550,new TournamentController(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void yearButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)yearButton.getScene().getWindow(),"Scenes/Year.fxml",800, 550,new YearController(this.menRanking));
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
