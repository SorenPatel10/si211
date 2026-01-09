/*
MIDN 3/C Soren Patel (284932)
SI211
HW02
*/

import java.util.*;

public class HW02{
    
    //method returns length of longest string in string arr
    public static int maxLength(String[] A){
        int max = 0;
        //compare each new to previous
        for(String temp: A){
            if(temp.length() > max){
                max = temp.length();
            }
        }
        return max;
    }

    //main method
    public static void main(String[] args){

        //create scanner, read in num
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt();
        String[] words = new String[n];

        //intialize array with input
        for(int i = 0; i < n; i++){
            words[i] = myScanner.next();
        }

        int max = maxLength(words);

        //outer loop goes until max string length
        for(int i = 0; i < max; i++){
            //inner loop is the number of words
            for(int j = 0; j < n; j++){
                
                //print string char
                if(i < words[j].length())
                    System.out.print(words[j].charAt(i));
                //endspace for strings shorter than max
                else
                    System.out.print(" ");
                
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}