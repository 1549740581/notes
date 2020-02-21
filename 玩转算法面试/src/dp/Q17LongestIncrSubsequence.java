package dp;

import java.util.Arrays;

/**
 * 300 longest increasing sub-sequence
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度
 *
 * @author sherman
 */
public class Q17LongestIncrSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 二分查找 & 动态规划
     */
    public int lengthOfLIS01(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int res = Arrays.binarySearch(nums, 0, len, num);
            if (res < 0) {
                res = -(res + 1);
            }
            dp[res] = num;
            if (res == len) {
                ++len;
            }
        }
        return len;
    }
}
