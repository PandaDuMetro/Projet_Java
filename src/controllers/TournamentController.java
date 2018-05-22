package src.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import src.Classement;
import src.sample.Switcher;

public class TournamentController extends Controller {
	
	//a ajouter : mettre liste des matchs, ouvrir fenetre match quand click sur liste
	
	protected String name;

    public TournamentController(Classement menRanking, String name) {
		super(menRanking);
		this.name = name;
	}

    @FXML
    public javafx.scene.control.Button returnButton;
    public javafx.scene.control.Button rankingButton;



    @FXML
    public void returnButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)returnButton.getScene().getWindow(),"Scenes/Menu.fxml",1000, 750,new Controller(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void rankingButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)rankingButton.getScene().getWindow(),"Scenes/Ranking.fxml",1000, 750,new RankingController(this.menRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
