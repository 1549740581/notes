package search;

import java.util.HashMap;
import java.util.Map;

/**
 * 290 word pattern
 * 给定一种规律【pattern】和一个字符串【str】，判断str是否遵循相同的规律。
 * 例如：
 *      * pattern="abba", str="dog cat cat dog"  -> true
 *      * pattern="abba", str="dog cat cat fish" -> false
 *      * pattern="abba", str="dog dog dog dog"  -> false
 *
 * @author sherman
 */

class Q05WordPattern {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> maps = new HashMap<>();
        String[] strings = str.split(" ");
        if (pattern.length() != strings.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); ++i) {
            char ch = pattern.charAt(i);
            /**
             * 不存在
             */
            if (!maps.containsKey(ch)) {
                /**
                 * 但是已经存在过映射关系，直接返回false
                 */
                if (maps.containsValue(strings[i])) {
                    return false;
                }
                maps.put(ch, strings[i]);
            } else {
                if (!maps.get(ch).equals(strings[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
