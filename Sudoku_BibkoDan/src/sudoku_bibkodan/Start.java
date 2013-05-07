/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku_bibkodan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Daniel
 */
public class Start extends JFrame {

    int obtiaznost;
    Tlacitko start;
    Tlacitko load;
    Tlacitko lahke;
    Tlacitko stredne;
    Tlacitko tazke;
    NovaHraListener o = new NovaHraListener();
    ObtiaznostListener o1 = new ObtiaznostListener();

    public Start() { // Vytvorenie prvotneho okna, kde sa hráč rozhoduj či chce začať hru novú alebo pokračovaj v hre rozohratej
        super("SUDOKU  BIBKODAN START");
        this.setSize(285, 180);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        start = new Tlacitko("Nová hra"); // Implementovanie tlačítka novej hry
        start.setBounds(10, 10, 120, 50);
        start.addActionListener(o);
        this.add(start);

        load = new Tlacitko("Načítať hru"); // Implementovanie tlačítka načítanie hry
        load.setBounds(140, 10, 120, 50);
        load.addActionListener(o);
        this.add(load);
    }

    private class NovaHraListener implements ActionListener { // Listener kliku voľby new/load hry

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == start) {   // Pri kliknutí na novú hru sa vytvora nové 3 tlačítka na zvolenie obtiažnosti
                lahke = new Tlacitko("ľahké", 10, 70, 80, 50);
                lahke.addActionListener(o1);
                Start.this.add(lahke);

                stredne = new Tlacitko("stredné", 95, 70, 80, 50);
                stredne.addActionListener(o1);
                Start.this.add(stredne);

                tazke = new Tlacitko("ťazké", 180, 70, 80, 50);
                tazke.addActionListener(o1);
                Start.this.add(tazke);
                
                Start.this.setVisible(false);
                Start.this.setVisible(true);
            }
            if (e.getSource() == load) { 
                Window w = null;
                try {
                    w = new Window(0); // Pri zvolení nažítania hry, sa vytvorá nové okno s parametrom 0
                } catch (IOException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                }
                w.setVisible(true);  
                Start.this.setVisible(false);
            }
        }
    }

    private class ObtiaznostListener implements ActionListener { // Listener pre nastavenie obtiažnosti

        @Override
        public void actionPerformed(ActionEvent e) { 
            if (e.getSource() == lahke) {
                obtiaznost = 25;
            }
            if (e.getSource() == stredne) {
                obtiaznost = 45;
            }
            if (e.getSource() == tazke) {
                obtiaznost = 58;
            }
            Window w = null;
            try {
                w = new Window(obtiaznost); // Podľa zvolenej obtiažnosti sa vytvára nové okno hry, s nastaveným parametrom obtiažnosti
            } catch (IOException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            w.setVisible(true);
            Start.this.setVisible(false);
        }
    }
}
