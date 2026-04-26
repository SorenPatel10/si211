/**
 * Soren Patel
 * Proj03
 * Tile.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import si211.*;
import java.util.ArrayList;

/**
 * Tile class, each is its own Jpanel and a MouseListener
 */
public class Tile extends JPanel implements MouseListener{

    //position and id
    private Pos pos;
    private int kindID;
    //flags to control selection state and 
    private boolean matched = false;
    private boolean active = false;

    //arraylist of tilelisteners to report click events
    private ArrayList<TileListener> listeners = new ArrayList<>();

    /**
     * tile constructor
     */
    public Tile(Pos pos, int kindID) {
        this.pos = pos;
        this.kindID = kindID;

        //set tilesize, border, and color
        setPreferredSize(new Dimension(100,100));
        setBackground(P3Tools.getSwatchColor(kindID));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //set tile as its own moselistener
        addMouseListener(this);
    }

    /**
     * method to add listeners for Board use
     **/
    public void addTileListener(TileListener t){
        listeners.add(t);
    }

    /**
     * getters
     */
    public int getKindID(){
        return kindID;
    }
    public Pos getPos(){
        return pos;
    }
    public boolean isMatched(){
        return matched;
    }

    /**setters
     * 
     */
    public void setMatched(){
        matched = true;
        active = false;
        //change tile color to white
        setBackground(Color.WHITE);
        repaint();
    }
    public void setActive(boolean b){
        if (matched)
            return;
        active = b;
        repaint();
    }

    /**
     * handle click on tile
     */
    public void mousePressed(MouseEvent e){
        
        //ignore tiles which are already matched
        if (matched)
            return;
        
        //notify board that tile was clicked
        for (TileListener t: listeners)
            t.activated(this);
    }

    /**
     * empty methods to fill MouseListener interface
     */
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    /**
     * paint border when tile is clicked
     */
    protected void paintComponent(Graphics g){
        //draw normal tile
        super.paintComponent(g);
        
        //conditions to show border
        if (active && !matched){
            //cast to a graphics2d and draw black border
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(8));
            //start 2 pix from top left
            //subtract 4 so border is inset
            g2.drawRect(2,2,getWidth()-4,getHeight()-4);
        }
    }
}