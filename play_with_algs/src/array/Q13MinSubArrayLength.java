package array;

/**
 * 209 min sub array length
 * 给定一个含有n个正整数的数组和一个正整数s，找出该数组中满足其和≥ s的长度最小的连续子数组.
 * 如果不存在满足条件的连续子数组，则返回0.
 *
 * @author sherman
 */
public class Q13MinSubArrayLength {
    public int minSubArrayLen(int s, int[] nums) {
        int res = nums.length + 1;
        int lo = 0, hi = 0, sum = 0;
        while (lo < nums.length) {
            if (hi < nums.length && sum < s) {
                sum += nums[hi++];
            } else {
                sum -= nums[lo++];
            }
            if (sum >= s) {
                res = Math.min(res, hi - lo);
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }
}
