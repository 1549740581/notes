package dp;

/**
 * 416 partition equal subset sum
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * @author sherman
 */
public class Q19PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum >> 1;
        boolean[][] dp = new boolean[nums.length][target + 1];
        dp[0][0] = true;
        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j <= target; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    /**
     * 压缩到一维
     */
    public boolean canPartition1(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < nums.length; ++i) {
            for (int j = target; j >= nums[i]; --j) {
                if (dp[target]) {
                    return true;
                }
                /**
                 * 将二维数据压缩成一维数组：
                 * dp[j]：上一行
                 * dp[j - nums[i]]：上一行左边某个位置数据
                 */
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
