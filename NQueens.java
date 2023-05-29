public class NQueens {

    private int n;
    private int[] cols;
    private boolean[] usedCols;
    private boolean[] usedDiag1;
    private boolean[] usedDiag2;
    private int solutionsCount;

    public NQueens(int n) {
        this.n = n;
        this.cols = new int[n];
        this.usedCols = new boolean[n];
        this.usedDiag1 = new boolean[2 * n - 1];
        this.usedDiag2 = new boolean[2 * n - 1];
    }

    public void solve() {
        solutionsCount = 0;
        placeQueen(0);
    }

    private void placeQueen(int row) {
        if (row == n) {
            solutionsCount++;
            printSolution();
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValidPosition(row, col)) {
                cols[row] = col;
                usedCols[col] = true;
                usedDiag1[row + col] = true;
                usedDiag2[row - col + n - 1] = true;

                placeQueen(row + 1);

                cols[row] = 0;
                usedCols[col] = false;
                usedDiag1[row + col] = false;
                usedDiag2[row - col + n - 1] = false;
            }
        }
    }

    private boolean isValidPosition(int row, int col) {
        return !usedCols[col] && !usedDiag1[row + col] && !usedDiag2[row - col + n - 1];
    }

    private void printSolution() {
        System.out.print("Solution " + solutionsCount + ": ");
        for (int i = 0; i < n; i++) {
            System.out.print("(" + i + "," + cols[i] + ") ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int n = 4;
        NQueens solver = new NQueens(n);
        solver.solve();
    }
    
}




