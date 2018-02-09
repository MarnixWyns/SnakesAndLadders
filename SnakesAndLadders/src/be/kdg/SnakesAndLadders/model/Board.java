package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class Board {

    //Array[X][Y]
    private int[][] board;
    private Scanner easyScan;
    private Dice dice;
    private Scanner txtScanner;
    private File easy;
    private File normal;
    private File hard;
    private Scanner normalScan;
    private Scanner hardScan;

    private ArrayList<Player> players;

    private Path path;

    public Board(){
        board = new int[10][10];
        players = new ArrayList<>();

        initialiseScanners();

    }

    private void initialiseScanners() {
        easy = new File("/BoardLayouts/Easy.txt");
        normal = new File("/BoardLayouts/Normal.txt");
        hard = new File("/BoardLayouts/Normal.txt");

        try {
            easyScan = new Scanner(easy);
            normalScan = new Scanner(normal);
            hardScan = new Scanner(hard);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void initialize(ArrayList<Player> players, Difficulty difficulty){
        this.players =  players;
        dice = new Dice();
        //if (difficulty.equals(Difficulty.EASY)) this.path = Difficulty.getPath(difficulty);
    }

    public void update(int playerID){
        Player currentPlayer = players.get(playerID - 1);
        currentPlayer.addToPlayerPos(dice.getValue());
    }

    public void checkIfPosLadder(){

    }

    public void checkIfPosSnake(){

    }

}
