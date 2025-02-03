public class Service {
    private static final int defaultMaxLoad = 5;
    private int maxLoad;
    private Car carType;
    private Car[] handedInCars;
    private int currentLoad;

    // inga argument specificeras, anropar konstruktor med default argument
    public Service() { this(null, defaultMaxLoad); }

    // endast carType specificeras, anropar konstruktor med default maxvikt
    public Service(Car carTypeInput) {
        this(carTypeInput, defaultMaxLoad);
    }

    // endast maxLoad specificeras, anropar konstruktor med default carType (ingen)
    public Service(int maxLoadInput) {
        this(null, maxLoadInput);
    }

    // om det specificeras att verstaden endast ska hantera en specifik typ av bil
    public Service(Car carTypeInput, int maxLoadInput) {
        this.maxLoad = maxLoadInput;
        this.carType = carTypeInput;
        handedInCars = new Car[maxLoad];
        currentLoad = 0;
    }

    // ser till så att endast bilar som verstaden hanterar kan lämnas in
    public void handInCar(carType.getClass() car) { // ?? idk
        if (currentLoad < maxLoad) {
            handedInCars[currentLoad] = car;
            currentLoad++;
        }
    }

    public void takeBackCar() {

    }
}
