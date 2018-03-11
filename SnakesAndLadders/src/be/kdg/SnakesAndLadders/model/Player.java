package be.kdg.SnakesAndLadders.model;

public class Player {
    private Object color;
    private String username;

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

    /**
     *
     * Method that calculates the new Player position. It first calculates the rebound if a player goes above position 100.
     * after this, it is thrown through the checkPos method in the board class that returns the position of the player
     * in case the player is on a snake or ladder.
     *
     * @param addToPos int that is used to add to current position, obtained from Dice class
     * @param board Board that contains positions of snakes and ladders for use
     */
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


}
