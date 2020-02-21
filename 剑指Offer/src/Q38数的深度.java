/**
 * 二叉树的深度
 *
 * @author sherman
 */
public class Q38数的深度 {
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }
}
