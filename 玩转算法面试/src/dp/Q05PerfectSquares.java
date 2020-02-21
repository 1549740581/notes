package dp;

/**
 * 279 perfect squares
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * @author sherman
 */
public class Q05PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            /**
             * n可以从n个1变换过来，默认需要n次
             */
            dp[i] = i;
            for (int j = 1; ; j++) {
                int tmp = i - j * j;
                if (tmp < 0) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[tmp] + 1);
            }
        }
        return dp[n];
    }
}
