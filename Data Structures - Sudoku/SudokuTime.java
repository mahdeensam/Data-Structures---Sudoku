/* 
Mahdeen Ahmed Khan Sameer
SudokuTime class: Relationship between the number of initial values and the likelihood of finding a solution.

*/

public class SudokuTime {

    public static void main(String[] args) {
        int[] initialValuesCounts = {10, 20, 30, 40};
        int runsPerCount = 50;
        int delay = 0;

        for (int initialValues : initialValuesCounts) {
            int solvedCount = 0;
            long totalTime = 0;

            for (int run = 0; run < runsPerCount; run++) {
                Board board = new Board();
                Sudoku sudoku = new Sudoku(board);
                sudoku.generateRandomBoard(initialValues);

                long startTime = System.currentTimeMillis();
                boolean solved = sudoku.solve();
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;

                if (solved) {
                    solvedCount++;
                }
                totalTime += elapsedTime;
            }

            double successRate = (double) solvedCount / runsPerCount * 100;
            double averageTime = (double) totalTime / runsPerCount;

            System.out.printf("Initial values: %d\n", initialValues);
            System.out.printf("Solved boards: %d/%d (%.2f%%)\n", solvedCount, runsPerCount, successRate);
            System.out.printf("Average time to solve: %.2f ms\n\n", averageTime);
        }
    }
}
