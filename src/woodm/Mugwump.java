/*
 * Course: CS1101 - 111
 * Fall 2023
 * Lab 7 - Battle Simulator 3000
 * Name: Michael Wood
 * Created: 10/12/2023
 */
package woodm;

public class Mugwump {
    private int hitPoints;
    private int maxHitPoints;
    private Die d100;
    private Die d20;
    private Die d10;
    private Die d6;

    public Mugwump() {
        final int d100Sides = 100;
        final int d20Sides = 20;
        final int d10Sides = 10;
        final int d6Sides = 6;
        d100 = new Die(d100Sides);
        d20 = new Die(d20Sides);
        d10 = new Die(d10Sides);
        d6 = new Die(d6Sides);
        hitPoints = setInitialHitPoints();
        maxHitPoints = hitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void takeDamage(int damage) {
        hitPoints -= damage;
    }
    /**
     * This method handles the attack logic
     * @return the amount of damage an attack has caused, 0 if the attack misses or
     *         a negative amount of damage if the Mugwump heals itself
     */
    public int attack() {
        final int razorSharpClawsChance = 13;
        final int fangsOfDeathChance = 16;
        int damage = 0;
        d20.roll();
        if(ai() == 1) {
            if(d20.getCurrentValue() >= razorSharpClawsChance) {
                for(int i = 0; i < 2; i++) {
                    d6.roll();
                    damage += d6.getCurrentValue();
                }
            }
        } else if(ai() == 2) {
            if(d20.getCurrentValue() >= fangsOfDeathChance) {
                for(int i = 0; i < 3; i++) {
                    d6.roll();
                    damage += d6.getCurrentValue();
                }
            }
        } else {
            d6.roll();
            damage -= d6.getCurrentValue();
        }
        return damage;
    }

    private int setInitialHitPoints() {
        int initialHitPoints = 0;
        final int initialHitPointRolls = 6;
        for(int i = 0; i < initialHitPointRolls; i++) {
            d10.roll();
            initialHitPoints += d10.getCurrentValue();
        }
        return initialHitPoints;
    }

    /**
     * This method determines what action the Mugwump performs
     * @return 1 for a Claw attack, 2 for a Bite, and 3 if the Mugwump licks its wounds
     */
    private int ai() {
        final int razorSharpClawsChance = 60;
        final int fangsOfDeathChance = 25;
        d100.roll();
        if(d100.getCurrentValue() <= razorSharpClawsChance) {
            return 1;
        } else if(d100.getCurrentValue() <= razorSharpClawsChance + fangsOfDeathChance) {
            return 2;
        } else {
            return 3;
        }
    }
}
