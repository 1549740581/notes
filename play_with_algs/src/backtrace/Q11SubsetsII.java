package backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90 subsets II
 * 给定一个可能包含重复元素的整数数组nums，返回该数组所有可能的子集（幂集）。
 * - 解集不能包含重复的子集。
 * 输入: [1,2,2]
 * 输出: [[2], [1], [1,2,2], [2,2], [1,2], []]
 *
 * @author sherman
 */
public class Q11SubsetsII {
    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] visited;


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        visited = new boolean[nums.length];
        Arrays.sort(nums);
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
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                    continue;
                }
                visited[i] = true;
                tmp.add(nums[i]);
                backtrace(nums, len, i + 1, tmp);
                visited[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
