import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CBn extends JButton implements ActionListener,WindowListener
{
  private int  count = 0;
  public CBn(String label)
  { 
    super(label); 
    this.addActionListener(this);
  }
  public void actionPerformed(ActionEvent e)
  {
    count++;
  }
  //handle window closing, print click count
  public void windowClosing(WindowEvent e){
    System.out.println("Button clicked " + count + " times.");
    System.exit(0);
  }

    //empty implementations of the other methods to fulfill interface contract
    //list of methods found in online java documentation
    public void windowActivated(WindowEvent e){}
    public void windowClosed(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowOpened(WindowEvent e){}

}
