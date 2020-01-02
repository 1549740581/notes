/**
 * 矩阵中的路径
 * 输入一个矩阵和一个字符串，判断矩阵中是否存在一条包含字符串的路径，例如：
 * 矩阵：
 *      A B C E
 *      S F C S
 *      A D E E
 * 字符串：
 *      ABCCED -> true
 *      ABCB   -> false
 *
 * @author sherman
 */
public class Q65PathInMatrix {
    private int rows;
    private int cols;
    private char[] str;
    private boolean[] visited;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (str.length > rows * cols) {
            return false;
        }
        /**
         * 初始化
         */
        this.rows = rows;
        this.cols = cols;
        this.str = str;
        visited = new boolean[matrix.length];

        for (int x = 0; x < rows; ++x) {
            for (int y = 0; y < cols; ++y) {
                if (hasPath(matrix, x, y, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPath(char[] matrix, int x, int y, int idx) {
        if (idx == str.length) {
            return true;
        }
        int cur = x * cols + y;
        boolean res = false;
        if (x >= 0 && x < rows && y >= 0 && y < cols && str[idx] == matrix[cur] && !visited[cur]) {
            idx++;
            visited[cur] = true;
            res = hasPath(matrix, x + 1, y, idx)
                    || hasPath(matrix, x - 1, y, idx)
                    || hasPath(matrix, x, y + 1, idx)
                    || hasPath(matrix, x, y - 1, idx);
            /**
             * 没有找到注意回退
             */
            if (!res) {
                --idx;
                visited[cur] = false;
            }
        }
        return res;
    }
}
