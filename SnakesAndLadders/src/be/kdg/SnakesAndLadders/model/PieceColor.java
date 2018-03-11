package be.kdg.SnakesAndLadders.model;

public enum PieceColor {
    BLUE("Blue"), YELLOW("Yellow"), RED("Red"), GREEN("Green");

    private String colorString;

    PieceColor( String colorString) {
        this.colorString = colorString;
    }

    @Override
    public String toString() {
        return colorString;
    }
}


