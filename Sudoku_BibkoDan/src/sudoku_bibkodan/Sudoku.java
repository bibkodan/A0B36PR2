/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku_bibkodan;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class Sudoku {
    
    Scanner scan = new Scanner(System.in);
    public int[][] herneSudoku = new int[9][9]; // Herne sudoku
    public int[][] zadaneSudoku = new int[9][9]; // Zaciatocne sudoku 
    public int[][] vyrieseneSudoku = new int[9][9]; // Vygenerovanie vyrieseneho sudoku
    private Random rand = new Random();

    public Sudoku() // Konstruktor generuje nove sudoku a jeho riesenie
    {
        generujVyrieseneSudoku(0);
        generujZadaneSudoku();
        generujHerneSudoku();
    }

    private boolean generujVyrieseneSudoku(int pom) // Vygeneruje vyplnene sudoku puzzle.
    {
        if (pom > 80) {
            return true;
        }

        int stlpec = pom % 9;
        int riadok = pom / 9;
        int[] cisla = new int[9];
        for (int i = 0; i < 9; i++) {
            cisla[i] = 1 + i; // Vytvori postupnosť celých čísel 1 - 9 
        }
        zamiesa(cisla); // Vytvorenu postupnost zamiesa.

        for (int i = 0; i < 9; i++) {
            int n = cisla[i];
            if (!existujeVstlpci(vyrieseneSudoku, n, stlpec)
                    && !existujeVriadku(vyrieseneSudoku, n, riadok)
                    && !existujeVbunke(vyrieseneSudoku, n, riadok, stlpec)) {
                vyrieseneSudoku[riadok][stlpec] = n; //Pokial prejde podmienkou na zistenie podla pravidiel v sudoku, tak sa na danu poziciu zapise cislo.

                if (generujVyrieseneSudoku(pom + 1)) {
                    return true;
                }
                vyrieseneSudoku[riadok][stlpec] = 0; // Ak sa cislo nemoze dosatit na danu pozicii kvoli pravidlam, nastavi 0.
            }
        }
        return false;
    }

    private void generujZadaneSudoku() // Vygeneruje Sudoku uz z vyrieseneho 
    {
        for (int riadok = 0; riadok < 9; riadok++) {
            for (int stlpec = 0; stlpec < 9; stlpec++) {
                zadaneSudoku[riadok][stlpec] = vyrieseneSudoku[riadok][stlpec]; // Kopirovanie vyrieseneho sudoku.
            }
        }

        int[] pole = new int[81];
        for (int i = 0; i < 81; i++) {
            pole[i] = i;
        }
        zamiesa(pole);

        for (int i = 0; i < 45; i++) {
            int pom = pole[i];
            int stlpec = pom % 9;
            int riadok = pom / 9;
            zadaneSudoku[riadok][stlpec] = 0;
        }
    }

    private void generujHerneSudoku() { // Skopirovanie pociatocneho sudoku puzzle do puzzle pre hranie.
        for (int riadok = 0; riadok < 9; riadok++) {
            for (int stlpec = 0; stlpec < 9; stlpec++) {
                herneSudoku[riadok][stlpec] = zadaneSudoku[riadok][stlpec];
            }
        }
    }

    public boolean existujeVbunke(int[][] sudoku, int cislo, int riadok, int stlpec) //Kontrola existencie cisa v bunke 3*3
    {
        int start_riadok = (riadok / 3) * 3;
        int start_stlpec = (stlpec / 3) * 3;
        for (int r = start_riadok; r < start_riadok + 3; r++) {
            for (int s = start_stlpec; s < start_stlpec + 3; s++) {
                if (sudoku[r][s] == cislo) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existujeVriadku(int[][] sudoku, int cislo, int riadok) //Kontrola existencie cisla v riadku.
    {
        for (int stlpec = 0; stlpec < 9; stlpec++) {
            if (sudoku[riadok][stlpec] == cislo) {
                return true;
            }
        }
        return false;
    }

    public boolean existujeVstlpci(int[][] sudoku, int cislo, int stlpec) //Kontorla ci sa cislo nachadza v stlpci. 
    {
        for (int riadok = 0; riadok < 9; riadok++) {
            if (sudoku[riadok][stlpec] == cislo) {
                return true;
            }
        }
        return false;
    }

    private void zamiesa(int pole[]) {
        for (int i = 0; i < pole.length; i++) {
            int j = i + rand.nextInt(pole.length - i);
            int t = pole[j];
            pole[j] = pole[i];
            pole[i] = t;
        }
    }

    public boolean vytaztvo(int[][] a) { // Kontroluje ci sme dosiahli sucet cisel na vsetkcyh poziciach 405, co znamena ze sme vsetko spravne vyplnili.
        int sucet = 0;
        for (int riadok = 0; riadok < 9; riadok++) {
            for (int stlpec = 0; stlpec < 9; stlpec++) {
                sucet = sucet + a[riadok][stlpec];
            }
        }
        if (sucet == 405) {
            return true;
        }
        return false;
    }

    
    public void ukladanie(int[][] a, int[][] b, int[][] c) throws IOException { // Ukladanie hry, program uklada tri polia. 
        FileWriter fw = new FileWriter("c:/Users/Daniel/Documents/NetBeansProjects/Bibko_Daniel_Semestralka_Sudoku/sudoku.txt");
        for (int riadok = 0; riadok < 9; riadok++) {
            for (int stlpec = 0; stlpec < 9; stlpec++) {
                fw.write(a[riadok][stlpec]);
            }
        }
        for (int riadok = 0; riadok < 9; riadok++) {
            for (int stlpec = 0; stlpec < 9; stlpec++) {
                fw.write(b[riadok][stlpec]);
            }
        }
        for (int riadok = 0; riadok < 9; riadok++) {
            for (int stlpec = 0; stlpec < 9; stlpec++) {
                fw.write(c[riadok][stlpec]);
            }
        }
        fw.close();
    }

    public void nacitanie(int[][] a, int[][] b, int[][] c) throws IOException { // Nacitanie ulozenej hry.
        FileReader fr = new FileReader("c:/Users/Daniel/Documents/NetBeansProjects/Bibko_Daniel_Semestralka_Sudoku/sudoku.txt");
        for (int riadok = 0; riadok < 9; riadok++) {
            for (int stlpec = 0; stlpec < 9; stlpec++) {
                a[riadok][stlpec] = fr.read();
            }
        }
        for (int riadok = 0; riadok < 9; riadok++) {
            for (int stlpec = 0; stlpec < 9; stlpec++) {
                b[riadok][stlpec] = fr.read();
            }
        }
        for (int riadok = 0; riadok < 9; riadok++) {
            for (int stlpec = 0; stlpec < 9; stlpec++) {
                c[riadok][stlpec] = fr.read();
            }
        }
        fr.close();
    }
}
