package dp;

/**
 * 63 unique paths II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 输入: [[0,0,0],
 *       [0,1,0],
 *       [0,0,0]]
 * 输出: 2
 *
 * @author sherman
 */
public class Q08UniquePathII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1];
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
