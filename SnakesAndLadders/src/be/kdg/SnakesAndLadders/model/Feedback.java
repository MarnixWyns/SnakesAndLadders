package be.kdg.SnakesAndLadders.model;

import java.util.ArrayList;
import java.util.Random;

public class Feedback {
    private ArrayList<String> feedbackStrings;
    private Random random;

    public Feedback() {
        random = new Random();

        feedbackStrings.add(0, "Nice throw");
        feedbackStrings.add(1, "Not bad!");
        feedbackStrings.add(2, "Lucky you!");
        feedbackStrings.add(3, "Better luck next time..");
        feedbackStrings.add(4, "Godlike!");
        feedbackStrings.add(5, "Like a pro!");
    }

    public String getRandFeedback() {
        return feedbackStrings.get(random.nextInt(feedbackStrings.size() - 1) + 1);
    }
}
