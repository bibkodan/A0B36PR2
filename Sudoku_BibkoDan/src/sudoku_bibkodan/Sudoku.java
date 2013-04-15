/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku_bibkodan;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class Sudoku {

    Scanner scan = new Scanner(System.in);
    public int[][] toPlaySudoku = new int[9][9];       // Sudoku to play
    public int[][] assignedSudoku = new int[9][9];     // Sudoku at the beginning, before the first move. 
    public int[][] fullSudoku = new int[9][9];         // Full resolved sudoku

    public Sudoku() {
        generateFullSudoku(0);
        generateAssignedSudoku();
        generateToPlaySudoku();
    }

    private void mix(int pole[]) {
        Random rand = new Random();
        for (int i = 0; i < pole.length; i++) {
            int j = i + rand.nextInt(pole.length - i);
            int t = pole[j];
            pole[j] = pole[i];
            pole[i] = t;
        }
    }

    private boolean generateFullSudoku(int pom) // Generate full resolved sudoku.
    {
        if (pom > 80) {
            return true;
        }

        int column = pom % 9;
        int row = pom / 9;
        int[] numbers = new int[9];
        for (int i = 0; i < 9; i++) {
            numbers[i] = 1 + i;         // Created sequence of numbers 1 - 9 
        }
        mix(numbers);   // Mix created sequence.

        for (int i = 0; i < 9; i++) {
            int n = numbers[i];
            if (!isInColumn(fullSudoku, n, column)
                    && !isInRow(fullSudoku, n, row)
                    && !isInBlock(fullSudoku, n, row, column)) {
                fullSudoku[row][column] = n;

                if (generateFullSudoku(pom + 1)) {
                    return true;
                }
                fullSudoku[row][column] = 0;
            }
        }
        return false;
    }

    private void generateAssignedSudoku() // Generate assigned sudoku. 
    {
        for (int row = 0; row < 9; row++) {
            System.arraycopy(fullSudoku[row], 0, assignedSudoku[row], 0, 9);
        }

        int[] pole = new int[81];
        for (int i = 0; i < 81; i++) {
            pole[i] = i;
        }
        mix(pole);

        for (int i = 0; i < 45; i++) { // Set 45 of nubmers to zero.
            int pom = pole[i];
            int column = pom % 9;
            int row = pom / 9;
            assignedSudoku[row][column] = 0;
        }
    }

    private void generateToPlaySudoku() { // Just copy assigned sudoku to new empty sudoku. 
        for (int row = 0; row < 9; row++) {
            System.arraycopy(assignedSudoku[row], 0, toPlaySudoku[row], 0, 9);
        }
    }

    public boolean isInBlock(int[][] sudoku, int number, int row, int column) // Check, if our number is in block.
    {
        int start_row = (row / 3) * 3;
        int start_column = (column / 3) * 3;
        for (int r = start_row; r < start_row + 3; r++) {
            for (int c = start_column; c < start_column + 3; c++) {
                if (sudoku[r][c] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInRow(int[][] sudoku, int number, int row) //Check, if our number is in row.
    {
        for (int column = 0; row < 9; column++) {
            if (sudoku[row][column] == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isInColumn(int[][] sudoku, int number, int column) //Check, if our number is in column.
    {
        for (int row = 0; row < 9; row++) {
            if (sudoku[row][column] == number) {
                return true;
            }
        }
        return false;
    }

    public int enterRow() { // Line in which we are going to make a change.
        int row = 99;
        while (row < 0 || row > 9) {
            System.out.println("Please enter the LINE number where you want to make a change");
            System.out.println("           - If you enter  0 - game will be ended.");
            System.out.println("           - If you enter 10 - help move");
            try {
                row = scan.nextInt();
            } catch (Exception e) {
                scan.next();
            }

            if (row < 0 || row > 11) {
                System.out.println("Misentry!!! Repeat entry!");
            }
            if (row == 10) {
                help();
            }
        }
        return row;
    }

    public int enterColumn() { // Column in which we are going to make a change.
        int column = 99;
        while (column < 0 || column > 9) {
            System.out.println("Please enter the COLUMN number where you want to make a change");
            System.out.println("           - If you enter  0 - game will be ended.");
            System.out.println("           - If you enter 10 - help move");
            try {
                column = scan.nextInt();
            } catch (Exception e) {
                scan.next();
            }
            if (column < 0 || column > 10) {
                System.out.println("Misentry!!! Repeat entry!");
            }
            if (column == 10) {
                help();
            }

        }
        return column;
    }

    public int enterNumber() { // Number we want to add to the desired location
        int number = 99;
        while (number < 0 || number > 9) {
            System.out.println("Please enter the number that you want to substitute.");
            System.out.println("           - If you enter  0 - game will be ended.");
            System.out.println("           - If you enter 10 - help move");
            try {
                number = scan.nextInt();
            } catch (Exception e) {
                scan.next();
            }
            if (number < 0 || number > 10) { // Pokial bude nami zvolene number mimo rozsah, hra zahlasi chybu a volba sa opakuje.
                System.out.println("Misentry!!! Repeat entry!");
            }
            if (number == 10) {
                help();
            }
        }
        return number;
    }

    private void help() { // Help mode. Help user find out number on the specific position.
        System.out.println("You have to enter the line number and column number in which you want to find out which number is at that position.");

        int row = 99;
        while (row < 0 || row > 9) {
            System.out.println("Please enter the LINE number.");
            try {
                row = scan.nextInt();
            } catch (Exception e) {
                scan.next();
            }

            if (row < 0 || row > 9) {
                System.out.println("Misentry!!! Repeat entry!");
            }
        }
        int column = 99;
        while (column < 0 || column > 9) {
            System.out.println("Please enter the COLUMN number.");
            try {
                column = scan.nextInt();
            } catch (Exception e) {
                scan.next();
            }
            if (column < 0 || column > 9) {
                System.out.println("Misentry!!! Repeat entry!");
            }
        }
        System.out.printf("The number on selected position is %d%n", fullSudoku[row - 1][column - 1]);
    }

    public boolean checkThePosition(int[][] a, int[][] b, int row, int column) { // Check the position, where we want to make a change. We can not replace number, which was generated on the start of game.
        if (a[row][column] == b[row][column] && a[row][column] != 0) {
            return true;
        }
        return false;
    }

    public boolean victory(int[][] a) {
        int sum = 0;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                sum = sum + a[row][column];
            }
        }
        if (sum == 405) {
            return true;
        }
        return false;
    }

    public void print(int[][] a) {
        System.out.println("    1  2  3     4  5  6     7  8  9");
        System.out.println("    -  -  -     -  -  -     -  -  -");

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (column == 0) {
                    System.out.printf("%d|  %d", row + 1, a[row][column]);
                } else {
                    System.out.printf("  %d", a[row][column]);
                }
            }
            for (int column = 3; column < 6; column++) {
                if (column == 3) {
                    System.out.printf("     %d", a[row][column]);
                } else {
                    System.out.printf("  %d", a[row][column]);
                }
            }
            for (int column = 6; column < 9; column++) {
                if (column == 6) {
                    System.out.printf("     %d", a[row][column]);
                } else {
                    System.out.printf("  %d", a[row][column]);
                }
            }
            System.out.println("");
        }

        System.out.println("");
        for (int row = 3; row < 6; row++) {
            for (int column = 0; column < 3; column++) {
                if (column == 0) {
                    System.out.printf("%d|  %d", row + 1, a[row][column]);
                } else {
                    System.out.printf("  %d", a[row][column]);
                }
            }
            for (int column = 3; column < 6; column++) {
                if (column == 3) {
                    System.out.printf("     %d", a[row][column]);
                } else {
                    System.out.printf("  %d", a[row][column]);
                }
            }
            for (int column = 6; column < 9; column++) {
                if (column == 6) {
                    System.out.printf("     %d", a[row][column]);
                } else {
                    System.out.printf("  %d", a[row][column]);
                }
            }
            System.out.println("");
        }

        System.out.println("");
        for (int row = 6; row < 9; row++) {
            for (int column = 0; column < 3; column++) {
                if (column == 0) {
                    System.out.printf("%d|  %d", row + 1, a[row][column]);
                } else {
                    System.out.printf("  %d", a[row][column]);
                }
            }
            for (int column = 3; column < 6; column++) {
                if (column == 3) {
                    System.out.printf("     %d", a[row][column]);
                } else {
                    System.out.printf("  %d", a[row][column]);
                }
            }
            for (int column = 6; column < 9; column++) {
                if (column == 6) {
                    System.out.printf("     %d", a[row][column]);
                } else {
                    System.out.printf("  %d", a[row][column]);
                }
            }
            System.out.println("");
        }
    }

    public void save(int[][] a, int[][] b, int[][] c) throws IOException { // Saving the game. 
        FileWriter fw = new FileWriter("c:Users/Daniel/Documents/NetBeansProjects/A0B36PR2/Sudoku_BibkoDan/sudoku.txt");
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                fw.write(a[row][column]);
            }
        }
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                fw.write(b[row][column]);
            }
        }
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                fw.write(c[row][column]);
            }
        }
        fw.close();
    }

    public void load(int[][] a, int[][] b, int[][] c) throws IOException {
        FileReader fr = new FileReader("c:Users/Daniel/Documents/NetBeansProjects/A0B36PR2/Sudoku_BibkoDan/sudoku.txt");
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                a[row][column] = fr.read();
            }
        }
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                b[row][column] = fr.read();
            }
        }
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                c[row][column] = fr.read();
            }
        }
        fr.close();
    }
}
