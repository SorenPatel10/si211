import java.io.*;
import java.util.*;
public class Store {
  private Queue inventory = new Queue();

  public void load(String fname) {
      Scanner sc = null;
      try { sc = new Scanner(new FileReader(fname)); }
      catch(IOException e) { e.printStackTrace(); System.exit(1); }
      while(sc.hasNext())
	inventory.enqueue(Item.read(sc));
    }

  public void store(String fname) {
    PrintWriter fout = null;
    try { fout = new PrintWriter(fname); }
    catch(IOException e) { e.printStackTrace(); System.exit(1); }
    while(!inventory.empty())
      fout.println(inventory.dequeue());
    fout.close();
  }
  
  public void printInventory() {
    Queue.Iter itr = inventory.iterator();
    while(itr.hasNext())
      System.out.println(itr.next().custString());
  }

  public Item remove(String desc) {
    Queue.Iter itr = inventory.iterator();
    Item found = null;
    while(itr.hasNext() && found == null) {
      Item tmp = itr.next();
      if (tmp.getDesc().equals(desc))
	found = tmp;
    }
    if (found != null && found.getCount() > 0) {
      found.decCount();
      return found;
    }
    else
      return null;    
  }

  public static void main(String[] args) {
    String fname = "data.txt";
    Store s = new Store();
    s.load(fname);
    s.printInventory();
    Scanner sc = new Scanner(System.in);
    Item i = s.remove(sc.nextLine().trim());
    System.out.println(i == null ? "failed!" : i.toString());
    s.printInventory();
    
  }
  
}

