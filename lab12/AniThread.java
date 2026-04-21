public class AniThread extends Thread {

    private DrawArea drawArea;
    private boolean running = false;

    public AniThread(DrawArea d) {
        drawArea = d;
    }

    public void setRunning(boolean r) {
        running = r;
    }

    public void run() {
        while(true) {
            if(running) {
                drawArea.updateAnimation();
                drawArea.repaint();
            }
            try{
                Thread.sleep(20);
            } catch(Exception e) {
            }
        }
    }
}