import java.awt.*;

/**
 * Car Dodger
 * Author: Peter Mitchell (2021)
 *
 * AnimatedRoad class:
 * Visually changes an animated road to make it appear like the background is moving.
 */
public class AnimatedRoad {
    private final int ROAD_SEGMENT_LENGTH = 10;
    private final int ROAD_MOVE_SPEED = 3;

    /**
     * Number of lanes to display.
     */
    private int lanes;
    /**
     * Animation offset to offset the visual elements.
     */
    private int offset;
    /**
     * Timer for triggering the changes in the animation offset.
     */
    private ActionTimer offsetTimer;

    /**
     * Prepares the AnimatedRoad to be updated as an animation,
     * and to show the result of the animation.
     *
     * @param lanes Number of lanes to show.
     */
    public AnimatedRoad(int lanes) {
        this.lanes = lanes;
        offset = 0;
        offsetTimer = new ActionTimer(40);
    }

    /**
     * Updates the animation step by incrementing the offset on a timer.
     * The animation resets after it has passed the point where it needs to repeat.
     *
     * @param deltaTime Time since last update.
     */
    public void update(int deltaTime) {
        offsetTimer.update(deltaTime);
        if(offsetTimer.isTriggered()) {
            offset+=ROAD_MOVE_SPEED;
            offset = offset % (ROAD_SEGMENT_LENGTH*2-1);
            offsetTimer.reset();
        }
    }

    /**
     * Draws all the segments of road.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        int laneWidth = GamePanel.PANEL_WIDTH / lanes;
        int segments = GamePanel.PANEL_HEIGHT / ROAD_SEGMENT_LENGTH + 3;
        for(int column = 1; column < lanes; column++) {
            for(int row = -1; row < segments-1; row+=2) {
                g.fillRect(column*laneWidth, row*ROAD_SEGMENT_LENGTH+offset, 3, ROAD_SEGMENT_LENGTH);
            }
        }
    }
}
