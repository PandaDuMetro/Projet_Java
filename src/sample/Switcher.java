package src.sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.controllers.Controller;

    public class Switcher {
    	//charge une nouvelle vue dans la meme fenetre, avec un controller distinct
        public void uploadNewScene(Stage newStage, String fileName, int width, int height, Controller controller) throws Exception{
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
    		loader.setController(controller);
    		Parent root = loader.load();
            newStage.setScene(new Scene(root, width, height));
            newStage.setResizable(false);
            root.getStylesheets().add("src/sample/CSS/style.css");
            newStage.show();
        }
    }
