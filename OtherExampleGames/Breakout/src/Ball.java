import java.awt.*;
import java.util.Random;

/**
 * Breakout
 * Author: Peter Mitchell (2021)
 *
 * Ball class:
 * Defines a single Ball that will bounce around the game space.
 */
public class Ball extends Rectangle {
    /**
     * Reference to test collisions.
     */
    private GamePanel gamePanel;
    /**
     * Size in pixels of the ball.
     */
    private static final int BALL_SIZE = 20;
    /**
     * The translation vector for how much to move x and y during each update.
     */
    private Position moveVector;
    /**
     * The start position used for resetting the start position after the ball hits left/right walls.
     */
    private Position startPos;
    /**
     * Shared reference to Random object.
     */
    private Random rand;

    /**
     * Initialises a ball to randomly move upward initially.
     *
     * @param startPos The position to start the ball at.
     * @param gamePanel Reference to the GamePanel for doing collision checks.
     */
    public Ball(Position startPos, GamePanel gamePanel) {
        super(startPos, BALL_SIZE, BALL_SIZE);
        this.gamePanel = gamePanel;
        rand = new Random();
        setRandomMoveVector();
        this.startPos=new Position(startPos);
    }

    /**
     * Moves the ball. If it collided with an edge the direction of motion is flipped
     * based on the side that was hit. If the collision is with the bottom wall,
     * it triggers a game over. If a collision happens with the paddle the ball
     * will move upward, and collisions with any other type of collidable object
     * will result in flipping the vertical motion direction.
     */
    public void update() {
        position.add(moveVector);
        // Test for left/right wall collisions
        if(position.x <= 0 || position.x >= GamePanel.PANEL_WIDTH-width) {
            moveVector.x = -moveVector.x;
        }
        // Test for top wall collision
        if(position.y <= 0) {
            moveVector.y = -moveVector.y;
        } else if(position.y >= GamePanel.PANEL_HEIGHT-height) {
            // Bottom wall collision causes game over.
            gamePanel.endGame();
        }
        // Find out if this movement made the ball collide with anything.
        Rectangle hitRect = gamePanel.testCollisionsAgainstBall(this);
        if(hitRect != null) {
            // If it did, check if the element was a paddle, or other.
            if(hitRect instanceof Paddle) {
                // Always move up for a paddle.
                moveVector.y = -Math.abs(moveVector.y);
            } else {
                // Flip the direction of vertical motion for anything else.
                moveVector.y = -moveVector.y;
            }
        }
    }

    /**
     * Resets the ball back to the initial start position.
     * With a new random upward direction.
     */
    public void reset() {
        position = new Position(startPos);
        setRandomMoveVector();
    }

    /**
     * Generate a new random move vector that can have a random positive/negative,
     * and random magnitude from 2 to 6 for both x, and y directions.
     */
    private void setRandomMoveVector() {
        boolean xPositive = rand.nextBoolean();
        int xMagnitude = rand.nextInt(5)+4;
        int yMagnitude = rand.nextInt(5)+4;
        moveVector = new Position(xPositive?xMagnitude:-xMagnitude,-yMagnitude);
    }

    /**
     * Draws the oval to represent the ball.
     *
     * @param g Reference to the  Graphics object for drawing.
     */
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(position.x, position.y, width, height);
    }
}
