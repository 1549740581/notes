package recursive;

import utils.TreeNode;

import java.util.ArrayDeque;

/**
 * 101 symmetric tree
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 注意：空节点返回true
 *
 * @author sherman
 */

public class Q05SymmetricTree {
    /**
     * 递归
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }

    /**
     * 迭代法
     */
    private TreeNode dummy = new TreeNode(-1);

    public boolean isSymmetricIter(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root.left);
        deque.add(root.right);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size / 2; ++i) {
                TreeNode left = deque.removeFirst();
                TreeNode right = deque.removeLast();
                if (left == dummy && right == dummy) {
                    continue;
                }
                if (left == dummy || right == dummy) {
                    return false;
                }
                if (left.val != right.val) {
                    return false;
                }
                /**
                 * addXxx、offerXxx都不能添加null元素
                 */
                deque.addFirst(left.left == null ? dummy : left.left);
                deque.addFirst(left.right == null ? dummy : left.right);
                deque.addLast(right.right == null ? dummy : right.right);
                deque.addLast(right.left == null ? dummy : right.left);
            }
        }
        return true;
    }
}
