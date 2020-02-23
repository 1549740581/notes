package dp;

/**
 * 494 target sum
 * 找到nums一个正子集（P）和一个负子集（N），使得总和等于target
 * sum(P) - sum(N) = target
 * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
 * 2 * sum(P) = target + sum(nums)
 * 原来的问题已转化为一个求子集的和问题：
 * 找到nums的一个子集 P，使得sum(P) = (target + sum(nums)) / 2
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * @author sherman
 */
public class Q26TargetSum {
    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1);
    }

    /**
     * 换换成多重背包问题
     */
    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = s; i >= n; --i) {
                dp[i] += dp[i - n];
            }
        }
        return dp[s];
    }
}
