import java.util.ArrayList;

public class Service<T extends Car> {
    private static final int defaultMaxLoad = 5;
    private int maxLoad;
    private T carType;
    private ArrayList<T> handedInCars;
    private int currentLoad;

    // inga argument specificeras, anropar konstruktor med default argument
    public Service() { this(null, defaultMaxLoad); }

    // endast carType specificeras, anropar konstruktor med default maxvikt
    public Service(T carTypeInput) {
        this(carTypeInput, defaultMaxLoad);
    }

    // endast maxLoad specificeras, anropar konstruktor med default carType (Car)
    public Service(int maxLoadInput) {
        this(null, maxLoadInput);
    }

    // om det specificeras att verstaden endast ska hantera en specifik typ av bil
    public Service(T carTypeInput, int maxLoadInput) {
        this.maxLoad = maxLoadInput;
        this.carType = carTypeInput;
        currentLoad = 0;
    }

    // ser till så att endast bilar som verkstaden hanterar kan lämnas in
    public void handInCar(T car) { // ?? idk
        if (currentLoad < maxLoad) {
            handedInCars.add(car);
            currentLoad++;
        }
    }

    public void takeBackCar(T car) {

        // kollar om bilen finns bland de inlämnade bilarna
        for (T handedInCar : handedInCars) {
            if (handedInCar.equals(car)) {
                handedInCars.remove(car);
                currentLoad--;
            }
        }
    }
}
