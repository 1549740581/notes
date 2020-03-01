package greedy;

import java.util.Arrays;

/**
 * 435 non-overlapping intervals
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * - 可以认为区间的终点总是大于它的起点。
 * - 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * @author sherman
 */
public class Q03NonOverlappingIntervals {
    /**
     * 类比最长递增子序列问题
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int[] memo = new int[intervals.length];
        memo[0] = 1;
        for (int i = 1; i < intervals.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (intervals[i][0] >= intervals[j][1]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                }
            }
        }
        int res = memo[0];
        for (int i = 1; i < memo.length; ++i) {
            res = Math.max(res, memo[i]);
        }
        return memo.length - res;
    }

    /**
     * 贪心
     */
    public int eraseOverlapInterval01(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        int cnt = 1;
        // 记录前一个选择的区间
        int pre = 0;
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] > intervals[pre][1]) {
                ++cnt;
                pre = i;
            }
        }
        return intervals.length - cnt;
    }
}
