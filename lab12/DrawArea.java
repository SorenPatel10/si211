import java.awt.*;
import javax.swing.*;
import java.util.*;

public class DrawArea extends JComponent {

    private Orbiter body0;
    private Orbiter body1;

    private ArrayList<Integer> xCoords = new ArrayList<>();
    private ArrayList<Integer> yCoords = new ArrayList<>();

    public DrawArea() {
        super();
        setPreferredSize(new Dimension(400, 400));

        body0 = new Orbiter(200, 200, 100, 0.01);
        body1 = new Orbiter(0, 0, 50, 0.04);
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(
            RenderingHints.KEY_RENDERING, 
        RenderingHints.VALUE_RENDER_QUALITY);

        int p0x = 200;
        int p0y = 200;

        int p1x = body0.getX();
        int p1y = body0.getY();

        body1.setCenter(p1x, p1y);

        int p2x = body1.getX();
        int p2y = body1.getY();

        xCoords.add(p2x);
        yCoords.add(p2y);

        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.PINK);
        drawCenteredCircle(g2, p0x, p0y, 100);

        g2.setColor(Color.PINK);
        fillCenteredCircle(g2, p1x, p1y, 8);

        g2.setColor(new Color(128, 0, 128));
        drawCenteredCircle(g2, p1x, p1y, 50);

        g2.setColor(new Color(128, 0, 128));
        fillCenteredCircle(g2, p2x, p2y, 6);
        
        for(int i = 1; i < xCoords.size(); i++){
            g2.drawLine(xCoords.get(i-1), yCoords.get(i-1), xCoords.get(i), yCoords.get(i));
        }
    }

    public void updateAnimation() {
        body0.update();
        body1.update();
    }

    public void drawCenteredCircle(Graphics g, int centerX, int centerY, int radius) {
        int diameter = radius * 2;
        int x = centerX - radius;
        int y = centerY - radius;
        g.drawOval(x, y, diameter, diameter);
    }

    public void fillCenteredCircle(Graphics g, int centerX, int centerY, int radius) {
        int diameter = radius * 2;
        int x = centerX - radius;
        int y = centerY - radius;
        g.fillOval(x, y, diameter, diameter);
    }
}