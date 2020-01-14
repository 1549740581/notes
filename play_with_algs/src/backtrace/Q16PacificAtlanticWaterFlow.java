package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 417 pacific atlantic water flow
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * 输入：
 * 太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 * 输出：
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
 *
 * @author sherman
 */
public class Q16PacificAtlanticWaterFlow {
    /**
     * 能流向太平洋的点
     */
    private boolean[][] pac;
    /**
     * 能流向大西洋的点
     */
    private boolean[][] atl;
    private int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private int rows;
    private int cols;
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        rows = matrix.length;
        cols = matrix[0].length;
        pac = new boolean[rows][cols];
        atl = new boolean[rows][cols];
        for (int col = 0; col < cols; col++) {
            infect(matrix, 0, col, pac);
            infect(matrix, rows - 1, col, atl);
        }
        for (int row = 0; row < rows; ++row) {
            infect(matrix, row, 0, pac);
            infect(matrix, row, cols - 1, atl);
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (pac[row][col] && atl[row][col]) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(row);
                    tmp.add(col);
                    res.add(tmp);
                }
            }
        }
        return res;
    }

    private void infect(int[][] matrix, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int newX = x + dir[d][0];
            int newY = y + dir[d][1];
            if (isValidCord(newX, newY) && matrix[newX][newY] >= matrix[x][y] && !visited[newX][newY]){
                infect(matrix, newX, newY, visited);
            }
        }
    }

    private boolean isValidCord(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
