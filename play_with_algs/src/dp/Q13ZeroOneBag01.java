package dp;

/**
 * 普通01背包问题：
 * 有M个物品，每个物品的重量为W[i]，每个物品的价值为V[i]，现在有一个背包重量为N
 * 注意每个物品只能被取一次，求能够装下的最大容量
 * 状态转移方程：
 * dp[j] = Math.max(dp[j], V[i] + dp[j - wgt[i]])
 * 约束条件：
 * SUM{num[i] * wgt[i] <= N}, num[i]表示第i个物品的数量，只能取0、1两种可能
 *
 * @author sherman
 */
public class Q13ZeroOneBag01 {
    /**
     * dp[i]：当前背包装了重量为i时，所获得的最大重量，最终应该返回dp[N]
     *
     * @param wgt wgt[i]：第i个物品的重量
     * @param val val[i]：第i个物品的价值
     * @param M   物品的总数量
     * @param N   背包总重量
     */
    public int zeroOneBag(int[] wgt, int[] val, int M, int N) {
        /**
         * dp[i]：当前背包装了重量为i时，所获得的最大重量，最终应该返回dp[N]
         */
        int[] dp = new int[N + 1];
        for (int i = 0; i < M; ++i) {
            for (int j = N; j >= wgt[i]; --j) {
                dp[j] = Math.max(dp[j], val[i] + dp[j - wgt[i]]);
            }
        }
        return dp[N];
    }
}
