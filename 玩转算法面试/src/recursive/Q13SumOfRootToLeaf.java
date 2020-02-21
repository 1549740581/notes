package recursive;

import utils.TreeNode;

/**
 * 129 sum root to leaf numbers
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * 计算从根到叶子节点生成的所有数字之和。
 *      1
 *    /   \
 *   2     3
 *
 *   12 + 13 = 25
 *
 * @author sherman
 */
public class Q13SumOfRootToLeaf {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumNumbersInternal(root, 0);
    }

    private int sumNumbersInternal(TreeNode node, int base) {
        int sum = 0;
        if (node.left == null && node.right == null) {
            return base * 10 + node.val;
        }
        if (node.left != null) {
            sum += sumNumbersInternal(node.left, base * 10 + node.val);
        }
        if (node.right != null) {
            sum += sumNumbersInternal(node.right, base * 10 + node.val);
        }
        return sum;
    }
}
