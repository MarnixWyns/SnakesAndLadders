package be.kdg.SnakesAndLadders.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Ruben Vanloo
 * 3/02/2018.
 */
/*
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
*/

public class Feedback{
    private ArrayList<String> feedbackStrings;
    private Random random;

    public Feedback() {
        random = new Random();

        feedbackStrings.add("Nice throw");
        feedbackStrings.add("Not bad!");
    }

    public String getRandFeedback(){
        return feedbackStrings.get(random.nextInt(feedbackStrings.size() - 1));
    }
}
