import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Breakout
 * Author: Peter Mitchell (2021)
 *
 * BrickManager class:
 * Manages all the Brick objects in the game. Providing access to all
 * the elements for any necessary updating or collision detection.
 */
public class BrickManager {
    /**
     * The list of all bricks that are still available.
     */
    private List<Brick> bricks;

    /**
     * Initialises the BrickManager ready to store bricks.
     */
    public BrickManager() {
        bricks = new ArrayList<>();
    }

    /**
     * Creates a random brick layout that has 10 rows and maximises the
     * number of bricks horizontally with uniform size.
     */
    public void createDefaultBrickLayout() {
        clearBricks();
        populateBricks(50,50, 40, 20,
                (GamePanel.PANEL_WIDTH-100)/40, 10, 4);
    }

    /**
     * Removes all the bricks.
     */
    public void clearBricks() {
        bricks.clear();
    }

    /**
     * Creates a grid of bricks at the designated position with specified properties for all of them.
     *
     * @param x X coordinate of the left of the grid to place.
     * @param y Y coordinate of the top of the grid to place.
     * @param brickWidth Width of each individual brick.
     * @param brickHeight Height of each individual brick.
     * @param gridWidth Width of the grid. (number of columns)
     * @param gridHeight Height of the grid. (number of rows)
     * @param brickType Type of block to spawn in this grid.
     */
    public void populateBricks(int x, int y, int brickWidth, int brickHeight, int gridWidth, int gridHeight, int brickType) {
        for(int gridX = 0; gridX < gridWidth; gridX++) {
            for(int gridY = 0; gridY < gridHeight; gridY++) {
                bricks.add(new Brick(new Position(x+brickWidth*gridX, y+brickHeight*gridY),
                                        brickWidth, brickHeight, brickType));
            }
        }
    }

    /**
     * Updates all the bricks to check if they should be removed and does so if necessary.
     */
    public void update() {
        for(int i = 0; i < bricks.size(); i++) {
            if(bricks.get(i).isExpired()) {
                bricks.remove(i);
                i--;
            }
        }
    }

    /**
     * Test for collisions against all the bricks for the specified ball.
     *
     * @param objectToTest The ball to check for collisions against.
     * @return A list of all objects the ball has collided with.
     */
    public List<Brick> testCollisionsOnBricks(Ball objectToTest) {
        List<Brick> result = new ArrayList<>();
        for(Brick brick : bricks) {
            if(brick.isIntersecting(objectToTest)) {
                result.add(brick);
            }
        }
        return result;
    }

    /**
     * Draws all the bricks to the screen.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    public void paint(Graphics g) {
        for(Brick brick : bricks) {
            brick.paint(g);
        }
    }

    /**
     * Checks all blocks to find if all the blocks that can be destroyed are destroyed.
     *
     * @return True if all blocks that can be destroyed, are destroyed.
     */
    public boolean allBlocksDestroyed() {
        for(Brick brick : bricks) {
            if(!brick.isImmortal()) {
                return false;
            }
        }
        return true;
    }
}
