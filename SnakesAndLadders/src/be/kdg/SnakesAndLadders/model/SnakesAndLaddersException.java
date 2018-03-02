package be.kdg.SnakesAndLadders.model;

public class SnakesAndLaddersException extends RuntimeException {
    public SnakesAndLaddersException() {
        super();
    }

    public SnakesAndLaddersException(String message) {
        super(message);
    }

    public SnakesAndLaddersException(String message, Throwable cause) {
        super(message, cause);
    }

    public SnakesAndLaddersException(Throwable cause) {
        super(cause);
    }
}
