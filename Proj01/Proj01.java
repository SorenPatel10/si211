/*
MIDN 3/C Soren Patel (284932)
Proj01
Proj01.java
*/

import java.util.*;
import java.io.*;

/**
 * Proj01 class
 */
public class Proj01{
    
    /**
     * main method
     */
    public static void main(String[] args){
        
        //error check cmdline
        if(args.length == 0){
            System.out.println("usage: java Proj01 <sectionsfile>");
            return;
        }
        
        //read filename and sections
        String fname = args[0];
        SectionList sections = readSections(fname);

        //scanner to read user input
        Scanner input = new Scanner(System.in);

        //create schedule
        Schedule sched = new Schedule();
        
        //read commands until quit
        String cmd = "";
        while(!cmd.equals("quit")){
            System.out.print("> ");
            cmd = input.next();
            
            //sections command
            if(cmd.equals("sections")){
                if(!input.hasNext())
                    continue;
                String courseNumber = input.next();
                
                //print matching sections
                sections.printByCourse(courseNumber);
            }
            //add command
            else if(cmd.equals("add")){
                
                //take user input and query list
                String course = input.next();
                String sec = input.next();
                Section temp = sections.find(course,sec);

                //either add to schedule or give error message
                if(temp==null)
                    System.out.println("Error! Section not found!");
                else
                    sched.add(temp);
            }
            //show comand
            else if(cmd.equals("show")){
                sched.printSectionsInSchedule();
            }
            //invalid command
            else if(!cmd.equals("quit")){
                System.out.println("Unknown command: " + cmd);
            }
        }
    }

    /**
     * readSections method to return a unknown size
     * list of sections from the given file
     */
    private static SectionList readSections(String fname){
        SectionList sections = new SectionList();
        
        //create and error check scanner
        Scanner sc = null;
        try { sc = new Scanner(new FileReader(fname)); } 
        catch(IOException e) { e.printStackTrace(); System.exit(1); }

        //loop until last line
        while(sc.hasNextLine()){
            String currLine = sc.nextLine();
            
            //split by tab, add to list
            String[] words = currLine.split("\t");
            sections.add(new Section(words[0], words[1], words[2], words[3]));
        }

        sc.close();
        return sections;
    }
}
