package recursive;

import utils.TreeNode;

/**
 * 100 same tree
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 相同：结构和对应节点值都相同
 *
 * @author sherman
 */
public class Q04SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        /**
         * 两者都是null
         */
        if (p == null && q == null) {
            return true;
        }
        /**
         * 有一个等于null
         */
        if (p == null || q == null) {
            return false;
        }
        /**
         * 都不等于null
         */
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
