import java.util.HashMap;
import java.util.Map;

/**
 * 第一个只出现一次的字符
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符，并返回它的位置,，如果没有则返回 -1（需要区分大小写）
 *
 * @author sherman
 */
public class Q34FirstNotRepeatingChar {
    public int firstNotRepeatingChar(String str) {
        Map<Character, Integer> maps = new HashMap<>();
        for (char ch : str.toCharArray()) {
            maps.put(ch, maps.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < str.length(); ++i) {
            if (maps.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
