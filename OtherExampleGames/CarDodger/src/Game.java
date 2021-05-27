import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * CarDodger
 * Author: Peter Mitchell (2021)
 *
 * Game class:
 * Defines the entry point for the game by creating the frame,
 * and populating it with a GamePanel.
 */
public class Game implements KeyListener {
    /**
     * Entry point for the application to create an instance of the Game class.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Game game = new Game();
    }

    /**
     * Reference to the GamePanel object to pass key events to.
     */
    private GamePanel gamePanel;
    /**
     * Reference to the StatusPanel to pass updates to.
     */
    private StatusPanel statusPanel;

    /**
     * Creates the JFrame with a GamePanel inside it, attaches a key listener,
     * and makes everything visible.
     */
    public Game() {
        JFrame frame = new JFrame("Car Dodger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        gamePanel = new GamePanel(this);
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
        statusPanel = new StatusPanel();
        frame.getContentPane().add(statusPanel, BorderLayout.EAST);

        frame.addKeyListener(this);
        frame.pack();
        frame.setVisible(true);
        // Moves the focus back to the frame it will default to the quit button, this allows keyboard input
        frame.requestFocus();
    }

    /**
     * Passes the current score to the status panel.
     *
     * @param newScore New score to display.
     */
    public void setScore(int newScore) {
        statusPanel.setScore(newScore);
    }

    /**
     * Passes the current number of crashes to the status panel.
     *
     * @param newCrashes New number of crashes to display.
     */
    public void setCrashes(int newCrashes) {
        statusPanel.setCrashes(newCrashes);
    }

    /**
     * Called when the key is pressed down. Passes the key press on to the GamePanel.
     *
     * @param e Information about what key was pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode());
    }

    /**
     * Not used.
     *
     * @param e Not used.
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    /**
     * Not used.
     *
     * @param e Not used.
     */
    @Override
    public void keyReleased(KeyEvent e) {}
}
