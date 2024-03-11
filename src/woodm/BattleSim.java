/*
 * Course: CS1101 - 111
 * Fall 2022
 * Lab 7 - Battle Simulator 3000
 * Name: Michael Wood
 * Created: 10/12/2023
 */
package woodm;

import java.util.Scanner;

/**
 * BattleSim Driver for Battle Simulator 3000
 */
public class BattleSim {
    /**
     * Ten-sided die to be used for checking initiative by all classes
     */
    public static final Die INITATIVE_DIE = new Die(10);

    public static void main(String[] args) {
        // Local variables
        // Include any variable that will need to be accessed throughout the program here

        // sentinel value for the game loop
        boolean play;
        // String used to determine the winner of the epic battle
        String victor = "none";
        // game loop
        do {
            intro();
            // initialize game
            // Initialize the Warrior and Mugwump classes, set the current victor to "none"
            Warrior warrior = new Warrior();
            Mugwump mugwump = new Mugwump();
            Scanner in = new Scanner(System.in);

            // while neither combatant has lost all of their hit points, report status and battle!
            while (victor.equalsIgnoreCase("none")) {
                report(warrior, mugwump);
                victor = battle(warrior, mugwump, in);
            }
            // declare the winner
            victory(victor);
            // ask to play again
            play = playAgain(in);
        } while(play);
        // Thank the user for playing your game
        System.out.println("Thank you for playing Battle Simulator 3000!");
    }

    /**
     * This method displays the introduction to the game and gives a description of the rules.
     */
    private static void intro() {
        System.out.println("""

                Welcome to Battle Simulator 3000! The greatest Battle Simulator since Battle Simulator 2000!
                You are a Valiant Warrior defending your humble village from an evil Mugwump! Fight bravely,\s
                or the citizens of your town will be the Mugwump's tasty dinner!

                You have your Trusty Sword, which deals decent damage, but can be tough to hit with sometimes.\s
                You also have your Shield of Light, which is not as strong as your sword, but is easier to deal\s
                damage with.

                Let the epic battle begin!""");
    }

    /**
     * This method handles the battle logic for the game.
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     * @return The name of the victor, or "none", if the battle is still raging on
     */
    private static String battle(Warrior warrior, Mugwump mugwump, Scanner in) {
        // determine who attacks first (Roll! For! Initiative!) and store the result
        int firstAttack = initiative();

        int attackChoice;
        int damage;
        if(firstAttack == 1) {
            attackChoice = attackChoice(in);
            damage = warrior.attack(attackChoice);
            mugwump.takeDamage(damage);
            if(mugwump.getHitPoints() > 0) {
                damage = mugwump.attack();
                warrior.takeDamage(damage);
                if(warrior.getHitPoints() > 0) {
                    return "none";
                } else {
                    return "mugwump";
                }
            } else {
                return "warrior";
            }
        } else {
            damage = mugwump.attack();
            warrior.takeDamage(damage);
            if(warrior.getHitPoints() > 0) {
                attackChoice = attackChoice(in);
                damage = warrior.attack(attackChoice);
                mugwump.takeDamage(damage);
                if(mugwump.getHitPoints() > 0) {
                    return "none";
                } else {
                    return "warrior";
                }
            } else {
                return "mugwump";
            }
        }
        // attack code
        // If the Warrior attacks first

        // Warrior attacks and assigns the resulting damage to the mugwump

        // Check if the Mugwump has been defeated
        // If not, Mugwump attacks!

        // Otherwise, the Warrior wins!

        // Otherwise the Mugwump is first
        // see above

        // If neither combatant is defeated, the battle rages on!
    }

    /**
     * This method reports the status of the combatants
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     */
    private static void report(Warrior warrior, Mugwump mugwump) {
        System.out.println("\nWarrior HP: " + warrior.getHitPoints());
        System.out.println("Mugwump HP: " + mugwump.getHitPoints() + "\n");
    }

    /**
     * This method asks the user what attack type they want to use and returns the result
     * @return 1 for sword, 2 for shield
     */
    private static int attackChoice(Scanner in) {
        System.out.println("How would you like to attack?\n" +
                "1. Your Trusty Sword\n" +
                "2. Your Shield of Light");
        String choice;
        do {
            System.out.print("Enter choice: ");
            choice = in.nextLine();
        } while(choice.length() > 1 || !choice.equals("1") && !choice.equals("2"));
        return Integer.parseInt(choice);
    }

    /**
     * Determines which combatant attacks first and returns the result. In the case of a tie,
     * re-roll.
     * @return 1 if the warrior goes first, 2 if the mugwump goes first
     */
    private static int initiative() {
        int mugwumpRoll;
        int warriorRoll;
        do {
            INITATIVE_DIE.roll();
            mugwumpRoll = INITATIVE_DIE.getCurrentValue();
            INITATIVE_DIE.roll();
            warriorRoll = INITATIVE_DIE.getCurrentValue();
        } while(mugwumpRoll == warriorRoll);
        if(mugwumpRoll > warriorRoll) {
            System.out.println("The Mugwump attacks first!");
            return 2;
        }
        System.out.println("The Warrior attacks first!");
        return 1;
    }

    /**
     * This method declares the victor of the epic battle
     * @param victor the name of the victor of the epic battle
     */
    private static void victory(String victor) {
        if(victor.equals("warrior")) {
            System.out.println("\nThe Warrior triumphs with his victory over the Evil Mugwump!\n" +
                    "The citizens cheer and invite you back to town for a feast as thanks " +
                    "for saving their lives (again)!\n");
        } else {
            System.out.println("\nThe Mugwump mocks the Warrior for his loss against the " +
                    "almighty evil Mugwump!\nHe eats every citizen in the town for his dinner " +
                    "after such an easy victory!\n");
        }
    }

    /**
     * This method asks the user if they would like to play again
     * @param in Scanner
     * @return true if yes, false otherwise
     */
    private static boolean playAgain(Scanner in) {
        System.out.print("Would you like to play again? (yes/no): ");
        String playAgain = in.nextLine();
        return playAgain.equalsIgnoreCase("yes") || playAgain.equalsIgnoreCase("y");
    }
}