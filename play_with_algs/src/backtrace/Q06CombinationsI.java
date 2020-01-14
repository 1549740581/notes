package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 77 combinations I
 * 给定两个整数n和k，返回1 ... n中所有可能的k个数的组合。
 *
 * @author sherman
 */
public class Q06CombinationsI {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrace(n, k, 1, new ArrayList<Integer>());
        return res;
    }

    private void backtrace(int n, int k, int idx, ArrayList<Integer> tmp) {
        if (tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        /**
         * 注意剪枝操作
         */
        for (int i = idx; i <= n - (k - tmp.size()) + 1; i++) {
            tmp.add(i);
            backtrace(n, k, i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
