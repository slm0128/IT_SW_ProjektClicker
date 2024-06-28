import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Die GUI-Klasse erstellt und verwaltet die grafische Benutzeroberfläche des
 * Spiels.
 */
public class GUI {
    private Klicker klicker;
    
    private JLabel counterLabel;
    private JButton lichtButton;
    private JButton reifenButton;
    private JButton motorButton;
    private JButton turboButton;
    private JButton karosserieButton;
    private JButton carButton;
    private JButton neuesAutoButton;
    private ImageIcon initialCarIcon;
    private ImageIcon newCarIcon;
    private int x = 0;

    public GUI() {
        this.klicker = new Klicker(this);
        
        
        createAndShowGUI();
    }

   
    
 

    // Erstellt und zeigt das GUI-Fenster
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

    // Erstellt das Label zur Anzeige des Zählers
    private JLabel createCounterLabel() {
        JLabel label = new JLabel("Schrauben und Mutter: 0");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(800, 50));
        return label;
    }
    public void updateCounterLabel() {
        counterLabel.setText("Schrauben und Mutter: " + klicker.getCounter());
    }

    
  

    // Erstellt den Button zum Klicken auf das Auto
    private JButton createCarButton() {
        initialCarIcon = new ImageIcon("CarKlicker/images/RX8_transparent.png");
        newCarIcon = new ImageIcon("CarKlicker/images/Car.png");
        JButton clickButton = new JButton(initialCarIcon);
        clickButton.setPreferredSize(new Dimension(100, 50));
        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                klicker.incrementCounter();
                updateCounterLabel();
            }
        });
        return clickButton;
    }

    // Erstellt das linke Panel mit den Schaltflächen
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        String[] leftButtonNames = { "Wetten", "Rennen", "         Neues Auto         " };
        for (int i = 0; i < leftButtonNames.length; i++) {
            JButton leftButton = new JButton(leftButtonNames[i]);
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

    // Erstellt das rechte Panel mit den Upgrade-Schaltflächen
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridLayout(5, 1));
        String[] rightButtonNames = { "Licht", "Reifen", "Motor", "Turbo", "         Karosserie         " };
        for (int i = 0; i < rightButtonNames.length; i++) {
            JButton rightButton = new JButton(rightButtonNames[i]);
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

    

    // Behandelt die Aktionen der linken Schaltflächen
    /**
     * @param index
     */
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

    



    // Behandelt die Aktionen der rechten Schaltflächen (Upgrades)
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

    // Überprüft, ob alle Upgrades auf Maximallevel sind und aktiviert den "Neues
    // Auto"-Button
    private void checkAllUpgradesMaxed() {
        if (klicker.areAllUpgradesMaxed()&& x==0) {

            neuesAutoButton.setEnabled(true);
        }
    }

    // Aktualisiert den Text und den Status der Schaltfläche nach einem Upgrade
    private void updateButton(JButton button, String componentName, int level, boolean upgraded) {
        if (upgraded) {
            button.setText(componentName + " (Level " + level + ")");
            if (level >= Klicker.MAX_LEVEL) {
                button.setEnabled(false);
                button.setText(componentName + " (Max Level)");
            }
        }
    }

    // Setzt das Spiel zurück und aktualisiert die GUI
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