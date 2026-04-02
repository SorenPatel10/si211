import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calc{

    public static void main(String[] args){
        JFrame f = new JFrame();
        Strip s1 = new TopStrip();
        Strip s2 = new MidStrip();
        Strip s3 = new BotStrip(); 
        f.add(s1, BorderLayout.NORTH);
        f.add(s2, BorderLayout.CENTER);
        f.add(s3, BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);
    }
}