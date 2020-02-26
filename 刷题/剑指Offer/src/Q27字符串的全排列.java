import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * 字符串全排列，注意字符串中可能包含重复字符，返回结果中不能包含重复结果
 *
 * @author sherman
 */
public class Q27字符串的全排列 {
    private ArrayList<String> res = new ArrayList<>();
    private boolean[] visited;

    public ArrayList<String> Permutation(String str) {

        if (str == null || str.length() == 0) {
            return res;
        }
        visited = new boolean[str.length()];
        ArrayList<Character> tmp = new ArrayList<>();
        char[] chars = str.toCharArray();
        // 需要排序
        Arrays.sort(chars);
        dfs(chars, 0, tmp);
        return res;
    }

    private void dfs(char[] chars, int idx, ArrayList<Character> tmp) {
        if (idx == chars.length) {
            StringBuilder sb = new StringBuilder();
            for (Character ch : tmp) {
                sb.append(ch);
            }
            res.add(sb.toString());
            // 注意return，剪枝操作
            return;
        }
        for (int i = 0; i < chars.length; ++i) {
            if (!visited[i]) {
                /**
                 * 如果当前元素和之前元素相等，且之前元素被访问过，说明当前是在之前一步基础上进行的，也就是第一次操作，不会出现重复；
                 * 如果之前一个元素没有被访问，说明已经经过一次回溯，之前的元素已经被重置为false，这一次如果在继续操作则会出现重复。
                 */
                if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                    continue;
                }
                tmp.add(chars[i]);
                visited[i] = true;
                dfs(chars, idx + 1, tmp);
                visited[i] = false;
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
