import java.util.Random;
import java.util.Scanner;

/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell (2021)
 *
 * Class Game:
 * Manages the game loop by setting up the game and then
 * alternating between the Map and random Encounters until the game ends.
 */
public class Game {
    /**
     * The Player object to store their defined name, attacks, and current status.
     */
    private Player player;
    /**
     * Scanner object to enable input from the keyboard.
     */
    private Scanner scan;
    /**
     * Random object to generate random numbers.
     */
    private Random rand;
    /**
     * Current map that the player is progressing through.
     */
    private Map map;

    /**
     * Sets up the keyboard input and Random number generator.
     */
    public Game() {
        scan = new Scanner(System.in);
        rand = new Random();
    }

    /**
     * Allows the player to enter a name of their choice.
     * The new name is used to create the Player object.
     */
    public void createPlayer() {
        System.out.println("Welcome new player!");
        System.out.print("Enter your name: ");
        String playerName = getStringOrQuit(scan).trim();
        player = new Player(playerName);
    }

    /**
     * Game loop. Initialises the player and a map.
     * Then loops until the game is over showing the map and encounters in sequence.
     * Ends when either the player's health reaches 0, or all the enemies on the map are defeated.
     */
    public void startGame() {
        createPlayer();
        map = new Map(rand);
        String input;
        int enemiesDefeated = 0;

        // While the player is still alive
        while(player.getPlayerHealth() > 0) {
            // Loop until an encounter is found on the map
            int encounterEvent = 0;
            while(encounterEvent <= 0) {
                map.printMap();
                System.out.print("Where do you want to go: ");
                input = getStringOrQuit(scan);
                encounterEvent = map.movePlayer(input);
            }

            // Complete an encounter between the player and a random enemy
            Encounter encounter = new Encounter(player, rand, scan);
            encounter.runEncounter();

            // If the player is still alive after the encounter update and restore the player
            if(player.getPlayerHealth() > 0) {
                enemiesDefeated++;
                player.healPlayer();
                player.restoreMana();

                // If the game is over by winning the map
                if(map.allEnemiesDefeated()) {
                    System.out.println("You defeated all the enemies! You win!");
                    return;
                }
            }
        }
        // Game over from running out of health
        System.out.println("Oh no! You were defeated.");
        System.out.println("You defeated " + enemiesDefeated + " before you fell!");
    }

    /**
     * Helper method that reads in a line of text and checks if the text is "quit".
     * If the text is "quit" it will exit the game. Otherwise returns the input.
     *
     * @param scan Reference to shared Scanner object.
     * @return The input from nextLine() if the input was not "quit".
     */
    public static String getStringOrQuit(Scanner scan) {
        String input = scan.nextLine();
        if(input.equalsIgnoreCase("quit")) {
            System.out.println("Thanks for playing! Goodbye!");
            System.exit(0);
        }
        return input;
    }
}
