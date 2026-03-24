import java.util.*;
import java.io.*;

public class HW1
{
  public static void main(String[] args)
  {
    
    LineNumberReader reader = null;
    try{
      if(args.length > 0){
        try{
          reader = new LineNumberReader(new FileReader(args[0]));
        }
        catch(FileNotFoundException f){
          System.out.println("File \""+ args[0] + "\" not found");
          return;
        }
      }
      else
        reader = new LineNumberReader(new InputStreamReader(System.in));

      Scanner sc = new Scanner(reader);
      try {
        System.out.println(Mystery.compute(sc));
      }
      catch(Exception e)
      {
        System.out.println("Error " + e.getMessage() + " at line " + (reader.getLineNumber()));
      }
    }
  catch(Exception e){
    System.out.println("Error " + e.getMessage());
  }

    
  }
}