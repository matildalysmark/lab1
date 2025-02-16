import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    Workshop<Volvo240> volvoWorkshop = new Workshop<>();

    //methods:
    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());
        cc.cars.get(0).setX(300);
        cc.cars.get(1).setY(100);
        cc.cars.get(2).setY(200);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        // anropas varje gång timern tickar! alltså typ hela tiden
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {

                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                // om bilarna nuddar kanten
                if (x > 800 || x < 0|| y > 500 || y < 0) {
                    car.stopEngine();

                    switch (car.getDirection()) {
                        case NORTH -> {
                            car.setDirection(Directions.SOUTH);
                            car.setY(500);
                            y = 500;
                        }
                        case SOUTH ->{
                            car.setDirection(Directions.NORTH);
                            car.setY(0);
                            y = 0;
                        }
                        case WEST ->{
                            car.setDirection(Directions.EAST);
                            car.setX(800);
                            x = 800;
                        }
                        case EAST ->{
                            car.setDirection(Directions.WEST);
                            car.setX(0);
                            x = 0;
                        }
                    }

                    car.startEngine();
                }

                //Volvo workshop
                if (x > 295 && x < 305 && y> 295 && y < 305) {
                    if (car instanceof Volvo240) {
                        volvoWorkshop.handInCar((Volvo240) car); // aja baja!?
                    }
                }

                frame.drawPanel.moveitmoveit(x, y, car);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) { car.gas(gas); }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) { car.brake(brake); }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) { ((Saab95) car).setTurboOn(); } // olagligt kanske
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) { ((Saab95) car).setTurboOff(); }
        }
    }

    void liftBed() {
        for (Car car : cars) {
            if (car instanceof Scania) { ((Scania) car).raiseFlatbed(); }
        }
    }

    void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Scania){ ((Scania) car).lowerFlatbed(); }
        }
    }

    void start() {
        for (Car car : cars) { car.startEngine(); }
    }

    void stop() { for (Car car : cars) { car.stopEngine(); } }
}
