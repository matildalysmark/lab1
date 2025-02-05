import java.awt.*;

public class CarTransporter extends FlatbedCar<Ramp> {
    private static final int maxLoad = 10;
    private int currentLoad; // index i listan
    private Car[] carsLoaded;
    private Flatbed ramp;

    public CarTransporter() {
        super(new Ramp()); // ??
        color = Color.blue;
        enginePower = 125;
        modelName = "Car Transporter";
        currentLoad = 0;
        carsLoaded = new Car[maxLoad];
        ramp = super.getFlatbed(); // ??
    }

    public boolean rampIsUp() {
        return ramp.isUp();
    }

    public void loadCar(Car inputCar) {
        // räknar ut avstånd mellan biltransporten och bilen som ska lastas
        double distance = Math.sqrt(Math.pow((this.getX() - inputCar.getX()), 2) + Math.pow((this.getY() - inputCar.getY()), 2));

        // kollar att ramp är nere, bilen är inom rimligt avstånd, bilen ej är en annan biltransport och transport ej är full
        if (!ramp.isUp() && distance <= 5 && !(inputCar instanceof CarTransporter) && currentLoad < maxLoad) {
            carsLoaded[currentLoad] = inputCar; // lägger in bilen i arrayen med lastade bilar (på rätt position)
            currentLoad++; // ökar antalet bilar på flaket

            // ändra bilens position och riktning till samma som carTransport
            inputCar.setX(this.getX());
            inputCar.setY(this.getY());
            inputCar.setDirection(this.getDirection());

            inputCar.loadCar(); // markerar bilen som loaded, så att den inte kan röra sig själv innan den lastats av
        }
    }

    public void unloadCar() {
        // kollar att flaket är nere och att det finns minst en bil på flaket
        if (!ramp.isUp() && currentLoad > 0) {
            currentLoad--; // minskar antalet bilar på flaket
            Car outputCar = carsLoaded[currentLoad];
            carsLoaded[currentLoad] = null;

            outputCar.unloadCar(); // markerar bilen som unloaded, så att den kan börja röra sig själv

            // placerar den avlastade bilen i närheten
            outputCar.setX(this.getX() - 2);
            outputCar.setY(this.getY() - 2);
        }
    }

    @Override
    public void move() {
        if (ramp.isUp()) {
            super.move(); // förflyttar sig själv

            // ser till att alla lastade bilar förflyttas med biltransporten (koordinater)
            for (Car car : carsLoaded) {
                if (car != null) {
                    switch (car.getDirection()) {
                        case NORTH -> car.setY(car.getY() + car.getCurrentSpeed());
                        case EAST -> car.setX(car.getX() + car.getCurrentSpeed());
                        case SOUTH -> car.setY(car.getY() - car.getCurrentSpeed());
                        case WEST -> car.setX(car.getX() - car.getCurrentSpeed());
                    }
                }
            }
        }
    }

    @Override
    public void turnLeft() {
        if (ramp.isUp()) {
            super.move(); // roterar sig själv

            // ser till att alla lastade bilar ändrar riktning med biltransportens vänstersvängar
            for (Car car : carsLoaded) {
                if (car != null) {
                    switch (car.getDirection()) {
                        case NORTH -> car.setDirection(Directions.WEST);
                        case EAST -> car.setDirection(Directions.NORTH);
                        case SOUTH -> car.setDirection(Directions.EAST);
                        case WEST -> car.setDirection(Directions.SOUTH);
                    }
                }
            }
        }
    }

    @Override
    public void turnRight() {
        if (ramp.isUp()) {
            super.move(); // roterar sig själv

            // ser till att alla lastade bilar ändrar riktning med biltransportens högersvängar
            for (Car car : carsLoaded) {
                if (car != null) {
                    switch (car.getDirection()) {
                        case NORTH -> car.setDirection(Directions.EAST);
                        case EAST -> car.setDirection(Directions.SOUTH);
                        case SOUTH -> car.setDirection(Directions.WEST);
                        case WEST -> car.setDirection(Directions.NORTH);
                    }
                }
            }
        }
    }
}
