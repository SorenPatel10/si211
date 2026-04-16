import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerPanel extends JPanel implements ActionListener{

    //has a label, textfield, and button
    private JLabel label;
    private JTextField textField;
    private JButton button;

    public TimerPanel(String labTxt, String fieldTxt, String butTxt){
        setLayout(new FlowLayout());

        label = new JLabel(labTxt);
        label.setForeground(Color.RED);
        label.setPreferredSize(new Dimension(60,15));

        textField = new JTextField(10);
        textField.setText(fieldTxt);

        button = new JButton(butTxt);
        button.addActionListener(this);

        add(label);
        add(textField);
        add(button);
    }

    public void actionPerformed(ActionEvent e){
        int sec;
        

        try{
            sec = Integer.parseInt(textField.getText());
            if(sec <= 0)
                throw new NumberFormatException();
        
        }
        catch(NumberFormatException nfe){
            label.setText("ERROR");
            return;
        }

        CDThread thr = new CDThread(label,sec);
        thr.start();

    }
}