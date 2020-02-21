package backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47 permutations II
 * 给定一个【可包含重复】数字的序列，返回所有不重复的全排列。
 *
 * @author sherman
 */
public class Q05PermutationsII {
    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] visited;


    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtrace(nums, 0, new ArrayList<Integer>());
        return res;
    }

    private void backtrace(int[] nums, int idx, ArrayList<Integer> tmp) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                    continue;
                }
                visited[i] = true;
                tmp.add(nums[i]);
                backtrace(nums, idx + 1, tmp);
                visited[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
