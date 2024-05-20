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
            JButton leftButton = new JButton("Left Button " + i);
            final int leftButtonIndex = i;
            leftButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Left Button " + leftButtonIndex + " clicked");
                    // Füge hier spezifische Aktionen für jeden linken Button hinzu
                    switch (leftButtonIndex) {
                        case 1:
                            System.out.println("Action for Left Button 1");
                            break;
                        case 2:
                            System.out.println("Action for Left Button 2");
                            break;
                        case 3:
                            System.out.println("Action for Left Button 3");
                            break;
                        case 4:
                            System.out.println("Action for Left Button 4");
                            break;
                        case 5:
                            System.out.println("Action for Left Button 5");
                            break;
                    }
                }
            });
            leftPanel.add(leftButton);
        }

        // Panel für rechte Seite
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1)); // 5 Buttons untereinander

        for (int i = 1; i <= 5; i++) {
            JButton rightButton = new JButton("Right Button " + i);
            final int rightButtonIndex = i;
            rightButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Right Button " + rightButtonIndex + " clicked");
                    // Füge hier spezifische Aktionen für jeden rechten Button hinzu
                    switch (rightButtonIndex) {
                        case 1:
                            System.out.println("Action for Right Button 1");
                            break;
                        case 2:
                            System.out.println("Action for Right Button 2");
                            break;
                        case 3:
                            System.out.println("Action for Right Button 3");
                            break;
                        case 4:
                            System.out.println("Action for Right Button 4");
                            break;
                        case 5:
                            System.out.println("Action for Right Button 5");
                            break;
                    }
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
