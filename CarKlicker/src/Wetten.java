import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Wetten-Klasse verwaltet das Wetten-Minispiel.
 */
public class Wetten extends JFrame {
    private JLabel raceStatus; // Label zur Anzeige des Rennstatus
    private Klicker klicker;
    

    public Wetten(Klicker klicker) {
        this.klicker = klicker;
        

        setTitle("Race");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        raceStatus = new JLabel("Race has not started.");
        raceStatus.setHorizontalAlignment(SwingConstants.CENTER);
        add(raceStatus, BorderLayout.CENTER);

        JButton startRaceButton = new JButton("Start Race");
        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRace();
            }
        });
        add(startRaceButton, BorderLayout.PAGE_END);

        setVisible(true);
    }

    // Startet das Rennen
    private void startRace() {
        int max = 2;
        int min = 1;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        System.out.println(rand);
        System.out.println(klicker.getCounter());

        if (klicker.getCounter()<50) {
            JOptionPane.showMessageDialog(null, "Nicht genug Schrauben und Mutter für die Wette/ kosten=50", "Information",
                    JOptionPane.DEFAULT_OPTION);
            
        }

        if(rand==1&&klicker.getCounter()>=50){
            raceWon(klicker);
        }
        if(rand==2&&klicker.getCounter()>=50){
            raceLost(klicker);
        }
      
            
        


    }

    private void raceWon(Klicker klicker){
        
        raceStatus.setText("Race is running...");
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raceStatus.setText("Race finished! You won");
                klicker.update();
            }
        });
        timer.setRepeats(false);
        timer.start();
        klicker.incrementCounterBy(50);
        
    
       
       
        
        

    }private void raceLost(Klicker klicker) {
        int x = 3000;
        
        raceStatus.setText("Race is running...");
        Timer timer = new Timer(x, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raceStatus.setText("Race finished! You lose");
                klicker.update();
            }
           
        });
        timer.setRepeats(false);
        timer.start();
        klicker.incrementCounterBy(-50);
        
        
        
        
        
        
        

    }
}
