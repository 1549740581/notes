package search;

import java.util.Arrays;

/**
 * 16 3 sum closest
 * 给定一个包括n个整数的数组nums和一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。
 * 例如，nums={-1, 2, 1, -4}, target=1，返回只为2（-1 + 2 + 1）
 *
 * @author sherman
 */
public class Q11ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            throw new RuntimeException();
        }
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; ++i) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int curSum = nums[i] + nums[l] + nums[r];
                if (Math.abs(curSum - target) < Math.abs(closest - target)) {
                    closest = curSum;
                }
                if (curSum == target) {
                    return curSum;
                } else if (curSum > target) {
                    --r;
                } else {
                    ++l;
                }
            }
        }
        return closest;
    }
}
