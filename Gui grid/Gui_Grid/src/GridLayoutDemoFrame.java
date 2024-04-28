import java.awt.*;
import javax.swing.*;

public class GridLayoutDemoFrame extends JFrame {
  public GridLayoutDemoFrame(String title) {
    super(title);
    setSize(600, 450);
    setLayout(new GridLayout(0,5,5,5));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    for(int i = 1; i < 11; i++) {
        add(new JButton(String.valueOf(i)));
    }

    setVisible(true);
  }
}