/*
MIDN 3/C Soren Patel (284932)
Lab 03
Lab03.java
*/

import java.util.*;

public class Lab03{
    public static void main(String[] args){
        
        //create scanner, read command, initialize box
        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        Box b = null;

        //loop until "done" is entered
        while(!command.equals("done")){
            
            //command box
            if(command.equals("box")){
                System.out.println(b);
            }

            //command map
            else if(command.equals("map")){
                Point curr = Point.read(sc);

                Point temp = b.mapIntoUnitSquare(curr);
                
                //if null, error. else print mapped point
                if(temp == null){
                    System.out.println("error");
                }
                else
                    System.out.println(temp);
            }

            //command add
            else if(command.equals("add")){
                Point curr = Point.read(sc);

                //if box still null, use one argument constructor. else grow box
                if(b == null)
                    b = new Box(curr);
                else
                    b.growBy(curr);
            }

            //command unknown
            else{
                System.out.println("Error! Unknown command \"" + command + "\"!");
            }

            //read next command
            command = sc.next();
        }
    }
}