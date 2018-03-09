package be.kdg.SnakesAndLadders.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//TODO: Move to boardscan or make static

public class Help {
    public static final String HELP_FILE = "txtFiles/Help.txt";

    private String help = "";

    public Help() throws IOException {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("/" + HELP_FILE))); {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    help += line + "\n";
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public String getHelp() {
        return help;
    }
}