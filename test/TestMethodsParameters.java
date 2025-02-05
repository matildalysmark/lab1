import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestMethodsParameters {
    private Saab95 saab;
    private Volvo240 volvo;
    private Scania scania;
    private static Random random = new Random();

    @Before
    public void setUp() {
        saab = new Saab95();
        volvo = new Volvo240();
        scania = new Scania();
    }

    // Method to generate list of random numbers to use for testing
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        /* JUnits @Parameterized.Parameters requires this method to return a Collection<Object[]>
        (Collection is an interface, so the class can return any object that implements it),
        where each Object[] holds parameters (in this case, only one parameter per test) */

        /* Creating and instantiating an ArrayList named "parameters" to store Object[] arrays,
        type is ArrayList to enable dynamic resizing (as new test values are added)  */
        ArrayList<Object[]> parameters = new ArrayList<>();

        // Deciding how many numbers should be generated and setting limits
        int numberOfTests = 20;
        double minLimit = -3.0;
        double maxLimit = 3.0;

        // Generating random numbers within limits, adding the numbers to parameter list
        for (int i = 0; i < numberOfTests; i++) {
            double value = minLimit + (maxLimit - minLimit) * random.nextDouble();
            parameters.add(new Object[] {value});
        }
        return parameters;
    }

    // Defining variable "amount" where the values from the generated parameter list should be used instead
    @Parameterized.Parameter(0)
    public double amount;

    @Test //Check that Saabs's currentSpeed is within accepted interval after calling brake method
    public void testCurrentSpeedAcceptedAfterBrakingSaab() {
        saab.startEngine();
        double original_speed = saab.getCurrentSpeed();
        saab.brake(amount);

        assertTrue("Original speed " + original_speed +
                        "\nAmount of brake: " + amount +
                        "\nCurrent speed: " + saab.getCurrentSpeed(),
                saab.getCurrentSpeed() >= 0 && saab.getCurrentSpeed() <= saab.getEnginePower());
    }

    @Test //Check that Saabs's currentSpeed is within accepted interval after calling brake method
    public void testCurrentSpeedAcceptedAfterBrakingVolvo() {
        volvo.startEngine();
        double original_speed = volvo.getCurrentSpeed();
        volvo.brake(amount);

        assertTrue("Original speed " + original_speed +
                        "\nAmount of brake: " + amount +
                        "\nCurrent speed: " + volvo.getCurrentSpeed(),
                volvo.getCurrentSpeed() >= 0 && volvo.getCurrentSpeed() <= volvo.getEnginePower());
    }

    @Test //Check that Saabs's currentSpeed is within accepted interval after calling gas method
    public void testCurrentSpeedAcceptedAfterGasingSaab() {
        saab.startEngine();
        double original_speed = saab.getCurrentSpeed();
        saab.gas(amount);

        assertTrue("Original speed " + original_speed +
                        "\nAmount of gas: " + amount +
                        "\nCurrent speed: " + saab.getCurrentSpeed(),
                saab.getCurrentSpeed() >= 0 && saab.getCurrentSpeed() <= saab.getEnginePower());
    }

    @Test //Check that Volvos currentSpeed is within accepted interval after calling gas method
    public void testCurrentSpeedAcceptedAfterGasingVolvo() {
        volvo.startEngine();
        double original_speed = volvo.getCurrentSpeed();
        volvo.gas(amount);

        assertTrue("Original speed " + original_speed +
                        "\nAmount of gas: " + amount +
                        "\nCurrent speed: " + volvo.getCurrentSpeed(),
                volvo.getCurrentSpeed() >= 0 && volvo.getCurrentSpeed() <= volvo.getEnginePower());
    }

    @Test //Check that Volvo's Gas method Increases the Speed
    public void testGasNotDecreaseSpeedVolvo() {
        double speed = volvo.getCurrentSpeed();
        volvo.gas(amount);
        assertTrue(volvo.getCurrentSpeed() >= speed);
    }

    @Test //Check that Saab's Gas method Increases the Speed
    public void testGasNotDecreaseSpeedSaab() {
        double speed = saab.getCurrentSpeed();
        saab.gas(amount);
        assertTrue(saab.getCurrentSpeed() >= speed);
    }

    @Test //Check that Volvo's Brake method Decreases the Speed
    public void testBrakeNotIncreaseSpeedVolvo() {
        double speed = volvo.getCurrentSpeed();
        volvo.brake(amount);
        assertTrue(volvo.getCurrentSpeed() <= speed);
    }

    @Test //Check that Saab's Brake method Decreases the Speed
    public void testBrakeNotIncreaseSpeedSaab() {
        double speed = saab.getCurrentSpeed();
        saab.brake(amount);
        assertTrue(saab.getCurrentSpeed() <= speed);
    }

    @Test // kollar att flaket ej kan fällas ned när Scania är i rörelse
    public void testScaniaFlatbedInMovement(){
        scania.startEngine();
        scania.gas(amount);
        scania.setAngle(35);
        assertEquals(0.0, scania.getAngle(), 0);
    }
}
