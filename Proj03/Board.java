/**
 * Soren Patel
 * Board.java
 * Proj03
 */

import javax.swing.*;
import java.awt.*;
import si211.*;
import java.util.ArrayList;

/**
 * board class to put the tiles in a gridlayout
 */
public class Board extends JPanel implements TileListener{

    private Tile first = null;
    private Tile second = null;
    private boolean handling = false;
    private static Tile active = null;

    /**
     * constructor, takes in 2d int array of ids
     */
    public Board(int[][] ids){
        
        int size = ids.length;
        //structure as gridlayout
        setLayout(new GridLayout(size,size));

        //create all tiles and populate by looping through board array
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                //add a new tile with pos(current index) and id(given array current index)
                Tile temp = new Tile(new Pos(i,j), ids[i][j]);
                temp.addTileListener(this);
                add(temp);
            }
        }
    }

    /**
     * activated method from TileListener interface
     */
    public void activated(Tile t){
        if(t.isMatched()){
            return;
        }

        // if(handling)
        //     return;

        if(active == t){
            active = null;
            t.repaint();
            return;
        }

        if(active == null){
            active = t;
            t.repaint();
            return;
        }

        Tile first = active;
        Tile second = t;

        //match logic
        //not match
        if(first.getKindID() != second.getKindID()){
            
            System.out.println("Tile " + first.getPos() + " deactivated");
            System.out.println("Tile " + second.getPos() + " deactivated");
        }
        //match
        else{
            first.setMatchedTrue();
            second.setMatchedTrue();
            System.out.println("Tile " + first.getPos() + " matched");
            System.out.println("Tile " + second.getPos() + " matched");
        }

        first.repaint();
        second.repaint();
        first.setActivated(false);
            second.setActivated(false);
        // handling = false;
    }

    /**
     * deactivated method from TileListener interface
     */
    public void deactivated(Tile t){
    }

    public static Tile getActiveTile(){
        return active;
    }
}