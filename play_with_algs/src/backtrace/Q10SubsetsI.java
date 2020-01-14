package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 78 subsets
 * 给定一组不含重复元素的整数数组nums，返回该数组所有可能的子集（幂集）。
 * - 解集不能包含重复的子集。
 * 输入: nums = [1,2,3]
 * 输出: [[3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], []]
 *
 * @author sherman
 */
public class Q10SubsetsI {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        res.add(new ArrayList<>());
        for (int len = 1; len <= nums.length; ++len) {
            backtrace(nums, len, 0, new ArrayList<>());
        }
        return res;
    }

    private void backtrace(int[] nums, int len, int idx, ArrayList<Integer> tmp) {
        if (tmp.size() == len) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = idx; i < nums.length; ++i) {
            tmp.add(nums[i]);
            backtrace(nums, len, i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
