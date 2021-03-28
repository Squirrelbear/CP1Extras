import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scan;
    private Random rand;

    public Game() {
        scan = new Scanner(System.in);
        rand = new Random();
    }

    public void createPlayer() {
        System.out.println("Welcome new player!");
        System.out.print("Enter your name: ");
        String playerName = scan.nextLine().trim();
        player = new Player(playerName);
    }

    public void startGame() {
        createPlayer();
        for(int i = 0; i < 20; i++) {
            Enemy tempEnemy = spawnRandomEnemy();
            System.out.println(tempEnemy);
        }

        /*while(true) {
            AttackType attackType = player.chooseAttack(scan);
            System.out.println("You selected the " + attackType.getAttackName() + " attack!");
        }*/

        /*System.out.println(player);
        Enemy enemy = new Enemy("Bob",50);
        System.out.println(enemy);
        AttackType sampleAttack = new AttackType("Zap", 10, 15, 5);
        System.out.println(sampleAttack);
        System.out.println(sampleAttack.getAttackName() + " costs " + sampleAttack.getManaCost());
        System.out.println("Example damage output: " + sampleAttack.getDamageValueInRange(rand));
        player.printAttackList();*/
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
