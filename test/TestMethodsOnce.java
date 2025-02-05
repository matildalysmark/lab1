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

    @Test //Check Saab Door Amount
    public void testSaabDoors() {
        assertEquals(2, saab.getNrDoors());
    }

    @Test //Check Volvo Door Amount
    public void testVolvoDoors() {
        assertEquals(4, volvo.getNrDoors());
    }

    @Test //Check Saab Engine Power
    public void testEnginePowerSaab() { assertEquals(125, saab.getEnginePower(), 0); }

    @Test //Check Volvo Engine Power
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
    public void testRightMovementSaab() {
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

        assertTrue("Actual direction after right turn: " + expectedDirection +
                        "\nCalculated direction after right turn: " + saab.getDirection() +
                        "\nActual coordinates after movement: " + expected_x + ", " + expected_y +
                        "\nCalculated coordinates after movement: " + saab.getX() + ", " + saab.getY()
                ,expectedDirection == saab.getDirection()
                        && expected_x == saab.getX() && expected_y == saab.getY());
    }

    @Test
    public void testLeftMovementSaab() {
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

        assertTrue("Actual direction after left turn: " + expectedDirection +
                        "\nCalculated direction after left turn: " + saab.getDirection() +
                        "\nActual coordinates after movement: " + expected_x + ", " + expected_y +
                        "\nCalculated coordinates after movement: " + saab.getX() + ", " + saab.getY()
                ,expectedDirection == saab.getDirection()
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

    @Test //testar CarTransporter rampen
    public void testCarTransporterRamp() {
        // höjer ramp och kollar att den är uppe
        carTransporter.raiseFlatbed();
        assertTrue(carTransporter.rampIsUp());

        // sänker ramp och kollar att den är sänkt
        carTransporter.lowerFlatbed();
        assertFalse(carTransporter.rampIsUp());
    }

    @Test // Testing that CarTransport cannot move when ramp is lowered
    public void testCarTransporterRampMoving() {
        double previousX = carTransporter.getX();
        double previousY = carTransporter.getY();
        
        carTransporter.lowerFlatbed();
        carTransporter.startEngine();
        carTransporter.gas(10);
        carTransporter.move();
        
        assertEquals(previousX, carTransporter.getX(), 0);
        assertEquals(previousY, carTransporter.getY(), 0);
    }

    @Test // Testing that CarTransport cannot lower ramp when CarTransport is moving
    public void testCarTransporterMovingRamp() {
        carTransporter.raiseFlatbed();
        
        carTransporter.startEngine();
        carTransporter.gas(10);
        
        carTransporter.lowerFlatbed();

        assertTrue(carTransporter.rampIsUp());
    }

    @Test
    public void testCarTransporterMovingLoadedCars() {
        carTransporter.lowerFlatbed();

        Car[] cars = {volvo, saab, carTransporter};

        // sätter CarTransport, saab och volvos position till (0, 0)
        for (Car car : cars) {
            car.setX(0);
            car.setY(0);
        }

        carTransporter.loadCar(volvo);
        carTransporter.loadCar(saab);

        // flyttar på CarTransport
        carTransporter.startEngine();
        carTransporter.gas(10);
        carTransporter.move();
        carTransporter.turnLeft();
        carTransporter.turnLeft();
        carTransporter.move();
        carTransporter.turnRight();

        // kollar så att de loadade bilarna har rört sig med CarTransport
        assertTrue(carTransporter.getX() == volvo.getX() && carTransporter.getY() == volvo.getY());
        assertTrue(carTransporter.getX() == saab.getX() && carTransporter.getY() == saab.getY());
        assertEquals(carTransporter.getDirection(), volvo.getDirection());
        assertEquals(carTransporter.getDirection(), saab.getDirection());

        carTransporter.unloadCar();
        carTransporter.unloadCar();
    }
    
    @Test // testar att Scania ej kan förflytta om flaket ej är uppfällt
    public void testScaniaMovementWhenFlatbedDown(){
        double previous_x = scania.getX();
        double previous_y = scania.getY();
        scania.setAngle(35);
        scania.move();
        assertEquals(previous_x, scania.getX(), 0);
        assertEquals(previous_y, scania.getY(), 0);
    }

    @Test // Testar så att bil ej kan loadas om den är för långt bort
    public void testLoadCarFromDistance() {
        carTransporter.setX(0);
        carTransporter.setY(0);

        volvo.setX(10);
        volvo.setY(30);

        carTransporter.loadCar(volvo);
        assertFalse(volvo.loadStatus());
    }

    @Test // samma test för lastning och avlastning för att veta att vi har en bil att lasta av
    public void testLoadStatusCar(){
        //ser till att status ändras när bil lastats
        carTransporter.loadCar(saab);
        assertTrue(saab.loadStatus());

        //ser till att status ändras när bil lastats av
        carTransporter.unloadCar();
        assertFalse(saab.loadStatus());
    }

    @Test // testar att en CarTransport ej kan lastas på en annan CarTransport
    public void testLoadCarTransportOnCarTransport(){
        carTransporter.loadCar(carTransporter2);
        assertFalse(carTransporter2.loadStatus());
    }
}
