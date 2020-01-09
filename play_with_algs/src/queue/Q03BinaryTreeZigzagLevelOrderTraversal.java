package queue;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 103 binary tree zigzag level order traversal
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * @author sherman
 */
public class Q03BinaryTreeZigzagLevelOrderTraversal {
    private List<List<Integer>> res = new ArrayList<>();
    private Stack<TreeNode> stk1 = new Stack<>();
    private Stack<TreeNode> stk2 = new Stack<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return res;
        }
        stk1.push(root);
        while (!stk1.isEmpty() || !stk2.isEmpty()) {
            if (!stk1.isEmpty()) {
                List<Integer> tmp = new ArrayList<>();
                while (!stk1.isEmpty()) {
                    TreeNode node = stk1.pop();
                    tmp.add(node.val);
                    if (node.left != null) {
                        stk2.push(node.left);
                    }
                    if (node.right != null) {
                        stk2.push(node.right);
                    }
                }
                res.add(tmp);
            } else {
                List<Integer> tmp = new ArrayList<>();
                while (!stk2.isEmpty()) {
                    TreeNode node = stk2.pop();
                    tmp.add(node.val);
                    if (node.right != null) {
                        stk1.push(node.right);
                    }
                    if (node.left != null) {
                        stk1.push(node.left);
                    }
                }
                res.add(tmp);
            }
        }
        return res;
    }
}

