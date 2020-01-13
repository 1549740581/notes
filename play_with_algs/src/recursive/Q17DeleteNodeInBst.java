package recursive;

import utils.TreeNode;

/**
 * 450 delete node in a bst
 * 给定一个二叉搜索树的根节点root和一个值key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * @author sherman
 */
public class Q17DeleteNodeInBst {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        int cmp = Integer.compare(key, root.val);
        if (cmp < 0) {
            root.left = deleteNode(root.left, key);
        } else if (cmp > 0) {
            root.right = deleteNode(root.right, key);
        } else {
            /**
             * root左右子孩子不全都是非空节点
             */
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            /**
             * Hibbard deletion
             */
            TreeNode tmp = root;
            root = getMin(root.right);
            root.right = deleteMin(tmp.right);
            root.left = tmp.left;
        }
        return root;
    }

    /**
     * 删除以node节点为根节点的子树中最小的节点，并返回当前节点node
     *
     * @param node 当前子树的根节点
     * @return 当前子树根节点的引用，即node
     */
    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     * 获取以node节点为根节点的子树中值最小的节点
     *
     * @param node 当前子树的根节点
     * @return 值最小的节点
     */
    private TreeNode getMin(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }
}
