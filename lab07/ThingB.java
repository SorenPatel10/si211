import java.util.*;

public class ThingB extends ThingA {
    
    private int timeSinceLast;
    
    public ThingB(int row, int col, char lab) {
        super(row,col);
        this.lab = lab;
        this.timeSinceLast = 0;
    }

    public void maybeTurn() {
        int i = rand.nextInt(3);
        this.timeSinceLast++;
        if (this.timeSinceLast == 10) {
            this.timeSinceLast = 0;
            if (i == 1)
                this.rightTurn();

            if (i == 2)
                this.leftTurn();
        }
    }
}