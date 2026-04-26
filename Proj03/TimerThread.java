import javax.swing.*;

public class TimerThread
{
    private JLabel label;

    private volatile boolean running = false;
    private volatile boolean paused = false;

    private int seconds = 0;
    private Thread thread;

    public TimerThread(JLabel label)
    {
        this.label = label;
    }

    public void startTimer()
    {
        if (thread != null && thread.isAlive())
        {
            // already running → just resume
            paused = false;
            return;
        }

        running = true;
        paused = false;

        thread = new Thread(() ->
        {
            while (running)
            {
                if (!paused)
                {
                    seconds++;

                    int m = seconds / 60;
                    int s = seconds % 60;

                    label.setText(String.format("%02d:%02d", m, s));
                }

                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    // ignore
                }
            }
        });

        thread.start();
    }

    public void pauseTimer()
    {
        paused = true;
    }

    public void stopTimer()
    {
        running = false;
    }
}