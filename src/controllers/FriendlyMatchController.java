package src.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import src.Classement;
import src.sample.Switcher;


public class FriendlyMatchController extends Controller {

    public FriendlyMatchController(Classement menRanking) {
		super(menRanking);
		// TODO Auto-generated constructor stub
	}

	@FXML
    public javafx.scene.control.Button returnButton;
    public javafx.scene.control.Button launchButton;
    public javafx.scene.control.Button randomButton;

    @FXML
    public void returnButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)returnButton.getScene().getWindow(),"Scenes/Menu.fxml",800, 550,new Controller(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void launchButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)launchButton.getScene().getWindow(),"Scenes/Match.fxml",800, 550,new MatchController(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void randomButtonAction(){
       System.out.println("Oulala c'est le random");
    }

}
