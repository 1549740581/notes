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
        // 中序遍历思想体现
        boolean leftCond = isValidBST(root.left);
        boolean midCond = false;
        if (root.val > pre) {
            midCond = true;
            pre = root.val;
        }
        boolean rightCond = isValidBST(root.right);
        // 这种写法仅仅写法优雅，但是效率不高，应该在各个cond之后立即判断
        // 如果条件为false，直接返回，避免多余的无用操作
        return leftCond && midCond && rightCond;
    }
}
