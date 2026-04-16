import java.awt.*;
import javax.swing.*;
import java.util.*;

public class DrawArea extends JComponent {

    private double a0 = 0.0;
    private double a1 = 0.0;
    private ArrayList<Integer> xCoords = new ArrayList<>();
    private ArrayList<Integer> yCoords = new ArrayList<>();

    public DrawArea() {
        super();
        setPreferredSize(new Dimension(400, 400));
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
        int r0 = 100;
        int r1 = 50;

        int p1x = (int)(p0x + r0*Math.cos(a0));
        int p1y = (int)(p0y + r0*Math.sin(a0));
        int p2x = (int)(p1x + r1*Math.cos(a1));
        int p2y = (int)(p1y + r1*Math.sin(a1));

        xCoords.add(p2x);
        yCoords.add(p2y);

        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.PINK);
        drawCenteredCircle(g2, p0x, p0y, r0);

        g2.setColor(Color.PINK);
        fillCenteredCircle(g2, p1x, p1y, 8);

        g2.setColor(new Color(128, 0, 128));
        drawCenteredCircle(g2, p1x, p1y, r1);

        g2.setColor(new Color(128, 0, 128));
        fillCenteredCircle(g2, p2x, p2y, 6);
        
        for(int i = 1; i < xCoords.size(); i++){
            g2.drawLine(xCoords.get(i-1), yCoords.get(i-1), xCoords.get(i), yCoords.get(i));
        }
    }

    public void updateAnimation() {
        a0 += 0.01;
        a1 += 0.04;
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