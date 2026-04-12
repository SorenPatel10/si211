/**
 * Soren Patel
 * Tile.java
 * Proj03
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import si211.*;
import java.util.ArrayList;

/**
 * Tile class, extends JPanel, uses MouseListener interface
 */
public class Tile extends JPanel implements MouseListener{
    
    //fields
    private boolean clicked = false;
    private Pos pos;
    private int kindID;
    private boolean matched = false;
    //to keep track of current listeners
    private ArrayList<TileListener> tLists = new ArrayList<>();

    /**
     * no arg constructor for p1
     */
    public Tile(){
        //set default size, color, and border
        setPreferredSize(new Dimension(100,100));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //set tile as its own mouse listener
        addMouseListener(this);
    }

    /**
     * p2 constructor - takes a pos and a kindID
    */
    public Tile(Pos pos, int kindID){
        //instantiate arguments
        this.pos = pos;
        this.kindID = kindID;

        //set size, color(according to kindID), and border
        setPreferredSize(new Dimension(100,100));
        setBackground(P3Tools.getSwatchColor(kindID));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //set tile as its own mouselistener
        addMouseListener(this);
    }

    /**
     * various getter/setter methods needed by Board class
     */
    public void addTileListener(TileListener t){
        tLists.add(t);
    }
    public void unclick(){
        clicked = false;
    }
    public void setMatchedTrue(){
        matched = true;
        //changes tile color to white once a match
        setBackground(Color.WHITE);
        repaint();
    }
    public boolean isMatched(){
        return matched;
    }
    public int getKindID(){
        return kindID;
    }
    public Pos getPos(){
        return pos;
    }

    /**
     * handle click on tile
     */
    public void mousePressed(MouseEvent e){
        
        //ignore clicks if already matched
        if(matched)
            return;

        //set click flag and print accordingly
        clicked = !clicked;
        System.out.print("Tile " + (pos != null ? pos : "") + " ");
        System.out.println(clicked ? "activated" : "deactivated");

        //show border
        repaint();

        //for all listeners of each tile, set activation status
        for(TileListener temp: tLists){
            if(clicked)
                temp.activated(this);
            else
                temp.deactivated(this);
        }
    }

    /**
     * overriding paintComponent method from JPanel -> JComponent
     */
    protected void paintComponent(Graphics g){
        //clear background
        super.paintComponent(g);

        //draw border if clicked but not yet matched
        if(clicked && !matched){
            //cast g to a 2d graphics object
            Graphics2D g2d = (Graphics2D)g;
            
            //set border color and thickness
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(4));
            
            //draw rectangle to keep border inside tile.
            g2d.drawRect(2,2,getWidth()-4, getHeight()-4);
        }
    }


    /**
     * handle mouse release
     */
    public void mouseReleased(MouseEvent e){
        //System.out.println("Tile released");
    }
    /**
     * empty methods to fulfill interface contract
     */
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
}