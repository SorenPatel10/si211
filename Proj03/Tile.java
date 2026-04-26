import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import si211.*;
import java.util.ArrayList;

public class Tile extends JPanel implements MouseListener
{
    private Pos pos;
    private int kindID;
    private boolean matched = false;
    private boolean active = false;

    private ArrayList<TileListener> listeners = new ArrayList<>();

    public Tile(Pos pos, int kindID)
    {
        this.pos = pos;
        this.kindID = kindID;

        setPreferredSize(new Dimension(100,100));
        setBackground(P3Tools.getSwatchColor(kindID));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        addMouseListener(this);
    }

    public void addTileListener(TileListener t)
    {
        listeners.add(t);
    }

    public int getKindID()
    {
        return kindID;
    }

    public Pos getPos()
    {
        return pos;
    }

    public boolean isMatched()
    {
        return matched;
    }

    public void setMatched()
    {
        matched = true;
        active = false;
        setBackground(Color.WHITE);
        repaint();
    }

    public void setActive(boolean val)
    {
        if (matched) return;
        active = val;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (matched) return;

        for (TileListener t : listeners)
            t.activated(this);
    }

    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (active && !matched)
        {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(8));
            g2.drawRect(2,2,getWidth()-4,getHeight()-4);
        }
    }
}