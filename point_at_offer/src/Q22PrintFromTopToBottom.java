import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 *
 * @author sherman
 */
public class Q22PrintFromTopToBottom {
    private ArrayList<Integer> lists = new ArrayList<>();
    private Queue<TreeNode> queue = new LinkedList<>();

    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        if (root == null) {
            return lists;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode head = queue.poll();
            lists.add(head.val);
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
        }
        return lists;
    }
}
