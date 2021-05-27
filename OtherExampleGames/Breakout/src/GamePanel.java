import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Breakout
 * Author: Peter Mitchell (2021)
 *
 * GamePanel class:
 * Manages the state of the game with individual elements for the
 * paddle, bricks, and ball.
 */
public class GamePanel extends JPanel {
    /**
     * Width of the play space with paddles in pixels.
     */
    public static final int PANEL_WIDTH = 800;
    /**
     * Height of the play space with paddles in pixels.
     */
    public static final int PANEL_HEIGHT = 500;

    /**
     * Reference to the Game object for passing the score updates to.
     */
    private Game game;
    /**
     * The player's paddle controlled with left/right arrow keys.
     */
    private Paddle playerPaddle;
    /**
     * The brick manager containing all active bricks.
     */
    private BrickManager brickManager;
    /**
     * The ball that will bounce around the screen.
     */
    private Ball ball;
    /**
     * Current score;
     */
    private int score;
    /**
     * True indicates that all blocks that can be destroyed have been destroyed.
     */
    private boolean isGameOver;

    /**
     * Creates the game with a default state ready to be played.
     *
     * @param game Reference to the Game object for passing score updates.
     */
    public GamePanel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
        score = 0;
        playerPaddle = new Paddle(new Position(PANEL_WIDTH/2-50, PANEL_HEIGHT-50), 100, 20);
        brickManager = new BrickManager();
        brickManager.createDefaultBrickLayout();
        ball = new Ball(new Position(PANEL_WIDTH/2, PANEL_HEIGHT-80), this);
        isGameOver = false;
    }

    /**
     * Updates the paddle, bricks, and the ball.
     * Changes the game state to game over once all blocks are destroyed.
     */
    public void update() {
        if(isGameOver) return;
        playerPaddle.update();
        brickManager.update();
        ball.update();
        if(brickManager.allBlocksDestroyed()) {
            isGameOver = true;
        }
        repaint();
    }

    /**
     * Tests all the bricks for any hits with the ball and applies hits to the bricks
     * and score as necessary. After testing bricks the player paddle is tested for
     * collisions. If no collision was found null is returned.
     *
     * @param ball The ball to test collisions against.
     * @return The first element collision was detected against or null.
     */
    public Rectangle testCollisionsAgainstBall(Ball ball) {
        List<Brick> brickHits = brickManager.testCollisionsOnBricks(ball);
        if(brickHits.size() > 0) {
            for (Brick brick : brickHits) {
                brick.hitBrick();
                if(brick.isExpired()) {
                    score += brick.getBrickType() + 1;
                }
            }
            game.setScore(score);
            return brickHits.get(0);
        }

        if(playerPaddle.isIntersecting(ball)) {
            return playerPaddle;
        }

        return null;
    }

    /**
     * Swaps the game state to game over.
     */
    public void endGame() {
        isGameOver = true;
    }

    /**
     * Restarts by resetting all properties back to their defaults.
     */
    public void restart() {
        ball.reset();
        brickManager.createDefaultBrickLayout();
        playerPaddle.reset();
        isGameOver = false;
        score = 0;
        game.setScore(0);
    }

    /**
     * Draws the paddle, bricks, and the ball.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    public void paint(Graphics g) {
        super.paint(g);
        playerPaddle.paint(g);
        brickManager.paint(g);
        ball.paint(g);
        if(isGameOver) {
            drawGameOverMessage(g);
        }
    }

    /**
     * Update the game state based on input.
     *
     * @param keyCode The key that was interacted with.
     * @param pressed True if the key was just pressed, false if it was just released.
     */
    public void handleInput(int keyCode, boolean pressed) {
        if(keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if(keyCode == KeyEvent.VK_R) {
            restart();
        }

        playerPaddle.processInput(keyCode, pressed);
    }

    /**
     * Draws a game over message with a background that will show if the game
     * was won or lost depending on whether all the blocks were destroyed.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    private void drawGameOverMessage(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,PANEL_HEIGHT/2-20,PANEL_WIDTH, 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String message = "GAME OVER! " + (brickManager.allBlocksDestroyed() ? "You won!" : "You lost!") + " R to Restart.";
        int strWidth = g.getFontMetrics().stringWidth(message);
        g.drawString(message, PANEL_WIDTH/2-strWidth/2, PANEL_HEIGHT/2+10);
    }
}
