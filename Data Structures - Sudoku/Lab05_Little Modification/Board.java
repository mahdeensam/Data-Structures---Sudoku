import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Board {
    private Cell[][] cells;

    public Board() {
        cells = new Cell[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new Cell(row, col, 0);
            }
        }
    }

    public Cell get(int row, int col) {
        return cells[row][col];
    }

    public void set(int row, int col, int value) {
        cells[row][col].setValue(value);
    }

    public void set(int row, int col, boolean locked) {
        cells[row][col].setLocked(locked);
    }

    public boolean read(String filename) {
        try {
            // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor

            FileReader fr = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor

            BufferedReader br = new BufferedReader(fr);
            // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
           
            String line = br.readLine();
            int row = 0;
            while (line != null && row < 9) {
                String[] values = line.split("[ ]+");
                for (int col = 0; col < 9 && col < values.length; col++) {
                    int value = Integer.parseInt(values[col]);
                    if (value != 0) {
                        cells[row][col].setValue(value);
                        cells[row][col].setLocked(true);
                    }
                }
                row++;
                line = br.readLine();
            }
            br.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        } catch (NumberFormatException ex) {
            System.out.println("Board.read():: invalid value in file " + filename);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            if (row > 0 && row % 3 == 0) {
                sb.append("------+-------+------\n");
            }
            for (int col = 0; col < 9; col++) {
                if (col > 0 && col % 3 == 0) {
                    sb.append("| ");
                }
                sb.append(cells[row][col]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        testBoard(); // Call the testBoard method unconditionally
        if (args.length != 1) {
            System.out.println("Usage: java Board <filename>");
            return;
        }
        Board board = new Board();
        if (board.read(args[0])) {
            System.out.println(board);
        }
    }
    

    public static void testBoard() {
        // Test 1: Create a new empty board
        System.out.println("Test 1: Create a new empty board");
        Board emptyBoard = new Board();
        System.out.println("Empty board:\n" + emptyBoard);

        // Test 2: Set and get values of cells
        System.out.println("Test 2: Set and get values of cells");
        emptyBoard.set(0, 0, 5);
        emptyBoard.set(1, 1, 9);
        emptyBoard.set(8, 8, 4);
        System.out.println("Board after setting values:\n" + emptyBoard);
        System.out.println("Cell (0, 0): " + emptyBoard.get(0, 0));
        System.out.println("Cell (1, 1): " + emptyBoard.get(1, 1));
        System.out.println("Cell (8, 8): " + emptyBoard.get(8, 8));
        
        
}}