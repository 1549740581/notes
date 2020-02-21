package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 完全01背包问题：
 * 有M个物品，每个物品的重量为W[i]，每个物品的价值为V[i]，现在有一个背包重量为N
 * 相对于普通01背包问题：现在每个物品可以取无限次，没有限制 状态转移方程：
 * dp[j] = Math.max(dp[j], V[i] + dp[j -wgt[i]])
 * 约束条件：
 * SUM{num[i] * wgt[i] <= N}, num[i]表示第i个物品的数量，nums[i] >= 0即可
 * 相对于普通01背包问题，此时因为可以重复添加，因此在遍历物品重量时候，可以顺序遍历
 * 完全01背包问题因为每种物品的数量无限，因此可以用贪心算法，尽可能的放入【性价比】最高的物品
 *
 * @author sherman
 */
public class Q14ZeroOneBag02 {
    /**
     * 时间复杂度：O(MN) 空间复杂度：O(N)
     *
     * @param wgt wgt[i]第i个物品的重量
     * @param val val[i]第i个物品的价值
     * @param M   物品的种类M类
     * @param N   背包的容量N
     */
    public int zeroOneBag(int[] wgt, int[] val, int M, int N) {
        int[] dp = new int[N + 1];
        for (int i = 0; i < M; ++i) {
            for (int j = wgt[i]; j <= N; ++j) {
                dp[j] = Math.max(dp[j], val[i] + dp[j - wgt[i]]);
            }
        }
        return dp[N];
    }

    private static class MyEntry implements Comparable<MyEntry> {
        private int wgt;
        private int val;
        private double ratio;

        MyEntry(int wgt, int val) {
            this.wgt = wgt;
            this.val = val;
            this.ratio = 1.0 * val / wgt;
        }

        @Override
        public int compareTo(MyEntry o) {
            double cmp = o.ratio - this.ratio;
            if (cmp > 0) {
                return 1;
            } else if (cmp < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * 注意不要将wgt和val对应的值封装成一个key-val结构放入到Map中，因为无论是用wgt还是用val作为key
     * 都有可能出现重复key的情况（并不保证每个重量/价值都唯一），如果出现重复key情况，put操作时会导致覆盖情况
     */
    public int zeroOneBag01(int[] wgt, int[] val, int M, int N) {
        List<MyEntry> lists = new ArrayList<>();
        for (int i = 0; i < M; ++i) {
            lists.add(new MyEntry(wgt[i], val[i]));
        }
        int total = 0;
        for (int i = 0; i < lists.size(); ++i) {
            MyEntry entry = lists.get(0);
            total += N / entry.wgt * entry.val;
            N %= entry.wgt;
            if (N == 0) {
                break;
            }
        }
        return total;
    }
}

