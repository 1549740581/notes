import java.util.Stack;

/**
 * 两个栈来实现一个队列
 *
 * @author sherman
 */
public class Q05TwoStackImplQueue {
    Stack<Integer> data = new Stack<>();
    Stack<Integer> helper = new Stack<>();

    public void push(int node) {
        data.push(node);
    }

    public int pop() {
        if (!helper.isEmpty()) {
            return helper.pop();
        } else {
            while (!data.isEmpty()) {
                helper.push(data.pop());
            }
        }
        return helper.pop();
    }
}
