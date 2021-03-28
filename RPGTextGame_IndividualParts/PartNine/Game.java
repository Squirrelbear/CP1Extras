import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scan;
    private Random rand;
    private Map map;

    public Game() {
        scan = new Scanner(System.in);
        rand = new Random();
    }

    public void createPlayer() {
        System.out.println("Welcome new player!");
        System.out.print("Enter your name: ");
        String playerName = getStringOrQuit(scan).trim();
        player = new Player(playerName);
    }

    public void startGame() {
        createPlayer();
        map = new Map(rand);
        String input;
        int enemiesDefeated = 0;

        while(player.getPlayerHealth() > 0) {
            int encounterEvent = 0;
            while(encounterEvent <= 0) {
                map.printMap();
                System.out.print("Where do you want to go: ");
                input = getStringOrQuit(scan);
                encounterEvent = map.movePlayer(input);
            }

            Encounter encounter = new Encounter(player, rand, scan);
            encounter.runEncounter();

            if(player.getPlayerHealth() > 0) {
                enemiesDefeated++;
                player.healPlayer();
                player.restoreMana();

                if(map.allEnemiesDefeated()) {
                    System.out.println("You defeated all the enemies! You win!");
                    return;
                }
            }
        }
        System.out.println("Oh no! You were defeated.");
        System.out.println("You defeated " + enemiesDefeated + " before you fell!");
    }

    public static String getStringOrQuit(Scanner scan) {
        String input = scan.nextLine();
        if(input.equalsIgnoreCase("quit")) {
            System.out.println("Thanks for playing! Goodbye!");
            System.exit(0);
        }
        return input;
    }
}
