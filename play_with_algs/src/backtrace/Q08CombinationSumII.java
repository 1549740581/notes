package backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40 combination sum II
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。candidates 中的每个数字在每个组合中只能使用一次。
 * - 所有数字（包括目标数）都是正整数
 * - 解集不能包含重复的组合。
 * 输入: candidates = [2,5,2,1,2], target = 5
 * 输出：[[1, 2, 2], [5]]
 *
 * @author sherman
 */
public class Q08CombinationSumII {
    List<List<Integer>> res = new ArrayList<>();
    private boolean[] visited;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        visited = new boolean[candidates.length];
        Arrays.sort(candidates);
        backtrace(candidates, target, 0, 0, new ArrayList<Integer>());
        return res;
    }

    private void backtrace(int[] arr, int target, int idx, int curSum, ArrayList<Integer> tmp) {
        if (target == curSum) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = idx; i < arr.length; ++i) {
            if (!visited[i]) {
                /**
                 * arr现在已经有序了，curSum + arr[idx] > target
                 * arr之后所有元素 + curSum > target，直接return
                 */
                if (curSum + arr[idx] > target) {
                    return;
                }
                if (i > 0 && arr[i - 1] == arr[i] && !visited[i - 1]) {
                    continue;
                }
                curSum += arr[i];
                tmp.add(arr[i]);
                visited[i] = true;
                backtrace(arr, target, i + 1, curSum, tmp);
                curSum -= arr[i];
                tmp.remove(tmp.size() - 1);
                visited[i] = false;
            }
        }
    }
}
