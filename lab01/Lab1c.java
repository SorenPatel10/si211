import java.util.*;


public class Lab1c {
  
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
    
    String name = "";
    for(int i = 0; i < args.length-2; i++){
        if(i == args.length - 3)
            name += args[i];
        else
            name += args[i] + " ";
    }

    int n = Integer.parseInt(args[args.length-2]);
    int k = Integer.parseInt(args[args.length-1]);

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