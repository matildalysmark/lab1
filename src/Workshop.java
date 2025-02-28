import java.util.ArrayList;

public class Workshop<T extends Car> {
    private static final int defaultMaxLoad = 2;
    private final int maxLoad;
    private final ArrayList<T> handedInCars;
    private int currentLoad;

    // No maxLoad specified, receives the default value
    public Workshop() {
        this(defaultMaxLoad);
    }

    // MaxLoad specified
    public Workshop(int maxLoadInput) {
        this.maxLoad = maxLoadInput;
        this.handedInCars = new ArrayList<>();
        currentLoad = 0;
    }

    // Makes sure that the workshop only takes in cars it can handle
    public void handInCar(T car) {
        if (currentLoad < maxLoad) {
            handedInCars.add(car);
            currentLoad++;
            car.setHandedInStatus(true);
        }
    }

    public void takeBackCar(T car) {
        // Removes the car if it is among the turned in cars
        if (handedInCars.remove(car)) {
            currentLoad--;
            car.setHandedInStatus(false);
        }
    }

    public boolean checkIfCarInWorkshop(T handedInCar) {
        // Returns true if the car is amongst the turned in cars, else false
        return handedInCars.contains(handedInCar);
    }

    public int getCurrentLoad() { return currentLoad; }
}
