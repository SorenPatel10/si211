/*
MIDN 3/C Soren Patel (284932)
Proj01
Section.java
*/

import java.util.*;

/**
 * Section class
 */
public class Section{
    
    //relevant fields
    private String course;
    private String sec;
    private String days;
    private String rooms;

    /**
     * Section constructor
     */
    public Section(String course, String sec, String days, String rooms){
        this.course = course;
        this.sec = sec;
        this.days = days;
        this.rooms = rooms;
    }

    /**
     * Getters for each field
     */
    public String getCourse(){
        return course;
    }
    public String getSec(){
        return sec;
    }
    public String getDays(){
        return days;
    }
    public String getRooms(){
        return rooms;
    }

    /**
     * toString for print formatting
     */
    public String toString(){
        return course + " " + sec + " " + days + " " + rooms;
    }
}