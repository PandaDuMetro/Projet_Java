package src.controllers;

import java.util.ArrayList;

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
import src.services.MatchService;
import src.threads.FriendlyMatch;
import src.threads.Match;

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
	public ScrollPane rankingPane;
	public javafx.scene.control.Label winnerName;
    public javafx.scene.control.Label tournamentName;
    public javafx.scene.control.Label roundNumber;
    public javafx.scene.control.Label firstPlayerScoreSet1;
    public javafx.scene.control.Label firstPlayerScoreSet2;
    public javafx.scene.control.Label firstPlayerScoreSet3;
    public javafx.scene.control.Label secondPlayerScoreSet1;
    public javafx.scene.control.Label secondPlayerScoreSet2;
    public javafx.scene.control.Label secondPlayerScoreSet3;
    public javafx.scene.control.Label namePlayer1;
    public javafx.scene.control.Label namePlayer2;
    public javafx.scene.control.Label secondPlayerName;
    public javafx.scene.control.Label firstPlayerName;

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
		                rankLabel.setText(""+(self.menRanking.getPlayers().indexOf(player)+1));
		                VBox content1 = new VBox();
		        		rankingPane.setContent(content1);
		        		int[] histPoints = player.getHistPoints();
		        		for(int j = 1; j <= histPoints.length ; j++) {
		        			Label label1 = new Label(""+histPoints[j-1]);
		        			content1.getChildren().add(label1);
		        		}
		        		VBox content2 = new VBox();
		        		matchBox.setContent(content2);
		        		MatchService matchSer = new MatchService(player.getName());
		        		ArrayList<Match> matchs = (ArrayList<Match>) matchSer.getMany();
		        		for(int k = 1; k <= matchs.size() ; k++) {
		        			Label label2 = new Label(k+" : "+matchs.get(k-1).getPlayer1().getName()+" vs "+
		        					matchs.get(k-1).getPlayer2().getName());
		        			label.getStyleClass().add("ScrollPaneLabel");
		        			int l = k-1;
		        		    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
		        		    	@Override
		        		    	public void handle(MouseEvent e) {
		        		    		try{
		        		    			Match match = new Match(matchs.get(l).get_id());
		        		    			Stage newStage = new Stage();
		        		        		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/src/sample/Scenes/Match.fxml"));
		        		    			loader2.setController(new MatchController(self.menRanking, self.womenRanking, match));
		        		    			Parent root = loader2.load();
		        		    			newStage.setTitle("Player window");
		        		                newStage.setScene(new Scene(root, 800, 500));
		        		                root.getStylesheets().add("src/sample/CSS/style.css");
		        		                newStage.show();
		        		        	}catch (Exception ex){
		        		        		ex.printStackTrace();
		        		        	}
		        		    	}
		        		    });
		        			content2.getChildren().add(label2);
		        		}
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
		                rankLabel.setText(""+(self.menRanking.getPlayers().indexOf(player)+1));
		                VBox content1 = new VBox();
		        		rankingPane.setContent(content1);
		        		int[] histPoints = player.getHistPoints();
		        		for(int j = 1; j <= histPoints.length ; j++) {
		        			Label label1 = new Label(""+histPoints[j-1]);
		        			content1.getChildren().add(label1);
		        		}
		        		VBox content3 = new VBox();
		        		matchBox.setContent(content3);
		        		MatchService matchSer = new MatchService(player.getName());
		        		ArrayList<Match> matchs = (ArrayList<Match>) matchSer.getMany();
		        		for(int k = 1; k <= matchs.size() ; k++) {
		        			Label label2 = new Label(k+" : "+matchs.get(k-1).getPlayer1().getName()+" vs "+
		        					matchs.get(k-1).getPlayer2().getName());
		        			label2.getStyleClass().add("ScrollPaneLabel");
		        			int l = k-1;
		        		    label2.setOnMouseClicked(new EventHandler<MouseEvent>() {
		        		    	@Override
		        		    	public void handle(MouseEvent e) {
		        		    		try{
		        		    			Match match = new Match(matchs.get(l).get_id());
		        		    			Stage newStage = new Stage();
		        		        		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/src/sample/Scenes/Match.fxml"));
		        		    			loader2.setController(new MatchController(self.menRanking, self.womenRanking, match));
		        		    			Parent root = loader2.load();
		        		    			newStage.setTitle("Player window");
		        		                newStage.setScene(new Scene(root, 800, 500));
		        		                root.getStylesheets().add("src/sample/CSS/style.css");
		        		                newStage.show();
		        		        	}catch (Exception ex){
		        		        		ex.printStackTrace();
		        		        	}
		        		    	}
		        		    });
		        			content3.getChildren().add(label2);
		        		}
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
            elt.setRank(100);
        }
		for (Player elt: this.womenRanking.getPlayers()) {
            elt.setRank(100);
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
