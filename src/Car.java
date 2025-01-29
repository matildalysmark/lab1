import java.awt.*;

public abstract class Car implements Movable {

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    final private String[] directions = {"north", "east", "south", "west"};
    private int direction_index = 0;
    private double x = 0;
    private double y = 0;

    protected Car() {
        stopEngine();
    }

    @Override
    public void move() {
        if (directions[direction_index].equals("north")) { y += currentSpeed; }
        else if (directions[direction_index].equals("east")) { x += currentSpeed; }
        else if (directions[direction_index].equals("south")) { y -= currentSpeed; }
        else if (directions[direction_index].equals("west")) { x -= currentSpeed; }
    }

    @Override
    public void turnLeft() {
        if (direction_index == 0)
            direction_index = 3;
        else
            direction_index -= 1;
    }

    @Override
    public void turnRight() {
        if (direction_index == 3)
            direction_index = 0;
        else
            direction_index += 1;
    }

    public void setDirection(int direction) {direction_index = direction % 4;}

    public String getDirection() {return directions[direction_index];}

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

    public void stopEngine() {
        currentSpeed = 0;
    }

    public abstract double speedFactor();

    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    public void gas(double amount) {
        if (amount >= 0 && amount <= 1) {incrementSpeed(amount); }
    }

    public void brake(double amount) {
        if (amount >= 0 && amount <= 1) {decrementSpeed(amount); }
    }
}