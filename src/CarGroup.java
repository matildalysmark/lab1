import java.util.List;
import java.util.ArrayList;

class CarGroup<T extends Car & Loadable> {
    List<T> cars;

    CarGroup() {
        cars = new ArrayList<>();
    }

    void addCar(T car) {
        cars.add(car);
    }

    void removeCar(T car) {
        cars.remove(car);
    }

    T removeLastCar() {
        T car = cars.get(cars.size() - 1);
        cars.removeLast();
        return car;
    }

    void move(CarTransporter transporter) {
        // Makes sure that all the cars are moving with the car transporter, coordinates
        for (T car : cars) {
            if (car != null) {
                car.setX(transporter.getX());
                car.setY(transporter.getY());
            }
        }
    }

    void turnLeft(CarTransporter transporter) {
        //Makes sure that all loaded cars change direction along with the transporters left turns
        for (T car : cars) {
            if (car != null) { car.setDirection(transporter.getDirection()); }
        }
    }

    void turnRight(CarTransporter transporter) {
        for (T car : cars) {
            if (car != null) { car.setDirection(transporter.getDirection()); }
        }
    }

}
