import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Wetten-Klasse verwaltet das Wetten-Minispiel.
 */
public class Wetten extends JFrame {
    private JLabel raceStatus; // Label zur Anzeige des Rennstatus

    public Wetten() {
        setTitle("Race");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        raceStatus = new JLabel("Race has not started.");
        raceStatus.setHorizontalAlignment(SwingConstants.CENTER);
        add(raceStatus, BorderLayout.CENTER);

        JButton startRaceButton = new JButton("Start Race");
        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRace();
            }
        });
        add(startRaceButton, BorderLayout.PAGE_END);

        setVisible(true);
    }

    // Startet das Rennen
    private void startRace() {
        raceStatus.setText("Race is running...");
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raceStatus.setText("Race finished!");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
