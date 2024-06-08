import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Die RacingGame-Klasse verwaltet das Rennen-Minispiel.
 */
public class RacingGame extends JFrame {
    private JPanel gamePanel;  // Panel für das Rennen
    private JLabel carLabel;  // Label für das Auto
    private Timer gameTimer;  // Timer für das Spiel
    private Timer hindernisseTimer;  // Timer für die Hindernisse
    private ArrayList<JLabel> hindernisse;  // Liste der Hindernisse
    private int carXPosition;  // X-Position des Autos
    private Klicker klicker;
    private final int HINDERNISSE_WIDTH = 50;  // Breite der Hindernisse
    private final int HINDERNISSE_HEIGHT = 50;  // Höhe der Hindernisse
    private final int CAR_WIDTH = 60;  // Breite des Autos
    private final int CAR_HEIGHT = 120;  // Höhe des Autos
    private int hindernisseSpeed; // Geschwindigkeit der Hindernisse

    public RacingGame(Klicker klicker) {
        this.klicker = klicker;
        this.hindernisseSpeed = 15; // Initiale Geschwindigkeit der Hindernisse
        
        setTitle("Rennen");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        setLayout(null);

        gamePanel = new JPanel();
        gamePanel.setBounds(0, 0, 800, 600);
        gamePanel.setLayout(null);
        add(gamePanel);

        ImageIcon carIcon = new ImageIcon("CarKlicker/images/Car.png");
        Image carImage = carIcon.getImage().getScaledInstance(CAR_WIDTH, CAR_HEIGHT, Image.SCALE_SMOOTH);
        carIcon = new ImageIcon(carImage);

        carLabel = new JLabel(carIcon);
        carXPosition = 375;
        carLabel.setBounds(carXPosition, 500, CAR_WIDTH, CAR_HEIGHT);
        gamePanel.add(carLabel);

        hindernisse = new ArrayList<>();
        gameTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveHindernisse();
                checkCollision();
            }
        });
        gameTimer.start();

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

        generateHindernisse();
        increaseHindernisseSpeed();

        setVisible(true);
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
                ImageIcon hindernisseIcon = new ImageIcon("CarKlicker/images/Hindernis.png");
                Image hindernisseImage = hindernisseIcon.getImage().getScaledInstance(HINDERNISSE_WIDTH, HINDERNISSE_HEIGHT, Image.SCALE_SMOOTH);
                hindernisseIcon = new ImageIcon(hindernisseImage);

                JLabel hindernis = new JLabel(hindernisseIcon);
                int xPosition = new Random().nextInt(750 - HINDERNISSE_WIDTH);
                hindernis.setBounds(xPosition, 0, HINDERNISSE_WIDTH, HINDERNISSE_HEIGHT);
                gamePanel.add(hindernis);
                hindernisse.add(hindernis);
                gamePanel.repaint();
            }
        });
        hindernisseTimer.start();
    }

    // Erhöht die Geschwindigkeit der Hindernisse in regelmäßigen Abständen alle 5 sek
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
                hindernisseToRemove.add(hindernis);
                klicker.incrementCounterBy(5); // Erhöht den Zähler um X
            } else {
                hindernis.setBounds(hindernis.getX(), y, HINDERNISSE_WIDTH, HINDERNISSE_HEIGHT);
            }
        }
        for (JLabel hindernis : hindernisseToRemove) {
            gamePanel.remove(hindernis);
            hindernisse.remove(hindernis);
        }
        gamePanel.repaint();
    }

    // Überprüft auf Kollisionen zwischen dem Auto und den Hindernissen
    private void checkCollision() {
        Rectangle carBounds = carLabel.getBounds();
        for (JLabel hindernis : hindernisse) {
            if (carBounds.intersects(hindernis.getBounds())) {
                gameTimer.stop();
                hindernisseTimer.stop();
                JOptionPane.showMessageDialog(this, "Game Over!");
                this.dispose();
                break;
            }
        }
    }
}
