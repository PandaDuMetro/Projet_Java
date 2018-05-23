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
import src.services.MatchService;
import src.threads.Match;
import java.util.ArrayList;

public class TournamentController extends Controller {
	
	//a ajouter : mettre liste des matchs, ouvrir fenetre match quand click sur liste
	
	protected String name;
	protected boolean sex;
	protected ArrayList<Match> matchs;

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
    public ScrollPane matchPane;
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
    public javafx.scene.control.Label winnerLabel;

    
    public void initialize() {
    	MatchService matchWinner = new MatchService(this.sex, this.name, 6);
		ArrayList<Match> winnerMatch = (ArrayList<Match>) matchWinner.getMany();
		winnerLabel.setText(winnerMatch.get(0).getWinner().getName());
    	VBox content = new VBox();
		roundPane.setContent(content);
		for(int i = 1; i < 8; i++) {
			Label label = new Label("Round number "+ i);
		    label.getStyleClass().add("ScrollPaneLabel");
		    int j = i;
		    TournamentController self = this;
		    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    	@Override
		    	public void handle(MouseEvent e) {
		    		VBox content2 = new VBox();
		    		matchPane.setContent(content2);
		    		MatchService matchSer = new MatchService(self.sex, self.name, j);
		    		self.matchs = (ArrayList<Match>) matchSer.getMany();
		    		for(int k = 1; k <= self.matchs.size() ; k++) {
	        			Label label2 = new Label(k+" : "+self.matchs.get(k-1).get_id());
	        			label.getStyleClass().add("ScrollPaneLabel");
	        			int l = k-1;
	        		    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        		    	@Override
	        		    	public void handle(MouseEvent e) {
	        		    		try{
	        		    			Match match = new Match(self.matchs.get(l).get_id());
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
	        		            	namePlayer1.setText(match.getPlayer1().getName());
	        		            	namePlayer2.setText(match.getPlayer2().getName());
	        		            	firstPlayerName.setText(match.getPlayer1().getName());
	        		            	secondPlayerName.setText(match.getPlayer2().getName());
	        		            	firstPlayerScoreSet1.setText(""+match.getPoints(0));
	        		            	secondPlayerScoreSet1.setText(""+match.getPoints(1));
	        		            	firstPlayerScoreSet2.setText(""+match.getPoints(2));
	        		            	secondPlayerScoreSet2.setText(""+match.getPoints(3));
	        		            	firstPlayerScoreSet3.setText(""+match.getPoints(4));
	        		            	secondPlayerScoreSet3.setText(""+match.getPoints(5));
	        		            	winnerName.setText(match.getWinner().getName());
	        		        	}catch (Exception ex){
	        		        		ex.printStackTrace();
	        		        	}
	        		    	}
	        		    });
	        			content2.getChildren().add(label2);
		    		}
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
            newStage.setScene(new Scene(root, 1000, 750));
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
    	MatchService matchWinner = new MatchService(this.sex, this.name, 6);
		ArrayList<Match> winnerMatch = (ArrayList<Match>) matchWinner.getMany();
		winnerLabel.setText(winnerMatch.get(0).getWinner().getName());
    }
}
