/*
 * Course: CS1101 - 111
 * Fall 2023
 * Lab 7 - Battle Simulator 3000
 * Name: Michael Wood
 * Created: 10/12/2023
 */
package woodm;

import java.util.Random;

/**
 * A Die class that can have a number of sides between 2 and 100.
 */
public class Die {
    private int numSides;
    private int currentValue;
    private Random generator;

    /**
     * Creates a new instance of a Die with a number of sides between 2 and 100.
     * If number of sides isn't between 2 and 100, will default to 6.
     * @param numSides the number of sides of the Die object.
     */
    public Die(int numSides) {
        final int minNumSides = 2;
        final int maxNumSides = 100;
        final int defaultNumSides = 6;
        if(numSides < minNumSides || numSides > maxNumSides) {
            this.numSides = defaultNumSides;
        } else {
            this.numSides = numSides;
        }
    }

    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * Rolls the Die to a number between 1 and the number of sides
     */
    public void roll() {
        currentValue = generator.nextInt(numSides) + 1;
    }
}
