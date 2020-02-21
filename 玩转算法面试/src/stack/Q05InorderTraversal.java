package stack;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94 binary tree inorder traversal
 * 给定一个二叉树，返回它的中序遍历。
 *
 * @author sherman
 */
public class Q05InorderTraversal {
    private List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }
        return res;
    }

    private static class StackFrame {
        private boolean isPrint;
        private TreeNode node;

        public StackFrame(boolean isPrint, TreeNode node) {
            this.isPrint = isPrint;
            this.node = node;
        }
    }

    public List<Integer> inorderTraversalIter(TreeNode root) {
        if (root == null) {
            return res;
        }
        Stack<StackFrame> stk = new Stack<>();
        stk.push(new StackFrame(false, root));
        while (!stk.isEmpty()) {
            StackFrame elem = stk.pop();
            if (elem.isPrint) {
                res.add(elem.node.val);
            } else {
                if (elem.node.right != null) {
                    stk.push(new StackFrame(false, elem.node.right));
                }
                stk.push(new StackFrame(true, elem.node));
                if (elem.node.left != null) {
                    stk.push(new StackFrame(false, elem.node.left));
                }
            }
        }
        return res;
    }
}
