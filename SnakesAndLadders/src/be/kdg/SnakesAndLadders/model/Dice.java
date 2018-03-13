package be.kdg.SnakesAndLadders.model;

import java.util.Random;

/**
 * Dice class to make a more streamlined version of a random number generator that fits better in
 * entire boardgame story
 *
 * @author Marnix Wyns
 * @version 1.0
 */
public class Dice {
    private Random random;
    private final int MAX_THROWN = 6;

    public Dice() {
        random = new Random();
    }

    /**
     * Function that simulates than random throw of a dice and returns the 'thrown' value
     *
     * @return returns a value between 1 and 6
     */
    public int getValue() {
        return random.nextInt(MAX_THROWN) + 1;
    }
}
