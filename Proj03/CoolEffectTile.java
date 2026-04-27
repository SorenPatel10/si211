/**
 * Soren Patel
 * File created by Google Gemini
 * Chat log documented in README
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import si211.*;

/**
 * A highly stylized Tile with depth effects, tactile borders, and a ripple animation on click.
 */
public class CoolEffectTile extends Tile {
    
    // Interaction Animation
    private Timer animationTimer;
    private double rippleRadius = 0;
    private float rippleAlpha = 0.0f;
    private Point rippleCenter = null;

    // Fixed 'active' tracking: Local boolean, since Tile.active is private.
    private boolean isCurrentlyActive = false;

    // Borders
    private BasicStroke defaultStroke;
    private BasicStroke activeStroke;

    // Visual noise texture (unique pattern per tile kind)
    private BufferedImage textureImage;
    private static final int TEXTURE_SIZE = 100;

    /**
     * @param pos Grid position
     * @param kindID Used to determine the color palette and texture pattern
     */
    public CoolEffectTile(Pos pos, int kindID) {
        super(pos, kindID);
        
        // Use custom rendering, ignore flat parent background
        setOpaque(false); 
        
        // Initialize distinct borders
        defaultStroke = new BasicStroke(1.5f); // Clean, thin outer edge
        activeStroke = new BasicStroke(4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND); // Thicker, smooth halo

        // Create a unique subtle texture based on kindID
        createStaticTexture(kindID);

        // Animation Timer (handles both the 'active' pulse and the 'click' ripple)
        animationTimer = new Timer(16, e -> {
            updateAnimationStates();
            repaint();
        });
        animationTimer.start();
    }

    @Override
    public void setActive(boolean val) {
        // We must track 'active' locally as Tile.java has no getter for its private 'active' field.
        super.setActive(val);
        this.isCurrentlyActive = val;
    }

    /**
     * Updates ripple animation progress over time.
     */
    private void updateAnimationStates() {
        if (rippleAlpha > 0.01f) {
            rippleRadius += 4.0; // Ripple expands
            rippleAlpha *= 0.93f; // Ripple fades out
        } else {
            rippleAlpha = 0; // Animation finished
            rippleCenter = null;
        }
    }

    @Override
    public void setMatched() {
        super.setMatched();
        if (animationTimer != null) {
            // Stop animations when matched, but don't stop it mid-ripple.
            if (rippleAlpha <= 0) {
                animationTimer.stop();
            }
        }
    }

    /**
     * Capture the exact click location to start the ripple effect.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (isMatched()) return;

        this.rippleCenter = e.getPoint();
        this.rippleRadius = 5.0; // Starting radius
        this.rippleAlpha = 0.8f; // Starting visibility

        if (!animationTimer.isRunning()) {
            animationTimer.start();
        }

        super.mousePressed(e);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Fully custom drawing, DO NOT call super.paintComponent(g)
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // **1. The Core Color Base (Depth Effect)**
        Color color1 = P3Tools.getSwatchColor(getKindID()); // Bright core
        Color color2 = color1.darker().darker();           // Dark edges
        
        if (isMatched()) {
            color1 = Color.WHITE;
            color2 = Color.LIGHT_GRAY;
        }

        // A radial gradient centered slightly off-center creates a 3D glass 'bead' effect
        RadialGradientPaint beadPaint = new RadialGradientPaint(
            new Point2D.Float(w * 0.4f, h * 0.4f), // Offset center
            w * 0.8f, // Radius of the gradient
            new float[]{0.0f, 0.9f, 1.0f}, // Color stop points
            new Color[]{color1, color2, color2.darker()}
        );
        g2.setPaint(beadPaint);
        // Rounded rectangle shape for the tile body
        RoundRectangle2D tileBody = new RoundRectangle2D.Float(2, 2, w - 4, h - 4, 20, 20);
        g2.fill(tileBody);


        // **2. Subliminal Texture Overlay**
        if (!isMatched()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));
            g2.drawImage(textureImage, 0, 0, w, h, null);
            g2.setComposite(AlphaComposite.SrcOver); // Reset composite
        }


        // **3. Matched/Unmatched State Shine**
        if (!isMatched()) {
            // Shiny highlight/glint on top
            g2.setPaint(new LinearGradientPaint(
                0, 0, w, h, 
                new float[]{0.0f, 0.5f, 1.0f}, 
                new Color[]{new Color(255, 255, 255, 100), new Color(255, 255, 255, 0), new Color(0, 0, 0, 50)}
            ));
            g2.fill(tileBody);
        }


        // **4. Dedicated Borders (Interaction States)**
        if (!isMatched()) {
            if (isCurrentlyActive) {
                // ACTIVE: Draw a glowing bright "halo" border
                g2.setStroke(activeStroke);
                g2.setColor(new Color(255, 255, 255, 220)); // Bright white, high opacity
                g2.draw(tileBody);
            } else {
                // DEFAULT (NOT ACTIVE): Draw a subtle, defined edge
                g2.setStroke(defaultStroke);
                // Dark gray, semi-transparent. Looks like a clean tactile edge.
                g2.setColor(new Color(0, 0, 0, 80)); 
                g2.draw(tileBody);
            }
        }


        // **5. Dynamic Click Ripple Effect**
        if (rippleCenter != null && rippleAlpha > 0.01f) {
            Ellipse2D ripple = new Ellipse2D.Double(
                rippleCenter.x - rippleRadius / 2,
                rippleCenter.y - rippleRadius / 2,
                rippleRadius,
                rippleRadius
            );

            // A wide, semi-transparent white ring that expands
            g2.setColor(new Color(255, 255, 255, (int)(rippleAlpha * 255)));
            g2.setStroke(new BasicStroke(12.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(ripple);
        }
    }

    /**
     * Generates a unique, faint noise texture for each kindID.
     */
    private void createStaticTexture(int kindID) {
        textureImage = new BufferedImage(TEXTURE_SIZE, TEXTURE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tg = textureImage.createGraphics();
        Random rand = new Random(kindID + 1337); // Seed ensures consistency per ID

        for (int x = 0; x < TEXTURE_SIZE; x += 4) {
            for (int y = 0; y < TEXTURE_SIZE; y += 4) {
                int gray = rand.nextInt(100, 255);
                int alpha = rand.nextInt(30, 80);
                tg.setColor(new Color(gray, gray, gray, alpha));
                tg.fillRect(x, y, 3, 3);
            }
        }
        tg.dispose();
    }
}