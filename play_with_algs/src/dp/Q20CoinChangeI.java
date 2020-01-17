package dp;

import java.util.Arrays;

/**
 * 322 coin change I
 *
 * @author sherman
 */
public class Q20CoinChangeI {
    private int minCnt = Integer.MAX_VALUE;

    /**
     * dfs：先将coins排序，从最后一个依次试探
     */
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        return minCnt == Integer.MAX_VALUE ? -1 : minCnt;
    }

    private void dfs(int[] coins, int idx, int left, int cnt) {
        if (idx < 0 || left / coins[idx] + cnt > minCnt) {
            return;
        }
        if (left % coins[idx] == 0) {
            minCnt = Math.min(minCnt, cnt + left / coins[idx]);
            return;
        }
        for (int i = left / coins[idx]; i >= 0; --i) {
            dfs(coins, idx - 1, left - i * coins[idx], cnt + i);
        }
    }

    /**
     * dp[i]：当持有的金币数为i时，所需要的最小兑换次数
     * dp[i] = Math.min(dp[i], dp[i - coin] + 1)
     */
    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 0x7fffffff);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i < dp.length; ++i) {
                if (dp[i - coin] != 0x7fffffff) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == 0x7fffffff ? -1 : dp[amount];
    }
}
