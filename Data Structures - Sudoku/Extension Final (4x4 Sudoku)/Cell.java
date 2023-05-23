
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

