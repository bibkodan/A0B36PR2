/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku_bibkodan;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 *
 * @author Daniel
 */
public class Start extends JFrame {

    Tlacitko start;
    Tlacitko load;
    

    public Start() {
        super("SUDOKU  BIBKODAN START");
        this.setSize(250, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        start = new Tlacitko("New game");
        start.setBounds(10, 10, 100, 50);
        start.addActionListener(o);
        this.add(start);

        load = new Tlacitko("Load game");
        load.setBounds(120, 10, 100, 50);
        load.addActionListener(o);
        this.add(load);
    }

    class Obsluha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == start) {
                Window w = null;
                try {
                    w = new Window(1);
                } catch (IOException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                }
                w.setVisible(true);
                Start.this.setVisible(false);
            }if(e.getSource() == load){
            Window w = null;
                try {
                    w = new Window(0);
                } catch (IOException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                }
                w.setVisible(true);
                Start.this.setVisible(false);
            }
        }
    }
    Obsluha o = new Obsluha();
}
