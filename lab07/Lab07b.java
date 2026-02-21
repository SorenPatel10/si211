/*
MIDN 3/C Soren Patel (284932)
MIDN 3/C Siddharth Swarup (286354)
Lab07
Lab07b.java
*/

import java.util.*;

/**
 * Class Lab07b
 */
public class Lab07b{
    
    /**
     * main method
     */
    public static void main(String[] args) {
        
        int N = 200;
        
        //handle cmd line argument
        if (args.length != 0)
            N = Integer.parseInt(args[0]);

        //create thinglist and add Thing types
        ThingList L = new ThingList();
        int count = 0;
        while (true) {
            // every N rounds add another typeA and typeB Thing
            if (count % N == 0) {
                // add a typeA thing to the list
                ThingA tA = new ThingA(45, 50);
                L.add(tA);

                // add a typeB thing to the list
                ThingA tB = new ThingB(55, 50, 'b');
                L.add(tB);
            }

            // print out each thing
            L.printList();
            System.out.println("done");
            System.out.flush();

            // move each thing
            L.moveEachThing();
            count++;
        }
    }
}