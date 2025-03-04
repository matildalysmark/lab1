import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching its controller in its state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of its components.
 **/

public class CarView extends JFrame {
    private static final int X = 800;
    private static final int Y = 800;

    Model model;
    DrawPanel drawPanel;

    String[] options = {"Random", "Volvo240", "Saab95", "Scania"};
    JComboBox<String> selectCar = new JComboBox<>(options);
    JLabel selectCarLabel = new JLabel("Car to add");

    JPanel controlPanel = new JPanel();
    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    //add and remove car
    JButton addCarButton = new JButton("Add car");
    JButton removeCarButton = new JButton("Remove car");

    //original buttons
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Lift truck Bed");
    JButton lowerBedButton = new JButton("Lower truck Bed");
    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    // Constructor
    public CarView(String framename, Model model) {
        this.model = model;
        drawPanel = new DrawPanel(X, Y-240, model);
        initComponents(framename);
    }

    JButton getAddCarButton() { return addCarButton; }
    JButton getRemoveCarButton() { return removeCarButton; }
    JComboBox<String> getSelectCar() { return selectCar; }

    JButton getGasButton() { return gasButton; }
    JButton getBrakeButton() { return brakeButton; }
    JButton getTurboOnButton() { return turboOnButton; }
    JButton getTurboOffButton() { return turboOffButton; }
    JButton getLiftBedButton() { return liftBedButton; }
    JButton getLowerBedButton() { return lowerBedButton; }
    JButton getStartButton() { return startButton; }
    JButton getStopButton() { return stopButton; }
    JSpinner getGasSpinner() { return gasSpinner; }

    // Sets everything in place and fits everything
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.add(drawPanel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1); //step

        gasSpinner = new JSpinner(spinnerModel);

        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new GridLayout(4,1));
        gasPanel.add(gasLabel, 0);
        gasPanel.add(gasSpinner, 1);
        gasPanel.add(selectCarLabel, 2);
        gasPanel.add(selectCar, 3);
        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,5));
        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(addCarButton, 3);
        controlPanel.add(brakeButton, 4);
        controlPanel.add(turboOffButton, 5);
        controlPanel.add(lowerBedButton, 6);
        controlPanel.add(removeCarButton, 7);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        controlPanel.setBackground(Color.white);
        this.add(controlPanel);

        startButton.setBackground(Color.orange);
        startButton.setForeground(Color.white);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);

        stopButton.setBackground(Color.pink);
        stopButton.setForeground(Color.white);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        fixAndShowFrame();
    }

    void fixAndShowFrame() {
        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();
        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}