package search;

import java.util.HashSet;
import java.util.Set;

/**
 * 219 contains duplicate II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 * 即：滑动窗口k+1范围内，是否有重复的元素
 *
 * @author sherman
 */
public class Q16ContainDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // ! 超时
//		for (int i = 0; i < nums.length; ++i) {
//			int[] range = Arrays.copyOfRange(nums, i, Math.min(i + k + 1, nums.length));
//			Set<Integer> sets = new HashSet<>();
//			for (int elem : range) {
//				sets.add(elem);
//			}
//			if (sets.size() != range.length) {
//				return true;
//			}
//		}
//		return false;

        Set<Integer> sets = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (sets.contains(nums[i])) {
                return true;
            } else {
                sets.add(nums[i]);
                if (sets.size() == k + 1) {
                    sets.remove(nums[i - k]);
                }
            }
        }
        return false;
    }
}
