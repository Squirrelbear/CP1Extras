import java.util.Random;

/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell (2021)
 *
 * Class Orc:
 * A specific type of Enemy with Orc relevant names and attacks.
 */
public class Orc extends Enemy {
    /**
     * Create a new Random Orc using appropriate name and attacks.
     * @param rand Shared Random reference.
     */
    public Orc(Random rand) {
        super(createRandomOrcName(rand), 100, createOrcAttacks());
    }

    /**
     * Generates a random name with the form:  "name (Orc)".
     * Name is randomly selected with equal waiting from 3 options.
     *
     * @param rand Shared Random reference.
     * @return Random name string from a set of options.
     */
    private static String createRandomOrcName(Random rand) {
        String name = "(Orc)";
        switch(rand.nextInt(3)) {
            case 0:
                name = "Draka " + name;
                break;
            case 1:
                name = "Thrall " + name;
                break;
            case 2:
                name = "Garrosh " + name;
                break;

        }
        return name;
    }

    /**
     * Creates an array with three Orc specific AttackTypes.
     *
     * @return An array with three Orc specific AttackTypes.
     */
    private static AttackType[] createOrcAttacks() {
        AttackType[] attackTypes = new AttackType[3];
        attackTypes[0] = new AttackType("Swing", 3, 5, 0);
        attackTypes[1] = new AttackType("Thwack", 3, 5, 0);
        attackTypes[2] = new AttackType("Roar", 3, 5, 0);
        return attackTypes;
    }
}
