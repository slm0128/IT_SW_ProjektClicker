import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui {
    private static int counter = 0; // Zähler für Klicks

    public static void main(String[] args) {
        // Frame wird erstellt
        JFrame myFrame = new JFrame("Car Clicker");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(800, 600);
        myFrame.setLocationRelativeTo(null);

        // Setzt Layout des Frames
        myFrame.setLayout(new BorderLayout());

        // Erstellt Label für den Zähler
        JLabel counterLabel = new JLabel("Schrauben und Mutter (Img): 0");
        counterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myFrame.add(counterLabel, BorderLayout.PAGE_START);
        ImageIcon car1 = new ImageIcon("C:\\Users\\Slmka\\Documents\\GitHub\\IT_SW_ProjektClicker\\ItProjekt12\\Img\\RX8_transparent.png");

        // Erstellt Button für auto und einen ICon was danach eingefügt wird
        JButton clickButton = new JButton("Car (Img)");
        clickButton.setIcon(car1);
        myFrame.add(clickButton, BorderLayout.CENTER);
        // Fügt ActionListener zum Button hinzu
        clickButton.setPreferredSize(new Dimension(100, 50));

        // rechte seite (LineEnd) wird erstellt die Buttons Werden in sowas wie einem
        // Array gespeichert
        JPanel lineEnd = createButtonPanellend("Licht/s", "Achse/s", "Karoserie/s", "Reifen/s", "Bremsen", "Auspuff/s",
                "Motor/s", "Turbo/s", "?", "?");
        myFrame.add(lineEnd, BorderLayout.LINE_END);

        // Linke seite (LineStart) wird erstellt die Buttons Werden in sowas wie einem
        // Array gespeichert
        JPanel lineStart = createButtonPanellend("Scharaube zu Geld", "Wetten um Geld", "Neues Auto Kaufen");
        myFrame.add(lineStart, BorderLayout.LINE_START);

        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Erhöht den Zähler um eins
                counter++;
                // Aktualisiert das Label
                counterLabel.setText("Schrauben und Mutter: " + counter);
            }
        });

        myFrame.setVisible(true);
    }

    public static JPanel createButtonPanelpStart(String... buttonLabels) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(100, 100));
            panel.add(button);
        }

        return panel;
    }

    public static JPanel createButtonPanellend(String... buttonLabels) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(100, 100));
            panel.add(button);
        }

        return panel;
    }
}
