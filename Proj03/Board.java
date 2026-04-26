import javax.swing.*;
import java.awt.*;
import si211.*;

public class Board extends JPanel implements TileListener
{
    private Tile first = null;
    private boolean enabled = false;
    private GameFrame frame;

    public Board(int[][] ids, GameFrame frame)
    {
        this.frame = frame;

        int N = ids.length;
        setLayout(new GridLayout(N,N));

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                Tile t = new Tile(new Pos(i,j), ids[i][j]);
                // Tile t = new PolygonTile(new Pos(i,j), ids[i][j]);
                t.addTileListener(this);
                add(t);
            }
        }
    }

    public void setEnabledGame(boolean val)
    {
        enabled = val;
    }

    @Override
    public void activated(Tile t)
    {
        if (!enabled || t.isMatched())
            return;

        // 🔥 FIX: clicking same tile twice
        if (first == t)
        {
            t.setActive(false);
            System.out.println("Tile " + t.getPos() + " deactivated");

            first = null;
            return;
        }

        // first selection
        if (first == null)
        {
            first = t;
            t.setActive(true);
            System.out.println("Tile " + t.getPos() + " activated");
            return;
        }

        // second selection
        Tile second = t;
        second.setActive(true);

        System.out.println("Tile " + second.getPos() + " activated");

        if (first.getKindID() == second.getKindID())
        {
            System.out.println("Tile " + first.getPos() + " matched");
            System.out.println("Tile " + second.getPos() + " matched");

            first.setMatched();
            second.setMatched();

            checkGameOver();
        }
        else
        {
            System.out.println("Tile " + first.getPos() + " deactivated");
            System.out.println("Tile " + second.getPos() + " deactivated");
        }

        first.setActive(false);
        second.setActive(false);
        first = null;
    }

    private void checkGameOver()
    {
        Component[] comps = getComponents();

        for (Component c : comps)
        {
            if (c instanceof Tile)
            {
                if (!((Tile)c).isMatched())
                    return;
            }
        }

        frame.gameFinished();
    }

    @Override
    public void deactivated(Tile t) {}
}