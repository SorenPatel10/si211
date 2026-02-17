/*
Soren Patel, Sid Swarup
Lab06
FilterTweetQueue.java
*/

import java.util.*;
import si211.*;
import java.io.*;

/**
 * FilterTweetQueue class
 * extends DumpTweetQueue with filtering functionality
 */
public class FilterTweetQueue extends DumpTweetQueue {
    
    //count field keep track of how many elements in queue
    private int count;

    /**
     * Constructor
     */
    public FilterTweetQueue() {
        super();
        this.count = 0;
    }

    /**
     * Override enqueue from Queue
     */
    public void enqueue(Tweet t) {
        //increment count
        count++;
        super.enqueue(t);
    }

    /**
     * Override dequeue from Queue
     */
    public Tweet dequeue(Tweet t) {
        //decrement count
        count--;
        return super.dequeue();
    }

    /**
     * filter method
     * returns FilterTweetQueue with only tweets containing
     * the String specified in the parameter
     */
    public FilterTweetQueue filter(String word) {
        FilterTweetQueue filtered = new FilterTweetQueue();

        //initiate iterator
        TweetQueue.Iter tweetIter = this.iterator();

        //use indexOf() to check if meets filter requiremetns
        while(tweetIter.hasNext()){
            Tweet temp = tweetIter.next();
            if(temp.getText().indexOf(word) >= 0) {
                filtered.enqueue(temp);
            }
        }
        //report how many were kept
        System.out.println(filtered.count + " tweets");
        return filtered;
    }

    /**
     * filterBand method
     * returns FilterTweetQueue with only tweets 
     * not containing the parameter String
     */
    public FilterTweetQueue filterBang(String word) {
        FilterTweetQueue filterBanged = new FilterTweetQueue();

        //initiate iterator
        TweetQueue.Iter tweetIter = this.iterator();

        while(tweetIter.hasNext()){
            Tweet temp = tweetIter.next();
                //indexOf returns -1, means no string found
                if(temp.getText().indexOf(word) < 0) {
                    filterBanged.enqueue(temp);
                }
            }
        System.out.println(filterBanged.count + " tweets");
        return filterBanged;
    }

    /**
     * getter method for count field
     */
    public int getCount() {
        return count;
    }
}