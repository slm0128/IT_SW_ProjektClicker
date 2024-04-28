import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui {
    private static int counter = 0; // Zähler für Klicks

    public static void main(String[] args) {
        JFrame meinFrame = new JFrame("Car Clicker");

        // Setzt Layout des Frames
        meinFrame.setLayout(new BorderLayout());

        // Erstellt Label für den Zähler
        JLabel counterLabel = new JLabel("Schrauben und Mutter (Img): 0");
        counterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        meinFrame.add(counterLabel, BorderLayout.NORTH);

        // Erstellt Button
        JButton clickButton = new JButton("Schrauben und Mutter (Img)");
        meinFrame.add(clickButton, BorderLayout.CENTER);
        

        // Fügt ActionListener zum Button hinzu
        clickButton.setPreferredSize(new Dimension(100, 50)); // Breite: 100, Höhe: 50

        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Erhöht den Zähler um eins
                counter++;
                // Aktualisiert das Label
                counterLabel.setText("Schrauben und Mutter: " + counter);
            }
        });

        meinFrame.setSize(500, 500);
        meinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        meinFrame.setVisible(true);
    }
}
