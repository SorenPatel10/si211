import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeColorThread extends Thread{
    
    private JLabel label;

    public ChangeColorThread(JLabel label){
        this.label = label;
    }

    public void run(){
        CChange.changeColor(label);
    }
}