import java.util.Random;

/**
 * RPGTextGame version 1.0
 * Author: Peter Mitchell (2021)
 *
 * Class Map:
 * Defines a simple ASCII map that can be traversed by the player using text commands.
 */
public class Map {
    /**
     * X coordinate on the map where the player's '@' character is located.
     */
    private int playerX;
    /**
     * Y coordinate on the map where the player's '@' character is located.
     */
    private int playerY;
    /**
     * 2d array of characters representing the map.
     */
    private char[][] map;
    /**
     * The number of enemies remaining on the map.
     */
    private int enemyCount;

    /**
     * Create a random map filled with empty cells.
     * With one player in the middle, and up to 5 randomly placed enemy encounters.
     *
     * @param rand Reference to shared Random.
     */
    public Map(Random rand) {
        fillNewMap(12, 12);
        setPlayerPosition(6,6);
        enemyCount = 0;
        for(int i = 0; i < 5; i++) {
            spawnRandomEnemy(rand);
        }
    }

    /**
     * Prints out the map as a grid of characters.
     */
    public void printMap() {
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[0].length; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Moves the player based on an input.
     * If not a valid input will show an error and skip move.
     * If move is not valid based on canMoveTo() will show an error and skip move.
     * Otherwise will move the player's @ character and return a code based on the content of the target cell.
     *
     * @param input Any valid String. Will only be valid input if text is "up", "down", "left" or "right".
     * @return -1 if failed move, 0 if valid move with no encounter, 1 or higher if special character on map.
     */
    public int movePlayer(String input) {
        // Get the { y, x } transation based on an input direction
        int[] translation = getTranslationFromInput(input);
        if(translation[0] == 0 && translation[1] == 0) {
            System.out.println("Invalid input. Enter up, down, left, or right.");
            return -1;
        }

        // Get the target cell to move to
        int newX = playerX + translation[1];
        int newY = playerY + translation[0];
        if(!canMoveTo(newX, newY)) {
            System.out.println("You can't move there. Try move somewhere else.");
            return -1;
        }

        // Get any encounter number before overwriting it with the player
        int resultCode = getTargetCellEventNumber(newX, newY);
        swapPlayerToPosition(newX, newY);
        return resultCode;
    }

    /**
     * Sets the playerX and playerY to x and y.
     * Then sets the associated cell on the map to an '@' character.
     *
     * @param x X coordinate to set playerX to.
     * @param y Y coordinate to set playerY to.
     */
    public void setPlayerPosition(int x, int y) {
        playerX = x;
        playerY = y;
        map[y][x] = '@';
    }

    /**
     * Test if there are any enemies remaining on the map.
     *
     * @return True if there is at least one enemy encounter still on the map.
     */
    public boolean allEnemiesDefeated() {
        return enemyCount == 0;
    }

    /**
     * Creates a new map with height and width, then fills with '.' characters
     * to represent empty cells.
     *
     * @param height Height of the new map.
     * @param width Width of the new map.
     */
    private void fillNewMap(int height, int width) {
        map = new char[height][width];
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[0].length; x++) {
                map[y][x] = '.';
            }
        }
    }

    /**
     * Tests for valid input (up, down, left, right).
     * Then returns either the { y, x } translation to move in that direction,
     * or { 0, 0 } to represent not a valid move.
     *
     * @param input Input expects (up, down, left, or right) anything else will give an invalid move.
     * @return {y,x} translation based on direction of input, or {0,0} for invalid input.
     */
    private int[] getTranslationFromInput(String input) {
        input = input.toLowerCase().trim();
        if(input.equals("up")) {
            return new int[] { -1, 0 };
        } else if(input.equals("down")) {
            return new int[] { 1, 0 };
        } else if(input.equals("left")) {
            return new int[] { 0, -1 };
        } else if(input.equals("right")) {
            return new int[] { 0, 1 };
        } else {
            return new int[] { 0, 0 };
        }
    }

    /**
     * Tests if the target cell is valid to move to.
     * Not valid if the cell is outside the map.
     * Not valid if the target cell contains an obstacle ('*').
     *
     * @param x X coordinate of cell to test.
     * @param y Y coordinate of cell to test.
     * @return True if target cell is inside the map, and target cell is not an obstacle.
     */
    private boolean canMoveTo(int x, int y) {
        if(x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
            return false;
        }
        if(map[y][x] == '*') { return false; }
        return true;
    }

    /**
     * Gets the encounter number based on the contents of the cell.
     * # is a general encounter, and anything else is no encounter.
     *
     * @param x X coordinate to check.
     * @param y Y coordinate to check.
     * @return 0 if no event, 1 if there is an encounter.
     */
    private int getTargetCellEventNumber(int x, int y) {
        if(map[y][x] == '#') return 1;
        return 0;
    }

    /**
     * Swaps the player position from playerX,playerY to x,y.
     * Decreases the number of enemies remaining if the cell had a # in it.
     * Fills the old cell with an empty cell '.'.
     *
     * @param x X coordinate to move player to.
     * @param y Y coordinate to move player to.
     */
    private void swapPlayerToPosition(int x, int y) {
        if(map[y][x] == '#') enemyCount--;
        map[playerY][playerX] = '.';
        setPlayerPosition(x, y);
    }

    /**
     * Spawns a random encounter (#) at a random location on the map.
     * Will only place the encounter if the target cell is empty.
     *
     * @param rand Reference to shared Random.
     */
    private void spawnRandomEnemy(Random rand) {
        int x = rand.nextInt(map[0].length);
        int y = rand.nextInt(map.length);
        if(map[y][x] == '.') {
            map[y][x] = '#';
            enemyCount++;
        }
    }
}
