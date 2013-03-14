/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku_bibkodan;

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

    private boolean generateFullSudoku(int pom)         // Generate full resolved sudoku.
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
            for (int column = 0; column < 9; column++) {
                assignedSudoku[row][column] = fullSudoku[row][column]; // Copy full sudoku, to our new empty sudoku.
            }
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
            for (int column = 0; column < 9; column++) {
                toPlaySudoku[row][column] = assignedSudoku[row][column];
            }
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
    
}
