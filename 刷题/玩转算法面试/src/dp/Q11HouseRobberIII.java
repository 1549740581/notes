package dp;

import utils.TreeNode;

/**
 * 337 house robber III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，
 * 每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接
 * 相连的房子在同一天晚上被打劫，房屋将自动报警。计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 输入：
 *               3
 *            /    \
 *           4      5
 *         /   \      \
 *        1     3       1
 * 输出：9
 *
 * @author sherman
 */
public class Q11HouseRobberIII {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        postOrder(root);
        return root.val;
    }

    /**
     * 后序遍历，由下向上递推
     */
    private void postOrder(TreeNode node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        /**
         * 不抢劫当前节点能够获取的最大价值
         */
        int res1 = 0;
        /**
         * 抢劫当前节点能够获取的最大价值
         */
        int res2 = node.val;
        if (node.left != null) {
            res1 += node.left.val;
            if (node.left.left != null) {
                res2 += node.left.left.val;
            }
            if (node.left.right != null) {
                res2 += node.left.right.val;
            }
        }
        if (node.right != null) {
            res1 += node.right.val;
            if (node.right.left != null) {
                res2 += node.right.left.val;
            }
            if (node.right.right != null) {
                res2 += node.right.right.val;
            }
        }
        node.val = Math.max(res1, res2);
    }
}
