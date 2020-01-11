package stack;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144 binary tree preorder traversal
 * 给定一个二叉树，返回它的前序遍历。
 *
 * @author sherman
 */
public class Q04PreorderTraversal {
    private List<Integer> res = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root != null) {
            res.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return res;
    }

    private static class DataFrame {
        private boolean isPrint;
        private TreeNode node;

        DataFrame(boolean isPrint, TreeNode node) {
            this.isPrint = isPrint;
            this.node = node;
        }
    }

    public List<Integer> preorderTraversalIter(TreeNode root) {
        if (root == null) {
            return res;
        }
        Stack<DataFrame> stk = new Stack<>();
        stk.push(new DataFrame(false, root));
        while (!stk.isEmpty()) {
            DataFrame elem = stk.pop();
            if (elem.isPrint) {
                res.add(elem.node.val);
            } else {
                if (elem.node.right != null) {
                    stk.push(new DataFrame(false, elem.node.right));
                }
                if (elem.node.left != null) {
                    stk.push(new DataFrame(false, elem.node.left));
                }
                stk.push(new DataFrame(true, elem.node));
            }
        }
        return res;
    }
}
