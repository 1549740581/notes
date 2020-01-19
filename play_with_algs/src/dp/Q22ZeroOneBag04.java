package dp;

/**
 * 混合背包问题：
 * nums[i]：代表第i个物品可以取的次数，特别的，nums[i]==0，表示第i个物品可以选取无数次
 * 否则，第i个物品最多只能选取nums[i]次
 */
public class Q22ZeroOneBag04 {
    public int zeroOneBag(int[] wgt, int[] val, int[] nums, int M, int N) {
        int[] dp = new int[N + 1];
        for (int i = 0; i < M; ++i) {
            /**
             * 完全背包问题，直接顺序遍历即可
             */
            if (nums[i] == 0) {
                for (int j = wgt[i]; j <= N; ++j) {
                    dp[j] = Math.max(dp[j], dp[j - wgt[i]] + val[i]);
                }
            } else {
                int cnt = Math.min(nums[i], N / wgt[i]);
                for (int k = 1; cnt > 0; k <<= 1) {
                    if (k > cnt) {
                        k = cnt;
                    }
                    cnt -= k;
                    for (int j = N; j >= wgt[i] * k; --j) {
                        dp[j] = Math.max(dp[j], dp[j - wgt[i] * k] + val[i] * k);
                    }
                }
            }
        }
        return dp[N];
    }
}