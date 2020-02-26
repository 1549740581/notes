import java.util.ArrayList;
import java.util.Stack;

/**
 * 二叉树中和为某一值的路径
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径.
 * 路径定义为从树的根结点开始往下一直到【叶子结点】所经过的结点形成一条路径.
 *
 * @author sherman
 */
public class Q24二叉树中和为某值的路径 {
    private ArrayList<ArrayList<Integer>> pathes = new ArrayList<>();
    private Stack<Integer> stack = new Stack<>();

    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        if (root == null){
            return pathes;
        }
        int currentSum = 0;
        findPathInternal(root, currentSum, target);
        return pathes;
    }	

    private void findPathInternal(TreeNode root, int currentSum, int target) {
        currentSum += root.val;
        stack.push(root.val);
        if (root.left == null && root.right == null && (currentSum == target)) {
            pathes.add(new ArrayList<>(stack));
        }
        if (root.left != null) {
            findPathInternal(root.left, currentSum, target);
        }
        if (root.right != null) {
            findPathInternal(root.right, currentSum, target);
        }
        stack.pop();
    }
}
