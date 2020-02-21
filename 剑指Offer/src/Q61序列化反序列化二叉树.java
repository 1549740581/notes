/**
 * 二叉树的序列化和反序列化
 *
 * @author sherman
 */
public class Q61序列化反序列化二叉树 {

    private int idx = -1;

    String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.val).append(",");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }

    TreeNode deserialize(String str) {
        idx++;
        if (idx >= str.length()) {
            return null;
        }
        String[] strs = str.split(",");
        TreeNode node = null;
        if (!strs[idx].equals("#")) {
            node = new TreeNode(Integer.parseInt(strs[idx]));
            node.left = deserialize(str);
            node.right = deserialize(str);
        }
        return node;
    }
}
