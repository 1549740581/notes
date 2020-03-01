package search;

/**
 * 242 valid anagram
 * 判断两个字符串是否是有效的【异位词】
 *
 * @author sherman
 */
public class Q03validAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] maps = new int[26];
        for (int i = 0; i < t.length(); ++i) {
            maps[s.charAt(i) - 'a'] += 1;
            maps[t.charAt(i) - 'a'] -= 1;
        }
        for (int item : maps) {
            if (item != 0) {
                return false;
            }
        }
        return true;
        // Java8
        // return Arrays.stream(maps).allMatch(e -> e == 0);
    }
}
