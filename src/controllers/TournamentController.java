package src.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.Classement;
import src.Player;
import src.sample.Switcher;

public class TournamentController extends Controller {
	
	//a ajouter : mettre liste des matchs, ouvrir fenetre match quand click sur liste
	
	protected String name;
	protected boolean sex;

    public TournamentController(Classement menRanking, Classement womenRanking, String name) {
		super(menRanking, womenRanking);
		this.name = name;
	}

    @FXML
    public javafx.scene.control.Button returnButton;
    public javafx.scene.control.Button rankingButton;
    public javafx.scene.control.Button sexButton;
    public javafx.scene.control.Label sexLabel;
    public ScrollPane roundPane;

    
    public void initialize() {
    	VBox content = new VBox();
		roundPane.setContent(content);
		for(int i = 1; i < 8; i++) {
			Label label = new Label("Round nb "+ i);
		    label.getStyleClass().add("ScrollPaneLabel");
		    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    	@Override
		    	public void handle(MouseEvent e) {
		    		
		    	}
		    });
		    content.getChildren().add(label);
		}
    }

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
    		loader2.setController(new RankingController(this.menRanking, this.womenRanking));
    		Parent root = loader2.load();
            newStage.setTitle("Ranking window");
            newStage.setScene(new Scene(root, 1000, 650));
            root.getStylesheets().add("src/sample/CSS/style.css");
            newStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void changeSex() {
    	this.sex = !this.sex;
    	if(this.sex == false) {
    		sexLabel.setText("Men");
    	}
    	else {
    		sexLabel.setText("Women");
    	}
    }
}
