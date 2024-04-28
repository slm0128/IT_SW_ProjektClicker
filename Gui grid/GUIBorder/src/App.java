import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(50,50));

        JButton btn1 = new JButton("Btn1");
        btn1.setPreferredSize(new Dimension(100,100));
  

        JButton btn2 = new JButton("Btn2");
        btn2.setPreferredSize(new Dimension(100,100));


        JButton btn3 = new JButton("Btn3");
        btn3.setPreferredSize(new Dimension(100,100));


        JButton btn4 = new JButton("Btn4");
        btn4.setPreferredSize(new Dimension(100,100));


        JButton btn5 = new JButton("Btn5");
        btn5.setPreferredSize(new Dimension(100,100));


        frame.add(btn1,BorderLayout.PAGE_START);
        frame.add(btn2,BorderLayout.PAGE_END);
        frame.add(btn3,BorderLayout.LINE_START);
        frame.add(btn4,BorderLayout.LINE_END);
        frame.add(btn5);



        frame.setVisible(true);

    }
}
