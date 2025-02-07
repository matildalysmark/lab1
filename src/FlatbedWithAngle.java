public class FlatbedWithAngle implements Flatbed {
    private double angle;
    private final double minAngle;
    private final double maxAngle;

    protected FlatbedWithAngle(int minAngle, int maxAngle) {
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
        angle = minAngle; // flatbed is initially closed
    }

    @Override
    public boolean isUp() { return angle == minAngle; }

    // raises flatbed towards start-angle (minAngle)
    @Override
    public void raise() { angle = Math.max(minAngle, angle - 10); }

    // lowers flatbed, to dump cargo (towards maxAngle)
    @Override
    public void lower() { angle = Math.min(maxAngle, angle + 10); }

    // If the angle is in an allowed angle, set the in inputAngle-angle
    protected void setAngle(double inputAngle) {
        if (inputAngle <= maxAngle && inputAngle >= minAngle)
            angle = inputAngle;
    }

    public double getAngle() { return angle; }
    public double getMinAngle() { return minAngle; }
    public double getMaxAngle() { return maxAngle; }
}
