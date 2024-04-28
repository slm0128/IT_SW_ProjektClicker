import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("CarClicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel pageStart = createButtonPanelpStart("Btn1", "Btn2", "Btn3", "Btn4", "Btn5");
        frame.add(pageStart, BorderLayout.PAGE_START);

        JPanel lineEnd = createButtonPanellend("Licht/s", "Achse/s", "Karoserie/s", "Reifen/s", "Bremsen", "Auspuff/s", "Motor/s", "Turbo/s", "?", "?");
        frame.add(lineEnd, BorderLayout.LINE_END);

        frame.setVisible(true);
    }

    public static JPanel createButtonPanelpStart(String... buttonLabels) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(100, 100));
            panel.add(button);
        }

        return panel;
    }

    public static JPanel createButtonPanellend(String... buttonLabels) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(100, 100));
            panel.add(button);
        }

        return panel;
    }
}
