public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        CarView view = new CarView("CarSim 1.0", model);

        CarController controller = new CarController(model, view);
    }
}