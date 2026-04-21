import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Orbit implements ActionListener {

    private JButton button;
    private AniThread t;

    public Orbit() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawArea d = new DrawArea();

        button = new JButton("Start");
        button.addActionListener(this);

        t = new AniThread(d);
        t.start();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(button);

        f.setLayout(new BorderLayout());
        f.add(topPanel, BorderLayout.NORTH);
        f.add(d, BorderLayout.CENTER);

        f.pack();
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (button.getText().equals("Start")) {
            t.setRunning(true);
            button.setText("Stop");
        }
        else {
            t.setRunning(false);
            button.setText("Start");
        }
    }

    public static void main(String[] args){
        Orbit myOrb = new Orbit();
    }
}