import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private Klicker klicker;
    private JLabel counterLabel;
    private JButton lichtButton;

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
        ImageIcon car1 = new ImageIcon("images/RX8_transparent.png");
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
        String[] leftButtonNames = {"Left Button A", "Left Button B", "Left Button C", "Left Button D", "Left Button E"};
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
