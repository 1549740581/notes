package dp;

/**
 * 多重01背包问题： 有M个物品，每个物品的重量为W[i]，每个物品的价值为V[i]，现在有一个背包重量为N
 * 相对于普通01背包问题，此时每个物品的数量由nums[i]来限定，即每个物品现在有0-nums[i]个
 * 状态转移方程：
 * dp[j] = Math.max(dp[j], k*V[i] + dp[j - k*W[i]]), 0 <= k <= nums[i]
 * 约束条件：
 * SUM{nums[i] * w[i] <= N}
 * 因为此时数量还是有限定的，仍然需要倒序遍历背包容量N，同时，同一个物品取的数量为： Math.min(j/W[i], nums[i])
 *
 * @author sherman
 */
public class Q15ZeroOneBag03 {
    public int zeroOneBag(int[] wgt, int[] val, int[] nums, int M, int N) {
        int[] dp = new int[N + 1];
        for (int i = 0; i < M; ++i) {
            for (int j = N; j >= wgt[i]; --j) {
                int cnt = Math.min(nums[i], j / wgt[i]);
                for (int k = 0; k <= cnt; ++k) {
                    dp[j] = Math.max(dp[j], k * val[i] + dp[j - k * wgt[i]]);
                }
            }
        }
        return dp[N];
    }

    /**
     * 使用二进制优化，将次数变为：1，2，4... nums[i] - 2^k + 1
     * 例如nums[i] == 13, 次数优化为：1，2，4，6
     */
    public int zeroOneBagOptimized(int[] wgt, int[] val, int[] nums, int M, int N) {
        int[] dp = new int[N + 1];
        for (int i = 0; i < M; i++) {
            int cnt = Math.min(nums[i], N / wgt[i]);
            for (int k = 1; cnt > 0; k <<= 1) {
                if (k > cnt) {
                    k = cnt;
                }
                cnt -= k;
                for (int j = N; j >= wgt[i] * k; --j) {
                    dp[j] = Math.max(dp[j], dp[j - k * wgt[i]] + val[i] * k);
                }
            }
        }
        return dp[N];
    }
}
