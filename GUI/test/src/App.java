import javax.swing.JFrame;  //Importiert JFrame
import javax.swing.JLabel;  //Importiert JLabel
import javax.swing.JDialog; //Importiert JDialog

public class App {
    public static void main(String[] args) throws Exception {
       
        JFrame meinGui= new JFrame("BSP");    //ein neues Objekt der Klasse JFrame wird erstellt
        meinGui.setSize(500,500);        // Breite und höhe werden festgesetzt 
        meinGui.add(new JLabel("BSP Label"));

        JDialog meinDialog= new JDialog();
        meinDialog.setTitle("Dialog");
        meinDialog.setSize(200, 100);




        meinDialog.setModal(true);
        meinDialog.setVisible(true);    // "Wird aktiviert" sollte immer am ende stehen
        meinGui.setVisible(true);     // "Wird aktiviert" sollte immer am ende stehen damit es keine probleme gibt


    }
}
