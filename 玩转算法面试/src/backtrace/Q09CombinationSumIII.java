package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 216 combination sum III
 * 找出所有相加之和为n的k个数的组合。组合中只允许含有1 - 9的正整数，并且每种组合中不存在重复的数字。
 * - 所有数字都是正整数。
 * - 解集不能包含重复的组合。
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @author sherman
 */
public class Q09CombinationSumIII {
    List<List<Integer>> res = new ArrayList<>();
    boolean[] visited = new boolean[10];

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k <= 0 || k > 9 || n > 45) {
            return res;
        }
        backtrace(k, n, new ArrayList<>(), 1, 0);
        return res;
    }

    private void backtrace(int k, int target, ArrayList<Integer> tmp, int idx, int curSum) {
        if (curSum == target && tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        if (curSum + idx > target) {
            return;
        }
        for (int i = idx; i <= 9; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                curSum += i;
                tmp.add(i);
                backtrace(k, target, tmp, i + 1, curSum);
                curSum -= i;
                visited[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}

