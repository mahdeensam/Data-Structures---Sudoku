/* 
Mahdeen Ahmed Khan Sameer
CellTest class: To test Cell class

*/

public class CellTest {
    public static void main(String[] args) {
        CellTest cellTest = new CellTest();
        cellTest.testConstructors();
        cellTest.testGetters();
        cellTest.testSetters();
        cellTest.testToString();
        System.out.println("All tests passed.");
    }

    public void testConstructors() {
        // Test default constructor
        Cell cell1 = new Cell();
        assert cell1.getRow() == 0;
        assert cell1.getCol() == 0;
        assert cell1.getValue() == 0;
        assert !cell1.isLocked();

        // Test constructor with row, col, and value
        Cell cell2 = new Cell(1, 2, 3);
        assert cell2.getRow() == 1;
        assert cell2.getCol() == 2;
        assert cell2.getValue() == 3;
        assert !cell2.isLocked();

        // Test constructor with row, col, value, and locked status
        Cell cell3 = new Cell(4, 5, 6, true);
        assert cell3.getRow() == 4;
        assert cell3.getCol() == 5;
        assert cell3.getValue() == 6;
        assert cell3.isLocked();

        System.out.println("testConstructors passed.");
    }

    public void testGetters() {
        Cell cell = new Cell(3, 4, 7, true);

        // Test getters for row, col, value, and locked status
        assert cell.getRow() == 3;
        assert cell.getCol() == 4;
        assert cell.getValue() == 7;
        assert cell.isLocked();

        System.out.println("testGetters passed.");
    }

    public void testSetters() {
        Cell cell = new Cell(3, 4, 7, true);

        // Test setter for value
        cell.setValue(9);
        assert cell.getValue() == 9;

        // Test setter for locked status
        cell.setLocked(false);
        assert !cell.isLocked();

        System.out.println("testSetters passed.");
    }

    public void testToString() {
        // Test toString method for value
        Cell cell = new Cell(3, 4, 7, true);
        assert cell.toString().equals("7");

        System.out.println("testToString passed.");
    }
}
