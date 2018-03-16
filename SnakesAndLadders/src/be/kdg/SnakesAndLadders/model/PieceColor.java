package be.kdg.SnakesAndLadders.model;

/**
 * Enum for more streamlined player generation file
 *
 * @author Ruben Vanloo
 */
public enum PieceColor {
    BLUE("Blue"), YELLOW("Yellow"), RED("Red"), GREEN("Green"), BLACK("Black");

    private String colorString;

    PieceColor( String colorString) {
        this.colorString = colorString;
    }

    @Override
    public String toString() {
        return colorString;
    }
}


