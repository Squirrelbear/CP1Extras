import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Car Dodger
 * Author: Peter Mitchell (2021)
 *
 * GamePanel class:
 * Manages the state of the game by controlling the EnemyCarManager,
 * the player, and animating the road.
 */
public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    /**
     * Width of the panel.
     */
    public static final int PANEL_WIDTH = 500;
    /**
     * Height of the panel.
     */
    public static final int PANEL_HEIGHT = 500;
    /**
     * Time between updates in ms.
     */
    public static final int TIME_INTERVAL = 20;
    /**
     * Number of lanes to show as columns.
     */
    private static final int NUMBER_OF_LANES = 14;
    /**
     * Maximum number of lanes that can be active at one time.
     */
    private static final int MAX_ACTIVE_LANES = 10;

    /**
     * Reference to the game for passing information about status changes.
     */
    private Game game;
    /**
     * The enemy car manager that stores all the enemy cars.
     */
    private EnemyCarManager enemyCarManager;
    /**
     * The player object that can be interacted with using the mouse.
     */
    private PlayerCar playerCar;
    /**
     * A simple animated object that makes it appear the car is driving forward.
     */
    private AnimatedRoad animatedRoad;
    /**
     * Timer for triggering updates.
     */
    private Timer gameTimer;
    /**
     * The current score based on number of cars dodged.
     */
    private int score;
    /**
     * The current crash count based on how many crashes have occurred.
     */
    private int crashCount;

    /**
     * Configures the game state ready to play.
     *
     * @param game Reference to the Game object for passing score updates.
     */
    public GamePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        setBackground(new Color(76, 72, 72));

        playerCar = new PlayerCar(new Position(PANEL_WIDTH/2, PANEL_HEIGHT - Car.CAR_HEIGHT - 50));
        enemyCarManager = new EnemyCarManager(NUMBER_OF_LANES, MAX_ACTIVE_LANES, playerCar, this);
        animatedRoad = new AnimatedRoad(NUMBER_OF_LANES);

        gameTimer = new Timer(TIME_INTERVAL, this);
        addMouseListener(this);
        addMouseMotionListener(this);
        gameTimer.start();
    }

    /**
     * Updates the enemy cars, the player's animation, and the road animation.
     */
    public void update() {
        enemyCarManager.update(TIME_INTERVAL);
        playerCar.update(TIME_INTERVAL);
        animatedRoad.update(TIME_INTERVAL);
        repaint();
    }

    /**
     * Draws the road, car and enemy cars to the panel.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    public void paint(Graphics g) {
        super.paint(g);
        animatedRoad.paint(g);
        playerCar.paint(g);
        enemyCarManager.paint(g);
    }

    /**
     * Handles the key input to exit the game when escape is pressed.
     *
     * @param keyCode The key that was pressed.
     */
    public void handleInput(int keyCode) {
        if(keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    /**
     * Increases the score by the specified amount, and passes the information to the Game object.
     *
     * @param amount Amount to change the score by.
     */
    public void increaseScore(int amount) {
        score += amount;
        game.setScore(score);
    }

    /**
     * Increases the number of crashes by 1. Notifies the Game object that this has happened.
     * Then resets the game state back to default to start with 0 score again.
     */
    public void increaseCrashCount() {
        crashCount++;
        game.setCrashes(crashCount);
        score = 0;
        game.setScore(0);
        playerCar.reset();
        enemyCarManager.reset();
    }

    /**
     * Called when the gameTimer triggers an event making the update loop trigger.
     *
     * @param e Information about the event that was triggered.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    /**
     * If the mouse is inside the player car this will start the player's
     * car being tied to the mouse's X position.
     *
     * @param e Information about the mouse event that occurred.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Position mousePosition = new Position(e.getX(), e.getY());
        if(playerCar.isPositionInside(mousePosition)) {
            playerCar.setIsMoving(true);
        }
    }

    /**
     * Releases the player car from any movement it may be engaged in.
     *
     * @param e Information about the mouse event that occurred.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        playerCar.setIsMoving(false);
    }

    /**
     * If the player's car is current considered to be moving, it will
     * update the player's X position based on that of the mouse.
     *
     * @param e Information about the mouse event that occurred.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(playerCar.isMoving()) {
            Position mousePosition = new Position(e.getX(), e.getY());
            playerCar.updatePosition(mousePosition);
        }
        repaint();
    }

    /**
     * Not used.
     *
     * @param e Not used.
     */
    @Override
    public void mouseClicked(MouseEvent e) {}
    /**
     * Not used.
     *
     * @param e Not used.
     */
    @Override
    public void mouseEntered(MouseEvent e) {}
    /**
     * Not used.
     *
     * @param e Not used.
     */
    @Override
    public void mouseExited(MouseEvent e) {}
    /**
     * Not used.
     *
     * @param e Not used.
     */
    @Override
    public void mouseMoved(MouseEvent e) {}
}
