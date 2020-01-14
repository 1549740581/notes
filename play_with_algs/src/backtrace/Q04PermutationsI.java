package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 46 permutations I
 * 给定一个【没有重复】数字的序列，返回其所有可能的全排列。
 *
 * @author sherman
 */
public class Q04PermutationsI {
    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        visited = new boolean[nums.length];
        backtrace(nums, 0, new ArrayList<>());
        return res;
    }

    private void backtrace(int[] nums, int idx, ArrayList<Integer> tmp) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                tmp.add(nums[i]);
                backtrace(nums, idx + 1, tmp);
                visited[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    /**
     * 方法二
     */
    private void permutateWay1(int[] nums, int l, int r) {
        if (l == r) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int elem : nums) {
                tmp.add(elem);
            }
            res.add(tmp);
            return;
        }
        for (int i = l; i <= r; ++i) {
            swap(nums, i, l);
            permutateWay1(nums, l + 1, r);
            swap(nums, i, l);

        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
