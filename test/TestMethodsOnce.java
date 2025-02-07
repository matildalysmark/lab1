import java.awt.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMethodsOnce {
    private Saab95 saab;
    private Volvo240 volvo;
    private Scania scania;
    private CarTransporter carTransporter;
    private CarTransporter carTransporter2;

    @Before
    public void setUp() {
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();
        carTransporter = new CarTransporter();
        carTransporter2 = new CarTransporter();
    }

    @Test
    public void testSaabDoors() {
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    public void testVolvoDoors() {
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    public void testEnginePowerSaab() { assertEquals(125, saab.getEnginePower(), 0); }

    @Test
    public void testEnginePowerVolvo() {
        assertEquals(100, volvo.getEnginePower(), 0);
    }

    @Test //Set color of Saab and check that the same color is returned with getter
    public void testColorSaab() {
        saab.setColor(Color.pink);
        assertEquals(Color.pink, saab.getColor());
    }

    @Test //Set color of Volvo and check that the same color is returned with getter
    public void testColorVolvo() {
        volvo.setColor(Color.cyan);
        assertEquals(Color.cyan, volvo.getColor());
    }

    @Test
    public void testRightMovementCar() {
        double expected_x = 0;
        double expected_y = 0;

        Directions expectedDirection = Directions.NORTH;

        for (int i = 0; i < 4; i++) {
            saab.turnRight();
            saab.move();

            //Expected results to compare with
            switch(expectedDirection) {
                case NORTH -> {
                    expectedDirection = Directions.EAST;
                    expected_y += saab.getCurrentSpeed();
                }

                case EAST-> {
                        expectedDirection = Directions.SOUTH;
                        expected_x += saab.getCurrentSpeed();
                }

                case SOUTH -> {
                    expectedDirection = Directions.WEST;
                    expected_y -= saab.getCurrentSpeed();
                }

                case WEST -> {
                    expectedDirection = Directions.NORTH;
                    expected_x -= saab.getCurrentSpeed();
                }
            }
        }
        assertTrue(expectedDirection == saab.getDirection()
                        && expected_x == saab.getX() && expected_y == saab.getY());
    }

    @Test
    public void testLeftMovementCar() {
        double expected_x = 0;
        double expected_y = 0;

        Directions expectedDirection = Directions.NORTH;

        for (int i = 0; i < 4; i++) {
            saab.turnLeft();
            saab.move();

            switch (expectedDirection) {
                case NORTH -> {
                    expectedDirection = Directions.WEST;
                    expected_y += saab.getCurrentSpeed();
                }

                case EAST -> {
                    expectedDirection = Directions.NORTH;
                    expected_x += saab.getCurrentSpeed();
                }

                case SOUTH -> {
                    expectedDirection = Directions.EAST;
                    expected_y -= saab.getCurrentSpeed();
                }

                case WEST -> {
                    expectedDirection = Directions.SOUTH;
                    expected_x -= saab.getCurrentSpeed();
                }
            }
        }

        assertTrue(expectedDirection == saab.getDirection()
                && expected_x == saab.getX() && expected_y == saab.getY());
    }

    @Test
    public void testTurboOn(){
        saab.setTurboOn();
        assertTrue(saab.getTurboState());
    }

    @Test
    public void testTurboOff(){
        saab.setTurboOff();
        assertFalse(saab.getTurboState());
    }

    @Test // tests ramp of car transport
    public void testCarTransporterRamp() {
        carTransporter.raiseFlatbed();
        assertTrue(carTransporter.flatbedIsUp());

        // lowers ramp and checks that it is lowered
        carTransporter.lowerFlatbed();
        assertFalse(carTransporter.flatbedIsUp());
    }

    @Test // Testing that CarTransport cannot move when ramp is lowered
    public void testCarTransporterRampMoving() {
        double previousX = carTransporter.getX();
        double previousY = carTransporter.getY();
        
        carTransporter.lowerFlatbed();
        carTransporter.startEngine();
        carTransporter.gas(10);
        carTransporter.move();
        
        assertTrue(previousX == carTransporter.getX() && previousY == carTransporter.getY());
    }

    @Test // Testing that CarTransport cannot lower ramp when CarTransport is moving
    public void testCarTransporterMovingRamp() {
        carTransporter.raiseFlatbed();
        
        carTransporter.startEngine();
        carTransporter.gas(10);
        
        carTransporter.lowerFlatbed();
        assertTrue(carTransporter.flatbedIsUp());
    }

    @Test
    public void testCarTransporterMovingLoadedCars() {
        carTransporter.lowerFlatbed();
        Car[] cars = {saab, volvo, carTransporter};
        scania.raiseFlatbed();

        for (Car car : cars) {
            car.setX(0);
            car.setY(0);
            carTransporter.loadCar(car);
        }
        carTransporter.raiseFlatbed();

        carTransporter.startEngine();
        carTransporter.gas(10);

        for (int x = 0; x < 6; x++) {
            carTransporter.turnRight();
            carTransporter.move();
        }
        carTransporter.turnLeft();

        for (Car car : cars) {
            assertEquals(carTransporter.getDirection(), car.getDirection());
            assertTrue(carTransporter.getX() == car.getX() && carTransporter.getY() == car.getY());
        }

        for (int x = 0; x < 2; x++)
            carTransporter.unloadCar();
    }
    
    @Test // checks that Scania cannot move if flatbed is not up
    public void testScaniaMovementWhenFlatbedDown(){
        double previous_x = scania.getX();
        double previous_y = scania.getY();
        scania.setAngle(35);
        scania.startEngine();
        scania.gas(10);
        scania.move();
        assertEquals(previous_x, scania.getX(), 0);
        assertEquals(previous_y, scania.getY(), 0);
    }

    @Test // Testing that cars cannot be loaded on a Car Transport if they are too far
    public void testLoadCarFromDistance() {
        carTransporter.setX(0);
        carTransporter.setY(0);

        volvo.setX(10);
        volvo.setY(30);

        carTransporter.loadCar(volvo);
        assertFalse(volvo.loadStatus());
    }

    @Test // same test for loading and unloading to have a car to unload
    public void testLoadStatusCar(){
        // checks that load status of car is changed when loaded
        carTransporter.lowerFlatbed();
        carTransporter.loadCar(saab);
        assertTrue(saab.loadStatus());

        // checks that load status of car is changed when unloaded
        carTransporter.unloadCar();
        assertFalse(saab.loadStatus());
    }

    @Test // Testing that a Car Transporter cannot be loaded on another Car Transporter
    public void testLoadCarTransportOnCarTransport(){
        carTransporter.loadCar(carTransporter2);
        assertFalse(carTransporter2.loadStatus());
    }

    @Test
    public void testRaiseAndLowerScaniaFlatbed() {
        scania.setAngle(scania.getMinAngle());

        while (scania.getAngle() < scania.getMaxAngle()) {
            scania.lowerFlatbed();
        }
        assertFalse(scania.flatbedIsUp());

        while (scania.getAngle() > scania.getMinAngle()) {
            scania.raiseFlatbed();
        }
        assertTrue(scania.flatbedIsUp());
    }

    @Test //Sends only the correct car type as an argument
    public void testSpecificCarWorkshop() {
        Workshop<Saab95> specificCarType = new Workshop<Saab95>();
        specificCarType.handInCar(saab);
        assertTrue(specificCarType.checkIfCarInWorkshop(saab));
        specificCarType.takeBackCar(saab);
    }

    @Test // Tests to make sure we dont exceed the max car limit of the workshop
    public void testInputMaxLoadWorkshop(){
        Workshop<Car> specificMaxLoad = new Workshop<Car>(2);
        specificMaxLoad.handInCar(scania);
        specificMaxLoad.handInCar(volvo);
        specificMaxLoad.handInCar(saab);

        assertEquals(2, specificMaxLoad.getCurrentLoad());

        specificMaxLoad.takeBackCar(scania);
        specificMaxLoad.takeBackCar(volvo);
    }
}