import java.util.Scanner;

public class Player implements AttackableTarget {
    private String playerName;
    private int playerHealth;
    private int playerMaxHealth;
    private int playerMana;
    private int playerMaxMana;
    private AttackType[] attackTypes;
    private int minimumSpellCost;
    private final double HEAL_HP_MULTIPLIER = 0.3;
    private final double MANA_RESTORE_MULTIPLIER = 0.8;

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

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void printAttackList() {
        for(int i = 0; i < attackTypes.length; i++) {
            System.out.println((i+1) + ". " + attackTypes[i]);
        }
    }

    public AttackType chooseAttack(Scanner scan) {
        int choice;
        do {
            choice = chooseAttackNumber(scan);
            scan.nextLine();
        } while(!isAttackValid(choice));
        return attackTypes[choice-1];
    }

    @Override
    public String toString() {
        return "Player Status: " + playerName + " has "
                + playerHealth + "/" + playerMaxHealth
                + " HP and " + playerMana + "/" + playerMaxMana
                + " Mana.";
    }

    private int chooseAttackNumber(Scanner scan) {
        boolean inputIsNumber = false;
        do {
            printAttackList();
            System.out.print("Which attack would you like to use (1-" + attackTypes.length + "): ");

            if(!scan.hasNextInt()) {
                System.out.println("You can only enter numbers in the valid range for this input!");
                scan.nextLine();
            } else {
                inputIsNumber = true;
            }
        } while (!inputIsNumber);
        return scan.nextInt();
    }

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

    @Override
    public void takeDamage(int amount) {
        playerHealth -= amount;
    }

    @Override
    public String getName() {
        return playerName;
    }

    public void spendMana(int amount) {
        playerMana -= amount;
    }

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

    public boolean canAttack() {
        return playerMana > minimumSpellCost;
    }

    public void updateMinimumSpellCost() {
        if(attackTypes.length == 0) {
            minimumSpellCost = playerMaxMana + 1;
            return;
        }
        minimumSpellCost = attackTypes[0].getManaCost();
        for(int i = 1; i < attackTypes.length; i++) {
            if(attackTypes[i].getManaCost() < minimumSpellCost) {
                minimumSpellCost = attackTypes[i].getManaCost();
            }
        }
    }
}
