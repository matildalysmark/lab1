import java.util.List;
import java.util.ArrayList;

class CarGroup {
    List<Car> cars;

    CarGroup() {
        cars = new ArrayList<>();
    }

    void addCar(Car car) {
        cars.add(car);
    }

    void removeCar(Car car) {
        cars.remove(car);
    }



}
