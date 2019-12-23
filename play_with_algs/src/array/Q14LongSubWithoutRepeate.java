package array;

/**
 * 3 long substring without repeating characters
 * 给定一个字符串,请你找出其中不含有重复字符的最长子串的长度.
 *
 * @author sherman
 */
public class Q14LongSubWithoutRepeate {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int l = 0, r = -1;
        int[] freq = new int[256];
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                freq[s.charAt(++r)]++;
            } else {
                freq[s.charAt(l++)]--;
            }
            res = Math.max(r - l + 1, res);
        }
        return res;
    }
}
