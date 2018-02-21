package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SnakesAndLadders {

    //Inner class enum voor difficulty
    public enum Difficulty{
        EASY, NORMAL, HARD;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    private ArrayList<Player> players = new ArrayList<>();
    private int currentPlayer;
    private Board board;
    private Dice dice;
    private int boardSize;
    private Difficulty difficulty;


    //TODO: Boardscan object + initialisation causes stackOverflowError, possibly due to extends?
    private BoardScan boardScan;

    public SnakesAndLadders() {
        currentPlayer = 0;
        boardSize = 10;

    }

    public void startGame() {
        dice = new Dice();
        boardScan = new BoardScan();
        board = boardScan.getBoard();
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

    public int getPlayerPos(Player player) {
        return player.getPlayerPos();
    }

    //Column = verticaal, werkt
    public int translateToColumn(int pos) {
        int row = translateToRow(pos);

        if (row % 2 == 0) {
            if ((boardSize - 1) - ((pos % boardSize) - 1) == boardSize) return 0;
            return (boardSize - 1) - ((pos % boardSize) - 1);
        } else {
            return pos - 1 - (((boardSize - 1) - row) * boardSize);
        }
    }

    //Row = horizontaal, werkt
    public int translateToRow(int pos) {
        return (boardSize - 1) - ((pos - 1) / boardSize);
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


    public BoardScan getBoardScan() {
        return boardScan;
    }


    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setSelectedDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }

    public String getSelectedDifficulty(){
        return difficulty.name();
    }
}
