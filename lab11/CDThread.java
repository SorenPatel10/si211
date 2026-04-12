import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CDThread extends Thread{
    
    private JLabel label;
    private int sec;

    public CDThread(JLabel label, int sec){
        this.label = label;
        this.sec = sec;
    }

    public void run(){
        try{
            for(int i = sec; i >= 0; i --){
                
                label.setText(String.valueOf(i));
                Thread.sleep(1000);
            }
            
            label.setText("DONE");
        }
        catch(InterruptedException e){
            return;
        }
    }    
}