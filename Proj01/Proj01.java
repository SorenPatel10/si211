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
        
        //read filename and 11 sections
        String fname = args[0];
        Section[] sections = readSections(fname, 11);

        //scanner to read user input
        Scanner input = new Scanner(System.in);
        
        //read commands until quit
        String cmd = "";
        while(!cmd.equals("quit")){
            System.out.print("> ");
            cmd = input.next();
            
            //sections is typed
            if(cmd.equals("sections")){
                if(!input.hasNext())
                    continue;
                String courseNumber = input.next();
                
                //loop through sections and print matching ones
                for(Section temp: sections){
                    if(temp.getCourse().equals(courseNumber))
                        System.out.println(temp);
                }
            }
            //invalid command
            else if(!cmd.equals("quit")){
                System.out.println("Unknown command: " + cmd);
            }
        }

    }

    /**
     * readSection method to return an array of
     * sections from a file with specified length
     */
    private static Section[] readSections(String fname, int num){
        Section[] sections = new Section[num];
        
        //create and error check scanner
        Scanner sc = null;
        try { sc = new Scanner(new FileReader(fname)); } 
        catch(IOException e) { e.printStackTrace(); System.exit(1); }

        int index = 0;

        //loop until specified amount of sections are read 
        while(sc.hasNextLine() && index < num){
            String currLine = sc.nextLine();
            
            //split by tab, initialize sections array
            String[] words = currLine.split("\t");
            sections[index++] = new Section(words[0], words[1], words[2], words[3]);
        }

        sc.close();
        return sections;
    }
}