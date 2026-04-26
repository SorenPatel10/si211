/**
 * Soren Patel
 * Proj03
 * MatchGame.java
 */

import javax.swing.*;
import java.util.*;
import si211.*;

/**
 * driver file for the game
 * used to initialize seed and start game
 */
public class MatchGame{
    public static void main(String[] args){
        
        //random seed
        //if user input is given then that is the seed
        //if no user input then random number
        int seed = args.length > 0 ? Integer.parseInt(args[0]) : new Random().nextInt();

        //generate random 2d array of kindIDs and start game
        int[][] ids = P3Tools.getRandomKindIdAssignments(seed, 18, 6);
        GameFrame currGame = new GameFrame(ids);
    }
}