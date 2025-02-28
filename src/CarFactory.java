import java.util.Random;

public class CarFactory {
    Model model;
    Random rand;
    private final String[] carNames = {"Volvo240", "Saab95", "Scania"};

    public CarFactory(Model model) {
        this.model = model;
        rand = new Random();
    }

    public Car createCar(String carType) {

        switch (carType) {
            case "Saab95" -> {
                Saab95 saab = new Saab95();
                model.addCar(saab);
                return saab;
            }

            case "Volvo240" -> {
                Volvo240 volvo = new Volvo240();
                model.addCar(volvo);
                return volvo;
            }

            case "Scania" -> {
                Scania scania = new Scania();
                model.addCar(scania);
                return scania;
            }

            // case "Random"
            default -> {
                String car = carNames[rand.nextInt(carNames.length)];
                Car randomCar = createCar(car);
                return randomCar;
            }
        }
    }
}
