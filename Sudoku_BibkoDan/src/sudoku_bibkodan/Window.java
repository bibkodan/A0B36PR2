/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku_bibkodan;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Daniel
 */
public class Window extends JFrame {

    Double cislo;
    int riadok;
    int stlpec;
    int cislo1;
    boolean tah = false;
    Tlacitko vymaz;
    Tlacitko help;
    Tlacitko cisla[] = new Tlacitko[10];
    Tlacitko hernePole[][];
    JTextArea l1 = new JTextArea();
    HernePoleListener o1 = new HernePoleListener();
    ObsluhaCiselListener o3 = new ObsluhaCiselListener();
    PomocneTlacitkaListener o4 = new PomocneTlacitkaListener();
    Sudoku sudoku;

    public Window(int a) throws IOException {
        super("SUDOKU  BIBKODAN");
        this.setSize(660, 510);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        hernePole = new Tlacitko[9][9]; // Pole tlačítok 9*9 pre hru
        sudoku = new Sudoku(a); // Vytvorenie sudoku (vyrieseneho, herneho, zadaneho), parameter a rozhoduje či sa bude hrať hra nová a akej obtiažnosti
        
        if (a == 0) { // Pri voľbe v predošlom okne na načítanie rozohranej hry sa načíta uložená
            sudoku.nacitanie(sudoku.herneSudoku, sudoku.zadaneSudoku, sudoku.vyrieseneSudoku);
        }

        for (int i = 0; i < 9; i++) { 
            for (int j = 0; j < 9; j++) {
                String pom = String.format("" + sudoku.herneSudoku[i][j]); 
                if (pom.equals("0")) { 
                    pom = ("");
                }
                hernePole[i][j] = new Tlacitko(pom); // Naplnenie poľa pre hru tlačítkami každé bude zobrazovať číslo z vypneného sudoku

                if (sudoku.zadaneSudoku[i][j] == 0) { // Pokiaľ na danej pozícii sa nenachádza číslo (v poli sa nachádza nula, uživáteľ tam číslo nevidí), nastavuje sa farba tlačítka na bielo pre rozlíšnie
                    hernePole[i][j].setBackground(Color.white);
                }
                hernePole[i][j].addActionListener(o1);
            }
        }

        //Implementovanie panelov, ktoré obsahujú jednotlivé časti herného poľa
        JPanel p00 = new JPanel(); 
        p00.setLayout(new GridLayout(3, 3));
        p00.setBounds(5, 5, 150, 150);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                p00.add(hernePole[i][j]);
            }
        }
        this.add(p00);

        JPanel p01 = new JPanel();
        p01.setLayout(new GridLayout(3, 3));
        p01.setBounds(160, 5, 150, 150);
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                p01.add(hernePole[i][j]);
            }
        }
        this.add(p01);

        JPanel p02 = new JPanel();
        p02.setLayout(new GridLayout(3, 3));
        p02.setBounds(315, 5, 150, 150);
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j < 9; j++) {
                p02.add(hernePole[i][j]);
            }
        }
        this.add(p02);

        JPanel p10 = new JPanel();
        p10.setLayout(new GridLayout(3, 3));
        p10.setBounds(5, 160, 150, 150);
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                p10.add(hernePole[i][j]);
            }
        }
        this.add(p10);

        JPanel p11 = new JPanel();
        p11.setLayout(new GridLayout(3, 3));
        p11.setBounds(160, 160, 150, 150);
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                p11.add(hernePole[i][j]);
            }
        }
        this.add(p11);

        JPanel p12 = new JPanel();
        p12.setLayout(new GridLayout(3, 3));
        p12.setBounds(315, 160, 150, 150);
        for (int i = 3; i < 6; i++) {
            for (int j = 6; j < 9; j++) {
                p12.add(hernePole[i][j]);
            }
        }
        this.add(p12);

        JPanel p20 = new JPanel();
        p20.setLayout(new GridLayout(3, 3));
        p20.setBounds(5, 315, 150, 150);
        for (int i = 6; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                p20.add(hernePole[i][j]);
            }
        }
        this.add(p20);

        JPanel p21 = new JPanel();
        p21.setLayout(new GridLayout(3, 3));
        p21.setBounds(160, 315, 150, 150);
        for (int i = 6; i < 9; i++) {
            for (int j = 3; j < 6; j++) {
                p21.add(hernePole[i][j]);
            }
        }
        this.add(p21);

        JPanel p22 = new JPanel();
        p22.setLayout(new GridLayout(3, 3));
        p22.setBounds(315, 315, 150, 150);
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                p22.add(hernePole[i][j]);
            }
        }
        this.add(p22);

        JPanel p32 = new JPanel(); // Implementovanie panela s číslami, ktoré sa využívajú pri dosadzovaní
        p32.setLayout(new GridLayout(3, 3));
        p32.setBounds(485, 315, 150, 150);
        for (int i = 1; i < 10; i++) {
            String pom = String.format("" + i);
            cisla[i] = new Tlacitko(pom);
            cisla[i].addActionListener(o3);
            p32.add(cisla[i]);
        }
        this.add(p32);

        vymaz = new Tlacitko("( - )", 485, 285, 70, 30);
        vymaz.addActionListener(o4);
        this.add(vymaz);

        help = new Tlacitko("help", 565, 285, 70, 30);
        help.addActionListener(o4);
        this.add(help);

        l1.setBounds(485, 5, 150, 180); // Priestor, kde sa vypisujú pokyny pre užívateľa
        l1.setText("Klikni na policko,"
                + "\nkde chces zmenit cislo");
        l1.setEditable(false);
        this.add(l1);

    }

    private class HernePoleListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!tah) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (e.getSource() == hernePole[i][j] && sudoku.zadaneSudoku[i][j] == 0) { // Kliknutie na tlačítko z herného poľa puzzle sudoku 9*9, pričom to nie je tlačítko s vygenerovaným číslom na začiatku
                            riadok = i;
                            stlpec = j;
                            l1.setText("Klikni na cislo, "
                                    + "\nktore chces "
                                    + "\nna dane miesto dosadit."
                                    + "\n\nKlikni na HELP"
                                    + "\npre automaticke dosadenie."
                                    + "\n\nPri stlaceni ( - ) sa vymaze"
                                    + "\nuz zadane cislo.");

                            hernePole[i][j].setBackground(Color.YELLOW);
                            tah = true;
                        }
                    }
                }
            }
        }
    }

    private class PomocneTlacitkaListener implements ActionListener { 

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                sudoku.ukladanie(sudoku.herneSudoku, sudoku.zadaneSudoku, sudoku.vyrieseneSudoku); // Uloženie rozohratej hry
                if (tah) {
                    if (e.getSource() == vymaz) { // Kliknuttie na tlačítko pre vymazanie použivateľom zadaného čísla
                        tah = false;
                        hernePole[riadok][stlpec].setBackground(Color.white);
                        hernePole[riadok][stlpec].setText("");
                        sudoku.herneSudoku[riadok][stlpec] = 0; // Nastavenie hodnoty 0 v poli pre sudoku na hranie
                        l1.setText("Klikni na policko,"
                                + "\nkde chces zmenit cislo");
                    }
                    if (e.getSource() == help) { // Kliknuttie na tlačítko pre pomoc 
                        tah = false;
                        String pom = String.format("" + sudoku.vyrieseneSudoku[riadok][stlpec]);
                        hernePole[riadok][stlpec].setText(pom); 
                        hernePole[riadok][stlpec].setBackground(Color.white);
                        sudoku.herneSudoku[riadok][stlpec] = sudoku.vyrieseneSudoku[riadok][stlpec];
                        l1.setText("Klikni na policko,"
                                + "\nkde chces zmenit cislo");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class ObsluhaCiselListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                sudoku.ukladanie(sudoku.herneSudoku, sudoku.zadaneSudoku, sudoku.vyrieseneSudoku);
                if (tah) {
                    for (int i = 1; i < 10; i++) {
                        if (e.getSource() == cisla[i]) { // Zachytenie kliku na nejaké číslo v rozmedzí 1-9
                            cislo = Double.parseDouble(cisla[i].getText());
                            cislo1 = cislo.intValue();
                            String pom = String.format("" + cislo1);
                            l1.setText("Klikni na policko,"
                                    + "\nkde chces zmenit cislo");
                            tah = false;
                            hernePole[riadok][stlpec].setBackground(Color.white);

                            if (sudoku.existujeVriadku(sudoku.herneSudoku, cislo1, riadok) // Kolntrola či sa užívateľ nesnaží dosadi´t číslo proti pravidlám hry sudoku
                                    || sudoku.existujeVstlpci(sudoku.herneSudoku, cislo1, stlpec)
                                    || sudoku.existujeVbunke(sudoku.herneSudoku, cislo1, riadok, stlpec)) {
                                l1.setText("Porusenie pravidiel."
                                        + "\n\nOpakuj Zadanie!");
                            } else { // Pokiaľ nedochádza k porušeniu pravidiel vybrané číslo sa dosadzuje
                                sudoku.herneSudoku[riadok][stlpec] = cislo1;
                                String pom1 = String.format("" + cislo1);
                                hernePole[riadok][stlpec].setText(pom1);
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
