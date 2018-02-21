package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import java.util.*;

public class Board {

    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;

    public Board(){

        snakes = new HashMap<>();
        ladders = new HashMap<>();
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

    public void setSnakes(HashMap<Integer, Integer> snakes) {
        this.snakes = snakes;
    }

    public void setLadders(HashMap<Integer, Integer> ladders) {
        this.ladders = ladders;
    }
}
