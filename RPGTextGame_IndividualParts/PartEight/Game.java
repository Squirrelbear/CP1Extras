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
        int enemiesDefeated = 0;

        while(player.getPlayerHealth() > 0) {
            Encounter encounter = new Encounter(player, rand, scan);
            encounter.runEncounter();

            if(player.getPlayerHealth() > 0) {
                enemiesDefeated++;
                player.healPlayer();
                player.restoreMana();
            }
        }
        System.out.println("Oh no! You were defeated.");
        System.out.println("You defeated " + enemiesDefeated + " before you fell!");
    }

}
