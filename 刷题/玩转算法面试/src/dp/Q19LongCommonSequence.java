package dp;

/**
 * 1143 long common sub-sequence
 * 给定两个字符串text1和text2，返回这两个字符串的最长公共子序列。
 *
 * @author sherman
 */
public class Q19LongCommonSequence {
    public int longestCommonSubsequence(String text1, String text2) {
        /**
         * vertical
         */
        int len1 = text1.length();
        /**
         * horizontal
         */
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
