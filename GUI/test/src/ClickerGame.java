// ganzen imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClickerGame extends JFrame implements ActionListener {
    private int clicks;
    private JButton clickButton;
    private JLabel clickCountLabel;

    public ClickerGame() {
        setTitle("Clicker Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        clicks = 0;

        // Button erstellen
        clickButton = new JButton("Klick mich!");
        clickButton.addActionListener(this);

        // Label erstellen
        clickCountLabel = new JLabel("Klicks: " + clicks);

        // Layout festlegen
        setLayout(new BorderLayout());
        add(clickButton, BorderLayout.CENTER);
        add(clickCountLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clickButton) {
            clicks++;
            clickCountLabel.setText("Klicks: " + clicks);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClickerGame game = new ClickerGame();
            game.setVisible(true);
        });
    }
}
