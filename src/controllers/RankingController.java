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
	
    public RankingController(Classement menRanking, Classement womenRanking) {
		super(menRanking, womenRanking);
		// TODO Auto-generated constructor stub
	}


	@FXML
    public javafx.scene.control.Button returnButton;
	public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button resetRanksButton;
	public ScrollPane menRankingPane;
	public ScrollPane womenRankingPane;
	public javafx.scene.control.Label powerLabel;
	public javafx.scene.control.Label staminaLabel;
	public javafx.scene.control.Label rankingLabel;
	public javafx.scene.control.Label rankLabel;
	public javafx.scene.control.Label nameLabel;
	public ScrollPane matchBox;

	public void initialize() {
		this.fillPane();
	}
	
	
	public void fillPane() {
		VBox content = new VBox();
		menRankingPane.setContent(content);
		int i = 1; 
		RankingController self = this;
		for (Player elt: this.menRanking.getPlayers())
		{
		    Label label = new Label(i+ "| " + elt.getName() + " -- points : " + elt.getPoints());
		    label.getStyleClass().add("ScrollPaneLabel");
		    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    	@Override
		    	public void handle(MouseEvent e) {
		    		try{
		            	Stage newStage = new Stage();
		            	FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/src/sample/Scenes/Player.fxml"));
		        		loader2.setController(self);
		        		Parent root = loader2.load();
		                newStage.setTitle("Player window");
		                newStage.setScene(new Scene(root, 800, 550));
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
		                //rankingLabel.setText(""+player.getPoints());
		                rankLabel.setText(""+(self.menRanking.getPlayers().indexOf(player)+1));
		            }catch (Exception ex){
		                ex.printStackTrace();
		            }
		    	}
		    });
		    content.getChildren().add(label);
		    i++;
		}
		VBox content2 = new VBox();
		womenRankingPane.setContent(content2);
		i = 1;
		for (Player elt: this.womenRanking.getPlayers()) //pareil mais pour les femmes
		{
		    Label label = new Label(i+ "| " + elt.getName() + " -- points : " + elt.getPoints());
		    label.getStyleClass().add("ScrollPaneLabel");
		    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    	@Override
		    	public void handle(MouseEvent e) {
		    		try{
		            	Stage newStage = new Stage();
		            	FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/src/sample/Scenes/Player.fxml"));
		        		loader2.setController(self);
		        		Parent root = loader2.load();
		                newStage.setTitle("Player window");
		                newStage.setScene(new Scene(root, 800, 550));
		                root.getStylesheets().add("src/sample/CSS/style.css");
		                newStage.show();
		                Player player = new Player(
		                		self.womenRanking.getPlayers().get(
		                				Integer.parseInt(
			    						label.getText().substring(
			    								0,label.getText().indexOf("|")))-1).getName());
		                nameLabel.setText(player.getName());
		                powerLabel.setText(""+player.getPower());
		                staminaLabel.setText(""+player.getStamina());
		                //rankingLabel.setText(""+player.getPoints());
		                rankLabel.setText(""+(self.womenRanking.getPlayers().indexOf(player)+1));
		            }catch (Exception ex){
		                ex.printStackTrace();
		            }
		    	}
		    });
		    content2.getChildren().add(label);
		    i++;
		}
	}

	@FXML
	public  void resetRanksButtonAction(){ //met plein d'erreurs voir pourquoi
		for (Player elt: this.menRanking.getPlayers()) {
            elt.setRank(1);
        }
		for (Player elt: this.womenRanking.getPlayers()) {
            elt.setRank(1);
        }
		menRankingPane.setContent(null);
		womenRankingPane.setContent(null);
		this.fillPane();
	}

    @FXML
    public void returnButtonAction(){
    	Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void closeButtonAction(){
    	Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
