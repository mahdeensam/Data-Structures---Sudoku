/* 
Mahdeen Ahmed Khan Sameer
SudokuPerformanceTest class: To test the performance of 4*4 Sudoku. 

*/

import java.util.Random;

public class SudokuPerformanceTest {

    public static void main(String[] args) {
        int size = 9;
        Board board = new Board();
        Sudoku sudoku = new Sudoku(board);

        // Generate a solvable Sudoku puzzle
        generateSolvablePuzzle(sudoku);

        // Measure the time it takes to solve the puzzle
        long startTime = System.currentTimeMillis();
        boolean solved = sudoku.solve(0);
        long endTime = System.currentTimeMillis();

        if (solved) {
            System.out.println("Sudoku puzzle solved!");
            System.out.println(sudoku);
        } else {
            System.out.println("Failed to solve the Sudoku puzzle.");
        }

        System.out.printf("Solving time: %d ms%n", endTime - startTime);
    }
    private static void generateSolvablePuzzle(Sudoku sudoku) {
        // Solve the empty board to create a complete and valid solution
        sudoku.solve(0);
    
        // Remove some numbers from the completed board while ensuring that the puzzle remains solvable
        int numRemoved = 0;
        int size = sudoku.getBoard().getRows();
        int numToRemove = size * size / 2; // Change this value based on the size of the board
        Random random = new Random();
    
        while (numRemoved < numToRemove) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
    
            if (sudoku.getBoard().get(row, col).getValue() != 0) {
                int oldValue = sudoku.getBoard().get(row, col).getValue();
                sudoku.getBoard().get(row, col).setValue(0);
    
                Sudoku tempSudoku = new Sudoku(sudoku.getBoard().copy()); // Create a copy of the board
                if (tempSudoku.solve(0)) {
                    numRemoved++;
                } else {
                    sudoku.getBoard().get(row, col).setValue(oldValue);
                }
            }
        }
    }
    
    }

