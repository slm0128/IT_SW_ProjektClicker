import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class RacingGame extends JFrame {
    private JPanel gamePanel;
    private JLabel carLabel;
    private Timer gameTimer;
    private Timer hindernisseTimer;
    private ArrayList<JLabel> hindernisse;
    private int carXPosition;
    private Klicker klicker;
    private final int HINDERNISSE_WIDTH = 50; // Breite der Hindernisse
    private final int HINDERNISSE_HEIGHT = 50; // Höhe der Hindernisse
    private final int CAR_WIDTH = 60; // Breite des Autos
    private final int CAR_HEIGHT = 120; // Höhe des Autos

    public RacingGame(Klicker klicker) {
        this.klicker = klicker;
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

        // Auto-Bild laden und skalieren
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

        setVisible(true);
    }

    private void moveCarLeft() {
        if (carXPosition > 0) {
            carXPosition -= 10;
            carLabel.setBounds(carXPosition, 500, CAR_WIDTH, CAR_HEIGHT);
        }
    }

    private void moveCarRight() {
        if (carXPosition < 750 - CAR_WIDTH) {
            carXPosition += 10;
            carLabel.setBounds(carXPosition, 500, CAR_WIDTH, CAR_HEIGHT);
        }
    }

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

    private void moveHindernisse() {
        ArrayList<JLabel> hindernisseToRemove = new ArrayList<>();
        for (JLabel hindernis : hindernisse) {
            int y = hindernis.getY() + 15;
            if (y > 600) {
                hindernisseToRemove.add(hindernis);
                klicker.incrementCounterBy(10); // Counter um 10 erhöhen
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
