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

    //arraylist to keep track of clicked tiles
    private ArrayList<Tile> clickedTiles = new ArrayList<>();

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
        clickedTiles.add(t);

        //check if 2 tiles are clicked
        if(clickedTiles.size() == 2){

            //get both of the clicked tiles
            Tile first = clickedTiles.get(0);
            Tile second = clickedTiles.get(1);

            //if they do not match
            if(first.getKindID() != second.getKindID()){
                //unclick both tiles and print accordingly
                first.unclick();
                second.unclick();
                System.out.println("Tile " + first.getPos() + " deactivated");
                System.out.println("Tile " + second.getPos() + " deactivated");
            }
            //they match
            else{
                //set match flag to true and print accordingly
                first.setMatchedTrue();
                second.setMatchedTrue();
                System.out.println("Tile " + first.getPos() + " matched");
                System.out.println("Tile " + second.getPos() + " matched");
                
            }

            //clear out and reset arraylist
            clickedTiles.clear();
        }
    }

    /**
     * deactivated method from TileListener interface
     */
    public void deactivated(Tile t){
        clickedTiles.remove(t);
    }
}