package src.controllers;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.Classement;
import src.Player;
import src.sample.Switcher;
import src.threads.Annee;
import src.threads.Tournoi;


public class Controller {

    @FXML
    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button matchButton;
    public javafx.scene.control.Button yearButton;
    public javafx.scene.control.Button rankingButton;
    public javafx.scene.control.Button tournamentButton;



    protected Switcher newSwitch = new Switcher();
    protected Classement menRanking = null;
    protected Classement womenRanking = null;
    
    public Controller(Classement menRanking, Classement womenRanking) {
    	this.menRanking = menRanking;
    	this.womenRanking = womenRanking;
    }

    @FXML
    public void closeButtonAction(){
      Stage stage = (Stage) closeButton.getScene().getWindow();
      stage.close();
  }

    @FXML
    public void matchButtonAction(){
        try{
            newSwitch.uploadNewScene((Stage)matchButton.getScene().getWindow(),"Scenes/FriendlyMatch.fxml",1000, 750,new FriendlyMatchController(this.menRanking, this.womenRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void tournamentButtonAction(){ //lance un tournoi et l'affiche
        this.cleanDb();
        try{
        	Tournoi tournament = new Tournoi("tournoi0",this.menRanking, this.womenRanking);
        	tournament.run();
            newSwitch.uploadNewScene((Stage)tournamentButton.getScene().getWindow(),"Scenes/Tournament.fxml",
                    1000, 750,new TournamentController(this.menRanking, this.womenRanking, "tournoi0", false));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void yearButtonAction(){ //lance l'annee et affiche la page
        try{
        	Annee year = new Annee(this.menRanking, this.womenRanking);
        	year.start();
        	try {
        		year.join();
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
            newSwitch.uploadNewScene((Stage)yearButton.getScene().getWindow(),"Scenes/Year.fxml",1000, 750,new YearController(this.menRanking, this.womenRanking));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void rankingButtonAction() throws IOException{
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

    public void cleanDb(){
        //on supprime tous les anciens matchs pour eviter les doublons
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL("http://localhost:8080/matchs/remiseazero");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json");
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes("");
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


}
