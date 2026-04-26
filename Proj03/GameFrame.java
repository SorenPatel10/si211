import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    private JLabel message;
    private JButton button;
    private JLabel timerLabel;

    private Board board;

    private boolean running = false;
    private boolean finished = false;

    private TimerThread timer;

    public GameFrame(int[][] ids)
    {
        setTitle("Tile Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Board
        board = new Board(ids, this);

        // ===== TOP PANEL (cleaned layout) =====
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        message = new JLabel("Match the tiles to win!");
        button = new JButton("start");
        timerLabel = new JLabel("00:00");

        // Consistent sizing
        message.setPreferredSize(new Dimension(220, 25));
        button.setPreferredSize(new Dimension(100, 30));
        timerLabel.setPreferredSize(new Dimension(80, 25));

        // Center text
        message.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Padding around top panel
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components
        top.add(message);
        top.add(button);
        top.add(timerLabel);

        // Add to frame
        add(top, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);

        // Timer
        timer = new TimerThread(timerLabel);

        // Button behavior
        button.addActionListener(e -> handleButton());

        pack();
        setLocationRelativeTo(null); // center window on screen
        setVisible(true);
    }

    private void handleButton()
    {
        if (finished)
        {
            System.exit(0);
        }

        if (!running)
        {
            running = true;

            board.setEnabledGame(true);
            message.setText("Hurry up, the clock's running!");
            button.setText("pause");

            timer.startTimer();
        }
        else
        {
            running = false;

            board.setEnabledGame(false);
            message.setText("Game paused");
            button.setText("resume");

            timer.pauseTimer();
        }
    }

    public void gameFinished()
    {
        finished = true;
        running = false;

        board.setEnabledGame(false);

        message.setText("You win!");
        button.setText("exit");

        timer.stopTimer();
    }
}