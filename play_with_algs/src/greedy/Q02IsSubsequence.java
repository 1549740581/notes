package greedy;

/**
 * 392 is sub-sequence
 * @author sherman
 */
public class Q02IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int idx = -1;
        for (char ch : s.toCharArray()) {
            idx = t.indexOf(ch, idx + 1);
            if (idx == -1) {
                return false;
            }
        }
        return true;
    }

    public boolean isSubsequence01(String s, String t) {
        int idx = 0;
        for (char ch : s.toCharArray()) {
            while (idx < t.length() && ch != t.charAt(idx)) {
                ++idx;
            }
            if (idx == t.length()) {
                return false;
            }
            ++idx;
        }
        return true;
    }
}
