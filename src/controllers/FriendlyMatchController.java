package src.controllers;

import java.util.Random;

import javafx.application.Platform;
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
import src.threads.FriendlyMatch;
import src.threads.Match;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class FriendlyMatchController extends Controller {
	
	//a ajouter : creer le match et ouvrir fenetre match, liste des joeurs au choix et fonction random
	
	protected Player player1;
	protected Player player2;
	protected FriendlyMatch match;

    public FriendlyMatchController(Classement menRanking) {
		super(menRanking);
		// TODO Auto-generated constructor stub
	}

	@FXML
    public javafx.scene.control.Button returnButton;
    public javafx.scene.control.Button launchButton;
    public javafx.scene.control.Button randomButton;
    public javafx.scene.control.Button closeButton;
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
    public ScrollPane player1Field = null;
    public ScrollPane player2Field = null;
    
    public void initialize() {
		VBox content1 = new VBox();
		player1Field.setContent(content1);
		FriendlyMatchController self = this;
		int i = 1;
		for (Player elt: this.menRanking.getPlayers())
		{
		    Label label = new Label(i + " " + elt.getName() + " -- points : " + elt.getPoints());
		    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    	@Override
		    	public void handle(MouseEvent e) {
		    		self.player1 = self.menRanking.getPlayers().get(
		    				Integer.parseInt(
		    						label.getText().substring(0,label.getText().indexOf(" ")))-1);
		    	}
		    });
		    content1.getChildren().add(label);
		    i++;
		}
		VBox content2 = new VBox();
		player2Field.setContent(content2);
		int j = 1;
		for (Player elt: this.menRanking.getPlayers())
		{
		    Label label = new Label(j + " " + elt.getName() + " -- points : " + elt.getPoints());
		    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    	@Override
		    	public void handle(MouseEvent e) {
		    		self.player2 = self.menRanking.getPlayers().get(
		    				Integer.parseInt(
		    						label.getText().substring(0,label.getText().indexOf(" ")))-1);
		    	}
		    });
		    content2.getChildren().add(label);
		    j++;
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

    @FXML
    public void launchButtonAction(){
    	if(this.player1 != null && this.player2 != null) {
    		try{
    			Stage newStage = new Stage();
        		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/src/sample/Scenes/Match.fxml"));
    			loader2.setController(this);
    			Parent root = loader2.load();
            	newStage.setTitle("Match window");
            	newStage.setScene(new Scene(root, 800, 500));
            	root.getStylesheets().add("src/sample/CSS/style.css");
            	newStage.show();
            	winnerName.setText("TBD");
            	tournamentName.setText("Match Amical");
            	roundNumber.setText("0");
            	this.match = new FriendlyMatch(this.player1, this.player2, this);
            	namePlayer1.setText(this.player1.getName());
            	namePlayer2.setText(this.player2.getName());
            	firstPlayerName.setText(this.player1.getName());
            	secondPlayerName.setText(this.player2.getName());
            	this.match.start();
            	try {
            		this.match.join();
            	}
            	catch(Exception e) {
            		e.printStackTrace();
            	}
            	firstPlayerScoreSet1.setText(""+this.match.getPoint(0));
            	secondPlayerScoreSet1.setText(""+this.match.getPoint(1));
            	firstPlayerScoreSet2.setText(""+this.match.getPoint(2));
            	secondPlayerScoreSet2.setText(""+this.match.getPoint(3));
            	firstPlayerScoreSet3.setText(""+this.match.getPoint(4));
            	secondPlayerScoreSet3.setText(""+this.match.getPoint(5));
            	winnerName.setText(this.match.getWinner().getName());
        	}catch (Exception e){
        		e.printStackTrace();
        	}
    	}
    }

    @FXML
    public void randomButtonAction(){
    	Random rand = new Random();
        int player1Number = rand.nextInt(127);
        int player2Number;
        do {
        	player2Number = rand.nextInt(127);
        }while(player1Number == player2Number);
        this.player1 = this.menRanking.getPlayers().get(player1Number);
        this.player2 = this.menRanking.getPlayers().get(player2Number);
    }
    
    @FXML
    public void closeButtonAction(){
    	Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
   
    

}
