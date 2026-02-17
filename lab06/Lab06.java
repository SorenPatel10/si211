/*
Soren Patel, Sid Swarup
Lab06
Lab06.java
*/

import java.util.*;
import si211.*;
import java.io.*;

/**
 * Class Lab06
 */
public class Lab06{
    
    /**
     * main method
     */
    public static void main(String[] args){
        
        //error check cmdline
        if(args.length == 0){
            System.out.println("usage: java Lab06 <filename>");
            return;
        }
        
        //read filename and sections
        String fname = args[0];

        //scanner to read user input
        Scanner input = new Scanner(System.in);

        //scanner for file reading
        Scanner sc = null;
        try { sc = new Scanner(new FileReader(fname)); } 
        catch(IOException e) { e.printStackTrace(); System.exit(1); }

        //create TweetQueue and its Iter
        FilterTweetQueue tweets = new FilterTweetQueue();
        int tweetCount = 0;
        
        //read all tweets from file into queue
        while(sc.hasNextLine()){
            
            tweets.enqueue(Tweet.read(sc));
            tweetCount++;
        }

        FilterTweetQueue originalTweets = tweets; 

        //print amt of tweets in file
        System.out.println(tweetCount + " tweets");
        String cmd = "";
        
        //command handling, stop when quit is entered
        while(!cmd.equals("quit")){
            System.out.print("> ");
            cmd = input.next();
            //quit cmd
            if(cmd.equals("quit")) return;

            //dump cmd
            if(cmd.equals("dump")){
                tweets.dump();
                System.out.println(tweets.getCount() + " tweets");
            //filter cmd
            } else if(cmd.equals("filter")) {
                String word = input.next();
                tweets = tweets.filter(word);
            //filter! cmd
            } else if(cmd.equals("filter!")) {
                String word = input.next();
                tweets = tweets.filterBang(word);
            //reset cmd
            } else if(cmd.equals("reset")) {
                System.out.println(tweetCount + " tweets");
                tweets = originalTweets;
                continue;
            }
        }

    }
}