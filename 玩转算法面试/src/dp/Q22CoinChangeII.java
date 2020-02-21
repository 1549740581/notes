package dp;

import java.util.Arrays;

/**
 * 518 coin change II
 *
 * @author sherman
 */
public class Q22CoinChangeII {
    private int cnt = 0;

    /**
     * dfs：超时，当amount较大，coins中coin数值较小时，递归树过深
     */
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        dfs(coins, 0, amount);
        return cnt;
    }

    private void dfs(int[] coins, int idx, int left) {
        if (left == 0) {
            ++cnt;
            return;
        }
        if (idx == coins.length) {
            return;
        }
        for (int i = left / coins[idx]; i >= 0; --i) {
            dfs(coins, idx + 1, left - i * coins[idx]);
        }
    }

    /**
     * 转换成0-1背包问题
     */
    public int change1(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
