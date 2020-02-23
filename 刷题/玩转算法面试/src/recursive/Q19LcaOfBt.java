package recursive;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 236 lowest common ancestor of a binary tree(LCA)
 * 给定一个【二叉树】, 找到该树中两个指定节点的最近公共祖先。
 * - 所有节点的值都是唯一的
 * - p、q 为不同节点且均存在于给定的二叉树中。
 *
 * @author sherman
 */
public class Q19LcaOfBt {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();
        findPath(root, p, path1);
        findPath(root, q, path2);
        int len = path1.size() < path2.size() ? path1.size() : path2.size();
        TreeNode common = root;
        for (int i = 0; i < len; i++) {
            if (path1.get(i) == path2.get(i)) {
                common = path1.get(i);
            }
        }
        return common;
    }

    /**
     * 查询到路径终止标志
     */
    private boolean isFound = false;

    private void findPath(TreeNode node, TreeNode target, List<TreeNode> path) {
        /**
         * 每次调用findPath在成功的情况下，都会将成员变量isFound置为true
         * 注意重置isFound的值
         */
        isFound = false;
        path.add(node);
        if (target == node) {
            isFound = true;
            return;
        }
        if (!isFound && node.left != null) {
            findPath(node.left, target, path);
        }
        if (!isFound && node.right != null) {
            findPath(node.right, target, path);
        }
        if (!isFound) {
            path.remove(path.size() - 1);
        }
    }
}
