import java.util.*;


public class Lab1b {
  
  public static int factorial(int n){
    if (n == 0 || n == 1)
            return 1;
    return n * factorial(n - 1);
  }

  public static int calcNum(int n, int k){
    int numerator = 1;
    for(int i = n; i > n-k; i--){
        numerator *= i;
    }
    return numerator;
  }
  public static int calcDenom(int k){
    int denominator = 1;
    
    while(k > 0){
        denominator *= k;
        k--;
    }
    return denominator;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter your name: ");
    String name = in.nextLine();
    System.out.print("Please input an integer ");
    int n = in.nextInt();
    System.out.print("Please input a second integer ");
    int k = in.nextInt();
    System.out.println("The two ints were " + n + " and " + k);

    int numerator = calcNum(n,k);
    int denominator = calcDenom(k);
    
    System.out.println("numerator = " + numerator);
    System.out.println("denominator = " + denominator);

    int odds = numerator/denominator;
    System.out.println("odds = 1 in " + odds + " = " + (1/(double)odds));
    System.out.println("Goodbye " + name + ".");
  }
}