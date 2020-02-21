package dp;

import java.util.Arrays;

/**
 * 62 unique paths I
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）
 * 问总共有多少条不同的路径？
 * C(m+n-2, n-1)
 *
 * @author sherman
 */
public class Q07UniquePathI {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[m - 1];
    }
}
