package be.kdg.SnakesAndLadders.model;

/**
 * Custom exception to be thrown by game, mostly used by BoardScan class.
 *
 * @author Marnix Wyns
 */
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
