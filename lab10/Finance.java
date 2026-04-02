import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Finance{

    public static void calcInterest(){
        double interest = TopStrip.getLoan();
        double rate = TopStrip.getRate();
        double monthlyPay = TopStrip.getMonthlyPayment();
        double monthsToPayoff = MidStrip.getMonthsToPayoff();
        double ans = 0.0;

        BotStrip.setCost(ans);
    }
}