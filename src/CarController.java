import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CarController {
    private final int delay = 50;
    private final Timer timer = new Timer(delay, new TimerListener());
    private CarView frame;
    private Model model;
    private int gasAmount = 0;
    CarFactory factory;

    public CarController(Model model, CarView frame) {
        this.model = model;
        this.frame = frame;
        factory = new CarFactory(model);

        addListeners();
        timer.start();
    }

    private void addListeners() {
        frame.getGasButton().addActionListener(e -> model.gas(gasAmount));
        frame.getBrakeButton().addActionListener(e -> model.brake(gasAmount));
        frame.getTurboOnButton().addActionListener(e -> model.turboOn());
        frame.getTurboOffButton().addActionListener(e -> model.turboOff());
        frame.getLiftBedButton().addActionListener(e -> model.liftBed());
        frame.getLowerBedButton().addActionListener(e -> model.lowerBed());
        frame.getStartButton().addActionListener(e -> model.startAllCars());
        frame.getStopButton().addActionListener(e -> model.stopAllCars());

        frame.getAddCarButton().addActionListener(e -> {
            String newCar = (String) frame.getSelectCar().getSelectedItem();
            factory.createCar(newCar);});

        frame.getRemoveCarButton().addActionListener(e -> model.getAndRemoveLastCar());

        // konstant uppdatering, oberoende av knapptryck
        frame.getGasSpinner().addChangeListener(e -> gasAmount = (int) frame.getGasSpinner().getValue());
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.moveCars();
        }
    }
}