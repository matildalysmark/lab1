import java.awt.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMethodsOnce {
    private Saab95 saab;
    private Volvo240 volvo;

    @Before
    public void setUp() {
        saab = new Saab95();
        volvo = new Volvo240();
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

}