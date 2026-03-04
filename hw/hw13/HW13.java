import java.util.*;

public class HW13
{
  public static void main(String[] args)
  {
    // setup
    boolean verbose = args.length > 0 && args[0].equals("-v");
    Scanner sc = new Scanner(System.in);
    
    try{
        if (verbose)
        System.out.print("Enter char c and ints k m: ");

        // read input
        char c = sc.next().charAt(0);
        int k = sc.nextInt();
        int m = sc.nextInt();

        // computer special value
        char d = CharStuff.foo(c,k,m);
        if (d > 127 || d <= 32)
        d = (char)(d/5 + 40);    

        // print results
        if (verbose)
        System.out.printf("encoding of %c with %d:%d = ",c,k,m);
        System.out.println(d);
    }
    catch(Throwable e){
        if(verbose)
            System.out.println("Error in HW13! invalid input.");
    }
  }
}