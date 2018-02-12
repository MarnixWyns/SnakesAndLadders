package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import java.util.*;

public class Board {

    private int[][] board;

    private ArrayList<Integer> snakeHeadPos;
    private ArrayList<Integer> snakeTailPos;

    private ArrayList<Integer> ladderBottomPos;
    private ArrayList<Integer> ladderTopPos;

    public Board(){
        snakeHeadPos = new ArrayList<>();
        snakeTailPos = new ArrayList<>();
        ladderBottomPos = new ArrayList<>();
        ladderTopPos = new ArrayList<>();

        board = new int[10][10];
    }


    //TODO: We hebbe dees nodig, kben ni aant cheate vo regels :P

    public ArrayList<Integer> getSnakeHeadPos() {
        return snakeHeadPos;
    }

    public ArrayList<Integer> getSnakeTailPos() {
        return snakeTailPos;
    }

    public ArrayList<Integer> getLadderBottomPos() {
        return ladderBottomPos;
    }

    public ArrayList<Integer> getLadderTopPos() {
        return ladderTopPos;
    }


    //region BoardScan utilised functions, interacting scanner with Board
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
    //endregion
}
