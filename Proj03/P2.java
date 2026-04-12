/**
 * Soren Patel
 * P2.java
 * Proj03
 */

import javax.swing.*;
import java.awt.*;
import si211.*;
import java.util.*;

/**
 * P2 class - driver file
 */
public class P2{

    /**
     * main method
     */
    public static void main(String[] args){
        
        //set seed either to cmdline argument or random if there is none
        int seed = args.length > 0 ? Integer.parseInt(args[0]) : new Random().nextInt();

        //randomly populate kindIDs for tiles using seed
        int[][] ids = P3Tools.getRandomKindIdAssignments(seed,18,6);

        //create frame and establich borderlayout
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        //initialize and add board to center
        frame.add(new Board(ids), BorderLayout.CENTER);
        
        //pack and show frame
        frame.pack();
        frame.setVisible(true);
    }

}