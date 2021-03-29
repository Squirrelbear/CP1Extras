import java.util.Random;

/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell (2021)
 *
 * Class Troll:
 * A specific type of Enemy with Troll relevant names and attacks.
 */
public class Troll extends Enemy {
    /**
     * Create a new Random Troll using appropriate name and attacks.
     * @param rand Shared Random reference.
     */
    public Troll(Random rand) {
        super(createRandomTrollName(rand), 100, createTrollAttacks());
    }

    /**
     * Generates a random name with the form:  "name (Troll)".
     * Name is randomly selected with equal waiting from 3 options.
     *
     * @param rand Shared Random reference.
     * @return Random name string from a set of options.
     */
    private static String createRandomTrollName(Random rand) {
        String name = "(Troll)";
        switch(rand.nextInt(3)) {
            case 0:
                name = "Zul'jin " + name;
                break;
            case 1:
                name = "Jin'zakk " + name;
                break;
            case 2:
                name = "Vol'jin " + name;
                break;

        }
        return name;
    }

    /**
     * Creates an array with three Troll specific AttackTypes.
     *
     * @return An array with three Troll specific AttackTypes.
     */
    private static AttackType[] createTrollAttacks() {
        AttackType[] attackTypes = new AttackType[3];
        attackTypes[0] = new AttackType("Spear Throw", 3, 5, 0);
        attackTypes[1] = new AttackType("Voodoo", 3, 5, 0);
        attackTypes[2] = new AttackType("Stealthy Slash", 3, 5, 0);
        return attackTypes;
    }
}
