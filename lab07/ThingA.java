/*
MIDN 3/C Soren Patel (284932)
MIDN 3/C Siddharth Swarup (286354)
Lab07
ThingA.java
*/

import java.util.*;

/**
 * Class ThingA
 */
public class ThingA {

    //random variable, seeded with current time
    protected static Random rand = new Random(System.currentTimeMillis());
    // dir: 0=North, 1=East, 2=South, 3=West.
    //row and col of start pos, lab determines color
    private int row, col, dir;
    protected char lab;

    /**
     * constructor. sets every dir to 0, every lab to r
     */
    public ThingA(int row, int col) {
        this.row = row;
        this.col = col;
        this.dir = 0;
        this.lab = 'r';
    }

    /**
     * change dir to reflect right turn
     */
    public void rightTurn() {
        this.dir = (this.dir + 1) % 4;
    }

    /**
     * change dir to reflect left turn
     */
    public void leftTurn() {
        this.dir = (this.dir + 3) % 4;
    }

    /**
     * turn left or right based on random value on each call
     */
    public void maybeTurn() {
        int i = rand.nextInt(3);
        if (i == 1)
            this.rightTurn();

        if (i == 2)
            this.leftTurn();
    }

    /**
     * move the Thing forward in its current direction
     */
    public void step() {
        final int[] dc = { 0, 1, 0, -1 }, dr = { -1, 0, 1, 0 };
        this.row += dr[this.dir];
        this.col += dc[this.dir];
    }

    /**
     * toString format
     */
    public String toString(){
        return row + " " + col + " " + lab;
    }


}