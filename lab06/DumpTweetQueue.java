import java.util.*;
import si211.*;
import java.io.*;

public class DumpTweetQueue extends TweetQueue{
    protected TweetQueue.Iter tweetIter;

    public DumpTweetQueue(){
        super();
        tweetIter = this.iterator();
    }

    public void dump(){
        TweetQueue.Iter tweetIter = this.iterator();
        while(tweetIter.hasNext()){
            System.out.println(tweetIter.next());
        }
    }

}