public class AniThread extends Thread {

    private DrawArea drawArea;

    public AniThread(DrawArea d) {
        drawArea = d;
    }

    public void run() {
        while (true) {

            drawArea.updateAnimation();
            drawArea.repaint();
            try {
                Thread.sleep(20);
            } catch (Exception e) {

            }
        }
    }
}