public abstract class FlatbedCar<T extends Flatbed> extends Car {
    protected T flatbed; // either Ramp or FlatbedWithAngle

    public FlatbedCar(T flatbed) {
        super();
        nrDoors = 2;
        this.flatbed = flatbed; // assigns flatbed (FlatbedWithAngle from Scania and Ramp from CarTransport)
    }

    protected T getFlatbed() { return this.flatbed; }

    @Override
    public void move() {
        if (flatbed.isUp()) {
            super.move();
        }
    }

    @Override
    public void turnLeft() {
        if (flatbed.isUp()) {
            super.turnLeft();
        }
    }

    @Override
    public void turnRight() {
        if (flatbed.isUp()) {
            super.turnRight();
        }
    }

    public boolean flatbedIsUp() { return flatbed.isUp();}

    public void raiseFlatbed() {
        if (currentSpeed == 0) { flatbed.raise(); }
    }

    public void lowerFlatbed() {
        if (currentSpeed == 0) { flatbed.lower(); }
    }

    protected double speedFactor() {
        return enginePower * 0.01; //estimerad enginePower
    }
}