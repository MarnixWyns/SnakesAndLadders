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

    public void throwDice() {
        value = random.nextInt(MAX_THROWN) + 1;
    }

    public int getValue() {
        return value;
    }
}
