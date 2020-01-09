package search;

import java.util.*;

/**
 * 49 group anagrams
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * @author sherman
 */
public class Q13GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> maps = new HashMap<>();
        for (String str : strs) {
            String key = sortString(str);
            List<String> list = maps.get(key);
            if (list == null) {
                List<String> tmp = new ArrayList<>();
                tmp.add(str);
                maps.put(key, tmp);
            } else {
                list.add(str);
            }
        }
        List<List<String>> res = new LinkedList<>();
        for (Map.Entry<String, List<String>> entry : maps.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    private String sortString(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            sb.append(ch);
        }
        return sb.toString();
    }
}
