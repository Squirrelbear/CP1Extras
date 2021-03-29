import java.util.Random;

/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell (2021)
 *
 * Class AttackType:
 * Defines an attack that can have a name, min/max damage range, and a mana cost.
 */
public class AttackType {
    /**
     * Name of the attack.
     */
    private String attackName;
    /**
     * Minimum of the random number range for this AttackType.
     */
    private int damageValueMin;
    /**
     * Maximum of the random number range for this AttackType.
     */
    private int damageValueMax;
    /**
     * Amount of mana this attack costs to use.
     */
    private int manaCost;

    /**
     * Assigns all the parameters to instance variables.
     *
     * @param attackName Name of the attack.
     * @param damageValueMin Minimum of the random number range for this AttackType.
     * @param damageValueMax Maximum of the random number range for this AttackType.
     * @param manaCost Amount of mana this attack costs to use.
     */
    public AttackType(String attackName, int damageValueMin, int damageValueMax, int manaCost) {
        this.attackName = attackName;
        this.damageValueMin = damageValueMin;
        this.damageValueMax = damageValueMax;
        this.manaCost = manaCost;
    }

    /**
     * Getter for attackName.
     *
     * @return The name of this attack.
     */
    public String getAttackName() {
        return attackName;
    }

    /**
     * Getter for manaCost.
     *
     * @return The mana cost of this attack.
     */
    public int getManaCost() {
        return manaCost;
    }

    /**
     * Generates a String representation of AttackType, formatted to be suitable for listing details.
     * Includes: attackName, damageValueMin, damageValueMax, and manaCost.
     *
     * @return Summary of all properties making up an AttackType.
     */
    @Override
    public String toString() {
        return attackName + " (" + damageValueMin + "-" + damageValueMax + ", costs " + manaCost + ")";
    }

    /**
     * Gets a random number in the range damageValueMin to damageValueMax (inclusive).
     *
     * @param rand Shared reference to Random.
     * @return Random number between damageValueMin and damageValueMax.
     */
    public int getDamageValueInRange(Random rand) {
        return rand.nextInt(damageValueMax - damageValueMin + 1) + damageValueMin;
    }
}
