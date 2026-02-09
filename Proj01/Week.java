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
        String[] meetings = DrBrown.explode(pattern);
        
        for(String m : meetings){
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
        for(int i = 0; i < 6; i++){
            System.out.print((i+1) + " ");
            for(int j = 0; j < 5; j++){
                System.out.print(layout[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Convert day character to index
     */
    private int dayToIndex(char day){
        //I researched how to use switch-case
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
     * Convert period string to index (0-5)
     * Handles "1","2",...,"6"
     * DrBrown.explode() guarantees single-period strings
     */
    private int periodToIndex(String p){
        try{
            int num = Integer.parseInt(p);
            if(num >= 1 && num <= 6) return num - 1;
        }catch(NumberFormatException e){
            //ignore
        }
        return -1;
    }

    /**
     * main method for testing
     */
    public static void main(String[] args){
        
        //intialize scanner and week
        Scanner sc = new Scanner(System.in);
        Week week = new Week();
        
        while(true){
            week.print();
            String line = sc.nextLine().trim();
            if(line.equalsIgnoreCase("quit")) break;
            if(line.isEmpty()) continue;
            Week temp = new Week(line);
            week.merge(temp);
        }
    }
}
