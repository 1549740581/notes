package recursive;

import utils.TreeNode;

/**
 * 230 kth smallest element in a bst
 * 给定一个二叉搜索树，编写一个函数kthSmallest 来查找其中第k个最小的元素。
 *
 * @author sherman
 */
public class Q18KthSmallestElemInBst {
    public int kthSmallest(TreeNode root, int k) {
        int leftCnt = countNode(root.left);
        if (leftCnt + 1 == k) {
            return root.val;
        } else if (k <= leftCnt) {
            return kthSmallest(root.left, k);
        } else {
            /**
             * 减去左子树 + 当前子树根节点
             */
            return kthSmallest(root.right, k - leftCnt - 1);
        }
    }

    private int countNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return countNode(node.left) + countNode(node.right) + 1;
    }
}
