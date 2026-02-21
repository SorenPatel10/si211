/*
MIDN 3/C Soren Patel (284932)
MIDN 3/C Siddharth Swarup (286354)
Lab07
ThingC.java
*/

import java.util.*;

/**
 * class ThingC extends ThingA
 */
public class ThingC extends ThingA {
    
    //fields to handle diagonal motion
    private boolean startRight = true;
    private boolean goRight;
    private int steps = 0;
    
    /**
     * constructor
     */
    public ThingC(int row, int col, char lab) {
        super(row,col);
        this.lab = lab;
        this.goRight = startRight;
    }

    /**
     * override maybeTurn method
     * switches diagonal direction every 10 steps
     */
    public void maybeTurn() {
        if(this.goRight)
            this.rightTurn();
        else
            this.leftTurn();
        
        this.goRight = !this.goRight;
        this.steps++;

        //switching direction
        if(this.steps >= 10){
            this.steps = 0;
            this.startRight = !this.startRight;
            this.goRight = this.startRight;
        }
    }
}