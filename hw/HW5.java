/*
MIDN 3/C Soren Patel (284932)
SI211 HW05
*/
import java.util.*;

public class HW5{
    public static void main(String[] args){
        
        //create Countdown instance
        Countdown beerCtr = new Countdown(99,0);

        //start current counter
        String curr = beerCtr.next();
        
        //loop until done
        while(!beerCtr.done()){
            
            //case: last bottle; finish
            if(curr.equals("one")){
                System.out.println("One bottle of beer on the wall, one bottle of beer.");
                System.out.println("Take one down and pass it around, no more bottles of beer on the wall.");
                return;
            }

            //case: 2 bottles left; go to last iteration
            else if (curr.equals("two")){
                System.out.println("Two bottles of beer on the wall, two bottles of beer.");
                System.out.println("Take one down and pass it around, one bottle of beer on the wall.\n");
                curr = beerCtr.next();
                continue;
            }

            //case: every other bottle
            System.out.println(curr.substring(0,1).toUpperCase() + curr.substring(1) + " bottles of beer on the wall, " + curr + " bottles of beer.");
            curr = beerCtr.next();
            System.out.println("Take one down and pass it around, " + curr + " bottles of beer on the wall.\n");
        }
    }
}