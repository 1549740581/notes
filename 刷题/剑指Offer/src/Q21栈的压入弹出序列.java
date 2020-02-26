import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * 给定两个序列，第一个表示栈的压入序列，第二个表示栈的弹出序列，所有数字均不相等，判断弹出序列是否可能是压入序列.
 *
 * 压入序列：[1, 2, 3, 4, 5]
 * 弹出序列：[4, 5, 3, 2, 1]
 *
 * @author sherman
 */
public class Q21栈的压入弹出序列 {
    private Stack<Integer> stack = new Stack<>();

    public boolean isPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null) {
            return false;
        }
        int idx = 0;
        for (int value : pushA) {
            if (value != popA[idx]) {
                stack.push(value);
            } else {
                ++idx;
            }
        }
        for (; idx < popA.length; ++idx) {
            if (popA[idx] != stack.peek()) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
