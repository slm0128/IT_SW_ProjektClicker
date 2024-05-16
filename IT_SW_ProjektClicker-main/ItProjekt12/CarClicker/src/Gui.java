import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui {
    private static int x =0;
    private static int counter = 0; // Zähler für Klicks

    public static void main(String[] args) {
        
        JFrame meinFrame = new JFrame("Car Clicker");
        

        // Setzt Layout des Frames
        meinFrame.setLayout(new BorderLayout());

        // Erstellt Label für den Zähler
        JLabel counterLabel = new JLabel("Schrauben und Mutter (Img): 0");
        counterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        meinFrame.add(counterLabel, BorderLayout.NORTH);

        // Erstellt Button
        JButton clickButton = new JButton("Schrauben und Mutter (Img)");
        meinFrame.add(clickButton, BorderLayout.CENTER);

        JButton clickButton1 = new JButton("Upgrade");
        meinFrame.add(clickButton1, BorderLayout.EAST);
        clickButton1.setPreferredSize(new Dimension(100, 50)); // Breite: 100, Höhe: 50
        
        

        // Fügt ActionListener zum Button hinzu
        clickButton.setPreferredSize(new Dimension(100, 50)); // Breite: 100, Höhe: 50

    
        clickButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent w) {

                if(counter>=50){
                x++;
                counter=counter-50;
                counterLabel.setText("Schrauben und Mutter: " + counter);
                clickButton1.setBackground(Color.GRAY);
                clickButton1.removeActionListener(this);
                
                }
                
                
               

                
    
            }
        });
    

        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (x==0) {
                    // Erhöht den Zähler um eins
                counter++;
                }
                if (x==1) {
                    // Erhöht den Zähler um eins
                counter= counter +2;
                
                }if (x==2){
                       // Erhöht den Zähler um eins
                counter= counter +2;
                    
                }
                
                // Aktualisiert das Label
                counterLabel.setText("Schrauben und Mutter: " + counter);
            }
        });


       

        meinFrame.setSize(500, 500);
        meinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        meinFrame.setVisible(true);

        int geld = 1;

        for(int i = 0; i < 0; i++){
            counter++;
        }
    }
}


