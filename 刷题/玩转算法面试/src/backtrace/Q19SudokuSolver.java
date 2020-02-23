package backtrace;

/**
 * 37 sudoku solver
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * - 数字 1-9 在每一行只能出现一次；
 * - 数字 1-9 在每一列只能出现一次；
 * - 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次；
 *
 * @author sherman
 */
public class Q19SudokuSolver {
    /**
     * rows[i][j] == true，第i行中，有数字j存在
     */
    private boolean[][] rows = new boolean[9][10];
    /**
     * cols[i][j] == true，第i列中，有数字j存在
     */
    private boolean[][] cols = new boolean[9][10];
    /**
     * blocks[i][j] == true，第i个block区块中，有数字j存在
     */
    private boolean[][] blocks = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        /**
         * 初始化已有的数字
         */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] != '.') {
                    int num = Character.digit(board[i][j], 10);
                    rows[i][num] = true;
                    cols[j][num] = true;
                    blocks[i / 3 * 3 + j / 3][num] = true;
                }
            }
        }
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int x, int y) {
        while (board[x][y] != '.') {
            if (++y == 9) {
                ++x;
                y = 0;
            }
            if (x >= 9) {
                return true;
            }
        }
        /**
         * 回溯
         */
        for (int num = 1; num <= 9; ++num) {
            int blockIdx = x / 3 * 3 + y / 3;
            if (!rows[x][num] && !cols[y][num] && !blocks[blockIdx][num]) {
                board[x][y] = (char) (num + '0');
                rows[x][num] = true;
                cols[y][num] = true;
                blocks[blockIdx][num] = true;
                if (dfs(board, x, y)) {
                    return true;
                } else {
                    blocks[blockIdx][num] = false;
                    cols[y][num] = false;
                    rows[x][num] = false;
                    board[x][y] = '.';
                }
            }
        }
        return false;
    }

    private void printBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

//    public static void main(String[] args) {
//        char[][] board = new char[][]{
//                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
//        };
//        Q19SudokuSolver solution = new Q19SudokuSolver();
//        solution.printBoard(board);
//        System.out.println("-----------------");
//        solution.solveSudoku(board);
//        solution.printBoard(board);
//    }
}
