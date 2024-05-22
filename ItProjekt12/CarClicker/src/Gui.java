import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui {
    private static int counter = 0;
    private static int licht = 0;
    private static  int upgrade = 1;
    private static JLabel counterLabel;
    private static JButton lichtButton;

    public static void main(String[] args) {
        // Frame setup
        JFrame myFrame = new JFrame("Car Clicker");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(800, 600);
        myFrame.setLocationRelativeTo(null);
        myFrame.setLayout(new BorderLayout());

        // Counter label
        counterLabel = new JLabel("Schrauben und Mutter: 0");
        counterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myFrame.add(counterLabel, BorderLayout.PAGE_START);

        // Car button with image
        JButton clickButton = createCarButton();
        myFrame.add(clickButton, BorderLayout.CENTER);

        // Panels for left and right buttons
        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        myFrame.add(leftPanel, BorderLayout.WEST);
        myFrame.add(rightPanel, BorderLayout.EAST);

        myFrame.setVisible(true);
    }

    private static JButton createCarButton() {
        ImageIcon car1 = new ImageIcon("images/RX8_transparent.png");
        JButton clickButton = new JButton("Car (Img)", car1);
        clickButton.setPreferredSize(new Dimension(100, 50));
        clickButton.addActionListener(new ActionListener() {
            @Override

            
            public void actionPerformed(ActionEvent e) {
                if(upgrade== 1){
                counter++;}
                if(upgrade== 2){
                counter= counter+2;}
                if(upgrade== 3){
                counter= counter+3;}
                if(upgrade== 4){
                counter= counter+4;}
                
            
                updateCounterLabel();
            }
        });
        return clickButton;
    }

    private static JPanel createLeftPanel() {
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

    private static JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridLayout(5, 1));
        String[] rightButtonNames = {"Licht", "Right Button 2", "Right Button 3", "Right Button 4", "Right Button 5"};
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
        lichtButton = (JButton) rightPanel.getComponent(0); // Storing the Licht button for future updates
        return rightPanel;
    }

    private static void updateCounterLabel() {
        counterLabel.setText("Schrauben und Mutter: " + counter);
    }

    private static void handleLeftButtonAction(int index) {
        // Add specific actions for each left button here
        switch (index) {
            case 0:
                System.out.println("Action for Left Button A");
                break;
            case 1:
                System.out.println("Action for Left Button B");
                break;
            case 2:
                System.out.println("Action for Left Button C");
                break;
            case 3:
                System.out.println("Action for Left Button D");
                break;
            case 4:
                System.out.println("Action for Left Button E");
                break;
        }
    }

    private static void handleRightButtonAction(int index) {
        switch (index) {
            case 0:
            int lupgrade= 10;

            if(licht==0){
            lupgrade = 10;}
            if(licht==1){
            lupgrade= 40;}
            if(licht==2){
            lupgrade = 80;}


                if (counter >= lupgrade) {
                    licht++;
                    counter = counter - lupgrade ; // Deduct the cost from counter
                    updateCounterLabel();
                    upgrade++;
                    if (licht <= 3) {
                        System.out.println("Licht upgrade: " + licht);
                        lichtButton.setText("Licht (Level " + licht + ")");
                        if (licht == 3) {
                            lichtButton.setEnabled(false);
                            lichtButton.setText("Licht (Max Level)");
                        }
                    }
                } else {
                    System.out.println("Not enough Schrauben und Mutter for Licht upgrade");
                }
                break;
            case 1:
                System.out.println("Action for Right Button 2");
                break;
            case 2:
                System.out.println("Action for Right Button 3");
                break;
            case 3:
                System.out.println("Action for Right Button 4");
                break;
            case 4:
                System.out.println("Action for Right Button 5");
                break;
        }
    }
}