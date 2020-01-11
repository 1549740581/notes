package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20 valid parentheses
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * @author sherman
 */
public class Q01ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        Map<Character, Character> maps = new HashMap<>(16);
        maps.put(')', '(');
        maps.put(']', '[');
        maps.put('}', '{');
        for (char ch : s.toCharArray()) {
            Character value = maps.get(ch);
            if (value == null) {
                stk.push(ch);
            } else {
                if (stk.isEmpty() || stk.pop() != value) {
                    return false;
                }
            }
        }
        return stk.isEmpty();
    }
}
