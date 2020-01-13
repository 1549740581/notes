package recursive;

import utils.TreeNode;

/**
 * 104 maximum depth of binary tree
 * 给定一个二叉树，找出其最大深度。
 *
 * @author sherman
 */
public class Q01MaxDepthOfBiTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
