import java.util.Random;

public class Enemy {
    private int enemyHealth;
    private String enemyName;
    private AttackType[] attackTypes;

    public Enemy(String enemyName, int enemyHealth, AttackType[] attackTypes) {
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
        this.attackTypes = attackTypes;
    }

    public AttackType chooseAttack(Random rand) {
        return attackTypes[rand.nextInt(attackTypes.length)];
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    @Override
    public String toString() {
        return "Enemy Status: " + enemyName + " has " + enemyHealth + " HP.";
    }
}
