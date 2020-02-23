package dp;

/**
 * 474 ones and zeros
 * 现在，假设你分别支配着m个0和n个1。另外，还有一个仅包含0和1字符串的数组。
 * 你的任务是使用给定的m个0和n个1，找到能拼出存在于数组中的字符串的最大数量。每个0和1至多被使用一次。
 *
 * @author sherman
 */
public class Q24ZerosAndOnes {
    /**
     * 二维多重背包问题
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeros = 0, ones = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    ++zeros;
                } else {
                    ++ones;
                }
            }
            for (int i = m; i >= zeros; --i) {
                for (int j = n; j >= ones; --j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
