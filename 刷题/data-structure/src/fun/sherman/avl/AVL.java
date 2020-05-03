package fun.sherman.avl;

import java.util.ArrayList;

/**
 * AVL树实现
 * Created on 2020/5/3
 *
 * @author sherman tang
 */
public class AVL<K extends Comparable<K>, V> {
    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        /**
         * 每个节点的高度，初始值为1
         */
        int hgt;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.hgt = 1; // 默认高度为1
        }
    }

    /**
     * AVL树根节点
     */
    private Node root;

    /**
     * AVL树节点数量
     */
    private int size;

    /**
     * 没有必要显示初始化
     */
    public AVL() {
        root = null;
        size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 获取给定node的高度，如果为null，返回0
     */
    private int getHgt(Node node) {
        return node == null ?
                0 :
                node.hgt;
    }

    /**
     * 向VAL树中添加一个节点
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            ++size;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = add(node.left, key, value);
        } else if (cmp > 0) {
            node.right = add(node.right, key, value);
        } else { // 直接替换
            node.value = value;
        }
        // 维护node的高度
        node.hgt = 1 + Math.max(getHgt(node.left), getHgt(node.right));

        // 平衡维护，re-balance操作
        int balanceFactor = getBalanceFactor(node);
        // 1. LL：插入的节点在当前节点node左侧的左侧，执行右旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        // 2. RR：插入的节点在当前节点node右侧的右侧，执行左旋转
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        /** 3. LR：插入的节点在当前节点node左孩子的右侧，先执行左旋转变为LL情况，再执行右旋转
         *              y                              y
         *            /  \                           /  \                                z
         *           x   T4       对x进行左旋转      z    T4       对y进行右旋转         /  \
         *         /  \           ------------->   / \           ------------->       x    y
         *        T1   z                          x  T3                             / \   / \
         *            / \                        / \                               T1 T2 T3 T4
         *           T2 T3                     T1  T2
         */
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // 先对x节点进行左旋转，返回的值成为node的左孩子
            node.left = leftRotate(node.left);
            // 在对y节点进行右旋转
            return rightRotate(node);
        }
        /**
         * 4. RL：插入的节点在当前节点node右孩子的左侧，先执行右旋转变为RR情况，再执行左旋转
         *              y                               y
         *            /  \                            /  \                                     z
         *          T1    x        对x进行右旋转     T1    z           对y进行左旋转           /  \
         *               / \      ------------->        /  \         ------------->        y     x
         *              z  T4                         T2    x                            /  \   / \
         *             / \                                /  \                          T1  T2 T3 T4
         *           T2  T3                              T3  T4
         */
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /**
     * 获取给定节点Node的平衡因子
     */
    private int getBalanceFactor(Node node) {
        return getHgt(node.left) - getHgt(node.right);
    }

    /**
     * AVL树中是否包含key
     */
    public boolean contains(K key) {
        return getNodeByKey(root, key) != null;
    }

    /**
     * 更新给定key的value值
     */
    public void put(K key, V value) {
        Node node = getNodeByKey(root, key);
        if (node == null) {
            return;
        }
        node.value = value;
    }

    /**
     * 获取给定key对应Node的value值
     */
    public V get(K key) {
        Node node = getNodeByKey(root, key);
        return node == null ?
                null :
                node.value;
    }

    /**
     * 给定key，返回对应的Node，如果不存在，返回null
     */
    private Node getNodeByKey(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getNodeByKey(node.left, key);
        } else if (cmp > 0) {
            return getNodeByKey(node.right, key);
        } else {
            return node;
        }
    }

    /**
     * 判断构建的AVL树是否是二分搜索树
     */
    public boolean isBST() {
        ArrayList<Node> arrayList = new ArrayList<>();
        inOrder(root, arrayList);
        for (int i = 0; i < arrayList.size() - 1; i++) {
            if (arrayList.get(i).key.compareTo(arrayList.get(i + 1).key) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历
     */
    private void inOrder(Node node, ArrayList<Node> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node);
        inOrder(node.right, list);
    }

    /**
     * 判断构建的AVL树是否是平衡二叉树
     */
    public boolean isBalanceTree() {
        return isBalanceTree(root);
    }

    private boolean isBalanceTree(Node node) {
        if (node == null) {
            return true;
        }
        if (Math.abs(getBalanceFactor(node)) > 1) {
            return false;
        }
        return isBalanceTree(node.left) && isBalanceTree(node.right);
    }

    /**
     * 对节点y进行右旋转操作，返回旋转后的根节点x
     *             y
     *           /  \
     *          x    T4                                x
     *        /  \             向右旋转(y)           /    \
     *       z    T3       ---------------->       z      y
     *     /  \                                  /  \    / \
     *   T1    T2                               T1  T2  T3  T4
     */
    private Node rightRotate(Node y) {
        // 先暂存节点x和节点T3
        Node x = y.left;
        Node t3 = x.right;

        // 右旋转过程
        x.right = y;
        y.left = t3;

        // 更新节点x和y的高度，注意先更新y的高度，在更新x的高度
        y.hgt = Math.max(getHgt(y.left), getHgt(y.right)) + 1;
        x.hgt = Math.max(getHgt(x.left), getHgt(x.right)) + 1;

        return x;
    }

    /**
     * 对节点y进行左旋转操作，返回旋转后的根节点x
     *            y
     *          /  \                                  x
     *        T1    x         向左旋转(y)           /   \
     *             / \    ------------------>     y      z
     *            T2  z                         /  \    / \
     *               / \                      T1   T2  T3  T4
     *             T3  T4
     */
    private Node leftRotate(Node y) {
        // 先暂存节点x和T2
        Node x = y.right;
        Node t2 = x.left;

        // 执行左旋转
        x.left = y;
        y.right = t2;

        // 更新节点x和节点y的高度，注意先更新y的高度，再更新x的高度
        y.hgt = Math.max(getHgt(y.left), getHgt(y.right)) + 1;
        x.hgt = Math.max(getHgt(x.left), getHgt(x.right)) + 1;

        return x;
    }

    /**
     * 删除给定key的节点
     */
    public V remove(K key) {
        Node node = getNodeByKey(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        Node retNode; // 暂存需要返回的节点，可能因为删除操作导致树不平衡
        if (cmp < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null; // for GC
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null; // for GC
                size--;
                retNode = leftNode;
            } else { // hibbard deletion
                Node successor = minimum(node.right);
                // 这里直接复用remove方法，不创建delMin方法，否在还需要在delMin方法中维护树的平衡性
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = null;
                node.right = null;
                retNode = successor;
            }
        }
        // 注意retNode可能为null
        if (retNode == null) {
            return null;
        }
        // 更新height
        retNode.hgt = Math.max(getHgt(retNode.left), getHgt(retNode.right)) + 1;
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        // 1. LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        // 2. RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        // 3. LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // 4. RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(node.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }
}
