package recursive;

import utils.TreeNode;

/**
 * 98 validate binary search tree
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * @author sherman
 */
public class Q15ValidBst {
    private long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftCond = isValidBST(root.left);
        boolean midCond = false;
        if (root.val > pre) {
            midCond = true;
            pre = root.val;
        }
        boolean rightCond = isValidBST(root.right);
        return leftCond && midCond && rightCond;
    }
}
