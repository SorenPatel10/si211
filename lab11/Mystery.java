import javax.swing.*;
import java.awt.event.*;

public class Mystery implements ActionListener
{
  private JLabel label;
  
  public Mystery(JLabel label) {
    this.label = label; 
  }
  public void actionPerformed(ActionEvent e)
  {
    //make thread instance with label
    ChangeColorThread thr = new ChangeColorThread(label);
    //call start -> calls run;
    thr.start();
  }
}