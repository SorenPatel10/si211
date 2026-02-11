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

    /**
     * helper to check if a course already is in the schedule
     */
    private boolean hasCourse(String course){
        for(int i = 0; i < size; i++){
            //found a section -> ret true
            if(sections[i].getCourse().equals(course))
                return true;
        }
        //no matching course found
        return false;
    }

    /**
     * create a week and merge all the sections into it
     */
    private Week buildWeek(){
        Week w = new Week();
        //add all sections
        for(int i = 0; i < size; i++){
            w.merge(new Week(sections[i].getDays()));
        }
        return w;
    }

    /**
     * check if a section fits in a week
     */
    public boolean fits(Section s){
        //checking for double
        if(hasCourse(s.getCourse()))
            return false;

        Week curr = buildWeek();
        Week next = new Week(s.getDays());

        //if weeks overlap does not fit
        return !curr.conflicts(next);
    }

}