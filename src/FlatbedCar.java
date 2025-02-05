public abstract class FlatbedCar<T extends Flatbed> extends Car {
    protected T flatbed; // antingen ramp eller vinklat flak


    public FlatbedCar(T flatbed) {
        super();
        nrDoors = 2;
        this.flatbed = flatbed; // sätter flaket till det som skickas in (FlatbedWithAngle från Scania och Ramp från CarTransport)
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

    public void raiseFlatbed() {
        // höjer flaket endast om lastfordon är stilla
        if (currentSpeed == 0) {
            flatbed.raise();
        }
    }

    public void lowerFlatbed() {
        // sänker flaket endast om lastfordon är stilla
        if (currentSpeed == 0) {
            flatbed.lower();
        }
    }

    public double speedFactor() {
        return enginePower * 0.01; //estimerad enginePower
    }
}



