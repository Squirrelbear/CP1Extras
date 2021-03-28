import java.util.Random;

public class Map {
    private int playerX, playerY;
    private char[][] map;
    private int enemyCount;

    public Map(Random rand) {
        fillNewMap(12, 12);
        setPlayerPosition(6,6);
        enemyCount = 0;
        for(int i = 0; i < 5; i++) {
            spawnRandomEnemy(rand);
        }
    }

    public void printMap() {
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[0].length; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    public int movePlayer(String input) {
        int[] translation = getTranslationFromInput(input);
        if(translation[0] == 0 && translation[1] == 0) {
            System.out.println("Invalid input. Enter up, down, left, or right.");
            return -1;
        }

        int newX = playerX + translation[1];
        int newY = playerY + translation[0];
        if(!canMoveTo(newX, newY)) {
            System.out.println("You can't move there. Try move somewhere else.");
            return -1;
        }

        int resultCode = getTargetCellEventNumber(newX, newY);
        swapPlayerToPosition(newX, newY);
        return resultCode;
    }

    public void setPlayerPosition(int x, int y) {
        playerX = x;
        playerY = y;
        map[y][x] = '@';
    }

    public boolean allEnemiesDefeated() {
        return enemyCount == 0;
    }

    private void fillNewMap(int height, int width) {
        map = new char[height][width];
        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[0].length; x++) {
                map[y][x] = '.';
            }
        }
    }

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

    private boolean canMoveTo(int x, int y) {
        if(x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
            return false;
        }
        if(map[y][x] == '*') { return false; }
        return true;
    }

    private int getTargetCellEventNumber(int x, int y) {
        if(map[y][x] == '#') return 1;
        return 0;
    }

    private void swapPlayerToPosition(int x, int y) {
        if(map[y][x] == '#') enemyCount--;
        map[playerY][playerX] = '.';
        setPlayerPosition(x, y);
    }

    private void spawnRandomEnemy(Random rand) {
        int x = rand.nextInt(map[0].length);
        int y = rand.nextInt(map.length);
        if(map[y][x] == '.') {
            map[y][x] = '#';
            enemyCount++;
        }
    }
}
