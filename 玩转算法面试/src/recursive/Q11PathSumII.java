package recursive;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113 path sum II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * @author sherman
 */
public class Q11PathSumII {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }
        pathSumInternal(root, sum, new ArrayList<>());
        return res;
    }

    private void pathSumInternal(TreeNode root, int sum, ArrayList<Integer> tmp) {
        if (root == null) {
            return;
        }
        tmp.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<>(tmp));
            /**
             * 提前结束，注意要移除tmp中最后一个元素
             */
            tmp.remove(tmp.size() - 1);
            return;
        }
        pathSumInternal(root.left, sum - root.val, tmp);
        pathSumInternal(root.right, sum - root.val, tmp);
        tmp.remove(tmp.size() - 1);
    }
}
