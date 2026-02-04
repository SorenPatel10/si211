/*
MIDN 3/C Soren Patel (284932)
Lab 05
NRRandom.java
*/

import java.util.*;

public class NRRandom extends Random{

    //field: last given int (start at -1 since no first int)
    private int lastInt = -1;

    /**
     * Empty Constructor
     */
    public NRRandom(){
        super();
    }

    /**
     * Constructor with one seed field
     */
    public NRRandom(long seed){
        super(seed);
    }

    /**
     * Override Random's nextInt(int num)
     * stores random int, requeries until it is different than last int
     * stores current value in last int and returns
     */
    public int nextInt(int num){
        int temp = super.nextInt(num);
        while(temp == lastInt){
            temp = super.nextInt(num);
        }
        lastInt = temp;
        return temp;
    } 
}