package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import java.util.*;

public class Board {

    private ArrayList<Integer> snakeHeadPos;
    private ArrayList<Integer> snakeTailPos;

    private ArrayList<Integer> ladderBottomPos;
    private ArrayList<Integer> ladderTopPos;

    public Board(){
        snakeHeadPos = new ArrayList<>();
        snakeTailPos = new ArrayList<>();
        ladderBottomPos = new ArrayList<>();
        ladderTopPos = new ArrayList<>();
    }

    public int checkPos(int pos){

        int iS = 0;
        for (Integer snakeHead : snakeHeadPos) {
            if (snakeHead == pos){
                return snakeTailPos.get(iS);
            }
            iS++;
        }

        int iL = 0;
        for (Integer ladderBottom : ladderBottomPos) {
            if (ladderBottom == pos){
                return ladderTopPos.get(iL);
            }
            iS++;
        }

        return pos;
    }

    public void setSnakeHeadPos(ArrayList<Integer> snakeHeadPos) {
        this.snakeHeadPos = snakeHeadPos;
    }

    public void setSnakeTailPos(ArrayList<Integer> snakeTailPos) {
        this.snakeTailPos = snakeTailPos;
    }

    public void setLadderBottomPos(ArrayList<Integer> ladderBottomPos) {
        this.ladderBottomPos = ladderBottomPos;
    }

    public void setLadderTopPos(ArrayList<Integer> ladderTopPos) {
        this.ladderTopPos = ladderTopPos;
    }
}
