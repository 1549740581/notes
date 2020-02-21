package recursive;

import utils.TreeNode;

/**
 * 226 invert binary tree
 * 翻转（镜像）一棵二叉树。
 *
 * @author sherman
 */
public class Q03InvertBiTree {
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }
}
