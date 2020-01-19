package dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139 word break
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 *
 * @author sherman
 */
public class Q25WordBreak {
    /**
     * 转换成完全背包问题
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        Set<String> sets = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && sets.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
