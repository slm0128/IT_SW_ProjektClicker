import javax.swing.JFileChooser; //FileChooser wird importiert

public class FileChooser {
    public static void main(String[] args) throws Exception {

        JFileChooser ch = new JFileChooser(); // Filechooser objekt wird erstellt
        // ch.showOpenDialog(null); //Linker "Öffnen" Button
        // ch.showSaveDialog(null); // Linker "Speicher" Button
        // ch.showDialog(null,"hehehe"); //Linker Button custom
        int rValue = ch.showOpenDialog(null); // Button wird in int rValue gespeichert

        if (rValue == JFileChooser.APPROVE_OPTION) {
            System.out.println("Die geöffnete Datei ist: " + ch.getSelectedFile().getName());

        } // Wenn rValue angecklickt/true dann sout

    }

}
