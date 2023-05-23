/* 
Mahdeen Ahmed Khan Sameer
Sudoku class: To provide a flexible and extensible representation of a Sudoku game board with methods to access, modify, solve and check the validity of the board and its cells.

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.Random;


public class Sudoku {
    private Board board;
    private LandscapeDisplay ld;

    public Sudoku(String filePath) throws IOException {
        board = new Board();
        loadBoardFromFile(filePath);
        ld = new LandscapeDisplay(board);
    }

    public Sudoku(Board board) {
        this.board = board;
        ld = new LandscapeDisplay(board);
    }

    public Board getBoard() {
        return board;
    }



    private void loadBoardFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        for (int row = 0; row < Board.SIZE; row++) {
            String line = reader.readLine();
            if (line == null) {
                throw new IOException("File format is incorrect. Not enough rows.");
            }
            String[] values = line.split(" ");
            if (values.length != Board.SIZE) {
                throw new IOException("File format is incorrect. Not enough columns.");
            }
            for (int col = 0; col < Board.SIZE; col++) {
                int value = Integer.parseInt(values[col]);
                board.get(row, col).setValue(value);
            }
        }
        reader.close();
    }

    private int findNextValue(Cell cell) {
        for (int value = cell.getValue() + 1; value <= Board.SIZE; value++) {
            if (board.validValue(cell.getRow(), cell.getCol(), value)) {
                return value;
            }
        }
        return 0;
    }

    private Cell findUnspecifiedCell() {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                Cell cell = board.get(row, col);
                if (cell.getValue() == 0) {
                    return cell;
                }
            }
        }
        return null;
    }

    public boolean solve(int delay) {
        Stack<Cell> stack = new Stack<>();
        Cell unspecifiedCell = findUnspecifiedCell();
    
        while (unspecifiedCell != null) {
            int nextValue = findNextValue(unspecifiedCell);
            if (nextValue != 0) {
                unspecifiedCell.setValue(nextValue);
                stack.push(unspecifiedCell);
                unspecifiedCell = findUnspecifiedCell();
            } else {
                unspecifiedCell.setValue(0);
                if (!stack.isEmpty()) { // Check if the stack is not empty
                    unspecifiedCell = stack.pop();
                } else {
                    break;
                }
            }
    
            // Add a sleep function and repaint to visualize the solving process
            try {
                Thread.sleep(delay); // Adjust the sleep duration using the delay parameter
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ld.repaint();
        }
    
        return board.validSolution();
    }

      

    @Override
    public String toString() {
        return board.toString();
    }

    private void generateRandomBoard(int initialValues) {
        Random random = new Random();
        int count = 0;
    
        while (count < initialValues) {
            int row = random.nextInt(Board.SIZE);
            int col = random.nextInt(Board.SIZE);
            int value = random.nextInt(Board.SIZE) + 1;
    
            if (board.get(row, col).getValue() == 0 && board.validValue(row, col, value)) {
                board.get(row, col).setValue(value);
                count++;
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Sudoku <filename>");
            return;
        }
        String filePath = args[0];
        try {
            Sudoku sudoku = new Sudoku(filePath);
            System.out.println("Initial board:");
            System.out.println(sudoku);
    
            // Add a delay before the solving starts, so you can see the initial board
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            boolean solved = sudoku.solve(10);
            sudoku.board.setFinished(true);
            sudoku.ld.repaint();
    
            System.out.println("Solved: " + solved);
            System.out.println("Solution:");
            System.out.println(sudoku);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}



