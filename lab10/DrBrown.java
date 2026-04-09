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
}