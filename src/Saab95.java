import java.awt.*;

public class Saab95 extends Car implements Loadable {
    private boolean turboOn;

    public Saab95() {
        super();
        nrDoors = 2;
        color = Color.red;
        enginePower = 125;
        turboOn = false;
        modelName = "Saab95";
        weight = 1600;
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    public boolean getTurboState() {return turboOn;}

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    public void loadCarOnTransporter() { isOnCarTransport = true; }

    public void unloadCarFromTransporter() { isOnCarTransport = false; }
}
