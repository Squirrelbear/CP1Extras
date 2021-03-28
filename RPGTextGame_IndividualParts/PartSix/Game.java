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
        Encounter encounter = new Encounter(player, rand, scan);
        encounter.runEncounter();
    }

}
