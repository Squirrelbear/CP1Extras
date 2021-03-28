public class Enemy {
    private int enemyHealth;
    private String enemyName;

    public Enemy(String enemyName, int enemyHealth) {
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
    }

    @Override
    public String toString() {
        return "Enemy Status: " + enemyName + " has " + enemyHealth + " HP.";
    }
}
