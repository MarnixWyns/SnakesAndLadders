package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

public class Player {
    private Object color;
    private String username;

    //number field has where player currently is
    private int playerPos;


    public Player(Object color, String username) {
        this.color = color;
        this.username = username;
        //Start position in array coordinates
        playerPos = 1;
    }

    public Object getColor() {
        return color;
    }

    public String getUsername() {
        return username;
    }

    //region Coordinate methods

    public void addToPlayerPos(int addToPos) {

        if (playerPos + addToPos > 100){
            //Returns player x positions if not on 100
            playerPos = 100 - ((playerPos+addToPos) - 100);
        }
        else playerPos = playerPos + addToPos;
    }

    //Horizontal pos
    public int getXval() {
        if (playerPos % 10 == 0){
            return 0;
        }
        else return playerPos % 10;
    }

    //Vertical pos
    public int getYval() {
        return 10 - (playerPos / 10);
    }
    //endregion

    public int getPlayerPos(){
        return playerPos;
    }

}
