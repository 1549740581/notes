import java.util.Stack;

/**
 * 包含min函数的栈
 *
 * @author sherman
 */
public class Q20StackWithMin {
    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> helper = new Stack<>();

    public void push(int node) {
        data.push(node);
        /**
         * 新元素比helper栈顶元素小或者helper为空
         */
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
