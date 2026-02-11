/*
MIDN 3/C Soren Patel (284932)
Proj01
Week.java
*/

import java.util.*;
import si211.*;
import java.io.*;

/**
 * Week class
 */
public class Week{

    //field representing the week
    private char[][] layout;

    /**
     * empty week constructor
     */
    public Week(){
        layout = new char[6][5];
        
        //initialize all slots to empty char
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                layout[i][j] = ' ';
            }
        }
    }

    /**
     * constructor from meeting time
     */
    public Week(String pattern){
        //use the empty constructor
        this();
        //explode input to get string array
        //uses Dr. Brown very helpful method
        String[] meetings = DrBrown.explode(pattern);
        
        for(String m: meetings){
            //get indices of day and period and fill in
            int day = dayToIndex(m.charAt(0));
            int period = periodToIndex(m.substring(1));
            if(day != -1 && period != -1){
                layout[period][day] = 'x';
            }
        }
    }

    /**
     * Merge another week into this one
     */
    public void merge(Week other){
        //uses this instance vs other instance to compare busy days
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                if(other.layout[i][j] == 'x'){
                    this.layout[i][j] = 'x';
                }
            }
        }
    }

    /**
     * Print the week grid
     */
    public void print(){
        System.out.println("  M T W R F");
        
        //print each period's row
        for(int i = 0; i < 6; i++){
            System.out.print((i+1) + " ");
            for(int j = 0; j < 5; j++){
                System.out.print(layout[i][j]);
                if(j<4) System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Convert day character to index
     */
    private int dayToIndex(char day){
        //switch-case is cleaner than lots of if-elseif statements
        switch(day){
            case 'M': return 0;
            case 'T': return 1;
            case 'W': return 2;
            case 'R': return 3;
            case 'F': return 4;
            default: return -1;
        }
    }

    /**
     * Convert period string to index
     */
    private int periodToIndex(String p){
        //ascii arithmetic
        return p.charAt(0)- '1';
    }

    /**
     * main method for testing
     */
    public static void main(String[] args){

        //intialize scanner and week
        Scanner sc = new Scanner(System.in);
        Week week = new Week();

        String cmd = "";
        
        //loop until quit is typed
        while(!cmd.equals("quit")){
            week.print();
            cmd = sc.nextLine();
            
            //another iteration is about to happen
            if(!cmd.equals("quit")){
                //create new week, lay it over the first with merge 
                Week temp = new Week(cmd);
                week.merge(temp);
            }
        }
    }

    /**
     * check if 2 weeks have conflicting days
     */
    public boolean conflicts(Week other){
        //check each slot in the 2d array
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                //both indices have a day mark
                if(this.layout[i][j]=='x' && other.layout[i][j]=='x')
                    return true;
            }
        }
        //no conflict days
        return false;
    }

}
