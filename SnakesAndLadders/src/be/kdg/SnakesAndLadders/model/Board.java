package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Board {

    //Array[X][Y]
    private int[][] board = new int[10][10];
    private Scanner scanner;
    private Dice dice;

    private ArrayList<Player> players;

    private Path path;

    public Board(){

    }

    public void initialize(ArrayList<Player> players, Difficulty difficulty){
        this.players =  players;
        dice = new Dice();
        //if (difficulty.equals(Difficulty.EASY)) this.path = Difficulty.getPath(difficulty);
    }

    public void update(int player){
        Player currentPlayer = players.get(player - 1);
        currentPlayer.setPlayerPos(dice.getValue());
    }





}
