package be.kdg.SnakesAndLadders.model;

/**
 * Ruben Vanloo
 * 14/03/2018.
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
