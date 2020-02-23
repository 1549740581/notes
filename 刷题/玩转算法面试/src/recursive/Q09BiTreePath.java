package recursive;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 257 binary tree paths
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * @author sherman
 */
public class Q09BiTreePath {
    private List<String> res = new LinkedList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return res;
        }
        return binaryTreePaths(root, new ArrayList<>());
    }

    private List<String> binaryTreePaths(TreeNode root, ArrayList<String> tmp) {
        tmp.add(String.valueOf(root.val));
        if (root.left == null && root.right == null) {
            CharSequence[] cs = new CharSequence[tmp.size()];
            res.add(String.join("->", tmp.toArray(cs)));
        }
        if (root.left != null) {
            binaryTreePaths(root.left, tmp);
        }
        if (root.right != null) {
            binaryTreePaths(root.right, tmp);
        }
        tmp.remove(tmp.size() - 1);
        return res;
    }
}
