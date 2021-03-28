import DemoProject.AttackType;

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
        System.out.println(player);
        Enemy enemy = new Enemy("Bob",50);
        System.out.println(enemy);
        AttackType sampleAttack = new AttackType("Zap", 10, 15, 5);
        System.out.println(sampleAttack);
        System.out.println(sampleAttack.getAttackName() + " costs " + sampleAttack.getManaCost());
        System.out.println("Example damage output: " + sampleAttack.getDamageValueInRange(rand));
    }
}
