import java.awt.*;

public abstract class Car implements Movable {

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    Directions direction = Directions.NORTH;
    private double x = 0;
    private double y = 0;

    protected Car() {
        stopEngine();
    }

    @Override
    public void move() {
        switch(direction) {
            case NORTH->
                y += currentSpeed;

            case EAST->
                x += currentSpeed;

            case SOUTH->
                y -= currentSpeed;

            case WEST->
                x -= currentSpeed;
        }
    }

    @Override
    public void turnLeft() {
        switch(direction) {
            case NORTH->
                direction = Directions.WEST;

            case EAST->
                direction = Directions.NORTH;

            case SOUTH->
                direction = Directions.EAST;

            case WEST->
                direction = Directions.SOUTH;
        }
    }

    @Override
    public void turnRight() {
        switch(direction) {
            case NORTH->
                direction = Directions.EAST;

            case EAST->
                direction = Directions.SOUTH;

            case SOUTH->
                direction = Directions.WEST;

            case WEST->
                direction = Directions.NORTH;
        }
    }

    public Directions getDirection() {return direction;}

    public double getX() {return x;}

    public double getY() {return y;}

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        currentSpeed = 0.1;
    }

    // private access as this method is only used in the Car constructor for now
    private void stopEngine() {
        currentSpeed = 0;
    }

    public abstract double speedFactor();

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    public void gas(double amount) {
        if (amount >= 0 && amount <= 1) {incrementSpeed(amount); }
    }

    public void brake(double amount) {
        if (amount >= 0 && amount <= 1) {decrementSpeed(amount); }
    }
}