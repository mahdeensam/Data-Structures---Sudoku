
/* 
Mahdeen Ahmed Khan Sameer
BoardTest class: To test Board class
*/

public class BoardTest {
    private Board board;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java BoardTest <filename>");
            return;
        }

        String filename = args[0];
        BoardTest boardTest = new BoardTest();
        boardTest.setUp(filename);
        boardTest.testValidValue();
        boardTest.testValidSolution();
        boardTest.testClear();
        System.out.println("All tests passed.");
    }

    public void setUp(String filename) {
        board = new Board(filename);
    }

    public void testValidValue() {
        boolean validValue = board.validValue(0, 0, 5);
        if (validValue) {
            System.out.println("testValidValue passed.");
        } else {
            System.out.println("testValidValue failed.");
            System.exit(1);
        }
    }

    public void testValidSolution() {
        boolean validLoadedBoard = true;
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                int value = board.value(row, col);
                if (value != 0 && !board.validValue(row, col, value)) {
                    validLoadedBoard = false;
                    break;
                }
            }
            if (!validLoadedBoard) {
                break;
            }
        }
    
        if (validLoadedBoard) {
            System.out.println("testValidSolution passed.");
        } else {
            System.out.println("testValidSolution failed.");
            System.exit(1);
        }
    }
    

    public void testClear() {
        board.clear();
        int unspecifiedCells = board.getUnspecifiedCells();
        if (unspecifiedCells == Board.SIZE * Board.SIZE) {
            System.out.println("testClear passed.");
        } else {
            System.out.println("testClear failed.");
            System.exit(1);
        }
    }
}
