package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SnakesAndLadders {

    private String[] demoStrings = {"Someone", "Dagobert", "Donald", "Bert"};

    private ArrayList<Player> players;
    private int currentPlayer;
    private Board board;
    private Stage stage = new Stage();
    private Dice dice;

    public SnakesAndLadders() {
        dice = new Dice();
        currentPlayer = 0;
    }

    public void startGame() {

    }

    public int throwDice() {
        return dice.getValue();
    }

    public String getCurrentPlayer() {
//        return players.get(currentPlayer).getUsername();

        //TODO: If nullpointers fixed, change demo array with prod arrayList
        if (currentPlayer == demoStrings.length -1) {
            currentPlayer = 0;
            return demoStrings[demoStrings.length - 1];
        } else {
            return demoStrings[currentPlayer++];
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

}
