package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

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
    private ArrayList<ImageView> playerImages = new ArrayList<>();
    private int currentPlayer;
    private Board board;
    private Dice dice;
    private int boardSize;
    private Difficulty difficulty;
    private Image colorPlayer1;
    private Image colorPlayer2;
    private Image colorPlayer3;
    private Image colorPlayer4;
    private int countPlayers;
    private boolean isFilled;
    private String player1Name;
    private File difficultyFile;
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

    public ImageView getCurrentPlayerImage(){
        return playerImages.get(currentPlayer);
    }

    public int getCurrentPlayerImageId(){
        return currentPlayer;
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

    public void addPlayerImage(ImageView imageView){
        playerImages.add(imageView);
    }

    public void disablePlayer(Player player){
        players.remove(player);
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

    public void setColorPlayer1(Image colorPlayer1) {
        this.colorPlayer1 = colorPlayer1;
    }

    public void setColorPlayer2(Image colorPlayer2) {
        this.colorPlayer2 = colorPlayer2;
    }

    public void setColorPlayer3(Image colorPlayer3) {
        this.colorPlayer3 = colorPlayer3;
    }

    public void setColorPlayer4(Image colorPlayer4) {
        this.colorPlayer4 = colorPlayer4;
    }

    public Image getColorPlayer1() {
        return colorPlayer1;
    }

    public Image getColorPlayer2() {
        return colorPlayer2;
    }

    public Image getColorPlayer3() {
        return colorPlayer3;
    }

    public Image getColorPlayer4() {
        return colorPlayer4;
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

    public ArrayList<ImageView> getPlayerImages() {
        return playerImages;
    }

    public int getCountPlayers() {
        return countPlayers;
    }

    public void setCountPlayers(int countPlayers) {
        this.countPlayers = countPlayers;
    }
}

