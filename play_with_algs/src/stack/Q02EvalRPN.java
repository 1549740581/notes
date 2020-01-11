package stack;

import java.util.Stack;

/**
 * 150 evaluate reverse polish notation
 * 根据逆波兰表示法，求表达式的值。
 *
 * @author sherman
 */
public class Q02EvalRPN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stk = new Stack<>();
        for (String token : tokens) {
            try {
                int val = Integer.parseInt(token);
                stk.push(val);
            } catch (NumberFormatException e) {
                int num2 = stk.pop();
                int num1 = stk.pop();
                if ("+".equals(token)) {
                    stk.push(num1 + num2);
                } else if ("-".equals(token)) {
                    stk.push(num1 - num2);
                } else if ("*".equals(token)) {
                    stk.push(num1 * num2);
                } else {
                    stk.push(num1 / num2);
                }
            }
        }
        return stk.pop();
    }

    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        int res = new Q02EvalRPN().evalRPN(tokens);
        System.out.println(res);
    }
}
