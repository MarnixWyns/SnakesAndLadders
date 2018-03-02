package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */


public class Player {
    private Object color;
    private String username;
    private boolean player1Finished;
    private boolean player2Finished;
    private boolean player3Finished;
    private boolean player4Finished;

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
        this.playerPos = playerPos;
    }

    public Object getColor() {
        return color;
    }

    public String getUsername() {
        return username;
    }

    //region Coordinate methods

    public void addToPlayerPos(int addToPos, Board board) {

        //Rebound if > 100
        if (playerPos + addToPos > 100) {
            //Returns player x positions if not on 100
            playerPos = 100 - ((playerPos + addToPos) - 100);
        } else playerPos += addToPos;

        this.playerPos = board.checkPos(playerPos);
    }

    public int getPlayerPos() {
        return playerPos;
    }

    public void setPlayerPos(int playerPos) {
        this.playerPos = playerPos;
    }

    public void setPlayer1Finished(boolean player1Finished) {
        this.player1Finished = player1Finished;
    }

    public void setPlayer2Finished(boolean player2Finished) {
        this.player2Finished = player2Finished;
    }

    public void setPlayer3Finished(boolean player3Finished) {
        this.player3Finished = player3Finished;
    }

    public void setPlayer4Finished(boolean player4Finished) {
        this.player4Finished = player4Finished;
    }

    public boolean isPlayer1Finished() {
        return player1Finished;
    }

    public boolean isPlayer2Finished() {
        return player2Finished;
    }

    public boolean isPlayer3Finished() {
        return player3Finished;
    }

    public boolean isPlayer4Finished() {
        return player4Finished;
    }

}
