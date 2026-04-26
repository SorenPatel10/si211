/**
 * Soren Patel
 * Proj03
 * Board.java
 */

import javax.swing.*;
import java.awt.*;
import si211.*;

/**
 * board class (is a JPanel), implements tile listener interface
 */
public class Board extends JPanel implements TileListener{
    
    //fields
    //first tile clicked
    private Tile first = null;
    //whether game is enabled (for start button functionality)
    private boolean enabled = false;
    //game listener
    private GameListener listener;
    //2d array of tiles
    private Tile[][] tiles;

    /**
     * Board constructor
     * takes in 2d array of kind ids and gameframe
     */
    public Board(int[][] ids, GameListener listener){
        this.listener = listener;

        //create 6x6 grid
        int n = ids.length;
        setLayout(new GridLayout(n,n));

        //initialize tile array
        tiles = new Tile[n][n];

        //initialize all tiles in grid
        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                
                //next 2 lines (select whether to use Tiles or PolygonTiles)
                Tile t = new Tile(new Pos(i,j), ids[i][j]);
                // Tile t = new PolygonTile(new Pos(i,j), ids[i][j]);

                t.addTileListener(this);

                //store tile in array
                tiles[i][j] = t;

                add(t);
            }
        }
    }

    /**
     * allow game to be played
     */
    public void setEnabledGame(boolean b){
        enabled = b;
    }

    /**
     * main clicking logic for tiles
     */
    public void activated(Tile t) {

        //ignore clicks if game isnt enabled
        if (!enabled || t.isMatched())
            return;

        //same tile clicked twice
        if (first == t){
            //remove border, reset selection
            t.setActive(false);
            System.out.println("Tile " + t.getPos() + " deactivated");
            first = null;
            return;
        }

        //first click on a tile
        if (first == null){
            //store clicked tile and draw border
            first = t;
            t.setActive(true);
            System.out.println("Tile " + t.getPos() + " activated");
            return;
        }

        //second click
        //we now have two tiles (first and second)
        Tile second = t;
        second.setActive(true);
        System.out.println("Tile " + second.getPos() + " activated");

        //match
        if (first.getKindID() == second.getKindID()){
            System.out.println("Tile " + first.getPos() + " matched");
            System.out.println("Tile " + second.getPos() + " matched");

            //paint tiles white and disable further clicking
            first.setMatched();
            second.setMatched();

            //check if game is over yet
            checkGameOver();
        }
        //no match
        else{
            System.out.println("Tile " + first.getPos() + " deactivated");
            System.out.println("Tile " + second.getPos() + " deactivated");
        }

        //remove borders and clear tile selections
        first.setActive(false);
        second.setActive(false);
        first = null;
    }

    /**
     * method to check if game is done yet
     */
    private void checkGameOver(){

        //loop through all tiles in 2D array
        for (int i = 0; i < tiles.length; i++){
            for (int j = 0; j < tiles[i].length; j++){

                if (!tiles[i][j].isMatched())
                    return;
            }
        }

        //all tiles matched means game finished
        listener.gameFinished();
    }

}