package dp;

import java.util.List;

/**
 * 120 triangle
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 状态转移方程（从下到上）：
 * dp[i, j] = min{dp[i+1, j], dp[i+1, j+1]} + arr[i, j]
 *
 * @author sherman
 */
public class Q02TriangleMinPathSum {
    public int minimumTotal(List<List<Integer>> triangle) {
        Integer[] dp = new Integer[triangle.size()];
        triangle.get(triangle.size() - 1).toArray(dp);
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    /**
     * 原地完成，但是需要修改triangle内容
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; --i) {
            for (int j = 0; j < triangle.get(i).size(); ++j) {
                int tmp = triangle.get(i).get(j);
                triangle.get(i).set(j, tmp + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
}
