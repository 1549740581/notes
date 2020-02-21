package queue;

import utils.TreeNode;

import java.util.*;

/**
 * 107 binary tree level order traversal II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * @author sherman
 */
public class Q02BinaryTreeLevelOrderTraversalII {
    /**
     * 使用LinkedList，每次向链表头部插入，实现倒序
     */
    private List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.poll();
                tmp.add(node.val);
                if (node.left != null) {
                    deque.add(node.left);
                }
                if (node.right != null) {
                    deque.add(node.right);
                }
            }
            res.add(0, tmp);
        }
        return res;
    }
}
