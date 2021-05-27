import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Breakout
 * Author: Peter Mitchell (2021)
 *
 * Paddle class:
 * Defines a simple rectangle that can be moved left/right by the player's input.
 */
public class Paddle extends Rectangle {
    /**
     * Status of the keys for left/right to determine if movement should happen during updates.
     */
    private boolean keyLeftIsPressed, keyRightIsPressed;
    /**
     * The magnitude of movement translation for up/down movement.
     */
    private final int moveRate = 10;
    /**
     * Start position to allow resetting.
     */
    private Position startPosition;

    /**
     * Creates the paddle ready for input.
     *
     * @param position Position to start the paddle at.
     * @param width Width of the paddle.
     * @param height Height of the paddle.
     */
    public Paddle(Position position, int width, int height) {
        super(position, width, height);
        startPosition = new Position(position);
        keyLeftIsPressed = false;
        keyRightIsPressed = false;
    }

    /**
     * Moves within the bounds of the gameplay space if either movement key is currently held.
     */
    public void update() {
        if(keyLeftIsPressed) {
            moveWithinBounds(new Position(-moveRate,0), GamePanel.PANEL_WIDTH-width, GamePanel.PANEL_HEIGHT);
        }
        if(keyRightIsPressed) {
            moveWithinBounds(new Position(moveRate,0), GamePanel.PANEL_WIDTH-width, GamePanel.PANEL_HEIGHT);
        }
    }

    /**
     * Reset the state of the Paddle back to default.
     */
    public void reset() {
        keyLeftIsPressed = false;
        keyRightIsPressed = false;
        position = new Position(startPosition);
    }

    /**
     * Takes an update state about an input. If it is either of the keys used for
     * this paddle the state will be updated.
     *
     * @param keyCode The key to test against keyUp and keyDown.
     * @param isKeyPressed State of the key.
     */
    public void processInput(int keyCode, boolean isKeyPressed) {
        if(keyCode == KeyEvent.VK_LEFT) {
            keyLeftIsPressed = isKeyPressed;
        } else if(keyCode == KeyEvent.VK_RIGHT) {
            keyRightIsPressed = isKeyPressed;
        }
    }

    /**
     * Moves based on the translationVector, but clamps the movement within the bounds of the play space.
     *
     * @param translationVector Added to position to calculate the new position.
     */
    private void moveWithinBounds(Position translationVector, int maxX, int maxY) {
        int newX = position.x+translationVector.x;
        int newY = position.y+translationVector.y;
        if(newX < 0) newX = 0;
        else if(newX > maxX) newX = maxX;
        if(newY < 0) newY = 0;
        else if(newY > maxY) newY = maxY;
        position.setPosition(newX, newY);
    }

    /**
     * Draws the rectangle.
     *
     * @param g Reference to the  Graphics object for drawing.
     */
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(position.x, position.y, width, height);
    }
}
