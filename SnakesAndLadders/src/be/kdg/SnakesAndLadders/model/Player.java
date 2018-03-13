package be.kdg.SnakesAndLadders.model;

/**
 * The Player class is the main class that contains the player information, it is the logical background behind
 * the 2 - 4 players that can be currently active in the game, it mainly tracks the players position and also
 * contains data like the username and color.
 *
 * @author Marnix Wyns
 * @version 1.0
 */
public class Player {
    private Object color;
    private String username;
    private int playerPos;


    /**
     * Player constructor to be used when the game is started for the first time, setting the players on 1 by default
     *
     * @param color Enum given bu the comboboxes in the setupView
     * @param username String given
     */
    public Player(Object color, String username) {
        this.color = color;
        this.username = username;
        playerPos = 1;
    }

    /**
     * Player constructor that takes an addition parameter containing a players positions for use in Save files.
     *
     * @param color Enum given by readSave function that is obtained from save file
     * @param username String containing the username to be read from save file
     * @param playerPos int containing the current position to be read from save file
     */
    public Player(Object color, String username, int playerPos) {
        this.color = color;
        this.username = username;
        this.playerPos = playerPos;
    }

    /**
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

    public Object getColor() {
        return color;
    }

    public String getUsername() {
        return username;
    }
}
