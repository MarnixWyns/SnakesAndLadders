package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SnakesAndLadders {

    private ArrayList<Player> players = new ArrayList<>();
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

        if (currentPlayer == players.size() - 1) {
            currentPlayer = 0;
            return players.get(players.size() - 1).getUsername();
        } else {
            return players.get(currentPlayer++).getUsername();
        }

    }

    public void addPlayer(Player player) {
        players.add(player);
    }

}
