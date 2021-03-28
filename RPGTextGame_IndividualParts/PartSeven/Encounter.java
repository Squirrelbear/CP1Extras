import java.util.Random;
import java.util.Scanner;

public class Encounter {
    private Player player;
    private Enemy enemy;
    private Random rand;
    private Scanner scan;

    public Encounter(Player player, Random rand, Scanner scan) {
        this.player = player;
        this.rand = rand;
        this.scan = scan;
        enemy = spawnRandomEnemy();
    }

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

    public void performPlayerTurn() {
        AttackType attackType = player.chooseAttack(scan);
        performAttack(player, enemy, attackType);
    }

    public void performEnemyTurn() {
        AttackType attackType = enemy.chooseAttack(rand);
        performAttack(enemy, player, attackType);
    }

    public void performAttack(AttackableTarget attacker, AttackableTarget defender, AttackType attackType) {
        int actualDamage = attackType.getDamageValueInRange(rand);
        System.out.println(attacker.getName() + " attacks " + defender.getName() + " with "
                            + attackType.getAttackName() + " for " + actualDamage + " damage.");
        defender.takeDamage(actualDamage);
    }

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
