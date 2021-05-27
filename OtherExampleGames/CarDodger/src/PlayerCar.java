import java.awt.*;

/**
 * Car Dodger
 * Author: Peter Mitchell (2021)
 *
 * PlayerCar class:
 * Represents the player's car as a police car that can move left/right using the mouse position.
 */
public class PlayerCar extends Car {
    /**
     * While true the car can be moved.
     */
    private boolean isMoving;
    /**
     * Animation timer to delay changes in animation state.
     */
    private ActionTimer animationTimer;
    /**
     * Current state of the animation.
     */
    private boolean flashBlue;

    /**
     * Configures the player car ready for animation and movement.
     *
     * @param position Start position to return to when reset.
     */
    public PlayerCar(Position position) {
        super(position);
        drawBackgroundColour = Color.BLACK;
        drawForegroundColour = Color.WHITE;
        flashBlue = true;
        animationTimer = new ActionTimer(80);
    }

    /**
     * Updates the animation timer, and if it triggered,
     * the colours will be toggled.
     *
     * @param deltaTime Time since last update.
     */
    public void update(int deltaTime) {
        animationTimer.update(deltaTime);
        if(animationTimer.isTriggered()) {
            flashBlue = !flashBlue;
            animationTimer.reset();
        }
    }

    /**
     * Takes the current mouse position and places the car centred on the same X value as the mouse,
     * with constraints for the panel size. It will also check if the mouse has moved too far up or
     * down from the car and then stop further movement until it is reselected.
     *
     * @param mousePosition Current mouse position.
     */
    public void updatePosition(Position mousePosition) {
        position.x = Math.max(Math.min(mousePosition.x-width/2, GamePanel.PANEL_WIDTH-width),0);
        if(Math.abs(mousePosition.y - position.y) > 100) {
            setIsMoving(false);
        }
    }

    /**
     * Resets the position of the player car and sets it to stop moving.
     */
    public void reset() {
        super.reset();
        isMoving = false;
    }

    /**
     * Changes the current state of whether the car can be moved.
     *
     * @param isMoving New value to set isMoving to.
     */
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     * Checks if the car can currently be moved.
     *
     * @return True if the car can be moved.
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Draws the base car, and then flashing coloured lights to imitate police lights.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(flashBlue ? Color.BLUE : Color.RED);
        g.fillRect(position.x + width/2-(width*4/7)/2-1, position.y + height/2 - height/7, (width*4/7)/2, height/7);
        g.setColor(!flashBlue ? Color.BLUE : Color.RED);
        g.fillRect(position.x + width/2-1, position.y + height/2 - height/7, (width*4/7)/2, height/7);
    }
}
