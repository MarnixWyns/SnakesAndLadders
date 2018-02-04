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

    private ArrayList<Player> players;

    private Path easyPath;
    private Path normalPath;
    private Path hardPath;

    public Board(){
        easyPath = Paths.get("SnakesAndLadders\\src\\be\\kdg\\SnakesAndLadders\\BoardLayouts\\easy.txt");
        normalPath = Paths.get("SnakesAndLadders\\src\\be\\kdg\\SnakesAndLadders\\BoardLayouts\\normal.txt");
        hardPath = Paths.get("SnakesAndLadders\\src\\be\\kdg\\SnakesAndLadders\\BoardLayouts\\hard.txt");

    }

    public void initialize(Player player1){
        players.add(player1);

    }

    public void initialize(Player player1, Player player2){
        Player[] gamePlayers = {player1, player2};
        players.addAll(Arrays.asList(gamePlayers));

    }

    public void initialize(Player player1, Player player2, Player player3){
        Player[] gamePlayers = {player1, player2, player3};
        players.addAll(Arrays.asList(gamePlayers));

    }

    public void initialize(Player player1, Player player2, Player player3, Player player4){
        Player[] gamePlayers = {player1, player2, player3, player4};
        players.addAll(Arrays.asList(gamePlayers));
    }

}
