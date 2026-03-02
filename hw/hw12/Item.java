import java.util.*;

public class Item {
  private String desc;
  private double price;
  private String iid;
  private int count;

  public Item(String iid, int count, String desc, double price) {
    this.iid = iid;
    this.count = count;
    this.desc = desc;
    this.price = price;
  }

  public static Item read(Scanner sc) {
    if (!sc.hasNext())
      return null;
    String i = sc.next();
    int n = sc.nextInt();
    String line = sc.nextLine();
    int k = line.lastIndexOf(" $");
    String d = line.substring(1,k);
    double c = Double.parseDouble(line.substring(k+2));
    return new Item(i,n,d,c);
  }

  public static double totalCost(Cart cart) {
    Queue Q = cart.getQueue();
    double total = 0.0;
    Queue.Iter itr = Q.iterator();
    while(itr.hasNext())
      total += itr.next().price;
    return total;
  }
  
  private String fp() { return String.format("%.2f",price); }
  public String toString() {
    return iid + " " + count + " " + desc + " $" + fp();
  }
  public String custString() { return desc + " $" + fp(); }
  public int getCount() { return count; }
  public void incCount() { count++; }
  public void decCount() { count--; }
  public String getDesc() { return desc; }
  public double getPrice() { return price; }
}
