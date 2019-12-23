package array;

/**
 * 76 minimum window substring
 * 给你一个字符串S、一个字符串T，请在字符串S里面找出：包含T所有字母的最小子串.
 *
 * @author sherman
 */
public class Q16MinWindowSubstring {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        // 'A' -> 'z' ==> 65 -> 122
        final int LEN = 58;
        int l = 0, r = -1;
        int[] sFreq = new int[LEN];
        int[] tFreq = new int[LEN];
        for (char ch : t.toCharArray()) {
            ++tFreq[ch - 'A'];
        }
        // 临时区间，之后如果找到更小的区间会更新start、end
        int start = -1, end = s.length() + 1;
        while (l <= s.length() - t.length()) {
            // 当前区间长度比t.length小
            if ((r - l + 1) < t.length()) {
                if (r + 1 < s.length()) {
                    ++sFreq[s.charAt(++r) - 'A'];
                    continue;
                } else {
                    break;
                }
            }
            // 当前区间大于等于t.length
            int i = 0;
            while (i < LEN) {
                if (sFreq[i] < tFreq[i]) {
                    break;
                }
                ++i;
            }
            // 当前区间并不能包含t
            if (i < LEN) {
                // 只能尝试s[l...r+1]范围能不能不包含t
                if (r + 1 < s.length()) {
                    ++sFreq[s.charAt(++r) - 'A'];
                } else {
                    // 已经下一个尝试元素了，直接终止
                    break;
                }
            } else {// 当前区间中包含t
                if (r - l + 1 == t.length()) {
                    // 当前区间长度和t完全相同，直接返回
                    return s.substring(l, r + 1);
                } else {
                    // 先把当前区间保存下来
                    if (r - l < end - start) {
                        start = l;
                        end = r;
                    }
                    --sFreq[s.charAt(l++) - 'A'];
                }
            }
        }
        return start == -1 ? "" : s.substring(start, end + 1);
    }
}
