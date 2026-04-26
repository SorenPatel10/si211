/**
 * Soren Patel
 * Proj03
 * HighScoreWindow.java
 */

import javax.swing.*;

/**
 * class to show the highscore pop up window
 */
public class HighScoreWindow{
    
    /**
     * method shows window using JOptionPane
     */
    public static void showScores(JFrame parent, HighScoreManager manager){
        //show joptionpane
        //takes in parent JFrame, scores to display, label, message type
        JOptionPane.showMessageDialog( parent, manager.getFormattedScores(),"High Scores", JOptionPane.PLAIN_MESSAGE);
    }
}