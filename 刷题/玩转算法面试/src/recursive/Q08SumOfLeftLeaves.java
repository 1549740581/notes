package recursive;

import utils.TreeNode;

/**
 * 404 sum of left leaves
 *
 * @author sherman
 */
public class Q08SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        int cnt = 0;
        if (root != null) {
            if (root.left != null && root.left.left == null && root.left.right == null) {
                cnt += root.left.val;
            }
            cnt += sumOfLeftLeaves(root.left);
            cnt += sumOfLeftLeaves(root.right);
        }
        return cnt;
    }
}
