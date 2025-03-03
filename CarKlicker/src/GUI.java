import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Benutzerdefinierte JButton Klasse mit Hintergrundbild
class BackgroundButton extends JButton {
    private Image backgroundImage;

    // Konstruktor für BackgroundButton
    public BackgroundButton(ImageIcon icon, Image backgroundImage) {
        super(icon);
        this.backgroundImage = backgroundImage;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    // Methode zum Zeichnen des Hintergrundbildes
    @Override
    protected void paintComponent(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        super.paintComponent(g);
    }
}

public class GUI {
    private Klicker klicker; // Instanz von Klicker
    private JLabel counterLabel; // Label zum Anzeigen des Zählers
    private BackgroundButton carButton; // Button mit Hintergrundbild für das Auto
    private JButton lichtButton; // Button für Licht Upgrade
    private JButton reifenButton; // Button für Reifen Upgrade
    private JButton motorButton; // Button für Motor-Upgrade
    private JButton turboButton; // Button für Turbo-Upgrade
    private JButton karosserieButton; // Button für Karosserie-Upgrade
    private JButton neuesAutoButton; // Button für Neues Auto
    private ImageIcon initialCarIcon; // Anfangssymbol für Auto
    private ImageIcon newCarIcon; // Neues Symbol für Auto nach Upgrade
    private int x = 0; // Zähler für neue Autos

    // Konstruktor für GUI
    public GUI() {
        this.klicker = new Klicker(this);
        createAndShowGUI();
    }

    // Methode zum Erstellen und Anzeigen der GUI
    private void createAndShowGUI() {
        JFrame myFrame = new JFrame("Car Clicker");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1400, 1200);
        myFrame.setLocationRelativeTo(null);
        myFrame.setLayout(new BorderLayout());

        counterLabel = createCounterLabel();
        myFrame.add(counterLabel, BorderLayout.PAGE_START);

        carButton = createCarButton();
        myFrame.add(carButton, BorderLayout.CENTER);

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        myFrame.add(leftPanel, BorderLayout.WEST);
        myFrame.add(rightPanel, BorderLayout.EAST);

        myFrame.setVisible(true);
    }

    // Methode zum Erstellen des ZählerLabels
    private JLabel createCounterLabel() {
        JLabel label = new JLabel("Schrauben und Mutter: 0");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(800, 50));
        return label;
    }

    // Methode zum Aktualisieren des Zähler-Labels
    public void updateCounterLabel() {
        counterLabel.setText("Schrauben und Mutter: " + klicker.getCounter());
    }

    // Methode zum Erstellen des Auto-Buttons mit Hintergrundbild
    private BackgroundButton createCarButton() {
        initialCarIcon = new ImageIcon("CarKlicker/images/car1.png");
        newCarIcon = new ImageIcon("CarKlicker/images/Car2.png");
        Image backgroundImage = new ImageIcon("CarKlicker/images/Hintergrund.jpeg").getImage();
        BackgroundButton clickButton = new BackgroundButton(initialCarIcon, backgroundImage);
        clickButton.setPreferredSize(new Dimension(300, 200));
        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                klicker.incrementCounter();
                updateCounterLabel();
            }
        });
        return clickButton;
    }

    // Methode zum Erstellen des linken Panels mit Buttons
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        leftPanel.setBackground(Color.BLACK); // Hintergrundfarbe des Panels schwarz setzen
        String[] leftButtonNames = { "Wetten", "Rennen", "         Neues Auto         " };
        for (int i = 0; i < leftButtonNames.length; i++) {
            JButton leftButton = new JButton(leftButtonNames[i]);
            customizeButton(leftButton); // Hintergrundfarbe der Buttons und andere Eigenschaften setzen
            int index = i;
            leftButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleLeftButtonAction(index);
                }
            });
            leftPanel.add(leftButton);
        }
        neuesAutoButton = (JButton) leftPanel.getComponent(2);
        neuesAutoButton.setEnabled(false);
        return leftPanel;
    }

    // Methode zum Erstellen des rechten Panels mit Buttons
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridLayout(5, 1));
        rightPanel.setBackground(Color.BLACK); // Hintergrundfarbe des Panels schwarz setzen
        String[] rightButtonNames = { "Licht", "Reifen", "Motor", "Turbo", "         Karosserie         " };
        for (int i = 0; i < rightButtonNames.length; i++) {
            JButton rightButton = new JButton(rightButtonNames[i]);
            customizeButton(rightButton); // Hintergrundfarbe der Buttons und andere Eigenschaften setzen
            int index = i;
            rightButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleRightButtonAction(index);
                }
            });
            rightPanel.add(rightButton);
        }
        lichtButton = (JButton) rightPanel.getComponent(0);
        reifenButton = (JButton) rightPanel.getComponent(1);
        motorButton = (JButton) rightPanel.getComponent(2);
        turboButton = (JButton) rightPanel.getComponent(3);
        karosserieButton = (JButton) rightPanel.getComponent(4);
        return rightPanel;
    }

    // Methode zum Anpassen der Buttons
    private void customizeButton(JButton button) {
        button.setBackground(Color.BLACK); // Hintergrundfarbe der Buttons schwarz setzen
        button.setForeground(Color.WHITE); // Schriftfarbe weiß setzen
        button.setOpaque(true);
        button.setBorderPainted(false);

        // Hintergrundfarbe ändern, wenn der Button gedrückt wird
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(50, 50, 50)); // Hellere Schwarztonfarbe
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(Color.BLACK); // Zurück zur ursprünglichen Farbe
            }
        });
    }

    // Methode zum Behandeln von Aktionen der linken Buttons
    private void handleLeftButtonAction(int index) {
        switch (index) {
            case 0:
                new Wetten(klicker);
                break;
            case 1:
                new RacingGame(klicker);
                break;
            case 2:
                resetGame();
                break;
            default:
                System.out.println("Action for Left Button " + index);
                break;
        }
    }

    // Methode zum Behandeln von Aktionen der rechten Buttons
    private void handleRightButtonAction(int index) {
        boolean upgraded = false;
        switch (index) {
            case 0:
                upgraded = klicker.upgradeLicht();
                updateButton(lichtButton, "Licht", klicker.getLicht(), upgraded);
                break;
            case 1:
                upgraded = klicker.upgradeReifen();
                updateButton(reifenButton, "Reifen", klicker.getReifen(), upgraded);
                break;
            case 2:
                upgraded = klicker.upgradeMotor();
                updateButton(motorButton, "Motor", klicker.getMotor(), upgraded);
                break;
            case 3:
                upgraded = klicker.upgradeTurbo();
                updateButton(turboButton, "Turbo", klicker.getTurbo(), upgraded);
                break;
            case 4:
                upgraded = klicker.upgradeKarosserie();
                updateButton(karosserieButton, "Karosserie", klicker.getKarosserie(), upgraded);
                break;
            default:
                System.out.println("Action for Right Button " + index);
                break;
        }
        if (!upgraded) {
            JOptionPane.showMessageDialog(null, "Nicht genug Schrauben und Mutter für Upgrade", "Information",
                    JOptionPane.DEFAULT_OPTION);
        } else {
            updateCounterLabel();
            checkAllUpgradesMaxed();
        }
    }

    // Methode zum Überprüfen, ob alle Upgrades maximiert sind
    private void checkAllUpgradesMaxed() {
        if (klicker.areAllUpgradesMaxed() && x == 0) {
            neuesAutoButton.setEnabled(true);
        }
    }

    // Methode zum Aktualisieren der Buttons nach Upgrade
    private void updateButton(JButton button, String componentName, int level, boolean upgraded) {
        if (upgraded) {
            button.setText(componentName + " (Level " + level + ")");
            if (level >= Klicker.MAX_LEVEL) {
                button.setEnabled(false);
                button.setText(componentName + " (Max Level)");
            }
        }
    }

    // Methode zum Zurücksetzen des Spiels
    private void resetGame() {
        klicker.resetValues();

        lichtButton.setEnabled(true);
        karosserieButton.setEnabled(true);
        reifenButton.setEnabled(true);
        motorButton.setEnabled(true);
        turboButton.setEnabled(true);
        updateCounterLabel();
        updateButton(lichtButton, "Licht", klicker.getLicht(), true);
        updateButton(reifenButton, "Reifen", klicker.getReifen(), true);
        updateButton(motorButton, "Motor", klicker.getMotor(), true);
        updateButton(turboButton, "Turbo", klicker.getTurbo(), true);
        updateButton(karosserieButton, "Karosserie", klicker.getKarosserie(), true);
        carButton.setIcon(newCarIcon);
        neuesAutoButton.setEnabled(false);
        x++;
    }
}
