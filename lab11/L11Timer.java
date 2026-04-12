import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class L11Timer{
    public static void main(String[] args){
        
        JFrame frame = new JFrame("Lab 11 - Timer");
        frame.setLayout(new BorderLayout());

        frame.add(new TimerPanel("DONE", "10", "countdown"), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}