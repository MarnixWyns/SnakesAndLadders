package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

public class Player {
    private PieceColor color;
    private String username;

    //Coordinates for positioning in board
    private int Xval;
    private int Yval;

    public Player(PieceColor color, String username) {
        this.color = color;
        this.username = username;

        //Start position in array coordinates
        Xval = 0;
        Yval = 9;
    }

    public PieceColor getColor() {
        return color;
    }

    public String getUsername() {
        return username;
    }

    //region Coordinate methods
    public void setXval(int xval) {
        Xval = xval;
    }

    public void setYval(int yval) {
        Yval = yval;
    }

    public int getXval() {
        return Xval;
    }

    public int getYval() {
        return Yval;
    }
    //endregion
}
