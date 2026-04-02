import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MidStrip extends Strip {

    private static DrBrown monthsToPayoff = new DrBrown("months to payoff");

    public MidStrip(){
        super();
        add(monthsToPayoff);
        add(new JButton("calculate"));
    
    }

    public static int getMonthsToPayoff(){
        return Integer.parseInt(monthsToPayoff.getText());
    }
}