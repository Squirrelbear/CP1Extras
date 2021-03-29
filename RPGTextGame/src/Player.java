import java.util.Scanner;

/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell (2021)
 *
 * Class Player:
 * A Player defines the properties for the player's current status in the game.
 * Players can performAttacks on any other AttackableTarget during Enounters.
 */
public class Player implements AttackableTarget {
    /**
     * Player's name is set once when the player is created. And is used to make output more personalised.
     */
    private String playerName;
    /**
     * Player's health represents a number between 0 and playerMaxHealth.
     * A value of playerHealth <= 0 means the player has been defeated.
     */
    private int playerHealth;
    /**
     * Represents the player's current maximum health. Used for restoring a percent of health.
     */
    private int playerMaxHealth;
    /**
     * Player's mana is used to perform attacks. Each attack will spend some mana and
     * requires the specified mana to use each attack.
     */
    private int playerMana;
    /**
     * Player's maximum mana is used for knowing how much to cap at for restoring mana.
     */
    private int playerMaxMana;
    /**
     * The collection of attacks a player can use against Enemy targets.
     */
    private AttackType[] attackTypes;
    /**
     * The cached minimum amount of mana it costs to case the cheapest AttackType in attackTypes.
     * This is recalculated by calling updateMinimumSpellCost().
     */
    private int minimumSpellCost;
    /**
     * A percent representing how much health should be restored by healPlayer().
     * 0.3 represents healing up to 30% of playerMaxHealth without going over playerMaxHealth.
     */
    private final double HEAL_HP_MULTIPLIER = 0.3;
    /**
     * A percent representing how much mana should be restored by restoreMana().
     * 0.8 represents restoring up to 80% of playerMaxMana without going over playerMaxMana.
     */
    private final double MANA_RESTORE_MULTIPLIER = 0.8;

    /**
     * Create a new player with a selected playerName. Will default mana and health stats all to 100.
     * Five specific attacks are created and added to the attackTypes, and updateMinimumSpellCost()
     * is called to update minimumSpellCost.
     *
     * @param playerName The chosen playerName used for personalisation.
     */
    public Player(String playerName) {
        playerHealth = playerMaxHealth = playerMaxMana = playerMana = 100;
        this.playerName = playerName;
        attackTypes = new AttackType[5];
        attackTypes[0] = new AttackType("Magic Missile", 5, 10, 5);
        attackTypes[1] = new AttackType("Lightning Bolt", 10, 20, 10);
        attackTypes[2] = new AttackType("Fireball", 5, 30, 15);
        attackTypes[3] = new AttackType("Blinding Flash", 10, 10, 8);
        attackTypes[4] = new AttackType("Tsunami", 0, 40, 20);
        updateMinimumSpellCost();
    }

    /**
     * Getter for playerHealth.
     *
     * @return The value stored in playerHealth.
     */
    public int getPlayerHealth() {
        return playerHealth;
    }

    /**
     * Print the list of attacks from attackTypes as a numbered list with numbering starting at 1.
     */
    public void printAttackList() {
        for(int i = 0; i < attackTypes.length; i++) {
            System.out.println((i+1) + ". " + attackTypes[i]);
        }
    }

    /**
     * Method to choose a valid AttackType from attackTypes.
     * Player will be asked to enter a valid number representing an element of attackTypes from 1 to N.
     * The attackType selected must exist and the player must have sufficient mana to use it.
     *
     * @param scan Shared Scanner reference.
     * @return The player's attack selected from the player's attackTypes.
     */
    public AttackType chooseAttack(Scanner scan) {
        int choice;
        do {
            choice = chooseAttackNumber(scan);
            scan.nextLine();
        } while(!isAttackValid(choice));
        return attackTypes[choice-1];
    }

    /**
     * Generates a String representing the player's current status.
     * Includes: playerName, playerHealth, playerMaxHealth, playerMana, and playerMaxMana.
     *
     * @return Player's current status String.
     */
    @Override
    public String toString() {
        return "Player Status: " + playerName + " has "
                + playerHealth + "/" + playerMaxHealth
                + " HP and " + playerMana + "/" + playerMaxMana
                + " Mana.";
    }

    /**
     * Loops until a valid integer number has been entered.
     * Shows an error if the input is not an integer, before re-asking the question.
     *
     * @param scan Shared Scanner reference.
     * @return Valid integer with no validation on range.
     */
    private int chooseAttackNumber(Scanner scan) {
        boolean inputIsNumber = false;
        do {
            printAttackList();
            System.out.print("Which attack would you like to use (1-" + attackTypes.length + "): ");

            // If input is not an integer drop the data and show an error.
            if(!scan.hasNextInt()) {
                Game.getStringOrQuit(scan);
                System.out.println("You can only enter numbers in the valid range for this input!");
            } else {
                inputIsNumber = true;
            }
        } while (!inputIsNumber);
        return scan.nextInt();
    }

    /**
     * Validates choice is in the correct range based on the number of elements in attackTypes.
     * Then validates if the mana cost of the selected attack is more than the player's current mana.
     * Will show a relevant error message if either validation fails.
     *
     * @param choice Chosen attackType number to validate.
     * @return True if validation is passed for number range and mana cost requirements.
     */
    private boolean isAttackValid(int choice) {
        if(choice < 1 || choice > attackTypes.length) {
            System.out.println("Invalid choice. Please enter a number in the valid range.");
            return false;
        } else if(attackTypes[choice-1].getManaCost() > playerMana) {
            System.out.println("You do not have enough mana to cast that attack. Select something else.");
            return false;
        }
        return true;
    }

    /**
     * Reduces the playerHealth by amount.
     *
     * @param amount Amount of health to reduce by.
     */
    @Override
    public void takeDamage(int amount) {
        playerHealth -= amount;
    }

    /**
     * Getter for the player's name.
     *
     * @return Returns playerName.
     */
    @Override
    public String getName() {
        return playerName;
    }

    /**
     * Reduces the playerMana by amount.
     *
     * @param amount Amount of mana to reduce by.
     */
    public void spendMana(int amount) {
        playerMana -= amount;
    }

    /**
     * Heals the player for a percent of their playerMaxHealth.
     * Does nothing if the player is already full health.
     * The percent defined by HEAL_HP_MULTIPLIER will only heal up to the maximum of playerMaxHealth.
     * The amount is added to the playerHealth and message indicating the change to health is printed.
     */
    public void healPlayer() {
        if(playerHealth == playerMaxHealth) {
            System.out.println("Heal was cast, but your health was already full.");
            return;
        }

        int healAmount = (int)(playerMaxHealth * HEAL_HP_MULTIPLIER);
        if(playerHealth + healAmount > playerMaxHealth) {
            healAmount = playerMaxHealth - playerHealth;
        }
        playerHealth += healAmount;
        System.out.println("You have been healed for " + healAmount + " bringing your health to "
                           + playerHealth + "/" + playerMaxHealth + ".");
    }

    /**
     * Restores mana to the player for a percent of their playerMaxMana.
     * Does nothing if the player is already full mana.
     * The percent defined by MANA_RESTORE_MULTIPLIER will only restore up to the maximum of playerMaxMana.
     * The amount is added to the playerMana and message indicating the change to mana is printed.
     */
    public void restoreMana() {
        if(playerMana == playerMaxMana) {
            System.out.println("Restore Mana was cast, but your Mana was already full.");
            return;
        }

        int healAmount = (int)(playerMaxMana * MANA_RESTORE_MULTIPLIER);
        if(playerMana + healAmount > playerMaxMana) {
            healAmount = playerMaxMana - playerMana;
        }
        playerMana += healAmount;
        System.out.println("You have been healed for " + healAmount + " bringing your Mana to "
                + playerMana + "/" + playerMaxMana + ".");
    }

    /**
     * Tests if the player's mana is high enough to use the cheapest attackType.
     *
     * @return True if the player has enough mana to use cheapest attack.
     */
    public boolean canAttack() {
        return playerMana > minimumSpellCost;
    }

    /**
     * Updates minimumSpellCost to the minimum cost in attackTypes.
     */
    public void updateMinimumSpellCost() {
        // There are no attacks, so set the minimumCost impossibly high to make canAttack() return false
        if(attackTypes.length == 0) {
            minimumSpellCost = playerMaxMana + 1;
            return;
        }

        // Find minimum mana cost and store it in minimumSpellCost
        minimumSpellCost = attackTypes[0].getManaCost();
        for(int i = 1; i < attackTypes.length; i++) {
            if(attackTypes[i].getManaCost() < minimumSpellCost) {
                minimumSpellCost = attackTypes[i].getManaCost();
            }
        }
    }
}
