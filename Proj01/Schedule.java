/*
MIDN 3/C Soren Patel (284932)
Proj01
Schedule.java
*/

import java.util.*;

/**
 * Schedule class
 */
public class Schedule{

    //fields: sections array and tracking how many are in it
    private Section[] sections;
    private int size;

    /**
     * Schedule constructor
     */

    public Schedule(){
        sections = new Section[10];
        //this will update as sections are added
        size = 0;
    }

    /**
     * add parameter section to array and update size
     */
    public void add(Section s){
        //checks if room, adds to next spot
        if(size<10){
            sections[size++] = s;
        }
    }

    /**
     * print all sections in the sections array, up to size
     */
    public void printSectionsInSchedule(){
        for(int i = 0; i < size; i++){
            System.out.println(sections[i]);
        }
    }

    /**
     * print current week (creates empty and merges on top)
     */
    public void printWeek(){
        //empty week
        Week week = new Week();

        //add all sections by creating/merging temporary week
        for(int i = 0; i < size; i++){
            Week temp = new Week(sections[i].getDays());
            week.merge(temp);
        }
        week.print();
    }

}