package src;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.controllers.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) throws Exception{
		Classement menRanking = new Classement(false);
		Classement womenRanking = new Classement(true);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("sample/Scenes/Menu.fxml"));
		loader.setController(new Controller(menRanking));
		Parent root = loader.load();
        primaryStage.setTitle("My beautiful view");
        primaryStage.setScene(new Scene(root, 800, 500));
        root.getStylesheets().add("src/sample/CSS/style.css");
        primaryStage.show();
    }

	public static void main(String [ ] args) {
		launch(args);
    }

    public static void remplirFichier() {
        String thisLine;
        List<String> temp = new ArrayList<>();
        File f = new File("src/players.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((thisLine = br.readLine()) != null) {
                temp.add(thisLine);
            }
            final FileWriter bw = new FileWriter(f);
            try {

                for (String str : temp) {
                    str = str + ":" + String.valueOf(rand()) + ":" + String.valueOf(rand()) + ":0\n";
                    bw.write(str);
                }
            } finally {
                bw.close();
            }
        } catch (IOException e) {
            System.err.println("Error : " + e);
        }

    }

    public static int rand() {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }
}
