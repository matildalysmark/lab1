import java.awt.*;

public abstract class Car implements Movable {
    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected String modelName;
    protected boolean isOnCarTransport;
    protected int weight;

    Directions direction = Directions.NORTH;
    private double x = 0;
    private double y = 0;

    protected Car() {
        stopEngine();
        isOnCarTransport = false;
    }

    @Override
    public void move() {
        if (!isOnCarTransport) {
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
    }

    @Override
    public void turnLeft() {
        if (!isOnCarTransport) {
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
    }

    @Override
    public void turnRight() {
        if (!isOnCarTransport) {
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
    }

    protected int getWeight() { return this.weight; }

    protected void loadCar(Car carTransport) { isOnCarTransport = true; }

    protected void unloadCar() { isOnCarTransport = false; }

    public boolean loadStatus() { return isOnCarTransport; }

    public Directions getDirection() { return direction; }

    protected void setDirection(Directions direction) { this.direction = direction; }

    public double getX() { return x; }

    public double getY() { return y; }

    protected void setX(double nX) { x = nX; }

    protected void setY(double nY) {
        y = nY;
    }

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

    protected abstract double speedFactor();

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    public void gas(double amount) {
        if (!isOnCarTransport && amount >= 0 && amount <= 1)
            incrementSpeed(amount);
    }

    public void brake( double amount ) {
        if (!isOnCarTransport && amount >= 0 && amount <= 1)
            decrementSpeed(amount);
    }
}