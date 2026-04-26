/**
 * Soren Patel
 * Proj03
 * HighScoreManager.java
 */

import java.util.*;

/**
 * class to manage scoring logic and store scores
 * this is separate from HighScoreDialog
 * this class handles the logic, the other one handles the window
 */
public class HighScoreManager{
    
    //arraylist holds scores (in seconds)
    private ArrayList<Integer> scores = new ArrayList<>();

    /**
     * add score and sort list
     */
    public void addScore(int seconds){
        scores.add(seconds);
        //Collections.sort modifies the list to go smallest->largest
        //uses the Comparable interface
        Collections.sort(scores);
    }

    /**
     * returns scores in a formatted manner to put on window
     */
    public String getFormattedScores(){
        
        //no games played yet = no scores
        if (scores.isEmpty())
            return "No scores yet.";

        //result building, start empty
        String result = "";

        //loop through all scores
        for (int i = 0; i < scores.size(); i++){
            
            //convert seconds into a minutes and seconds
            int s = scores.get(i);
            int m = s/60;
            int sec = s%60;
            
            //add to result using format string for 2 digit decimal and leading zero
            result += (i+1) + ". " + String.format("%02d:%02d", m, sec) + "\n";
        }

        return result;
    }
}