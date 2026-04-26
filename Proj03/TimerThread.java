/**
 * Soren Patel
 * Proj03
 * TimerThread.java
 */

import javax.swing.*;

/**
 * class to manage timer
 * implements runnable to separate timer logic from thread execution
 */
public class TimerThread implements Runnable{
    //label to show time in mm:ss
    private JLabel label;
    //volatile ensures atomic operations so they show across threads always
    private volatile boolean running = false;
    private volatile boolean paused = false;

    private int seconds = 0;
    
    /**
     * timerthread constructor
     */
    public TimerThread(JLabel label){
        this.label = label;
    }

    /**
     * thread main loop called with start()
     */
    public void run(){
        //go until game stops
        while (running){
            if (!paused){
                //increment seconds
                seconds++;
                int m = seconds / 60;
                int s = seconds % 60;
                //show formatted string.
                label.setText(String.format("%02d:%02d", m, s));
            }
            
            //potential catch of InterruptedException (sleep error)
            try{
                //sleep 1sec
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
            }
        }
    }

    /**
     * starts timer by creating new thread
     */
    public void startTimer(){
        //resume and continue if paused
        if (running){
            paused = false;
            return;
        }

        //ensure not paused
        running = true;
        paused = false;
        //start thread
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * pause timer (stops incrementing time)
     */
    public void pauseTimer(){
        paused = true;
    }

    /**
     * stop timer (cannot resume)
     */
    public void stopTimer(){
        running = false;
        paused = false;
    }

    /**
     * Reset time counter labels and seconds val
     */
    public void reset() {
        seconds = 0;
        label.setText("00:00");
    }

    /**
     * seconds getter
     */
    public int getSeconds(){
        return seconds;
    }
}