package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 131 palindrome partitioning
 * 给定一个字符串s，将s分割成一些子串，使每个子串都是回文串。
 * 返回s所有可能的分割方案。
 *
 * @author sherman
 */
public class Q03PalindromePartitioning {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || "".equals(s)) {
            return res;
        }
        backtrace(s, 0, new ArrayList<>());
        return res;
    }

    private void backtrace(String s, int idx, ArrayList<String> tmp) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 1; idx + i <= s.length(); ++i) {
            String seg = s.substring(idx, i + idx);
            if (!isPalindrome(seg)) {
                continue;
            }
            tmp.add(seg);
            backtrace(s, idx + i, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    private boolean isPalindrome(String seg) {
        if (seg.length() == 1) {
            return true;
        }
        for (int i = 0; i <= seg.length() / 2; i++) {
            if (seg.charAt(i) != seg.charAt(seg.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
