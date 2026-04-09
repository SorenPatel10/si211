import javax.swing.*;
import java.awt.event.*;

public class Mystery implements ActionListener
{
  private JLabel label;
  public Mystery(JLabel label) { this.label = label; }
  public void actionPerformed(ActionEvent e)
  {
    CChange.changeColor(label);
  }
}