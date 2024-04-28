import javax.swing.*;

public class GridLayoutDemo {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new GridLayoutDemoFrame("GridLayoutDemo");
      }
    });
  }
}