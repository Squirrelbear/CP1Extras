import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scan;

    public Game() {
        scan = new Scanner(System.in);
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
    }
}
