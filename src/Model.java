import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Model {
    private Map<Car, Coords> carPoints = new HashMap<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private final Workshop<Volvo240> volvoWorkshop = new Workshop<>();
    private List<ModelListener> listeners = new ArrayList<>();

    void addCar(Car car) {
        if (cars.size() < 6) {
            cars.add(car);

            // placerar bilarna på rad vertikalt
            car.setY((cars.size() - 1) * 100); // sätter alltså första bilen på y = 0
            car.setX(300);
            notifyListeners();
        }
    }

    void getAndRemoveLastCar() {
        if (!cars.isEmpty()) {
            carPoints.remove(cars.getLast());
            cars.removeLast();
            notifyListeners();
        }
    }

    void moveCars() {
        for (Car car : cars) {
            car.move();
            handleCarTouchingEdge(car);
            handleCarInWorkshop(car);

            if (carPoints.containsKey(car)) { // om bilens koordinater redan finns i listan, uppdaterar dem
                Coords existingCoords = carPoints.get(car);
                existingCoords.update(car.getX(), car.getY());
            } else { // om bilens koordinater inte finns i listan
                carPoints.put(car, new Coords(car.getX(), car.getY()));
            }
        }
        notifyListeners();
    }

    private void handleCarInWorkshop(Car car) {
        double x = car.getX();
        double y = car.getY();

        if (x > 295 && x < 305 && y > 295 && y < 305) {
            if (car instanceof Volvo240 && !volvoWorkshop.checkIfCarInWorkshop((Volvo240) car)) {
                volvoWorkshop.handInCar((Volvo240) car);
            }
        }
    }

    private void handleCarTouchingEdge(Car car) {
        double x = car.getX();
        double y = car.getY();

        if (x > 800 || x < 0 || y > 500 || y < 0) {
            car.stopEngine();
            switch (car.getDirection()) {
                case NORTH -> { car.setDirection(Directions.SOUTH); car.setY(500); }
                case SOUTH -> { car.setDirection(Directions.NORTH); car.setY(0); }
                case WEST -> { car.setDirection(Directions.EAST); car.setX(800); }
                case EAST -> { car.setDirection(Directions.WEST); car.setX(0); }
            }
            car.startEngine();
        }
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) { car.gas(gas); }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) { car.brake(brake); }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) { ((Saab95) car).setTurboOn(); } // olagligt kanske
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) { ((Saab95) car).setTurboOff(); }
        }
    }

    void liftBed() {
        for (Car car : cars) {
            if (car instanceof Scania) { ((Scania) car).raiseFlatbed(); }
        }
    }

    void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Scania){ ((Scania) car).lowerFlatbed(); }
        }
    }

    void startAllCars() {
        for (Car car : cars) { car.startEngine(); }
        notifyListeners();
    }

    void stopAllCars() {
        for (Car car : cars) { car.stopEngine(); }
        notifyListeners();
    }

    void addListener(ModelListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (ModelListener listener : listeners) {
            listener.onModelChanged(carPoints);
        }
    }
}
