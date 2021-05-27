import java.awt.*;

/**
 * Breakout
 * Author: Peter Mitchell (2021)
 *
 * Brick class:
 * Defines a single brick that can be collided with showing
 * different colours and having different effects based on their type.
 */
public class Brick extends Rectangle {
    /**
     * The number of hits it takes to destroy this brick.
     */
    private int remainingHits;
    /**
     * The colour to draw this brick with.
     */
    private Color drawColour;
    /**
     * The type of the brick.
     */
    private int brickType;

    /**
     * Initialises a brick with the specified properties.
     *
     * @param position Position of the Brick to be drawn.
     * @param width Width of the brick.
     * @param height Height of the brick.
     * @param brickType Type of the brick. 4 or higher will randomise the brick type.
     */
    public Brick(Position position, int width, int height, int brickType) {
        super(position, width, height);
        if(brickType >= 4) brickType = (int)(Math.random()*3);
        setBrickType(brickType);
    }

    /**
     * Reduces the number of remaining hits to destroy the brick.
     * If the number is 999 or higher it is considered indestructible.
     * Changes the colour to show how many hits are still remaining.
     */
    public void hitBrick() {
        if(remainingHits < 999) {
            remainingHits--;
            switch(remainingHits) {
                case 2:
                    drawColour = Color.ORANGE;
                    break;
                case 1:
                case 0:
                    drawColour = Color.RED;
                    break;
            }
        }
    }

    /**
     * Tests if the brick should be destroyed.
     *
     * @return True if the remaining hits indicates the brick should be destroyed.
     */
    public boolean isExpired() {
        return remainingHits <= 0;
    }

    /**
     * True if the remaining hits is 999 or higher.
     *
     * @return True if the brick can not be destroyed.
     */
    public boolean isImmortal() {
        return remainingHits >= 999;
    }

    /**
     * Gets the type of brick.
     *
     * @return Type of the brick.
     */
    public int getBrickType() {
        return brickType;
    }

    /**
     * Draws the rectangle.
     *
     * @param g Reference to the  Graphics object for drawing.
     */
    public void paint(Graphics g) {
        g.setColor(drawColour);
        g.fillRect(position.x, position.y, width, height);
        g.setColor(Color.WHITE);
        g.drawRect(position.x, position.y, width, height);
    }

    /**
     * Sets the number of hits a brick can take, and a
     * colour to show what the brick type is.
     *
     * @param type Type of brick to set.
     */
    public void setBrickType(int type) {
        this.brickType = type;
        switch(type) {
            case -1:
                remainingHits = 999;
                drawColour = Color.DARK_GRAY;
            case 1:
                remainingHits = 3;
                drawColour = Color.BLUE;
                break;
            case 2:
                remainingHits = 2;
                drawColour = Color.ORANGE;
                break;
            case 0:
            default:
                remainingHits = 1;
                drawColour = Color.RED;
                break;
        }
    }
}
