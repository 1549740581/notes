package recursive;

import utils.TreeNode;

/**
 * 111 minimum depth of binary tree
 * 给定一个二叉树，找出其最小深度。
 * 注意：叶子节点是没有子节点的节点，但是根节点不能算叶子结点
 *
 * @author sherman
 */
public class Q02MinDepthOfBiTree {
    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        /**
         * 注意left + right + 1:
         * 因为不知道是左子树到达叶子结点还是右子树到达叶子结点，但是不管谁达到，对应的left或right都是0，
         * 可以用left + right + 1统一表示。
         */
        return (left != 0 && right != 0) ? Math.min(left, right) + 1 : left + right + 1;
    }
}
