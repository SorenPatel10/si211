/*
MIDN 3/C Soren Patel (284932)
Lab 03
Box.java
*/

import java.util.*;

public class Box{
    
    //fields, lower left corner and upper right corner
    private Point lowerLeft;
    private Point upperRight;

    //single argument constructor
    public Box(Point p){
        this.lowerLeft = new Point(p.getX(), p.getY());
        this.upperRight = new Point(p.getX(), p.getY());
    }

    //two argument constructor
    public Box(Point a, Point b){
        
        //extract min/max x and y values
        double minX = Math.min(a.getX(), b.getX());
        double minY = Math.min(a.getY(), b.getY());
        double maxX = Math.max(a.getX(), b.getX());
        double maxY = Math.max(a.getY(), b.getY());

        this.lowerLeft = new Point(minX, minY);
        this.upperRight = new Point(maxX, maxY);
    }

    //grow the box if point is outside current bounds
    public void growBy(Point p){
        
        //x too low
        if(p.getX() < lowerLeft.getX())
            lowerLeft.setX(p.getX());
        //x too high
        if(p.getX() > upperRight.getX())
            upperRight.setX(p.getX());
        //y too low
        if(p.getY() < lowerLeft.getY())
            lowerLeft.setY(p.getY());
        //y too high
        if(p.getY() > upperRight.getY())
            upperRight.setY(p.getY());
    }

    //map point using formula
    public Point mapIntoUnitSquare(Point p){
        
        //this only applies for points in the box
        if(!isInBox(p))
            return null;
        
        //formula application
        double width = upperRight.getX() - lowerLeft.getX();
        double height = upperRight.getY() - lowerLeft.getY();
        double newX = (p.getX() - lowerLeft.getX()) / width;
        double newY = (p.getY() - lowerLeft.getY()) / height;

        return new Point(newX, newY);
    }

    //method to check if a point is in the box
    private boolean isInBox(Point p){
        return (p.getX() >= lowerLeft.getX()) && (p.getX() <= upperRight.getX())
                && (p.getY() >= lowerLeft.getY()) && (p.getY() <= upperRight.getY());
    }

    //toString
    public String toString(){
        return lowerLeft.getX() + " < x < " + upperRight.getX() + ", " + lowerLeft.getY() + " < y < " + upperRight.getY();
    }
}