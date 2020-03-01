package search;

import java.util.HashMap;
import java.util.Map;

/**
 * 205 Isomorphic strings
 * 给定两个字符串 s 和 t，判断它们是否是【同构的】。
 * 同构：如果s中的字符可以被替换得到t，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *  例如：
 *      * s = "egg", t = "add"      -> true
 *      * s = "foo", t = "bar"      -> false
 *      * s = "paper", t = "title"  -> true
 *
 * @author sherman
 */

public class Q06SomorphicString {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> maps = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!maps.containsKey(ch)) {
                if (maps.containsValue(t.charAt(i))) {
                    return false;
                }
                maps.put(ch, t.charAt(i));
            } else {
                if (maps.get(ch) != t.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
