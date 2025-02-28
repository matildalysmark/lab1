import java.awt.*;

public class CarTransporter<T extends Car & Loadable> extends FlatbedCar<Ramp> {
    private static final int maxLoad = 10;
    private int currentLoad; // index for the carsLoaded array
    private final Flatbed ramp;
    private CarGroup<T> carGroup;

    public CarTransporter() {
        super(new Ramp());
        color = Color.blue;
        enginePower = 125;
        modelName = "Car Transporter";
        currentLoad = 0;
        ramp = super.getFlatbed();
        weight = 6000;
        carGroup = new CarGroup();
    }

    public void loadCar(T inputCar) {
        // Calculates the distance between the car transporter and the car being loaded
        double distance = Math.sqrt(Math.pow((this.getX() - inputCar.getX()), 2) + Math.pow((this.getY() - inputCar.getY()), 2));

        // Checks that the ramp is lowered, car is within reach, car is not on another car transport and that transport has room for it
        if (!ramp.isUp() && distance <= 5 && !(inputCar instanceof CarTransporter) && currentLoad < maxLoad && inputCar.getWeight() < 2500) {
            carGroup.addCar(inputCar);
            currentLoad++;

            // Changes the cars position och direction to the same as the car transporter
            inputCar.setX(this.getX());
            inputCar.setY(this.getY());
            inputCar.setDirection(this.getDirection());

            // Marking the car as loaded, so that it cannot be moved individually before it has been unloaded
            inputCar.loadCarOnTransporter();
        }
    }

    public void unloadCar() {
        // Checking that the ramp is down and that there is at least one car on the flatbed
        if (!ramp.isUp() && currentLoad > 0) {
            currentLoad--;
            T outputCar = carGroup.removeLastCar();

            // Marking the car as unloaded, so that it can be moved individually
            outputCar.unloadCarFromTransporter();

            // Placing the unloaded car nearby
            outputCar.setX(this.getX() - 2);
            outputCar.setY(this.getY() - 2);
        }
    }

    @Override
    public void move() {
        if (ramp.isUp()) {
            super.move(); // moves itself
            carGroup.move(this);
        }
    }

    @Override
    public void turnLeft() {
        if (ramp.isUp()) {
            super.turnLeft(); // Rotates itself

            carGroup.turnLeft(this);
        }
    }

    @Override
    public void turnRight() {
        if (ramp.isUp()) {
            super.turnRight(); // Rotated itself

            carGroup.turnRight(this);

            // Makes sure that all loaded cars change direction along with the car transporter

        }
    }
}