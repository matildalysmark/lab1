import java.awt.*;

public class Scania extends FlatbedCar<FlatbedWithAngle> {
    protected FlatbedWithAngle angledFlatbed; // för composition

    public Scania() {
        super(new FlatbedWithAngle(0, 70)); // ??
        color = Color.orange;
        enginePower = 125;
        modelName = "Scania";
        angledFlatbed = super.getFlatbed(); // ??
    }

    public void setAngle(double angle) {
        if (currentSpeed == 0)
            angledFlatbed.setAngle(angle);
    }

    public double getAngle() {
        return angledFlatbed.getAngle();
    }
}
