package be.kdg.SnakesAndLadders.model;

import java.util.Random;

public class Dice {
    private Random random;
    private final int MAX_THROWN = 6;
    private int value;

    public Dice() {
        random = new Random();
    }

    /**
     * Function that simulates than random throw of a dice
     *
     * @return returns a value between 1 and 6
     */
    public int getValue() {
        value = random.nextInt(MAX_THROWN) + 1;
        return value;
    }
}
