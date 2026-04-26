import javax.swing.*;
import java.util.*;
import si211.*;

public class P2
{
    public static void main(String[] args)
    {
        int seed = args.length > 0
                ? Integer.parseInt(args[0])
                : new Random().nextInt();

        int[][] ids = P3Tools.getRandomKindIdAssignments(seed, 18, 6);

        new GameFrame(ids);
    }
}