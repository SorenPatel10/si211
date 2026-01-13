/*
MIDN 3/C Soren Patel (284932)
SI211 HW3
*/

import java.util.*;

public class HW3{
    
    public static Mid createMid(Scanner in){
        //create a temp new mid, read in values, return it
        Mid temp = new Mid();
        temp.alpha = in.next();
        temp.firstName = in.next();
        temp.lastName = in.next();
        temp.company = in.nextInt();
        return temp;
    }

    public static void printMid(Mid m){
        //print out mid information with required format
        System.out.println(m.alpha + " " + m.firstName + " " + m.lastName + " " + m.company);
    }

    public static void main (String[] args){
        //create scanner object
        Scanner in = new Scanner(System.in);
        int numMids = in.nextInt();
        
        //define mid array
        Mid[] mids = new Mid[numMids];

        //initialize mid array
        for(int i = 0; i < numMids; i++){
            mids[i] = createMid(in);
        }

        int match = in.nextInt();

        //print mids whos company matches
        for(Mid temp: mids){
            if (temp.company == match)
                printMid(temp);
        }
    }
}