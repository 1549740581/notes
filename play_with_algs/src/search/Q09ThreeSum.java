package search;

import java.util.*;

/**
 * 15 3sum
 * 给定一个包含n个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
 * 找出所有满足条件且不重复的三元组。
 *
 * @author sherman
 */
public class Q09ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    List<Integer> tmp = Arrays.asList(nums[i], nums[l], nums[r]);
                    res.add(tmp);
                    while (l < r && nums[l] == nums[l + 1]) {
                        ++l;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        --r;
                    }
                    ++l;
                    --r;
                } else if (sum > 0) {
                    while (l < r && nums[r] == nums[r - 1]) {
                        --r;
                    }
                    --r;
                } else {
                    while (l < r && nums[l] == nums[l + 1]) {
                        ++l;
                    }
                    ++l;
                }
            }
        }
        return res;
    }
}
