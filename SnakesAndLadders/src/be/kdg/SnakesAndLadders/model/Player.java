package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import java.io.File;
import java.nio.file.Path;

public class Player {
    private Object color;
    private String username;
    private boolean isFinished;

    //number field has where player currently is
    private int playerPos;


    public Player(Object color, String username) {
        this.color = color;
        this.username = username;
        playerPos = 1;
    }

    public Player(Object color, String username, int playerPos) {
        this.color = color;
        this.username = username;
    }

    public Object getColor() {
        return color;
    }

    public String getUsername() {
        return username;
    }

    //region Coordinate methods

    public void addToPlayerPos(int addToPos) {

        //Get board locations for snakes and ladders
        BoardScan boardScan = new BoardScan();
        //TODO: Get SnakesAndLadders getSelectedDifficulty in the readfile method below
        //boardScan.readFile(new File("BoardLayouts/ "+  + ".txt"));

        //Rebound if > 100
        if (playerPos + addToPos > 100) {
            //Returns player x positions if not on 100
            playerPos = 100 - ((playerPos + addToPos) - 100);
        } else playerPos += addToPos;

        playerPos = boardScan.getBoard().checkPos(playerPos);
    }

    public int getPlayerPos() {
        return playerPos;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
