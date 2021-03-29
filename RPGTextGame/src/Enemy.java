import java.util.Random;

/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell (2021)
 *
 * Class Enemy:
 * Defines a simple Enemy that has a name, health, and selection of attacks.
 */
public class Enemy implements AttackableTarget {
    /**
     * The enemy health that represents this enemy being dead once it reaches 0.
     */
    private int enemyHealth;
    /**
     * The enemy name used for personalisation of output.
     */
    private String enemyName;
    /**
     * A collection of attacks that can be used when the enemy wishes to choose and attack.
     */
    private AttackType[] attackTypes;

    /**
     * Store all the provided parameters into their associated instance variables.
     *
     * @param enemyName Name for personalised messages related to this enemy.
     * @param enemyHealth Amount of health that must be removed to destroy this enemy.
     * @param attackTypes A collection of attacks for the enemy to choose between.
     */
    public Enemy(String enemyName, int enemyHealth, AttackType[] attackTypes) {
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
        this.attackTypes = attackTypes;
    }

    /**
     * Chooses a random attack from all possible attacks for this enemy.
     *
     * @param rand Shared reference to Random.
     * @return A random attack type from the collection of attacks for this enemy.
     */
    public AttackType chooseAttack(Random rand) {
        return attackTypes[rand.nextInt(attackTypes.length)];
    }

    /**
     * Getter for the enemy's health.
     *
     * @return The value stored in enemyHealth.
     */
    public int getEnemyHealth() {
        return enemyHealth;
    }

    /**
     * Generates a String representing the enemy's current status.
     * Includes enemyName and enemyHealth.
     *
     * @return Enemy's current status String.
     */
    @Override
    public String toString() {
        return "Enemy Status: " + enemyName + " has " + enemyHealth + " HP.";
    }

    /**
     * Reduces the enemyHealth by amount.
     *
     * @param amount The amount of health to reduce by.
     */
    @Override
    public void takeDamage(int amount) {
        enemyHealth -= amount;
    }

    /**
     * Getter for the enemy's name.
     *
     * @return Returns enemyName.
     */
    @Override
    public String getName() {
        return enemyName;
    }
}
