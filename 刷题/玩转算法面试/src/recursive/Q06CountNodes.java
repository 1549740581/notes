package recursive;

import utils.TreeNode;

/**
 * 222 count complete tree nodes
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * @author sherman
 */
public class Q06CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {
            return (1 << leftDepth) + countNodes(root.right);
        } else {
            // assert(rightDepth + 1 == leftDepth);
            return (1 << rightDepth) + countNodes(root.left);
        }
    }

    private int getDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            ++depth;
            root = root.left;
        }
        return depth;
    }
}
