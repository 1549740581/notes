/**
 * 树的子结构
 *
 * 约定：空树不是任意一个树的子结构
 *
 * @author sherman
 */
public class Q17树的子结构 {
    /**
     * 判断root2是不是root1的子结构
     * 这道题包含了两个递归结构
     */
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                result = isContains(root1, root2);
            }
            if (!result) {
                result = hasSubtree(root1.left, root2);
            }
            if (!result) {
                result = hasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    private boolean isContains(TreeNode root1, TreeNode root2) {
        // 注意下面两个if顺序不能调换
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isContains(root1.left, root2.left) && isContains(root1.right, root2.right);
    }
}
