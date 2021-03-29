/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell
 *
 * Interface AttackableTarget:
 * Defines interface methods to allow AttackableTargets to interact.
 */
public interface AttackableTarget {
    /**
     * Abstract method takeDamage should be defined to modify the object's health by the value amount.
     *
     * @param amount Amount to modify health by.
     */
    void takeDamage(int amount);

    /**
     * Abstract method getName should be defined to return the object's name.
     *
     * @return Gets the name from this object.
     */
    String getName();
}
