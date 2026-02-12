import java.util.*;
import si211.*;
import java.io.*;

public class FilterTweetQueue extends DumpTweetQueue {
    private int count;

    public FilterTweetQueue() {
        super();
        this.count = 0;
    }

    public void enqueue(Tweet t) {
        count++;
        super.enqueue(t);
    }

    public Tweet dequeue(Tweet t) {
        count--;
        return super.dequeue();
    }



    public FilterTweetQueue filter(String word) {
        FilterTweetQueue filtered = new FilterTweetQueue();

        TweetQueue.Iter tweetIter = this.iterator();

        while(tweetIter.hasNext()){
            Tweet temp = tweetIter.next();
            if(temp.getText().indexOf(word) >= 0) {
                filtered.enqueue(temp);
            }
        }
        System.out.println(filtered.count + " tweets");
        return filtered;
    }

    public FilterTweetQueue filterBang(String word) {
        FilterTweetQueue filterBanged = new FilterTweetQueue();

        TweetQueue.Iter tweetIter = this.iterator();

        while(tweetIter.hasNext()){
            Tweet temp = tweetIter.next();
                if(temp.getText().indexOf(word) < 0) {
                    filterBanged.enqueue(temp);
                }
            }
        System.out.println(filterBanged.count + " tweets");
        return filterBanged;
    }



    public int getCount() {
        return count;
    }
}