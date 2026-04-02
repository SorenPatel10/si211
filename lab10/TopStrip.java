import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TopStrip extends Strip {

    private static DrBrown loanAmount = new DrBrown("loan amount");
    private static DrBrown monthlyPayment = new DrBrown("monthly payment");
    private String interTxt = "interest rate";
    private static JComboBox <Double> cbox = new JComboBox<Double>(new Double[]{3.50, 3.75, 4.00, 4.25, 4.50, 4.75, 5.00, 5.25, 
            5.50, 5.75, 6.00, 6.25, 6.50, 6.75, 7.00, 7.25, 7.50});

    public TopStrip(){
        super();
        
        add(loanAmount);

        add(new JLabel(interTxt));
        add(cbox);
        
        add(monthlyPayment);
    
    }

    public static double getLoan(){
        return Double.parseDouble(loanAmount.getText());
    }
    public static int getMonthlyPayment(){
        return Integer.parseInt(monthlyPayment.getText());
    }
    public static double getRate(){
        return (double)cbox.getSelectedIndex();
    }
}