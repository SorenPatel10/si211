import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Finance{

    public static void calcInterest(){

        double balance = TopStrip.getLoan();
        double rate = TopStrip.getRate();
        double monthlyPay = TopStrip.getMonthlyPayment();

        double totalPaid = 0.0;
        double overpayment = 0.0;
        int months = 0;

        while(balance > 0){

            double interest = balance * rate / 1200.0;
            balance += interest;

            balance -= monthlyPay;
            totalPaid += monthlyPay;

            months++;

            if(balance <= 0){
                overpayment = -balance;
                break;
            }
        }

        double originalLoan = TopStrip.getLoan();

        double cost = totalPaid - overpayment - originalLoan;

        BotStrip.setCost(cost);
        MidStrip.setMonthsToPayoff(months);
    }
}