import javax.swing.JColorChooser;

public class Color {
    public static void main(String[] args) throws Exception {
        java.awt.Color ausgColor= JColorChooser.showDialog(null, "Farbeauswahl", null);     //showDialog zeigt Vorgefertigte Fenster/klassen und ist static
        System.out.println(ausgColor);
    }
}
