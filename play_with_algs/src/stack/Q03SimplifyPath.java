package stack;

import java.util.Stack;

/**
 * 71 simplify path
 * 以Unix风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 *
 * @author sherman
 */
public class Q03SimplifyPath {
    public String simplifyPath(String path) {
        String[] split = path.replaceAll("//+", "/").substring(1).split("/");
        Stack<String> stk = new Stack<>();
        for (String str : split) {
            if (".".equals(str)) {
                continue;
            } else if ("..".equals(str)) {
                if (!stk.isEmpty()) {
                    stk.pop();
                }
            } else {
                stk.push(str);
            }
        }
        if (stk.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.insert(0, stk.pop()).insert(0, "/");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "";
        System.out.println(new Q03SimplifyPath().simplifyPath(str));
    }
}
