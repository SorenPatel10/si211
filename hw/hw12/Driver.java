import java.util.*;

public class Driver {

  public static void main(String[] args) {
    Store store = new Store();
    store.load("data.txt");
    Cart cart = new Cart();
    
    Scanner sc = new Scanner(System.in);
    System.out.println("commands are: " +
       "inv, add, checkout, view, quit");
    while(true) {
      System.out.print("> ");
      String comm = sc.next();
      if (comm.equals("inv"))
	store.printInventory();
      else if (comm.equals("add")) {
	String d = sc.nextLine().trim();
	Item it = store.remove(d);
	if (it == null)
	  System.out.println("Out of " + d);
	else
	  cart.add(it);
      }
      else if (comm.equals("view")) {
	cart.printContents();
	System.out.println("----------------------");
	double total = Item.totalCost(cart);
	System.out.printf("total: $%.2f\n",total);
      }
      else if (comm.equals("checkout")) {
	cart.buy();
      }
      else if (comm.equals("quit")) {
	cart.returnAll();
	break;
      }
      else {
	System.out.println("Unknown: " + comm);
      }
    }
    store.store("dataNEW.txt");
    System.out.println("Wrote: dataNEW.txt");
  }
}
