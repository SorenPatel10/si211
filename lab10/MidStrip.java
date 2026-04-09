import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MidStrip extends Strip {

    private static DrBrown monthsToPayoff = new DrBrown("months to payoff");

    public MidStrip(){
        super();
        add(monthsToPayoff);

        JButton calcButton = new JButton("calculate");
        calcButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Finance.calcInterest();
            }
        });
        add(calcButton);
    
    }

    public static void setMonthsToPayoff(int m){
        monthsToPayoff.setValue(String.valueOf(m));
    }
}