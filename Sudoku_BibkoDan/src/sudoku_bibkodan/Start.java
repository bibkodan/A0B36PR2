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
    Tlacitko easy;
    Tlacitko medium;
    Tlacitko hard;
    Obsluha o = new Obsluha();
    Obsluha1 o1 = new Obsluha1();

    public Start() {
        super("SUDOKU  BIBKODAN START");
        this.setSize(285, 180);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        start = new Tlacitko("New game");
        start.setBounds(10, 10, 120, 50);
        start.addActionListener(o);
        this.add(start);

        load = new Tlacitko("Load game");
        load.setBounds(140, 10, 120, 50);
        load.addActionListener(o);
        this.add(load);
    }

    class Obsluha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == start) {
                easy = new Tlacitko("easy");
                easy.setBounds(10, 70, 80, 50);
                easy.addActionListener(o1);
                Start.this.add(easy);

                medium = new Tlacitko("medium");
                medium.setBounds(95, 70, 80, 50);
                medium.addActionListener(o1);
                Start.this.add(medium);

                hard = new Tlacitko("Hard");
                hard.setBounds(180, 70, 80, 50);
                hard.addActionListener(o1);
                Start.this.add(hard);
                Start.this.setVisible(false);
                Start.this.setVisible(true);
            }
            if (e.getSource() == load) {
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

    class Obsluha1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == easy) {
                obtiaznost = 25;
            }
            if (e.getSource() == medium) {
                obtiaznost = 45;
            }
            if (e.getSource() == hard) {
                obtiaznost = 58;
            }
            Window w = null;
            try {
                w = new Window(obtiaznost);
            } catch (IOException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            w.setVisible(true);
            Start.this.setVisible(false);
        }
    }
}
