package be.kdg.SnakesAndLadders.model;

/**
 * Ruben Vanloo
 * 3/02/2018.
 */
public enum Feedback {
    ONE("Nice throw!"),
    TWO("Not bad!"),
    THREE("Better luck next time."),
    FOUR("Who taught you that?"),
    FIVE("You got lucky!"),
    SIX("Don't cry.."),
    SEVEN("WINNER!");

    private String feedbackString;

    Feedback(String feedbackString){
        this.feedbackString = feedbackString;
    }

    @Override
    public String toString() {
        return feedbackString;
    }
}
