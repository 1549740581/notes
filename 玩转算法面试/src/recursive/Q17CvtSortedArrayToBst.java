package recursive;

import utils.TreeNode;

/**
 * 108 convert sorted array to binary search tree
 *
 * @author sherman
 */
public class Q17CvtSortedArrayToBst {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = lo + ((hi - lo) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, lo, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, hi);
        return root;
    }
}
