import java.util.Stack;

/**
 * 包含min函数的栈
 *
 * @author sherman
 */
public class Q20包含min函数的栈 {
    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> helper = new Stack<>();

    public void push(int node) {
        data.push(node);
        // helper为空或者node值小于helper堆顶元素，直接向helper中推入
        if (helper.isEmpty() || helper.peek() > node) {
            helper.push(node);
        } else {
            helper.push(helper.peek());
        }
    }

    public void pop() {
        data.pop();
        helper.pop();
    }

    public int top() {
        return data.peek();
    }

    public int min() {
        return helper.peek();
    }
}
