package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 3/02/2018
 */

import java.util.Random;

public class Dice {
    private Random random;
    private final int MAX_THROWN = 6;
    private int value;

    public Dice() {
        random = new Random();
    }

    public int getValue() {
        value = random.nextInt(MAX_THROWN) + 1;
        return value;
    }
}
