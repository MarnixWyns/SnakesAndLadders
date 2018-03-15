package be.kdg.SnakesAndLadders.model;

import java.io.File;
import java.util.ArrayList;


/**
 * The main model class of the Snakes And Ladders game, this is the only class used for interfacing between the
 * different views and the accompanying game logic.
 *
 * @author Marnix Wyns
 * @author Ruben Vanloo
 * @version 1.0
 */
public class SnakesAndLadders {
    private ArrayList<Player> players = new ArrayList<>();
    private Feedback feedback;
    private int currentPlayer;
    private Dice dice;
    private int boardSize;
    private File difficultyFile;
    private BoardScan boardScan;
    private String selectedBackground;
    private boolean backgroundChanged;

    public SnakesAndLadders() {
        currentPlayer = 0;
        boardSize = 10;
    }

    /**
     * In order to prevent StackOverFlow errors the Boardscan class had when initializing, it has to be initialised
     * in a different method.
     */
    public void startGame() {
        dice = new Dice();
        boardScan = new BoardScan();
    }

    public int throwDice() {
        return dice.getValue();
    }

    /**
     * Method that translates a players position to a number between 0 and 9 to be used as a horizontal coordinate.
     *
     * @param pos takes the players position and parses that into an int between 0 and 9
     * @return int between 0-9 to be used for positioning horizontally in the boardGrid and accompanying methods
     */
    public int translateToColumn(int pos) {
        int row = translateToRow(pos);

        if (row % 2 == 0) {
            if ((boardSize - 1) - ((pos % boardSize) - 1) == boardSize) return 0;
            return (boardSize - 1) - ((pos % boardSize) - 1);
        } else {
            return pos - 1 - (((boardSize - 1) - row) * boardSize);
        }
    }

    /**
     * Method that translates a players position to a number between 0 and 9 to be used as a vertical coordinate.
     *
     * @param pos takes the players position and parses that into an int between 0 and 9
     * @return int between 0-9 to be used for positioning vertically in the boardGrid and accompanying methods
     */
    public int translateToRow(int pos) {
        return (boardSize - 1) - ((pos - 1) / boardSize);
    }

    public void nextPlayer() {
        if (currentPlayer == players.size() - 1) {
            currentPlayer = 0;
        } else {
            currentPlayer++;
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public BoardScan getBoardScan() {
        return boardScan;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public File getDifficultyFile() {
        return difficultyFile;
    }

    public void setDifficultyFile(File difficultyFile) {
        this.difficultyFile = difficultyFile;
    }

    public String getSelectedBackground() {
        return selectedBackground;
    }

    public void setSelectedBackground(String selectedBackground) {
        this.selectedBackground = selectedBackground;
    }

    public boolean isBackgroundChanged() {
        return backgroundChanged;
    }

    public void setBackgroundChanged(boolean backgroundChanged) {
        this.backgroundChanged = backgroundChanged;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public int getPlayerPos(Player player) {
        return player.getPlayerPos();
    }

    public Feedback getFeedback() {
        return feedback;
    }
}

