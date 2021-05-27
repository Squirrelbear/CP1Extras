import java.awt.*;

/**
 * Car Dodger
 * Author: Peter Mitchell (2021)
 *
 * Car class:
 * General definition of a car to represent the position and drawing of a car.
 */
public abstract class Car extends Rectangle {
    /**
     * Width of each car.
     */
    public static final int CAR_WIDTH = 30;
    /**
     * Height of each car.
     */
    public static final int CAR_HEIGHT = 60;
    /**
     * The initial position used to reset to the start position.
     */
    private Position startPosition;

    /**
     * Car body colour.
     */
    protected Color drawBackgroundColour;
    /**
     * Window/detail top colour.
     */
    protected Color drawForegroundColour;

    /**
     * Initialises the Car ready to draw.
     *
     * @param position Position used to reset the car back as a start position each time.
     */
    public Car(Position position) {
        super(position, CAR_WIDTH, CAR_HEIGHT);
        startPosition = new Position(position);
    }

    /**
     * Resets the car back to its start position.
     */
    public void reset() {
        position = new Position(startPosition);
    }

    /**
     * Draws the car with a body and two windows using the colours stored in the class.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    public void paint(Graphics g) {
        g.setColor(drawBackgroundColour);
        g.fillRect(position.x, position.y, width, height);
        g.setColor(drawForegroundColour);
        g.fillRect(position.x + width/7, position.y + height/5, width*5/7, height/6);
        g.fillRect(position.x + width/7, position.y + height*3/5, width*5/7, height/6);
    }
}
