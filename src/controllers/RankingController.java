package src.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.Classement;
import src.Player;
import src.sample.Switcher;

public class RankingController extends Controller {
	
    public RankingController(Classement menRanking) {
		super(menRanking);
		// TODO Auto-generated constructor stub
	}


	@FXML
    public javafx.scene.control.Button returnButton;
	public ScrollPane menRankingPane;

	public void initialize() {
		VBox content = new VBox();
		menRankingPane.setContent(content);
		int i = 1; 
		for (Player elt: this.menRanking.getPlayers())
		{
		    Label label = new Label(i+ "| " + elt.getName() + " -- points : " + elt.getPoints());
		    content.getChildren().add(label);
		    i++;
		}
	}

    @FXML
    public void returnButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)returnButton.getScene().getWindow(),"Scenes/Menu.fxml",800, 550,new Controller(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
