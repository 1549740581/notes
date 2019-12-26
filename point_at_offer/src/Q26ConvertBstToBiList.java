/**
 * 二叉搜索树转换成双向链表
 *
 * @author sherman
 */
public class Q26ConvertBstToBiList {

    TreeNode pre = null;

    public TreeNode convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        convert(pRootOfTree.right);
        if (pre == null) {
            pre = pRootOfTree;
        } else {
            pRootOfTree.right = pre;
            pre.left = pRootOfTree;
            pre = pRootOfTree;
        }
        convert(pRootOfTree.left);
        return pre;
    }
}
