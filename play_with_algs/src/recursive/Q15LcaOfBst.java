package recursive;

import utils.TreeNode;

/**
 * 235 lowest common ancestor of a binary search tree
 * 给定一个【二叉搜索树】, 找到该树中两个指定节点的最近公共祖先。
 *
 * @author sherman
 */
public class Q15LcaOfBst {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }
}
