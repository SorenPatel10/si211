import javax.swing.*;
import java.awt.*;
import si211.*;

/**
 * A Tile where matching is based on the shape (n-sides)
 * rather than the background color.
 */
public class PolygonTile extends Tile {
    private int numSides;

    /**
     * @param pos The grid position
     * @param kindID Used here to determine the number of sides
     */
    public PolygonTile(Pos pos, int kindID) {
        // Pass kindID to super, though we will override the color behavior
        super(pos, kindID);
        
        // Map kindID to number of sides.
        // If kindID starts at 0, 0+3 = triangle, 17+3 = 20-sided polygon.
        this.numSides = kindID + 3;

        // Override the background to be neutral so color isn't a hint
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void setMatched() {
        // Call parent logic to set matched = true
        super.setMatched();
        // Ensure background stays white/neutral as defined in Tile.java
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Draws the background and the 'active' selection border
        super.paintComponent(g);

        // Do not draw the shape if the tile is already matched/cleared
        if (isMatched()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the shape in Black or Dark Gray for high contrast
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = (int) (Math.min(width, height) * 0.4); 

        Polygon poly = new Polygon();
        for (int i = 0; i < numSides; i++) {
            // Distribute vertices evenly around the center
            double angle = 2 * Math.PI * i / numSides - Math.PI / 2;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            poly.addPoint(x, y);
        }

        g2.drawPolygon(poly);
        
        // Optional: Fill with a subtle color to make the area "clickable" 
        // and visually distinct from the tile background
        g2.setColor(new Color(100, 100, 100, 40)); 
        g2.fillPolygon(poly);
    }
}