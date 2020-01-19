package dp;

import java.util.Arrays;

/**
 * 377 combination sum IV
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * 输入：
 * nums = [1, 2, 3]，target = 4
 * 输出：7，所有可能组合：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * @author sherman
 */
public class Q23CombinationSumIV {
    private int res = 0;

    /**
     * dfs 超时
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        dfs(nums, 0, target);
        return res;
    }

    private void dfs(int[] arr, int cur, int target) {
        if (cur > target) {
            return;
        }
        if (cur == target) {
            ++res;
            return;
        }
        for (int value : arr) {
            cur += value;
            dfs(arr, cur, target);
            cur -= value;
        }
    }

    private int[] memo;

    /**
     * 记忆化搜索
     * memo[i]：当和为i时，组合的个数
     */
    public int combinationSum4Optimized01(int[] nums, int target) {
        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;
        return dfsOptimized01(nums, target);
    }

    private int dfsOptimized01(int[] nums, int target) {
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += dfsOptimized01(nums, target - num);
            }
        }
        memo[target] = res;
        return res;
    }

    /**
     * 完全背包问题
     */
    public int combinationSum4Optimized02(int[] nums, int target) {
        memo = new int[target + 1];
        memo[0] = 1;
        for (int i = 0; i < target; i++) {
            for (int num : nums) {
                if (i + num <= target) {
                    memo[i + num] += memo[i];
                }
            }
        }
        return memo[target];
    }
}
