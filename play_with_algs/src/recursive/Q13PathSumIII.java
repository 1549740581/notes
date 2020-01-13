package recursive;

import utils.TreeNode;

/**
 * 437 path sum III
 * 给定一个二叉树，它的每个结点都存放着一个整数值。找出路径和等于给定数值的路径总数。
 * 注意：路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * @author sherman
 */
public class Q13PathSumIII {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        /**
         * root节点不再当前路径中
         */
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        /**
         * root节点在当前path中，当前子树以root为根节点进行查询
         */
        res += findPath(root, sum);
        return res;
    }

    private int findPath(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.val == sum) {
            res += 1;
        }
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }
}
