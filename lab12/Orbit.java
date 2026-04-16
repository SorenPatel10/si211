import java.awt.*;
import javax.swing.*;

public class Orbit{
    public static void main(String[] args){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawArea d = new DrawArea();
        f.add(d);
        f.pack();
        f.setVisible(true);
        Thread t = new AniThread(d);
        t.start();

    }
}