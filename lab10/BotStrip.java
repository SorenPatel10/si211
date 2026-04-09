import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BotStrip extends Strip {

    private static DrBrown cost = new DrBrown("cost");

    public BotStrip(){
        super();
        add(cost);    
    }

    public static void setCost(double d){
        cost.setValue(String.format("%.2f",d));
    }
}