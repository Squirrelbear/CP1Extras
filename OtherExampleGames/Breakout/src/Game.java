import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Breakout
 * Author: Peter Mitchell (2021)
 *
 * Game class:
 * Defines the entry point for the Pong game.
 * Manages the game loop and input events passing them to the gamePanel.
 */
public class Game extends JFrame implements KeyListener, ActionListener {
    /**
     * Creates a new game and starts the game.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    /**
     * Timer used for the update/render loop.
     * Timer delay is changed to modify difficulty via the boardPanel.
     */
    private Timer gameLoopTimer;
    /**
     * Reference to the boardPanel to pass input to.
     */
    private GamePanel gamePanel;
    /**
     * Reference to the scorePanel to update score labels.
     */
    private ScorePanel scorePanel;

    /**
     * Creates a single JFrame with the boardPanel, then
     * configures the game loop timer, sets up the KeyListener
     * for input, and then makes it visible.
     */
    public Game() {
        super("Breakout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        scorePanel = new ScorePanel();
        getContentPane().add(scorePanel, BorderLayout.NORTH);
        gamePanel = new GamePanel(this);
        getContentPane().add(gamePanel, BorderLayout.SOUTH);

        gameLoopTimer = new Timer(20,this);
        addKeyListener(this);

        pack();
        setVisible(true);
    }

    /**
     * Starts the game by starting the game loop timer.
     */
    public void startGame() {
        gameLoopTimer.start();
    }

    /**
     * Passes the new score information to the score panel.
     *
     * @param newScore The new score to show.
     */
    public void setScore(int newScore) {
        scorePanel.setScore(newScore);
    }

    /**
     * Event triggered every time the gameLoopTimer triggers.
     * Forces the boardPanel to perform an update and will include a repaint().
     *
     * @param e Not used.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.update();
    }

    /**
     * Passes the information about pressed keys to the gamePanel.
     *
     * @param e The information about the key that was pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode(), true);
    }

    /**
     * Passes the information about released keys to the gamePanel.
     *
     * @param e The information about the key that was pressed.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode(), false);
    }
    /**
     * Not used.
     *
     * @param e Not used.
     */
    @Override
    public void keyTyped(KeyEvent e) {}
}
