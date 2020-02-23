package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 39 combination sum
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * - 所有数字（包括 target）都是正整数。
 * - 解集中不能包含重复元素
 * 输入：candidates = [2, 3, 6, 7], target = 7，
 * 输出：[[7], [2, 2, 3]]
 *
 * @author sherman
 */
public class Q07CombinationSumI {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        backtrace(candidates, 0, target, new ArrayList<>(), 0);
        return res;
    }

    private void backtrace(int[] arr, int idx, int target, ArrayList<Integer> tmp, int cur) {
        if (cur == target) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < arr.length; ++i) {
            if (cur + arr[i] > target) {
                return;
            }
            cur += arr[i];
            tmp.add(arr[i]);
            backtrace(arr, i, target, tmp, cur);
            cur -= arr[i];
            tmp.remove(tmp.size() - 1);
        }
    }
}
