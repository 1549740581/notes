import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 字符流中第一个不重复的字符
 * 字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 *
 * @author sherman
 */
public class Q54流中第一个不重复的字符 {
    // Insert one char from string stream
    Set<Character> sets = new LinkedHashSet<>();

    public void insert(char ch) {
        if (!sets.contains(ch)) {
            sets.add(ch);
        } else {
            sets.remove(ch);
        }
    }

    // return the first appearance once char in current string stream
    public char firstAppearingOnce() {
        for (Character ch : sets) {
            return ch;
        }
        return '#';
    }
}
