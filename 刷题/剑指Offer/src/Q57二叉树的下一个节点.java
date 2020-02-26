/**
 * 二叉树的下一个节点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针
 *
 * @author sherman
 */
public class Q57二叉树的下一个节点 {
    public TreeLinkNode getNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        // 当前节点的右子树存在，下一个节点为【右子树中最左边的节点】
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        // 当前节点的右子树不存在，递归向上查找这样的一个节点——该节点是其父节点的左孩子
        // 返回该节点的父节点
        while (pNode.next != null) {
            if (pNode.next.left == pNode) {
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }
}
