public class CharStuff {
  public static char foo(char c, int k, int m) throws Throwable{
    int x = (int)c;
    int r = MyMath.modexp(x,k,m);
    char d = (char)(r % 256);
    return d;
  }

}