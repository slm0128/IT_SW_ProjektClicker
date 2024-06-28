import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class RacingGame extends JFrame {
    private CustomPanel gamePanel; // Panel für das Rennen mit Hintergrundbild
    private JLabel carLabel; // Label für das Auto
    private Timer gameTimer; // Timer für das Spiel
    private Timer hindernisseTimer; // Timer für die Hindernisse
    private ArrayList<JLabel> hindernisse; // Liste der Hindernisse
    private int carXPosition; // X-Position des Autos
    private Klicker klicker; // Referenz auf das Klicker-Objekt
    private final int HINDERNISSE_WIDTH = 60; // Breite der Hindernisse
    private final int HINDERNISSE_HEIGHT = 120; // Höhe der Hindernisse
    private final int CAR_WIDTH = 60; // Breite des Autos
    private final int CAR_HEIGHT = 120; // Höhe des Autos
    private int hindernisseSpeed; // Geschwindigkeit der Hindernisse

    // Konstruktor für RacingGame
    public RacingGame(Klicker klicker) {
        this.klicker = klicker;
        this.hindernisseSpeed = 15; // Initiale Geschwindigkeit der Hindernisse

        setTitle("Rennen");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setLayout(null);

        gamePanel = new CustomPanel("CarKlicker/images/Straße.png"); // Hintergrund
        gamePanel.setBounds(0, 0, 800, 600);
        gamePanel.setLayout(null);
        add(gamePanel);

        ImageIcon carIcon = new ImageIcon("CarKlicker/images/rennen1.png"); // SpielerAuto
        Image carImage = carIcon.getImage().getScaledInstance(CAR_WIDTH, CAR_HEIGHT, Image.SCALE_SMOOTH);
        carIcon = new ImageIcon(carImage);

        carLabel = new JLabel(carIcon);
        carXPosition = 375; // Initiale X-Position des Autos
        carLabel.setBounds(carXPosition, 500, CAR_WIDTH, CAR_HEIGHT); // Position und Größe des AutoLabels
        gamePanel.add(carLabel);

        hindernisse = new ArrayList<>(); // Initialisiert die Liste der Hindernisse
        gameTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHindernisse();
                checkCollision();
            }
        });
        gameTimer.start();

        // KeyListener für die Steuerung des Autos
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveCarLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveCarRight();
                }
            }
        });

        generateHindernisse(); // Generiert Hindernisse
        increaseHindernisseSpeed(); // Erhöht die Geschwindigkeit der Hindernisse

        setVisible(true); // Macht das Fenster sichtbar
    }

    // Bewegt das Auto nach links
    private void moveCarLeft() {
        if (carXPosition > 0) {
            carXPosition -= 10;
            carLabel.setBounds(carXPosition, 500, CAR_WIDTH, CAR_HEIGHT);
        }
    }

    // Bewegt das Auto nach rechts
    private void moveCarRight() {
        if (carXPosition < 750 - CAR_WIDTH) {
            carXPosition += 10;
            carLabel.setBounds(carXPosition, 500, CAR_WIDTH, CAR_HEIGHT);
        }
    }

    // Generiert Hindernisse in zufälligen Intervallen
    private void generateHindernisse() {
        hindernisseTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon hindernisseIcon = new ImageIcon("CarKlicker/images/rennen2.png"); // Gegnerisches Auto
                Image hindernisseImage = hindernisseIcon.getImage().getScaledInstance(HINDERNISSE_WIDTH, HINDERNISSE_HEIGHT, Image.SCALE_SMOOTH);
                hindernisseIcon = new ImageIcon(hindernisseImage);

                JLabel hindernis = new JLabel(hindernisseIcon);
                int xPosition = new Random().nextInt(750 - HINDERNISSE_WIDTH); // Zufällige X-Position
                hindernis.setBounds(xPosition, 0, HINDERNISSE_WIDTH, HINDERNISSE_HEIGHT);
                gamePanel.add(hindernis);
                hindernisse.add(hindernis); // Fügt das Hindernis der Liste hinzu
                gamePanel.repaint();
            }
        });
        hindernisseTimer.start();
    }

    // Erhöht die Geschwindigkeit der Hindernisse in regelmäßigen Abständen alle 5 Sekunden
    private void increaseHindernisseSpeed() {
        Timer speedTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hindernisseSpeed += 2;
            }
        });
        speedTimer.start();
    }

    // Bewegt die Hindernisse nach unten
    private void moveHindernisse() {
        ArrayList<JLabel> hindernisseToRemove = new ArrayList<>();
        for (JLabel hindernis : hindernisse) {
            int y = hindernis.getY() + hindernisseSpeed;
            if (y > 600) {
                hindernisseToRemove.add(hindernis); // Fügt Hindernisse zur Entfernungsliste hinzu
                klicker.incrementCounterBy(5); // Erhöht den Zähler im Klicker-Objekt
                klicker.update();
            } else {
                hindernis.setBounds(hindernis.getX(), y, HINDERNISSE_WIDTH, HINDERNISSE_HEIGHT);
            }
        }
        for (JLabel hindernis : hindernisseToRemove) {
            gamePanel.remove(hindernis); // Entfernt das Hindernis vom Panel
            hindernisse.remove(hindernis); // Entfernt das Hindernis aus der Liste
        }
        gamePanel.repaint();
    }

    // Überprüft auf Kollisionen zwischen dem Auto und den Hindernissen
    private void checkCollision() {
        Rectangle carBounds = carLabel.getBounds();
        for (JLabel hindernis : hindernisse) {
            if (carBounds.intersects(hindernis.getBounds())) {
                gameTimer.stop(); // Stoppt das Spiel
                hindernisseTimer.stop(); // Stoppt die Hindernisse
                JOptionPane.showMessageDialog(this, "Game Over!");
                this.dispose(); // Schließt das Fenster
                break;
            }
        }
    }

    // CustomPanel-Klasse, um das Hintergrundbild zu zeichnen
    private class CustomPanel extends JPanel {
        private Image backgroundImage;

        // Konstruktor für CustomPanel
        public CustomPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Zeichnet das Hintergrundbild
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
