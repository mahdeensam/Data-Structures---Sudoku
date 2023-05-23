
/* 
Mahdeen Ahmed Khan Sameer
Board class: To form a 4x4 Sudoku game board while having variables of 2D array of Cell objects, representing the cells of the board. 
*/

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Board {
    private Cell[][] grid;
    public static final int SIZE = 4;
    private boolean finished;

    public Board() {
        grid = new Cell[SIZE][SIZE];
        finished = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell(i, j, 0);
            }
        }
    }
//Extenstion1
public Cell findCellWithFewestChoices() {
    Cell cellWithFewestChoices = null;
    int minChoices = Integer.MAX_VALUE;

    for (int row = 0; row < SIZE; row++) {
        for (int col = 0; col < SIZE; col++) {
            Cell cell = get(row, col);
            if (cell.getValue() == 0) {
                int choices = 0;
                for (int value = 1; value <= SIZE; value++) {
                    if (validValue(row, col, value)) {
                        choices++;
                    }
                }
                if (choices < minChoices) {
                    minChoices = choices;
                    cellWithFewestChoices = cell;
                }
            }
        }
    }

    return cellWithFewestChoices;
}

public int getColumns() {
    return SIZE;
}


//Final extenstion
public Board copy() {
    Board newBoard = new Board();
    for (int row = 0; row < getRows(); row++) {
        for (int col = 0; col < getColumns(); col++) {
            newBoard.set(row, col, get(row, col).getValue());
        }
    }
    return newBoard;
}


    public int getUnspecifiedCells() {
        int count = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (get(row, col).getValue() == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public Board(String filename) {
        this();
        read(filename);
    }

    public Board(int numLockedCells) {
        this();
        Random random = new Random();
        int count = 0;

        while (count < numLockedCells) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            int value = random.nextInt(SIZE) + 1;

            if (grid[row][col].getValue() == 0 && validValue(row, col, value)) {
                grid[row][col].setValue(value);
                grid[row][col].setLocked(true);
                count++;
            }
        }
    }

    public void read(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < SIZE; i++) {
                String[] line = br.readLine().trim().split(" ");
                for (int j = 0; j < SIZE; j++) {
                    int value = Integer.parseInt(line[j]);
                    boolean locked = value != 0;
                    grid[i][j] = new Cell(i, j, value, locked);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public int getCols() {
        return SIZE;
    }

    public int getRows() {
        return SIZE;
    }

    public Cell get(int r, int c) {
        return grid[r][c];
    }

    public boolean isLocked(int r, int c) {
        return grid[r][c].isLocked();
    }

    public int numLocked() {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j].isLocked()) {
                    count++;
                }
            }
        }
        return count;
    }

    public int value(int r, int c) {
        return grid[r][c].getValue();
    }

    public void set(int r, int c, int value) {
        grid[r][c].setValue(value);
    }

    public void set(int r, int c, int value, boolean locked) {
        grid[r][c].setValue(value);
        grid[r][c].setLocked(locked);
    }

    public boolean validValue(int row, int col, int value) {
        if (value < 1 || value > SIZE) {
            return false;
        }
    
        for (int i = 0; i < SIZE; i++) {
            if (i != row && grid[i][col].getValue() == value) {
                return false;
            }
            if (i != col && grid[row][i].getValue() == value) {
                return false;
            }
        }
    
        int boxRow = row / 2 * 2;
        int boxCol = col / 2 * 2;
        for (int i = boxRow; i < boxRow + 2; i++) {
            for (int j = boxCol; j < boxCol + 2; j++) {
                if (i != row && j != col && grid[i][j].getValue() == value) {
                    return false;
                }
            }
        }
        
    
        return true;
    }
    


    public boolean validSolution() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = grid[row][col].getValue();
                if (value == 0 || !validValue(row, col, value)) {
                    return false;
                }
            }
        }

        return true;
    }

    //For Exploration
    public void clear() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col].setValue(0); // Change this line
            }
        }
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            if (i % 2 == 0 && i != 0) {
                sb.append("------+-------+------\n");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 2 == 0 && j != 0) {
                    sb.append("| ");
                }
                sb.append(grid[i][j].getValue());
                if (j < SIZE - 1) {
                    sb.append(" ");
                }
            }
            if (i < SIZE - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void draw(Graphics g, int scale) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col].draw(g, 4, 9, scale);
            }
        }
    
        g.setColor(Color.BLACK);
        for (int i = 0; i <= SIZE; i++) {
            int width = (i % 2 == 0) ? 3 : 1; // Change the modulus value to 2
            g.fillRect(4 + i * scale, 9, width, SIZE * scale);
            g.fillRect(4, 9 + i * scale, SIZE * scale, width);
        }
    
        if (finished) {
            if (validSolution()) {
                g.setColor(new Color(0, 127, 0));
                g.drawString(":P", scale * 2 + 5, scale * 5 + 10); // Adjust the position of the smiley face
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawString(":)", scale * 2 + 5, scale * 5 + 10); // Adjust the position of the smiley face
            }
        }
    }
    
    
    

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Board <filename>");
            return;
        }
        String filename = args[0];

        Board board = new Board(filename);
        System.out.println("Board from file:");
        System.out.println(board);
        System.out.println("Is the board solved? " + board.validSolution());

        Board randomBoard = new Board(20);
        System.out.println("Randomly generated board:");
        System.out.println(randomBoard);
        System.out.println("Is the board solved? " + randomBoard.validSolution());
    }
}


            

