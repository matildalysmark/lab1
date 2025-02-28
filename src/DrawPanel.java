import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawPanel extends JPanel implements ModelListener {
    private Map<Car, Coords> carPoints = new HashMap<>();
    private final Map<Class<? extends Car>, BufferedImage> carImages = new HashMap<>();
    private BufferedImage volvoWorkshopImage;
    private final Point carWorkshopPoint = new Point(300, 300);

    public DrawPanel(int x, int y, Model model) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.lightGray);

        // lägger till sig själv som observer för modellen
        model.addListener(this);

        try {
            carImages.put(Volvo240.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            carImages.put(Saab95.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            carImages.put(Scania.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // lyssnar på modellen och kallar på repaint vid förändring
    @Override
    public void onModelChanged(Map<Car, Coords> carPoints) {
        this.carPoints = carPoints;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Map.Entry<Car, Coords> pairs : carPoints.entrySet()) {
            BufferedImage image = carImages.get(pairs.getKey().getClass());
            Coords point = pairs.getValue();
            g.drawImage(image, (int) Math.round(point.getX()), (int) Math.round(point.getY()), null);
        }
        g.drawImage(volvoWorkshopImage, carWorkshopPoint.x, carWorkshopPoint.y, null);
    }
}