public class Ramp implements Flatbed {
    private boolean isUp; // true = uppfälld, false = nedfälld

    public Ramp() {
        this.isUp = true; // rampen börjar uppfälld
    }

    @Override // status på rampen
    public boolean isUp() {
        return isUp;
    }

    @Override
    public void raise() {
        isUp = true;
    }

    @Override
    public void lower() {
        isUp = false;
    }
}

