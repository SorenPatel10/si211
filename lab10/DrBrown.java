import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrBrown extends JPanel {

    private JLabel label;
    private JTextField value;

    public DrBrown(String lab){
        super(new FlowLayout());
        label = new JLabel(lab);
        value = new JTextField(14);
        add(label);
        add(value);
    }

    public String getValue() { return value.getText(); }
    public void setValue(String nv) {  value.setText(nv); }

    // public static void main(String[] args) {
    //     JFrame f = new JFrame();
    //     f.add(new DrBrown("foo"),BorderLayout.WEST);
    //     f.add(new DrBrown("bar"),BorderLayout.EAST);
    //     f.pack();
    //     f.setVisible(true);
   
    // }
}