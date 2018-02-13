package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SnakesAndLadders {

    private ArrayList<Player> players = new ArrayList<>();
    private int currentPlayer;
    private Board board;
    private Stage stage = new Stage();
    private Dice dice;
    private BoardScan boardScan;

    public SnakesAndLadders() {
        dice = new Dice();
        currentPlayer = 0;
        BoardScan boardScan = new BoardScan();
        board = boardScan.getBoard();
    }

    public void startGame() {

    }

    public int throwDice() {
        return dice.getValue();
    }

    public String getCurrentPlayerName() {
            return players.get(currentPlayer).getUsername();
    }

    public Player getCurrentPlayer() {
            return players.get(currentPlayer);
    }

    public void addToPos(Player player, int dice) {
        player.addToPlayerPos(dice);
    }

    public int getPlayerPos(Player player) {
        return player.getPlayerPos();
    }

    //Column = verticaal
    public int translateToColumn(int pos) {
        int row = translateToRow(pos);
        int r;

        if (row % 2 == 0) {
            if (9 - ((pos % 10) - 1) == 10) return 0;
            return 9 - ((pos % 10) - 1);
        } else {
            return pos - 1 - ((9 - row) * 10);
        }
    }

    //Row = horizontaal, werkt
    public int translateToRow(int pos) {

        return 9 - ((pos - 1) / 10);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayerId(){
        return currentPlayer;
    }

    public void nextPlayer(){
        if (currentPlayer == players.size() - 1){
            currentPlayer = 0;
        }
        else {
            currentPlayer++;
        }
    }


}
