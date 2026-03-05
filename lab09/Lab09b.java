/*
MIDN 3/C Soren Patel
Lab09
Lab09b.java
*/

import java.util.*;
import java.io.*;

/**
 * class Lab09b
 */
public class Lab09b
{

/**
 * main method
 * @param args
 */
  public static void main(String[] args)
  {
    //set verbose flags
    boolean verbose = args.length > 0 && args[0].equals("-v");

    //input scanner, flag to see if reading from file or not
    Scanner sc = null;
    boolean fromFile = false;

    //case: first argument is file name
    if (args.length > 0 && !args[0].equals("-v")){
      try {
        //open file input
        sc = new Scanner(new FileReader(args[0]));
        fromFile = true;
      }
      catch(IOException e){
        //switch to stdin if file cant be opened
        System.out.println("File '" + args[0] + "' could not be opened; switching input to standard in.");
        sc = new Scanner(System.in);
      }
    }
    //case: verbose flag then filename
    else if (args.length > 1 && args[0].equals("-v")){
      try {
        //open file input
        sc = new Scanner(new FileReader(args[1]));
        fromFile = true;
      }
      catch(IOException e){
        //switch to stdin if file cant be opened
        System.out.println("File '" + args[1] + "' could not be opened; switching input to standard in.");
        sc = new Scanner(System.in);
      }
    }
    //using stdin since no filename provided
    else{
      sc = new Scanner(System.in);
    }

    //create modified queue
    ModQueue Q = new ModQueue();

    //main loop to handle commands
    while(true){
        //whether or not to print carrot only if reading stdin
        if(!fromFile)
        System.out.print("> ");

        //check if input is over
        if(!sc.hasNext())
        break;

        //get command from user
        String cmd = sc.next();
    
      try{
        //cmd quit, end program
        if(cmd.equals("quit"))
          break;
        //cmd add, enqueue the given string
        else if(cmd.equals("add")){
          String s = sc.next();
          Q.enqueue(s);
        }
        //cmd clearto, remove items up through the given string
        else if(cmd.equals("clearto")){
          String s = sc.next();
          try{
            Q.dequeue(s);
          }
          //string not found in queue
          catch(QueueException e){
            //only print message in verbose
            if(verbose)
              System.out.println("String '" + s + "' not found!");
            //dequeue everything if string not found
            try{
              while(true)
                Q.dequeue();
            }
            catch(QueueException ex){

            }
          }
        }

        //cmd dump, print all queue elements
        else if(cmd.equals("dump")){
          try{
            System.out.println(Q.dump());
          }
          //ignore if queue is empty
          catch(QueueException e){

          }
        }
        //cmd unknowns
        else{
          if(verbose)
            System.out.println("Unknown command '" + cmd + "'.");
        }

      }
      //if input ends while reading command arguments
      catch(NoSuchElementException e){
        System.out.println("Unexpected end of input.");
        break;
      }
    }
  }
}