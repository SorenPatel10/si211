public class Lab1a {
  public static void main(String[] args){
    int[] x = new int[10];
    for(int k = 0; k < 10; k++) {
        x[k] = k*k;
    }
    for (int j = 0; j < 10; j++) {
        System.out.println(j + " squared is " + x[j]);
    }
  }
}

