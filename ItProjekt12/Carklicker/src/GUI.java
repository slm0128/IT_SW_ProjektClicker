import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private Klicker klicker;
    private JLabel counterLabel;
    private JButton lichtButton;
    private JButton reifenButton;
    private JButton motorButton;
    private JButton turboButton;
    private JButton karosserieButton;

    public GUI() {
        this.klicker = new Klicker();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame myFrame = new JFrame("Car Clicker");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(800, 600);
        myFrame.setLocationRelativeTo(null);
        myFrame.setLayout(new BorderLayout());

        counterLabel = createCounterLabel();
        myFrame.add(counterLabel, BorderLayout.PAGE_START);

        JButton clickButton = createCarButton();
        myFrame.add(clickButton, BorderLayout.CENTER);

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        myFrame.add(leftPanel, BorderLayout.WEST);
        myFrame.add(rightPanel, BorderLayout.EAST);

        myFrame.setVisible(true);
    }

    private JLabel createCounterLabel() {
        JLabel label = new JLabel("Schrauben und Mutter: 0");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(800, 50));
        return label;
    }

    private JButton createCarButton() {
        ImageIcon car1 = new ImageIcon("C:\\Users\\Slmka\\Documents\\GitHub\\IT_SW_ProjektClicker\\ItProjekt12\\Carklicker\\images\\RX8_transparent.png");
        JButton clickButton = new JButton("Car (Img)", car1);
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

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        String[] leftButtonNames = {"Race/Wetten", "Left Button B", "Left Button C", "Left Button D", "Left Button E"};
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
        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridLayout(5, 1));
        String[] rightButtonNames = {"Lichte", "Right Button 2", "Right Button 3", "Right Button 4", "Right Button 5"};
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

    private void updateCounterLabel() {
        counterLabel.setText("Schrauben und Mutter: " + klicker.getCounter());
    }

    private void handleLeftButtonAction(int index) {
        switch (index) {
            case 0:
                // Open the race frame when "Left Button A" is clicked
                new RaceFrame();
                break;
            default:
                System.out.println("Action for Left Button " + index);
                break;
        }
    }

    private void handleRightButtonAction(int index) {
        switch (index) {
            case 0:
                if (klicker.upgradeLicht()) {
                    updateCounterLabel();
                    lichtButton.setText("Licht (Level " + klicker.getLicht() + ")");
                    if (klicker.getLicht() >= 3) {
                        lichtButton.setEnabled(false);
                        lichtButton.setText("Licht (Max Level)");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nicht genug Schrauben und Mutter für Licht upgrade", "Information", JOptionPane.DEFAULT_OPTION);
                }
                break;
            case 1:
                if (klicker.upgradeReifen()) {
                    updateCounterLabel();
                    reifenButton.setText("Reifen (Level " + klicker.getReifen() + ")");
                    if (klicker.getReifen() >= 3) {
                        reifenButton.setEnabled(false);
                        reifenButton.setText("Reifen (Max Level)");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nicht genug Schrauben und Mutter für Reifen upgrade", "Information", JOptionPane.DEFAULT_OPTION);
                }
                break;
            case 2:
                if (klicker.upgradeMotor()) {
                    updateCounterLabel();
                    motorButton.setText("Motor (Level " + klicker.getMotor() + ")");
                    if (klicker.getMotor() >= 3) {
                        motorButton.setEnabled(false);
                        motorButton.setText("Motor (Max Level)");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nicht genug Schrauben und Mutter für Motor upgrade", "Information", JOptionPane.DEFAULT_OPTION);
                }
                break;
            case 3:
                if (klicker.upgradeTurbo()) {
                    updateCounterLabel();
                    turboButton.setText("Turbo (Level " + klicker.getTurbo() + ")");
                    if (klicker.getTurbo() >= 3) {
                        turboButton.setEnabled(false);
                        turboButton.setText("Turbo (Max Level)");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nicht genug Schrauben und Mutter für Turbo upgrade", "Information", JOptionPane.DEFAULT_OPTION);
                }
                break;
            case 4:
                if (klicker.upgradeKarosserie()) {
                    updateCounterLabel();
                    karosserieButton.setText("Karosserie (Level " + klicker.getKarosserie() + ")");
                    if (klicker.getKarosserie() >= 3) {
                        karosserieButton.setEnabled(false);
                        karosserieButton.setText("Karosserie (Max Level)");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nicht genug Schrauben und Mutter für Karosserie upgrade", "Information", JOptionPane.DEFAULT_OPTION);
                }
                break;
            default:
                System.out.println("Action for Right Button " + index);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}

class RaceFrame extends JFrame {
    public RaceFrame() {
        setTitle("Race Frame");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
