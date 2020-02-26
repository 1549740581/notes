import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * 二叉树的层序遍历，并保留每一层节点值
 *
 * @author sherman
 */
public class Q60二叉树层序遍历保留每层节点值 {
    ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) {
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(pRoot);
        // 记录每一层节点数
        int cnt = 1;
        ArrayList<Integer> tmp = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode elem = queue.poll();
            --cnt;
            tmp.add(elem.val);
            if (elem.left != null) {
                queue.add(elem.left);
            }
            if (elem.right != null) {
                queue.add(elem.right);
            }
            if (cnt == 0) {
                res.add(new ArrayList<>(tmp));
                tmp.clear();
                cnt = queue.size();
            }
        }
        return res;
    }
}
