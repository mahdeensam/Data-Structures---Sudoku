/* 
Mahdeen Ahmed Khan Sameer
SudokuTest class: Test Sudoku Class

*/

import java.io.IOException;

public class SudokuTest {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java SudokuTest <filename>");
            return;
        }

        String filename = args[0];

        try {
            Sudoku sudoku = new Sudoku(filename);
            System.out.println("Original board:");
            System.out.println(sudoku);
            if (sudoku.solve()) {
                System.out.println("Solved board:");
                System.out.println(sudoku);
            } else {
                System.out.println("No solution found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + filename);
            e.printStackTrace();
        }
    }
}
