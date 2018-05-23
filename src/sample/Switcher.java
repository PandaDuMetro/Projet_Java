package src.sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.controllers.Controller;

    public class Switcher {

        public void uploadNewScene(Stage newStage, String fileName, int width, int height, Controller controller) throws Exception{
            System.out.println("Prout");
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
    		loader.setController(controller);
    		Parent root = loader.load();
            newStage.setScene(new Scene(root, width, height));
            newStage.setResizable(false);
            root.getStylesheets().add("src/sample/CSS/style.css");
            newStage.show();
        }
    }
