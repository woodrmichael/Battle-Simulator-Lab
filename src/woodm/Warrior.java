/*
 * Course: CS1101 - 111
 * Fall 2023
 * Lab 7 - Battle Simulator 3000
 * Name: Michael Wood
 * Created: 10/12/2023
 */
package woodm;

/**
 * A Human controlled Valiant Warrior who must take down the Evil Mugwump
 */
public class Warrior {
    private int hitPoints;
    private final Die d20;
    private final Die d10;
    private final Die d8;
    private final Die d4;

    /**
     * Creates an instance of a Warrior with an initial amount of hitpoints
     */
    public Warrior() {
        final int d20Sides = 20;
        final int d10Sides = 10;
        final int d8Sides = 8;
        final int d4Sides = 4;
        d20 = new Die(d20Sides);
        d10 = new Die(d10Sides);
        d8 = new Die(d8Sides);
        d4 = new Die(d4Sides);
        hitPoints = setInitialHitPoints();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Removes health from the Warriors hitpoints after he takes damage
     * @param damage the total damage the warrior takes during the round
     */
    public void takeDamage(int damage) {
        if(damage >= 0) {
            hitPoints -= damage;
        }
    }

    /**
     * Attacks the Mugwump with either the Trusty Sword or the Shield of Light
     * @param type the type of attack. 1 for Trusty Sword, 2 for Shield of Light
     * @return the total damage dealt to the Mugwump
     */
    public int attack(int type) {
        int damage = 0;
        if(type == 1) {
            d20.roll();
            final int trustySwordChance = 12;
            if(d20.getCurrentValue() >= trustySwordChance) {
                for(int i = 0; i < 2; i++) {
                    d8.roll();
                    damage += d8.getCurrentValue();
                }
                System.out.println("You hit the Mugwump with your Trusty Sword for " + damage +
                        " points of damage!");
            } else {
                System.out.println("You swing your sword and miss the foul creature!");
            }
        } else {
            d20.roll();
            final int shieldOfLightChance = 6;
            if(d20.getCurrentValue() >= shieldOfLightChance) {
                d4.roll();
                damage = d4.getCurrentValue();
                System.out.println("You hit the Mugwump with your Shield of Light for " + damage +
                        " points of damage!");
            } else {
                System.out.println("You swing your shield and miss the foul creature!");
            }
        }
        return damage;
    }

    private int setInitialHitPoints() {
        int initialHitPoints = 0;
        for(int i = 0; i < 4; i++) {
            d10.roll();
            initialHitPoints += d10.getCurrentValue();
        }
        return initialHitPoints;
    }
}
