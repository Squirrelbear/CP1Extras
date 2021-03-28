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
        System.out.println("Starting new encounter");
        int turnNumber = 1;
        while(player.getPlayerHealth() > 0 && enemy.getEnemyHealth() > 0) {
            System.out.println("Turn " + turnNumber + " beginning!");
            System.out.println(enemy + "\n" + player + "\n");
            performPlayerTurn();

            if(enemy.getEnemyHealth() <= 0) {
                System.out.println("You have defeated the enemy!");
            } else {
                performEnemyTurn();
            }
            System.out.println();
            turnNumber++;
        }
    }

    public void performPlayerTurn() {
        AttackType attackType = player.chooseAttack(scan);
        performAttack(attackType);
    }

    public void performEnemyTurn() {
        AttackType attackType = enemy.chooseAttack(rand);
        performAttack(attackType);
    }

    public void performAttack(AttackType attackType) {
        int actualDamage = attackType.getDamageValueInRange(rand);
        System.out.println("Used " + attackType.getAttackName() + " for " + actualDamage + " damage.");
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
