import java.util.Arrays;

/**
 * 重构二叉树
 * 给定二叉树的前序遍历和中序遍历（两个序列都不含重复数字），重构二叉树
 * 【示例】
 * - 前序遍历: {1,2,4,7,3,5,6,8}
 * - 中序遍历: {4,7,2,1,5,3,8,6}
 *
 * @author sherman
 */
public class Q04重构二叉树 {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if ((pre.length | in.length) == 0 || pre.length != in.length) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; ++i) {
            if (in[i] == pre[0]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }
}
