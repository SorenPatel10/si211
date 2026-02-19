import java.util.*;

public class ThingA {

    protected static Random rand = new Random(System.currentTimeMillis());
    // dir: 0=North, 1=East, 2=South, 3=West.
    // timeSinceLast: this is only important for "TypeB" Things.
    private int row, col, dir;
    protected char lab;

    public ThingA(int row, int col) {
        this.row = row;
        this.col = col;
        this.dir = 0;
        this.lab = 'r';
    }

    public void rightTurn() {
        this.dir = (this.dir + 1) % 4;
    }

    public void leftTurn() {
        this.dir = (this.dir + 3) % 4;
    }

    public void maybeTurn() {
        int i = rand.nextInt(3);
        if (i == 1)
            this.rightTurn();

        if (i == 2)
            this.leftTurn();
    }

    public void step() {
        final int[] dc = { 0, 1, 0, -1 }, dr = { -1, 0, 1, 0 };
        this.row += dr[this.dir];
        this.col += dc[this.dir];
    }

    public String toString(){
        return row + " " + col + " " + lab;
    }


}