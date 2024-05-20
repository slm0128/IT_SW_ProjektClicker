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

        // Erstellt Button für auto und einen Icon was danach eingefügt wird
        ImageIcon car1 = new ImageIcon("C:\\Users\\Slmka\\Documents\\GitHub\\IT_SW_ProjektClicker\\ItProjekt12\\Img\\RX8_transparent.png");
        JButton clickButton = new JButton("Car (Img)");
        clickButton.setIcon(car1);
        myFrame.add(clickButton, BorderLayout.CENTER);
        // Fügt ActionListener zum Button hinzu
        clickButton.setPreferredSize(new Dimension(100, 50));

        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Erhöht den Zähler um eins
                counter++;
                // Aktualisiert das Label
                counterLabel.setText("Schrauben und Mutter: " + counter);
            }
        });

        // Panel für linke Seite
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1)); // 5 Buttons untereinander

        for (int i = 1; i <= 5; i++) {
            JButton leftButton = new JButton("Left " + i);
            leftButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(leftButton.getText() + " clicked");
                }
            });
            leftPanel.add(leftButton);
        }

        // Panel für rechte Seite
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1)); // 5 Buttons untereinander

        for (int i = 1; i <= 5; i++) {
            JButton rightButton = new JButton("Right " + i);
            rightButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(rightButton.getText() + " clicked");
                }
            });
            rightPanel.add(rightButton);
        }

        // Füge Panels zu den entsprechenden BorderLayout-Positionen hinzu
        myFrame.add(leftPanel, BorderLayout.WEST);
        myFrame.add(rightPanel, BorderLayout.EAST);

        myFrame.setVisible(true);
    }
}
