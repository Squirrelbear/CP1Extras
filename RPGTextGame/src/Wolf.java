import java.util.Random;

/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell (2021)
 *
 * Class Wolf:
 * A specific type of Enemy with Wolf relevant names and attacks.
 */
public class Wolf extends Enemy {
    /**
     * Create a new Random Wolf using appropriate name and attacks.
     * @param rand Shared Random reference.
     */
    public Wolf(Random rand) {
        super(createRandomWolfName(rand), 100, createWolfAttacks());
    }

    /**
     * Generates a random name with the form:  "name (Wolf)".
     * Name is randomly selected with equal waiting from 3 options.
     *
     * @param rand Shared Random reference.
     * @return Random name string from a set of options.
     */
    private static String createRandomWolfName(Random rand) {
        String name = "(Wolf)";
        switch(rand.nextInt(3)) {
            case 0:
                name = "Shadow " + name;
                break;
            case 1:
                name = "Goremaw " + name;
                break;
            case 2:
                name = "Ironjaw " + name;
                break;

        }
        return name;
    }

    /**
     * Creates an array with three Wolf specific AttackTypes.
     *
     * @return An array with three Wolf specific AttackTypes.
     */
    private static AttackType[] createWolfAttacks() {
        AttackType[] attackTypes = new AttackType[3];
        attackTypes[0] = new AttackType("Bite", 3, 5, 0);
        attackTypes[1] = new AttackType("Vicious Bite", 3, 5, 0);
        attackTypes[2] = new AttackType("Howl", 3, 5, 0);
        return attackTypes;
    }
}
