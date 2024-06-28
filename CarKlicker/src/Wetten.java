import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Wetten-Klasse verwaltet das Wetten-Minispiel.
 */
public class Wetten extends JFrame {
    private JLabel raceStatus; // Label zur Anzeige des Rennstatus
    private Klicker klicker; // Referenz auf das Klicker-Objekt
    

    // Konstruktor für die Wetten-Klasse
    public Wetten(Klicker klicker) {
        this.klicker = klicker; // Initialisiert das Klicker-Objekt
        
        setTitle("Rennen");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        raceStatus = new JLabel("Rennen hat nicht begonnen"); // Initialisiert das Label für den Rennstatus
        raceStatus.setHorizontalAlignment(SwingConstants.CENTER);
        add(raceStatus, BorderLayout.CENTER);

        JButton startRaceButton = new JButton("Start Race"); // Erstellt den Start Race Button
        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRace(); // ActionListener für den Start Race Button
            }
        });
        add(startRaceButton, BorderLayout.PAGE_END);

        setVisible(true); // Macht das Fenster sichtbar
    }

    // Methode zum Starten des Rennens
    private void startRace() {
        int max = 2;
        int min = 1;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min; // Zufällige Zahl zwischen 1 und 2 generieren

        // Überprüft, ob genug "Schrauben und Mutter" vorhanden sind
        if (klicker.getCounter() < 50) {
            JOptionPane.showMessageDialog(null, "Nicht genug Schrauben und Mutter für die Wette/ kosten=50", "Information",
                    JOptionPane.DEFAULT_OPTION);
        }

        // Überprüft das Rennen basierend auf der zufälligen Zahl
        if (rand == 1 && klicker.getCounter() >= 50) {
            raceWon(klicker); // Rennen gewonnen
        }
        if (rand == 2 && klicker.getCounter() >= 50) {
            raceLost(klicker); // Rennen verloren
        }
    }

    // Methode für den Fall, dass das Rennen gewonnen wird
    private void raceWon(Klicker klicker) {
        raceStatus.setText("Rennen läuft..."); // Status aktualisieren

        // Timer für die Nachricht "Rennen fertig! du hast gewonnen"
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raceStatus.setText("Rennen fertig! du hast gewonnen");
                klicker.update(); // Klicker aktualisieren
            }
        });
        timer.setRepeats(false);
        timer.start();

        klicker.incrementCounterBy(50); // Zähler im Klicker um 50 erhöhen
    }

    // Methode für den Fall, dass das Rennen verloren wird
    private void raceLost(Klicker klicker) {
        int x = 3000; // Timer-Länge

        raceStatus.setText("Rennen läuft..."); // Status aktualisieren

        // Timer für die Nachricht "Rennen beendet! du hast verloren"
        Timer timer = new Timer(x, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raceStatus.setText("Rennen beendet! du hast verloren");
                klicker.update(); // Klicker aktualisieren
            }
        });
        timer.setRepeats(false);
        timer.start();

        klicker.incrementCounterBy(-50); // Zähler im Klicker um -50 verringern
    }
}
