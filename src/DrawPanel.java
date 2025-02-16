import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.
public class DrawPanel extends JPanel{

    // skapar "dictionary" där bilar (alltså bilobjekt) är nycklar och deras punkter är värden
    private Map<Car, Point> carPoints = new HashMap<>();

    // skapar "dictionary" där bilklasser är nycklar och bilderna på modellerna är värden
    private Map<Class<? extends Car>, BufferedImage> carImages = new HashMap<>();

    BufferedImage volvoWorkshopImage;
    Point carWorkshopPoint = new Point(300,300);

    void moveitmoveit(int x, int y, Car car) {
        // om bilen inte lagts till tidigare: addera den tillsammans med ny punkt
        carPoints.putIfAbsent(car, new Point());

        // uppdatera x och y för bilen
        carPoints.get(car).setLocation(x, y);
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.lightGray);

        try {
            carImages.put(Volvo240.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            carImages.put(Saab95.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            carImages.put(Scania.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        }
        catch (IOException ex) { // Print an error message in case file is not found with a try/catch block
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // loopar igenom varje nyckel-värde par i carPoints
        for (Map.Entry<Car, Point> entry : carPoints.entrySet()) {

            BufferedImage image = carImages.get(entry.getKey().getClass());
            Point point = entry.getValue();

            // målar ut bilen
            g.drawImage(image, point.x, point.y, null);
        }

        // målar ut volvo workshoppen
        g.drawImage(volvoWorkshopImage, carWorkshopPoint.x, carWorkshopPoint.y, null);
    }
}

