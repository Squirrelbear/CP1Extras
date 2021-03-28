import java.util.Scanner;

public class Player {
    private String playerName;
    private int playerHealth;
    private int playerMaxHealth;
    private int playerMana;
    private int playerMaxMana;
    private AttackType[] attackTypes;

    public Player(String playerName) {
        playerHealth = playerMaxHealth = playerMaxMana = playerMana = 100;
        this.playerName = playerName;
        attackTypes = new AttackType[5];
        attackTypes[0] = new AttackType("Magic Missile", 5, 10, 5);
        attackTypes[1] = new AttackType("Lightning Bolt", 10, 20, 10);
        attackTypes[2] = new AttackType("Fireball", 5, 30, 15);
        attackTypes[3] = new AttackType("Blinding Flash", 10, 10, 8);
        attackTypes[4] = new AttackType("Tsunami", 0, 40, 20);
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
}
