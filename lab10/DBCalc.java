public class DBCalc{
  int months;
  double cost;

  public DBCalc() { }

  public int getMonths() { return months; }
  public double getCost() { return cost; }
  
  public void calculate(double balance, double rate, double monthlyPay)  {
    double totalPaid = 0.0;
    double overpayment = 0.0;
    double originalLoan = balance;
    months = 0;
    
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
    cost = totalPaid - overpayment - originalLoan;
  }

  public static void main(String[] args) {
    double b = Double.parseDouble(args[0]);
    double r = Double.parseDouble(args[1]);
    double p = Double.parseDouble(args[2]);
    DBCalc f = new DBCalc();
    f.calculate(b,r,p);
    System.out.println("months = " + f.getMonths() + " cost = " + f.getCost());
  }
}
