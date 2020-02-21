package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438 find all anagrams in a string
 * 给定一个字符串s和一个非空字符串p，找到s中所有是p的字母异位词的子串，返回这些子串的起始索引.
 * 字符串中只包含小写字母，且s、q长度都不超过20100.
 *
 * @author sherman
 */
public class Q15FindAllAnagrams {
    /**
     * 把原本需要对s进行排序的操作转换成判定两个数组相等
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int l = 0, r = -1;
        int[] freqS = new int[26];
        int[] freqP = new int[26];
        for (char ch : p.toCharArray()) {
            ++freqP[ch - 'a'];
            ++freqS[s.charAt(++r) - 'a'];
        }
        if (Arrays.equals(freqP, freqS)) {
            res.add(l);
        }
        while (r < s.length() - 1) {
            ++freqS[s.charAt(++r) - 'a'];
            --freqS[s.charAt(l++) - 'a'];
            if (Arrays.equals(freqP, freqS)) {
                res.add(l);
            }
        }
        return res;
    }
}
