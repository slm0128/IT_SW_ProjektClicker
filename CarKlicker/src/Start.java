import javax.swing.*;

/**
 * Die Start-Klasse ist die Hauptklasse des Spiels und erstellt die GUI.
 */
public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
