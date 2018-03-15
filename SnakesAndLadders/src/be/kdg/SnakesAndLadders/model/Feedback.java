package be.kdg.SnakesAndLadders.model;

/**
 * Enum containing a few strings that are used to give feedback based on a player event in the game.
 *
 * @author Ruben Vanloo
 * @version 1.0
 */
public enum Feedback {ONE("Too bad..."), TWO("You got unlucky!"), THREE("2+2=4 minus 1 that's three, quick maths"),
    FOUR("Is this the real life?"), FIVE("Everyone is jealous.."), SIX("I choose you!"),
    DOWN("Looks like scotty beamed you down.."), UP("Stairway to heaven!"), HUNDRED("You finished!");

    private String feedbackString;

    Feedback (String feedbackString){
        this.feedbackString = feedbackString;
    }

    @Override
    public String toString() {
        return feedbackString;
    }
}
