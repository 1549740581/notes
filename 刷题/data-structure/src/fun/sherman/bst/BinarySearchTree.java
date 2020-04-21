package fun.sherman.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 不包含重复元素的二分查找树
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private class Node {
        private E e;
        private Node left;
        private Node right;

        public Node(E e) {
            this.e = e;
        }
    }

    /**
     * 二分搜索树根节点
     */
    private Node root;

    /**
     * 二分搜索树的节点数量
     */
    private int size;

    /**
     * 返回二分搜索树节点数量
     */
    public int size() {
        return size;
    }

    /**
     * 二分查找树是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回值为void，递归形式不够优雅
     */
    public void addV1(E e) {
        if (root == null) {
            root = new Node(e);
            ++size;
        } else {
            addInternalV1(root, e);
        }
    }

    /**
     * 插入操作内部调用，V1版本
     */
    private void addInternalV1(Node root, E e) {
        // 递归终止条件
        if (e.equals(root.e)) {
            return;
        }
        int cmp = e.compareTo(root.e);
        if (cmp < 0 && root.left == null) {
            root.left = new Node(e);
            ++size;
            return;
        } else if (cmp > 0 && root.right == null) {
            root.right = new Node(e);
            ++size;
            return;
        }

        // 开始递归调用
        if (cmp < 0) {
            addInternalV1(root.left, e);
        } else {
            addInternalV1(root.right, e);
        }
    }

    /**
     * 插入操作简洁形式，V2版本
     */
    public void addV2(E e) {
        root = addInternalV2(root, e);
    }

    /**
     * 插入操作简洁形式实现
     * 返回插入操作完成后BST的根节点
     */
    private Node addInternalV2(Node node, E e) {
        if (node == null) {
            ++size;
            return new Node(e);
        }
        int cmp = e.compareTo(node.e);
        if (cmp < 0) {
            node.left = addInternalV2(node.left, e);
        } else if (cmp > 0) { // 注意这里不能写成else，cmp==0的情况相当于no-op
            node.right = addInternalV2(node.right, e);
        }
        return node;
    }

    /**
     * 查找BST是否存在值e
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 查找BST递归实现
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        int cmp = e.compareTo(node.e);
        if (0 == cmp) {
            return true;
        } else if (cmp < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 先序遍历，最自然的遍历方式
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 先序遍历实现
     */
    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历，和排序相关
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历实现
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.e);
            inOrder(node.right);
        }
    }

    /**
     * 后续遍历，典型应用内存释放
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历实现
     */
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.e);
        }
    }

    /**
     * 模拟栈帧
     */
    private class DataFrame {
        private Node node;
        private boolean isVisited;

        public DataFrame(Node node, boolean isVisited) {
            this.node = node;
            this.isVisited = isVisited;
        }
    }

    /**
     * 使用DataFrame模拟栈帧前序遍历
     */
    public void preOrderNR() {
        preOrderNR(root);
    }

    /**
     * 使用DataFrame模拟栈帧前序遍历实现
     */
    private void preOrderNR(Node node) {
        if (node != null) {
            Stack<DataFrame> stk = new Stack<>();
            stk.push(new DataFrame(node, false));
            while (!stk.isEmpty()) {
                DataFrame top = stk.pop();
                if (top.isVisited) {
                    System.out.println(top.node.e);
                } else { // FIFO，右子树-左子树-根节点
                    if (top.node.right != null) {
                        stk.push(new DataFrame(top.node.right, false));
                    }
                    if (top.node.left != null) {
                        stk.push(new DataFrame(top.node.left, false));
                    }
                    stk.push(new DataFrame(top.node, true));
                }
            }
        }
    }

    /**
     * 使用DataFrame模拟栈帧中序遍历
     */
    public void inOrderNR() {
        inOrderNR(root);
    }

    /**
     * 使用DataFrame模拟栈帧中序遍历实现
     */
    private void inOrderNR(Node node) {
        if (node != null) {
            Stack<DataFrame> stk = new Stack<>();
            stk.push(new DataFrame(node, false));
            while (!stk.isEmpty()) {
                DataFrame top = stk.pop();
                if (top.isVisited) {
                    System.out.println(top.node.e);
                } else { // FIFO，右子树-根节点-左子树
                    if (top.node.right != null) {
                        stk.push(new DataFrame(top.node.right, false));
                    }
                    stk.push(new DataFrame(top.node, true));
                    if (top.node.left != null) {
                        stk.push(new DataFrame(top.node.left, false));
                    }
                }
            }
        }
    }

    /**
     * 使用DataFrame模拟栈帧后序遍历
     */
    public void postOrderNR() {
        postOrderNR(root);
    }

    /**
     * 使用DataFrame模拟栈帧后序遍历实现
     */
    private void postOrderNR(Node node) {
        if (node != null) {
            Stack<DataFrame> stk = new Stack<>();
            stk.push(new DataFrame(node, false));
            while (!stk.isEmpty()) {
                DataFrame top = stk.pop();
                if (top.isVisited) {
                    System.out.println(top.node.e);
                } else { // FIFO，根节点-右子树-左子树
                    stk.push(new DataFrame(top.node, true));
                    if (top.node.right != null) {
                        stk.push(new DataFrame(top.node.right, false));
                    }
                    if (top.node.left != null) {
                        stk.push(new DataFrame(top.node.left, false));
                    }
                }
            }
        }
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        if (root != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                System.out.println(node.e);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

    /**
     * 返回BST中节点最小值
     */
    public E minimum() {
        if (root == null) {
            throw new IllegalStateException("BST is empty");
        }
        return minimum(root).e;
    }

    /**
     * 返回BST中节点最小值递归实现
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 返回BST中节点最大值
     */
    public E maximum() {
        if (root == null) {
            throw new IllegalStateException("BST is empty");
        }
        return maximum(root).e;
    }

    /**
     * 返回BST中节点最大值递归实现
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 删除BST中最小值
     */
    public E deleteMin() {
        E min = minimum();
        deleteMin(root);
        return min;
    }

    /**
     * 删除BST中最小值递归实现，返回删除最小值后BST的根节点
     */
    private Node deleteMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null; // node是孤立节点
            --size;
            return rightNode;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     * 删除BST中最大值
     */
    public E deleteMax() {
        E max = maximum();
        deleteMax(root);
        return max;
    }

    /**
     * 删除BST中最大值递归实现，返回删除最大值后BST的根节点
     */
    private Node deleteMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null; // node是孤立节点
            --size;
            return leftNode;
        }
        node.right = deleteMax(node.right);
        return node;
    }

    /**
     * 删除BST指定值的节点
     */
    public void delete(E e) {
        root = delete(root, e);
    }

    /**
     * Hibbard Deletion algorithm
     */
    private Node delete(Node node, E e) {
        if (node == null) {
            return null;
        }
        int cmp = e.compareTo(node.e);
        if (cmp < 0) {
            node.left = delete(node.left, e);
        } else if (cmp > 0) {
            node.right = delete(node.right, e);
        } else { // Hibbard Deletion since 1962
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null; // node是孤立节点
                --size;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null; // node是孤立节点
                --size;
                return leftNode;
            }
            // 寻找node节点的后继successor，node右子树中最小值
            // 也可以寻找node节点的前驱predecessor，node左子树的最大值
            Node successor = minimum(node.right);
            /**
             * Attention：
             * deleteMin(node.right)内部执行了--size，但是拿到的successor还在BST中，
             * 相当于需要执行一次++size弥补数量，但是之后又需要删除节点（--size），即：
             * ++size和--size相互抵消，不需要对size进行操作
             */
            successor.right = deleteMin(node.right);
            successor.left = node.left;
            node.left = node.right = null; // node是孤立节点
            return successor;
        }
        return node;
    }
}
