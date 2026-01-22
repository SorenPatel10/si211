/*
MIDN 3/C Soren Patel (284932)
Lab 03
Point.java
*/

import java.util.*;

public class Point{
    
    //fields: coordinates
    private double x;
    private double y;

    //two argument constructor
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    //read and return next point
    public static Point read(Scanner sc){
        return new Point(sc.nextDouble(), sc.nextDouble());
    }

    //getters
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }

    //setters
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }   

    //toString
    public String toString(){
        return this.x + " " + this.y;
    }
}