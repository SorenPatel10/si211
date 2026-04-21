public class Orbiter {

    private int centerX;
    private int centerY;
    private int radius;
    private double angle;
    private double speed;

    public Orbiter(int cx, int cy, int r, double s) {
        centerX = cx;
        centerY = cy;
        radius = r;
        speed = s;
        angle = 0.0;
    }

    public void update() {
        angle += speed;
    }

    public int getX() {
        return (int)(centerX + radius * Math.cos(angle));
    }

    public int getY() {
        return (int)(centerY + radius * Math.sin(angle));
    }

    public void setCenter(int x, int y) {
        centerX = x;
        centerY = y;
    }
}