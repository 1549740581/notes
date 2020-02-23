/**
 * 包含指向父节点的二叉树节点
 *
 * @author sherman
 */
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    public TreeLinkNode(int val) {
        this.val = val;
    }
}
