
/* 
Mahdeen Ahmed Khan Sameer
Cell class: Cell represents a single cell in a grid. The purpose of this class is to provide a basic structure to store and manipulate the data associated with a cell in a grid-based Sudoku puzzle.
*/

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private int row;
    private int col;
    private int value;
    private boolean locked;

    public Cell() {
        this.row = 0;
        this.col = 0;
        this.value = 0;
        this.locked = false;
    }

    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = false;
    }

    public Cell(int row, int col, int value, boolean locked) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = locked;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newval) {
        this.value = newval;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean lock) {
        this.locked = lock;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public void draw(Graphics g, int x0, int y0, int scale) {
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked() ? Color.BLUE : Color.RED);
        g.drawChars(new char[]{toDraw}, 0, 1, x0 + col * scale + scale / 2 - 4 + 4, y0 + row * scale + scale / 2 + 4 - 1);
    }
    
}    

// public class Cell {
//     private int row;
//     private int col;
//     private int value;
//     private boolean locked;

//     public Cell() {
//         this.row = 0;
//         this.col = 0;
//         this.value = 0;
//         this.locked = false;
//     }

//     public Cell(int row, int col, int value) {
//         this.row = row;
//         this.col = col;
//         this.value = value;
//         this.locked = false;
//     }

//     public Cell(int row, int col, int value, boolean locked) {
//         this.row = row;
//         this.col = col;
//         this.value = value;
//         this.locked = locked;
//     }

//     public int getRow() {
//         return row;
//     }

//     public int getCol() {
//         return col;
//     }

//     public int getValue() {
//         return value;
//     }

//     public void setValue(int newval) {
//         this.value = newval;
//     }

//     public boolean isLocked() {
//         return locked;
//     }

//     public void setLocked(boolean lock) {
//         this.locked = lock;
//     }

//     public String toString() {
//         return Integer.toString(value);
//     }


//     public static void testDefaultConstructor() {
//         System.out.println("Testing default constructor...");
//         Cell cell1 = new Cell();
//         assert cell1.getRow() == 0;
//         assert cell1.getCol() == 0;
//         assert cell1.getValue() == 0;
//         assert !cell1.isLocked();
//         System.out.println("Default constructor test passed!");
//     }

//     public static void testConstructorWithRowColAndValue() {
//         System.out.println("Testing constructor with row, col, and value...");
//         Cell cell2 = new Cell(2, 3, 5);
//         assert cell2.getRow() == 2;
//         assert cell2.getCol() == 3;
//         assert cell2.getValue() == 5;
//         assert !cell2.isLocked();
//         System.out.println("Constructor with row, col, and value test passed!");
//     }

//     public static void testConstructorWithRowColValueAndLocked() {
//         System.out.println("Testing constructor with row, col, value, and locked...");
//         Cell cell3 = new Cell(1, 1, 3, true);
//         assert cell3.getRow() == 1;
//         assert cell3.getCol() == 1;
//         assert cell3.getValue() == 3;
//         assert cell3.isLocked();
//         System.out.println("Constructor with row, col, value, and locked test passed!");
//     }

//     public static void testSetValue() {
//         System.out.println("Testing setValue method...");
//         Cell cell1 = new Cell();
//         cell1.setValue(7);
//         assert cell1.getValue() == 7;
//         System.out.println("setValue method test passed!");
//     }

//     public static void testSetLocked() {
//         System.out.println("Testing setLocked method...");
//         Cell cell2 = new Cell(2, 3, 5);
//         cell2.setLocked(true);
//         assert cell2.isLocked();
//         System.out.println("setLocked method test passed!");
//     }

//     public static void testToString() {
//         System.out.println("Testing toString method...");
//         Cell cell1 = new Cell(1, 1, 7);
//         Cell cell2 = new Cell(2, 3, 5);
//         Cell cell3 = new Cell(1, 1, 3, true);
//         assert cell1.toString().equals("7");
//         assert cell2.toString().equals("5");
//         assert cell3.toString().equals("3");
//         System.out.println("toString method test passed!");
//     }

//     public static void main(String[] args) {
//         testDefaultConstructor();
//         testConstructorWithRowColAndValue();
//         testConstructorWithRowColValueAndLocked();
//         testSetValue();
//         testSetLocked();
//         testToString();
//         System.out.println("All tests passed!");
//     }
// }