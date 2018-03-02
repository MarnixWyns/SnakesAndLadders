package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import java.util.*;

public class Board {

    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;
    private String imgBackground;
    private int size;
    private ArrayList<Player> players;


    public Board(String background, HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders, int size){
        this.imgBackground = background;
        this.snakes = snakes;
        this.ladders = ladders;
        this.size = size;
    }

    public Board(String imgBackground, HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders, ArrayList<Player> players, int size) {
        //When save file is read, call this constructor
        this.snakes = snakes;
        this.ladders = ladders;
        this.imgBackground = imgBackground;
        this.players = players;
        this.size = size;
    }

    public int checkPos(int pos){

        if (snakes.containsKey(pos)){
            return snakes.get(pos);
        }

        if (ladders.containsKey(pos)){
            return ladders.get(pos);
        }

        return pos;
    }

    public ArrayList getSavedPlayers() {
        return players;
    }
}
