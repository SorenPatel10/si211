/*
MIDN 3/C Soren Patel (284932)
MIDN 3/C Siddharth Swarup (286354)
Lab07
ThingB.java
*/

import java.util.*;

/**
 * class ThingB extends ThingA
 */
public class ThingB extends ThingA {

    //field tracks iterations since last turn
    private int timeSinceLast;

    /**
     * constructor. sets timeSinceLast to 0
     */
    public ThingB(int row, int col, char lab) {
        super(row,col);
        this.lab = lab;
        this.timeSinceLast = 0;
    }

    /**
     * override maybeturn method
     * turn chance based on timeSinceLast (tracks eveyr 10 iters)
     */
    public void maybeTurn() {
        int i = rand.nextInt(3);
        this.timeSinceLast++;
        if (this.timeSinceLast == 10) {
            this.timeSinceLast = 0;
            if (i == 1)
                this.rightTurn();

            if (i == 2)
                this.leftTurn();
        }
    }
}