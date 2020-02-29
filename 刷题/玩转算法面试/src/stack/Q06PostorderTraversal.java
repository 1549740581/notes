package stack;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145 binary tree postorder traversal
 *
 * @author sherman
 */
public class Q06PostorderTraversal {
    private List<Integer> res = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            res.add(root.val);
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

    public List<Integer> postorderTraversalIter(TreeNode root) {
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
                stk.push(new StackFrame(true, elem.node));
                if (elem.node.right != null) {
                    stk.push(new StackFrame(false, elem.node.right));
                }
                if (elem.node.left != null) {
                    stk.push(new StackFrame(false, elem.node.left));
                }
            }
        }
        return res;
    }
}
