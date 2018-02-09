package be.kdg.SnakesAndLadders.model;

import java.util.ArrayList;
import java.util.Random;

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
