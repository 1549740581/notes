/**
 * 二叉搜索树的第K个结点
 *
 * @author sherman
 */
public class Q62KthNodeInBST {
    private int idx = 0;

    TreeNode KthNode(TreeNode root, int k) {
        TreeNode tmp = null;
        if (root != null) {
            if ((tmp = KthNode(root.left, k)) != null) {
                return tmp;
            }
            if (++idx == k) {
                return root;
            }
            if ((tmp = KthNode(root.right, k)) != null) {
                return tmp;
            }
        }
        return null;
    }
}



