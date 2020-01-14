package backtrace;

/**
 * 79 word search
 * 定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * board = [['A','B','C','E'],
 *          ['S','F','C','S'],
 *          ['A','D','E','E']]
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 *
 * @author sherman
 */
public class Q13WordSearch {
    private boolean[][] visited;
    private int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) {
            return false;
        }
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && backtrace(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrace(char[][] board, String word, int i, int j, int idx) {
        if (idx == word.length() - 1) {
            return word.charAt(idx) == board[i][j];
        }
        if (word.charAt(idx) == board[i][j]) {
            visited[i][j] = true;
            for (int d = 0; d < 4; ++d) {
                int newX = i + dir[d][0];
                int newY = j + dir[d][1];
                if (isValidCord(newX, newY) && !visited[newX][newY]) {
                    if (backtrace(board, word, newX, newY, idx + 1)) {
                        return true;
                    }
                }
            }
            visited[i][j] = false;
        }
        return false;
    }

    private boolean isValidCord(int x, int y) {
        return x >= 0 && x < visited.length && y >= 0 && y < visited[0].length;
    }
}
