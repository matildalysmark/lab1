public class Ramp implements Flatbed {
    private boolean isUp; // true = up, false = down

    protected Ramp() {
        this.isUp = true; // Ramp starts up
    }

    @Override // status for the ramp
    public boolean isUp() { return isUp; }

    @Override
    public void raise() { isUp = true; }

    @Override
    public void lower() { isUp = false; }
}