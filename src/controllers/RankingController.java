package src.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
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
	
	public javafx.scene.control.Label powerLabel;
	public javafx.scene.control.Label staminaLabel;
	public javafx.scene.control.Label rankingLabel;
	public javafx.scene.control.Label rankLabel;
	public javafx.scene.control.Label nameLabel;
	public ScrollPane matchBox;

	public void initialize() {
		VBox content = new VBox();
		menRankingPane.setContent(content);
		int i = 1; 
		RankingController self = this;
		for (Player elt: this.menRanking.getPlayers())
		{
		    Label label = new Label(i+ "| " + elt.getName() + " -- points : " + elt.getPoints());
		    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    	@Override
		    	public void handle(MouseEvent e) {
		    		try{
		            	Stage newStage = new Stage();
		            	FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/src/sample/Scenes/Player.fxml"));
		        		loader2.setController(self);
		        		Parent root = loader2.load();
		                newStage.setTitle("Player window");
		                newStage.setScene(new Scene(root, 800, 500));
		                root.getStylesheets().add("src/sample/CSS/style.css");
		                newStage.show();
		                Player player = new Player(
		                		self.menRanking.getPlayers().get(
		                				Integer.parseInt(
			    						label.getText().substring(
			    								0,label.getText().indexOf("|")))-1).getName());
		                nameLabel.setText(player.getName());
		                powerLabel.setText(""+player.getPower());
		                staminaLabel.setText(""+player.getStamina());
		                rankingLabel.setText(""+player.getPoints());
		                rankLabel.setText(""+(self.menRanking.getPlayers().indexOf(player)+1));
		            }catch (Exception ex){
		                ex.printStackTrace();
		            }
		    	}
		    });
		    content.getChildren().add(label);
		    i++;
		}
	}

    @FXML
    public void returnButtonAction(){
    	Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }

}
