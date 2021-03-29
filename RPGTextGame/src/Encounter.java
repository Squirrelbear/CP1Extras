import java.util.Random;
import java.util.Scanner;

/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell
 *
 * Class Encounter:
 * Encounter is a battle between one Player and one random Enemy that ends when one is defeated.
 */
public class Encounter {
    /**
     * Reference to player object.
     */
    private Player player;

    /**
     * Reference to randomly generated enemy object.
     */
    private Enemy enemy;

    /**
     * Reference to shared Random.
     */
    private Random rand;


    /**
     * Reference to shared Scanner.
     */
    private Scanner scan;

    /**
     * Create a new Encounter for the player and randomly spawn a new enemy.
     *
     * @param player Reference to player object.
     * @param rand Reference to shared Random object.
     * @param scan Reference to shared Scanner object.
     */
    public Encounter(Player player, Random rand, Scanner scan) {
        this.player = player;
        this.rand = rand;
        this.scan = scan;
        enemy = spawnRandomEnemy();
    }

    /**
     * Game loop for an encounter. Will end when either the player or enemy is defeated.
     * During each turn the player is given a turn, followed by the enemy if not
     * defeated by the player yet.
     */
    public void runEncounter() {
        System.out.println("Starting new encounter against " + enemy.getName());
        int turnNumber = 1;

        while(player.getPlayerHealth() > 0 && enemy.getEnemyHealth() > 0) {
            System.out.println("Turn " + turnNumber + " beginning!");
            System.out.println(enemy + "\n" + player + "\n");
            performPlayerTurn();

            if(enemy.getEnemyHealth() <= 0) {
                System.out.println("You have defeated " + enemy.getName() + "!");
            } else {
                performEnemyTurn();
            }
            System.out.println();
            turnNumber++;
        }
    }

    /**
     * Process player's current turn. Turn is skipped if they have insufficient mana.
     * Player chooses one attack at the cost of their mana and then performs the
     * attack against the enemy.
     */
    public void performPlayerTurn() {
        if(!player.canAttack()) {
            System.out.println("You have insufficient Mana to attack. Turn skipped.");
            return;
        }
        AttackType attackType = player.chooseAttack(scan);
        player.spendMana(attackType.getManaCost());
        performAttack(player, enemy, attackType);
    }

    /**
     * Processes enemy's current turn. Enemy will select an attack based on logic
     * in the chooseAttack() method and then perform the attack against the player.
     */
    public void performEnemyTurn() {
        AttackType attackType = enemy.chooseAttack(rand);
        performAttack(enemy, player, attackType);
    }

    /**
     * Generates a random damage value based on an AttackType and applies the damage
     * to the defender. A message is printed to provide context information about the attack.
     *
     * @param attacker The attacker that can be any valid Enemy or Player. Is dealing the damage.
     * @param defender The defender that can be any valid Enemy or Player. Is taking damage from the attacker.
     * @param attackType The selected attack to be used against defender.
     */
    public void performAttack(AttackableTarget attacker, AttackableTarget defender, AttackType attackType) {
        int actualDamage = attackType.getDamageValueInRange(rand);
        System.out.println(attacker.getName() + " attacks " + defender.getName() + " with "
                            + attackType.getAttackName() + " for " + actualDamage + " damage.");
        defender.takeDamage(actualDamage);
    }

    /**
     * Factory method to create a random enemy that can be an Orc, Troll, or Wolf.
     *
     * @return Random new Orc, Troll, or Wolf type Enemy object.
     */
    private Enemy spawnRandomEnemy() {
        Enemy enemy = null;
        switch(rand.nextInt(3)) {
            case 0:
                enemy = new Orc(rand);
                break;
            case 1:
                enemy = new Troll(rand);
                break;
            case 2:
                enemy = new Wolf(rand);
                break;
        }
        return enemy;
    }
}
