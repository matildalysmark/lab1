import java.awt.*;

public class Scania extends FlatbedCar {
    protected FlatbedWithAngle angledFlatbed; // för composition

    public Scania() {
        super();
        color = Color.orange;
        enginePower = 125;
        modelName = "Scania";
        angledFlatbed = new FlatbedWithAngle(0, 70);
    }

    public void setAngle(double angle) {
        if (currentSpeed == 0)
            angledFlatbed.setAngle(angle);
    }
}
