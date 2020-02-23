package dp;

/**
 * 64 minimum path sum
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * @author sherman
 */
public class Q03MinPathSum {
    /**
     * 原地，需要修改grid数组
     */
    public int minPathSum(int[][] grid) {
        for (int i = 1; i < grid.length; ++i) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < grid[0].length; ++i) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; ++j) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    /**
     * 空间复杂度O(n)
     */
    public int minPathSum1(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = grid[i][0] + grid[i - 1][0];
        }
        for (int i = 1; i < grid.length; ++i) {
            dp[0][i] = grid[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; ++i) {
            for (int j = 1; j < grid.length; ++j) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[rows - 1][cols - 1];
    }

}
