public class FlatbedWithAngle implements Flatbed {
    private double angle;
    private double minAngle;
    private double maxAngle;

    public FlatbedWithAngle(int minAngle, int maxAngle) {
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
        angle = minAngle; // flaket börjar helt uppe (ej vinklat)
    }

    @Override
    public boolean isUp() {
        return angle == minAngle;
    } //returnerar flakets läge

    // höjer flaket till ursprungsläge (mot minangle-vinkeln)
    @Override
    public void raise() {
        angle = Math.max(minAngle, angle - 10);
    }

    // alltså tippa nedåt, för att hälla ut sand/sten (mot maxangle-vinklen)
    @Override
    public void lower() {
        angle = Math.min(maxAngle, angle + 10);
    }

    // Om vinkeln är i tillåtet intervall, ställ in inputAngle-vinkeln
    public void setAngle(double inputAngle) {
        if (inputAngle <= maxAngle && inputAngle >= minAngle)
            angle = inputAngle;
    }

    public double getAngle() {
        return angle;
    }
}
