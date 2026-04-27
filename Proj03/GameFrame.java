/**
 * Soren Patel
 * Proj03
 * GameFrame.java
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import si211.*;

/**
 * overall frame with all components
 */
public class GameFrame extends JFrame implements GameListener{
    
    //all elements on the frame (buttons, labels, board)
    private JLabel message;
    private JButton startPauseBtn;
    private JButton scoreBtn;
    private JButton resetBtn;
    private JLabel timerLabel;
    private Board board;
    //flags to determine whether game is currently running or is finished
    private boolean running = false;
    private boolean finished = false;
    //thread for timing
    private TimerThread timer;
    //storing scores
    private HighScoreManager scoreManager = new HighScoreManager();

    /**
     * gameframe constructor
     */
    public GameFrame(int[][] ids){
        setTitle("Proj03 - Tile Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        board = new Board(ids, this);

        //top panel with a flowlayout to manage buttons/labels
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        //starting component values
        message = new JLabel("Get ready to play!");
        timerLabel = new JLabel("00:00");
        startPauseBtn = new JButton("start");
        scoreBtn = new JButton("scores");
        resetBtn = new JButton("reset");

        //sizing/spacing and margin adjustment
        message.setPreferredSize(new Dimension(220,25));
        timerLabel.setPreferredSize(new Dimension(80,25));
        top.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        //add components
        top.add(message);
        top.add(startPauseBtn);
        top.add(scoreBtn);
        top.add(resetBtn);
        top.add(timerLabel);
        //add panels to frame
        add(top, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);

        //initialize timer thread
        timer = new TimerThread(timerLabel);

        //start pause button using anonmous inner class
        startPauseBtn.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                handleStartPause();
            }
        });

        //score button using anaonymous inner class
        scoreBtn.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                HighScoreWindow.showScores(GameFrame.this, scoreManager);
            }
        });

        //reset button using anonymous inner class
        resetBtn.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                resetGame();
            }
        });

        //pack components and show frame
        pack();
        setVisible(true);
    }

    /**
     * logic for start pause button
     */
    private void handleStartPause(){
        
        //button becomes an exit button
        if(finished)
            System.exit(0);

        //start game
        if(!running){
            running = true;

            //change labels accordingly
            board.setEnabledGame(true);
            message.setText("Hurry up!");
            startPauseBtn.setText("pause");
            //start timer with new thread
            timer.startTimer();
        }
        //pause game
        else{
            running = false;
            
            //change labels accordingly
            board.setEnabledGame(false);
            message.setText("Paused");
            startPauseBtn.setText("resume");
            //pause timer
            timer.pauseTimer();
        }
    }

    /**
     * manage labels once game is done and save score
     */
    public void gameFinished(){
        //change flags to indicate game done
        finished = true;
        running = false;
        board.setEnabledGame(false);

        message.setText("You win!");
        startPauseBtn.setText("exit");

        timer.stopTimer();

        //save score 
        scoreManager.addScore(timer.getSeconds());
    }

    /**
     * logic for reset game button
     */
    private void resetGame(){
        //create new randomized board, clear the old one, and put the new
        int seed = new Random().nextInt();
        int[][] ids = P3Tools.getRandomKindIdAssignments(seed, 18, 6);
        remove(board);
        board = new Board(ids, this);
        add(board, BorderLayout.CENTER);

        //reset flags and label texts
        running = false;
        finished = false;
        message.setText("Get ready to play!");
        startPauseBtn.setText("start");
        
        //make new timer thread
        timer.stopTimer();
        timer = new TimerThread(timerLabel);
        timerLabel.setText("00:00");

        //reset layout and repaint new tiles
        //revalidate essentially redraws layout with new components
        //repaint actually draws the pixels
        revalidate();
        repaint();
    }
}