import java.awt.*;

public class CarTransporter extends FlatbedCar<Ramp> {
    private static final int maxLoad = 10;
    private int currentLoad; // index for the carsLoaded array
    private final Car[] carsLoaded;
    private final Flatbed ramp;

    public CarTransporter() {
        super(new Ramp());
        color = Color.blue;
        enginePower = 125;
        modelName = "Car Transporter";
        currentLoad = 0;
        carsLoaded = new Car[maxLoad];
        ramp = super.getFlatbed();
        weight = 6000;
    }

    public void loadCar(Car inputCar) {
        // Calculates the distance between the car transporter and the car being loaded
        double distance = Math.sqrt(Math.pow((this.getX() - inputCar.getX()), 2) + Math.pow((this.getY() - inputCar.getY()), 2));

        // Checks that the ramp is lowered, car is within reach, car is not on another car transport and that transport has room for it
        if (!ramp.isUp() && distance <= 5 && !(inputCar instanceof CarTransporter) && currentLoad < maxLoad && inputCar.getWeight() < 2500) {
            carsLoaded[currentLoad] = inputCar; // adds the car to the array of loaded cars
            currentLoad++;

            // Changes the cars position och direction to the same as the car transporter
            inputCar.setX(this.getX());
            inputCar.setY(this.getY());
            inputCar.setDirection(this.getDirection());

            // Marking the car as loaded, so that it cannot be moved individually before it has been unloaded
            inputCar.loadCar(this);
        }
    }

    public void unloadCar() {
        // Checking that the ramp is down and that there is at least one car on the flatbed
        if (!ramp.isUp() && currentLoad > 0) {
            currentLoad--;
            Car outputCar = carsLoaded[currentLoad];
            carsLoaded[currentLoad] = null;

            // Marking the car as unloaded, so that it can be moved individually
            outputCar.unloadCar();

            // Placing the unloaded car nearby
            outputCar.setX(this.getX() - 2);
            outputCar.setY(this.getY() - 2);
        }
    }

    @Override
    public void move() {
        if (ramp.isUp()) {
            super.move(); // moves itself

            // Makes sure that all the cars are moving with the car transporter, coordinates
            for (Car car : carsLoaded) {
                if (car != null) {
                    car.setX(this.getX());
                    car.setY(this.getY());
                }
            }
        }
    }

    @Override
    public void turnLeft() {
        if (ramp.isUp()) {
            super.turnLeft(); // Rotates itself

            //Makes sure that all loaded cars change direction along with the transporters left turns
            for (Car car : carsLoaded) {
                if (car != null) { car.setDirection(this.getDirection()); }
            }
        }
    }

    @Override
    public void turnRight() {
        if (ramp.isUp()) {
            super.turnRight(); // Rotated itself

            // Makes sure that all loaded cars change direction along with the car transporter
            for (Car car : carsLoaded) {
                if (car != null) { car.setDirection(this.getDirection()); }
            }
        }
    }
}
