/**
 * Soren Patel
 * P1.java
 * Proj03
 */

import javax.swing.*;
import java.awt.*;

/**
 * P1 class - driver file
 */
public class P1{
    
    /**
     * main method
     */
    public static void main(String[] args){

        //define overall frame and layout
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        //four labels using text centering
        JLabel n = new JLabel("NORTH", SwingConstants.CENTER);
        JLabel e = new JLabel("EAST", SwingConstants.CENTER);
        JLabel s = new JLabel("SOUTH", SwingConstants.CENTER);
        JLabel w = new JLabel("WEST", SwingConstants.CENTER);

        //add labels at respective locations and center tile
        frame.add(n, BorderLayout.NORTH);
        frame.add(e, BorderLayout.EAST);
        frame.add(s, BorderLayout.SOUTH);
        frame.add(w, BorderLayout.WEST);
        frame.add(new Tile(), BorderLayout.CENTER);

        //pack and show frame
        frame.pack();
        frame.setVisible(true);
    }
}