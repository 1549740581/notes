package backtrace;

/**
 * 130 surrounded regions
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * - 任何边界上的'O'都不会被填充为'X'
 * 输入：
 *      X X X X
 *      X O O X
 *      X X O X
 *      X O X X
 * 输出：
 *      X X X X
 *      X X X X
 *      X X X X
 *      X O X X
 *
 * @author sherman
 */
public class Q15SurroundedRegions {
    private boolean[][] visited;
    private int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public void solve(char[][] board) {
        /**
         * 至少3*3
         */
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        int rows = board.length, cols = board[0].length;
        visited = new boolean[rows][cols];
        for (int col = 0; col < cols; col++) {
            if (board[0][col] == 'O') {
                infect(board, 0, col);
            }
            if (board[rows - 1][col] == 'O') {
                infect(board, rows - 1, col);
            }
        }
        for (int row = 1; row < rows - 1; ++row) {
            if (board[row][0] == 'O') {
                infect(board, row, 0);
            }
            if (board[row][cols - 1] == 'O') {
                infect(board, row, cols - 1);
            }
        }
        for (int row = 1; row < rows - 1; ++row) {
            for (int col = 0; col < cols - 1; col++) {
                if (!visited[row][col]) {
                    board[row][col] = 'X';
                }
            }
        }
    }

    private void infect(char[][] board, int x, int y) {
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int newX = x + dir[d][0];
            int newY = y + dir[d][1];
            if (isValidCord(newX, newY) && board[newX][newY] == 'O' && !visited[newX][newY]) {
                infect(board, newX, newY);
            }
        }
    }

    private boolean isValidCord(int x, int y) {
        return x >= 0 && x < visited.length && y >= 0 && y < visited[0].length;
    }
}
