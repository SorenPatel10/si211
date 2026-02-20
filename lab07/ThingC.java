import java.util.*;

public class ThingC extends ThingA {
    
    private boolean startRight = true;
    private boolean goRight;
    private int steps = 0;
    private final int STEPS_PER_DIR = 10;
    
    public ThingC(int row, int col, char lab) {
        super(row,col);
        this.lab = lab;
        this.goRight = startRight;
    }

    public void maybeTurn() {
        if(this.goRight)
            this.rightTurn();
        else
            this.leftTurn();
        
        this.goRight = !this.goRight;
        this.steps++;

        if(this.steps >= STEPS_PER_DIR){
            this.steps = 0;
            this.startRight = !this.startRight;
            this.goRight = this.startRight;
        }
    }
}