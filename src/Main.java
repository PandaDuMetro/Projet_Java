package src;
import threads.Annee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {

	public static void main(String [ ] args) {


    }
    public static  void remplirFichier(){
		String thisLine;
        List<String> temp = new ArrayList<>();
        File f = new File("src/players.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(f));
            while((thisLine = br.readLine()) != null){
                temp.add(thisLine);
            }
            //BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
            final FileWriter bw = new FileWriter(f);
            try {

                for (String str : temp) {
                    str = str + ":" + String.valueOf(rand()) + ":" + String.valueOf(rand()) + ":0\n";
                    bw.write(str);
                }
            }finally {
                bw.close();
            }
        }
        catch(IOException e){
            System.err.println("Error : "+e);
        }

	}
	public static int rand(){
        Random rand = new Random();
        return rand.nextInt(100)+1;
    }
}
