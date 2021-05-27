import java.awt.*;

/**
 * Car Dodger
 * Author: Peter Mitchell (2021)
 *
 * EnemyCar class:
 * Extends from the basic car to enable the car moving down while active.
 * Speed and colour of the car are chosen based on a random type of car each time it is reset.
 */
public class EnemyCar extends Car {
    /**
     * Will only move while active.
     */
    private boolean isActive;
    /**
     * Type of car determines speed/colour. Is changed each time the car is reset.
     */
    private int carType;
    /**
     * Speed of the car is based on the carType, and can randomly change for carType 2 every update.
     */
    private int speed;

    /**
     * Initialises the car as a new random enemy car.
     *
     * @param position Start position to reset back to.
     */
    public EnemyCar(Position position) {
        super(position);
        drawForegroundColour = Color.BLACK;
        isActive = false;
        setRandomCarType();
    }

    /**
     * Resets the car back to start position,
     * deactivates the car, and randomises it to a new car type.
     */
    @Override
    public void reset() {
        super.reset();
        isActive = false;
        setRandomCarType();
    }

    /**
     * Does nothing if not active. Updates the speed based on car type,
     * and then moves the car based on the speed.
     *
     * @param deltaTime Time since last update.
     */
    public void update(int deltaTime) {
        if(!isActive) return;

        updateSpeed();
        position.y += speed * deltaTime / 1000.0;
    }

    /**
     * Enables the cars movement.
     */
    public void activate() {
        isActive = true;
    }

    /**
     * Gets the number representing which type of car this is.
     *
     * @return The number representing the type of car.
     */
    public int getCarType() {
        return carType;
    }

    /**
     * Updates the speed based on car type. 0 and 1 use set speeds,
     * but 2 uses a random speed every time the updateSpeed() method is applied.
     */
    private void updateSpeed() {
        switch(carType) {
            case 0:
                speed = 300;
                break;
            case 1:
                speed = 400;
                break;
            case 2:
                speed = (int)(Math.random()*400+100);
                break;
        }
    }

    /**
     * Randomly selects a new car type and sets the colour appropriately to match.
     */
    private void setRandomCarType() {
        carType = (int)(Math.random()*3);
        switch(carType) {
            case 0:
                drawBackgroundColour = Color.BLUE;
                break;
            case 1:
                drawBackgroundColour = Color.GREEN;
                break;
            case 2:
                drawBackgroundColour = Color.RED;
                break;
        }
    }
}
