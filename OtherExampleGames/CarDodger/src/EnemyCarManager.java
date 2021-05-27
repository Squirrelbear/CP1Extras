import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Car Dodger
 * Author: Peter Mitchell (2021)
 *
 * EnemyCarManager class:
 * Manages a collection of cars that have one car per lane making them update movement,
 * and managing collision/reset states.
 */
public class EnemyCarManager {
    /**
     * Reference to the GamePanel to pass collision and score updates.
     */
    private GamePanel gamePanel;
    /**
     * Collection of all the cars with one for each lane.
     */
    private EnemyCar[] cars;
    /**
     * List of the inactive cars for easy randomisation of which should active next.
     */
    private List<EnemyCar> inactiveCars;
    /**
     * The maximum number of lanes that should have a car moving down in them at a time.
     */
    private int maxActiveLanes;
    /**
     * Reference to the player car to check for collisions.
     */
    private PlayerCar playerCar;

    /**
     * Creates all the cars to be managed and initialises the correct number of active cars.
     *
     * @param lanes The total number of lanes.
     * @param maxActiveLanes The maximum number of lanes that should have a car moving down in them at a time.
     * @param playerCar Reference to the player car to check for collisions.
     * @param gamePanel Reference to the GamePanel to pass collision and score updates.
     */
    public EnemyCarManager(int lanes, int maxActiveLanes, PlayerCar playerCar, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.maxActiveLanes = maxActiveLanes;
        this.playerCar = playerCar;
        cars = new EnemyCar[lanes];
        int laneWidth = GamePanel.PANEL_WIDTH / lanes;
        inactiveCars = new ArrayList<>();
        // Create all the cars and then activate as many as are needed randomly
        for(int i = 0; i < lanes; i++) {
            cars[i] = new EnemyCar(new Position(laneWidth * i + laneWidth / 2 - Car.CAR_WIDTH/2, -Car.CAR_HEIGHT));
            inactiveCars.add(cars[i]);
        }
        for(int i = 0; i < maxActiveLanes; i++) {
            activateRandomCar();
        }
    }

    /**
     * Updates all the cars, while also checking if any of the cars
     * are colliding with the player to trigger a crash, and if they
     * have left the screen to provide score.
     *
     * @param deltaTime Time since last update.
     */
    public void update(int deltaTime) {
        for(EnemyCar car : cars) {
            car.update(deltaTime);
            if(car.isIntersecting(playerCar)) {
                gamePanel.increaseCrashCount();
            } else if(car.position.y > GamePanel.PANEL_HEIGHT) {
                // Car has left the screen
                gamePanel.increaseScore(car.getCarType()+1);
                car.reset();
                inactiveCars.add(car);
                activateRandomCar();
            }
        }
    }

    /**
     * Resets all cars, and then activates up to the max number of lanes again.
     */
    public void reset() {
        inactiveCars.clear();
        for(EnemyCar enemyCar : cars) {
            enemyCar.reset();
            inactiveCars.add(enemyCar);
        }
        for(int i = 0; i < maxActiveLanes; i++) {
            activateRandomCar();
        }
    }

    /**
     * Draws all the cars.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    public void paint(Graphics g) {
        for(Car car : cars) {
            car.paint(g);
        }
    }

    /**
     * Activates a random car that is not currently active.
     */
    private void activateRandomCar() {
        Collections.shuffle(inactiveCars);
        inactiveCars.get(0).activate();
        inactiveCars.remove(0);
    }
}
