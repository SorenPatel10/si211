/*
Soren Patel, Sid Swarup
Lab06
DumpTweetQueue.java
*/

import java.util.*;
import si211.*;
import java.io.*;

/**
 * DumpTweetQueue class
 * extends TweetQueue with dump functionality
 */
public class DumpTweetQueue extends TweetQueue{
    
    //Iterator field
    protected TweetQueue.Iter tweetIter;

    /**
     * Empty constructor, intiate with super
     * initiate iterator
     */
    public DumpTweetQueue(){
        super();
        tweetIter = this.iterator();
    }

    /**
     * dump method
     * prints all tweets in the current DumpTweetQueue
     */
    public void dump(){
        TweetQueue.Iter tweetIter = this.iterator();
        //loop until end of queue
        while(tweetIter.hasNext()){
            System.out.println(tweetIter.next());
        }
    }

}