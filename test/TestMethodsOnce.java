import java.awt.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

public class TestMethodsOnce {
    private Saab95 saab;
    private Volvo240 volvo;
    private Random random;

    @Before
    public void setUp() {
        saab = new Saab95();
        volvo = new Volvo240();
        random = new Random();
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
        String[] directions = {"north", "east", "south", "west"};

        //Generating random direction to start with
        int direction_index = random.nextInt(4); // 0: north, 1: east, 2: south, 3: west

        int initial_direction = direction_index;
        saab.setDirection(initial_direction);

        //Generating random int for how many turns
        int numberOfTakes = random.nextInt(10, 30);

        for (int i = 0; i < numberOfTakes; i++) {
            saab.turnRight();
            saab.move();

            direction_index = (direction_index + 1) % 4;

            if (direction_index == 0)
                expected_y += saab.getCurrentSpeed();

            else if (direction_index == 1)
                expected_x += saab.getCurrentSpeed();

            else if (direction_index == 2)
                expected_y -= saab.getCurrentSpeed();

            else if (direction_index == 3)
                expected_x -= saab.getCurrentSpeed();
        }

        assertTrue("Initial direction: " + directions[initial_direction] +
                        "\nActual direction after right turn: " + directions[direction_index] +
                        "\nCalculated direction after right turn: " + saab.getDirection() +
                        "\nActual coordinates after movement: " + expected_x + ", " + expected_y +
                        "\nCalculated coordinates after movement: " + saab.getX() + ", " + saab.getY()
                ,directions[direction_index].equals(saab.getDirection())
                        && expected_x == saab.getX() && expected_y == saab.getY());
    }

    @Test
    public void testLeftMovementSaab() {
        double expected_x = 0;
        double expected_y = 0;
        String[] directions = {"north", "east", "south", "west"};

        //Generating random direction to start with
        int direction_index = random.nextInt(0,4); // 0: north, 1: east, 2: south, 3: west


        int initial_direction = direction_index;
        saab.setDirection(initial_direction);

        //Generating random int for how many turns
        int numberOfTakes = random.nextInt(10, 30);

        for (int i = 0; i < numberOfTakes; i++) {
            saab.turnLeft();
            saab.move();

            // (-1 + 4) = 3 due to behaviour of % operator in java, to prevent index from being negative
            direction_index = (direction_index + 3) % 4;

            if (direction_index == 0)
                expected_y += saab.getCurrentSpeed();

            else if (direction_index == 1)
                expected_x += saab.getCurrentSpeed();

            else if (direction_index == 2)
                expected_y -= saab.getCurrentSpeed();

            else if (direction_index == 3)
                expected_x -= saab.getCurrentSpeed();
        }

        assertTrue("Initial direction: " + directions[initial_direction] +
                        "\nActual direction after left turn: " + directions[direction_index] +
                        "\nCalculated direction after left turn: " + saab.getDirection() +
                        "\nActual coordinates after movement: " + expected_x + ", " + expected_y +
                        "\nCalculated coordinates after movement: " + saab.getX() + ", " + saab.getY()
                ,directions[direction_index].equals(saab.getDirection())
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



