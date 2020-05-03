package fun.sherman.rbtree;

import fun.sherman.map.BSTImplMap;
import fun.sherman.utils.FileUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 2-3树：
 *     1. 是BST，但是有两种节点，2-节点和3-节点，是完全平衡的树
 *     2. 插入的时候不会形成叶子节点，而是会和之前节点进行融合，形成3-节点或者临时4-节点
 *     3. 临时4-节点需要进行分裂，形成3个2-节点
 *
 * 红黑树和2-3树是完全等价的：
 *     1. 黑色节点 ---> 2-节点，代表着独立节点，之后进行添加红色节点不会有问题
 *     2. 红色节点 ---> 3-节点，代表着必须和其父亲节点（黑色）进行融合，形成临时4-节点
 *     3. 临时4-节点需要分裂成3个黑色节点（2-节点），但是分裂后的父节点仍然需要改变成红色，
 *        因为分裂之后仅代表当前子树满足红黑树定义，但是往上回溯并不一定满足，所以需要将根
 *        改变成红色，代表需要向上进行融合
 *     4. 红黑树的颜色也可以理解成当前节点指向父节点边的颜色，直接对节点中添加颜色定义省略了
 *        对边信息的定义
 *     5. 根据和2-3树等价的定义，会导致最终形成的红黑树的红色节点是向左倾斜的
 *     6. 红黑树中每经过一个黑节点代表着2-3树中经过一个节点（2-节点或3-节点），和红黑树第5条定义保持一致
 *
 * 红黑树定义：
 *     1. 根节点是黑色的
 *     2. 每个节点要么是红色要么是黑色
 *     3. 所有叶子节点 (这里指的是null节点)是黑色的，空树也是红黑树，而红黑树的根节点必须是黑色的，定义相呼应
 *     4. 红色节点的孩子节点必定是黑色的  ---> 黑色节点的右孩子必定是黑色的，但左孩子可红可黑
 *     5. 从任意一个节点到叶子节点(null节点)，经过的黑色节点数量是一致的  ---> 红黑树是“黑”平衡的二叉树
 *        这也导致，红黑树最坏的情况下的高度为：2log(n)，即每经过一个黑节点，就会有一个红节点
 *
 * AVL和红黑树：
 *     1. 两者从时间复杂度分析上来说都是O(log(n))，但是红黑树的常数项是2
 *     2. 如果数据基本不会改动（很少的添加、删除操作），大量的查询操作，使用AVL树性能更高
 *     3. 如果数据频繁改动，使用红黑树性能更高
 * Created on 2020/5/3
 *
 * @author sherman tang
 */
public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            // 默认添加的节点是红色，因为2-3树中插入元素总是和之前节点融合
            // 而红色节点就代表着需要和父节点（黑色）进行融合，语义上满足
            this.color = RED;
        }
    }

    /**
     * 红黑树根节点
     */
    private Node root;

    /**
     * 红黑树中节点的个数
     */
    private int size;

    /**
     * 返回红黑树中节点数量
     */
    public int size() {
        return this.size;
    }

    /**
     * 判断节点的颜色
     */
    public boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    /**
     * 向红黑树中添加一个元素，默认添加是红色节点
     * 如果该红色节点不断向上融合至根节点，根节点颜色是黑色的，需要改变
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = add(node.left, key, value);
        }else if (cmp > 0) {
            node.right = add(node.right, key, value);
        }else {
            node.value = value;
        }
        /**
         * 维护红黑树：3-节点中添加元素需要维护的三种情况
         *     B         B             B
         *   /   case1  /   左旋     /    右旋     B   颜色翻转    R
         *  R   --->   R    --->    R     --->   /  \   --->    /  \
         *  |           \         /             R    R         B    B
         *  |            R       R                 ^
         *  |                   ^                  |
         *  |             case2 |            case3 |
         *  |-------------------|------------------|
         *
         * 2-节点中添加元素唯一需要维护的情况：这种情况可看做是3-节点中添加元素的case1
         *    B     左旋     B
         *     \    --->   /
         *      R         R
         */
        // 左旋
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        // 右旋
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        // 颜色翻转
        if (isRed(node.left) && isRed(node.right)) {
            flipColor(node);
        }

        return node;
    }


    /**
     * 向红黑树中插入一个节点形成临时4节点后，需要进行分裂
     * 1. node的左右子孩子颜色变为黑色
     * 2. node节点颜色变为红色，因为需要不断向上融合，满足红黑树定义
     */
    private void flipColor(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    /**
     * 左旋转只保持旋转后节点满足3-节点性质，并不保证红黑树性质，只是一个子过程
     *         node                                x
     *        /    \           左旋转            /   \
     *      T1      x      ----------->       node   T3
     *            /  \                        /  \
     *           T2   T3                     T1  T2
     *
     */
    private Node leftRotate(Node node) {
        Node x = node.right;

        // 左旋转
        node.right = x.left;
        x.left = node;

        // 更新颜色
        x.color = node.color; // x的颜色更新为原来node的颜色
        node.color = RED;     // node颜色需要变为RED，代表向上和x进行融合形成临时4-节点，之后需要进行flipColor过程
        return x;
    }

    /**
     * 右旋转只保持旋转后节点满足3-节点性质，并不保证红黑树性质，只是一个子过程
     *        node                              x
     *       /   \          右旋转             /  \
     *      x    T3      ------------>       T1  node
     *    /  \                                   /  \
     *   T1  T2                                 T2  T3
     */
    private Node rightRotate(Node node) {
        Node x = node.left;

        // 左旋转
        node.left = x.right;
        x.right = node;

        // 更新颜色
        x.color = node.color; // x的颜色更新为原来node的颜色
        node.color = RED;     // node颜色需要变为RED，代表向上和x进行融合形成临时4-节点，之后需要进行flipColor过程
        return x;
    }

    /**
     * 判断是否存在给定key的节点
     */
    public boolean contains(K key) {
        return getNodeByKey(root, key) != null;
    }

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
     * 获取给定key对应节点的value值
     */
    public V get(K key) {
        Node node = getNodeByKey(root, key);
        return node == null ?
                null :
                node.value;
    }

    /**
     * 在给定key存在的情况下，更新对应节点的value值
     */
    public void put(K key, V newValue){
        Node node = getNodeByKey(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " 不存在");

        node.value = newValue;
    }

    public static void main(String[] args) {
        List<String> words = FileUtil.getAllWords("apache license 2.0.txt");
        System.out.println(words.size());
        RBTree<String, Integer> rbTree = new RBTree<>();
        for (String word : words) {
            if(rbTree.contains(word)) {
                rbTree.put(word, rbTree.get(word) + 1);
            }else {
                rbTree.add(word, 1);
            }
        }
        System.out.println(Arrays.asList(rbTree.get("of"), rbTree.get("you"), rbTree.get("or")));

        BSTImplMap<String, Integer> bstImplMap = new BSTImplMap<>();
        for(String word: words) {
            if (bstImplMap.contains(word)) {
                bstImplMap.put(word, bstImplMap.get(word) +1);
            }else {
                bstImplMap.add(word, 1);
            }
        }
        System.out.println(Arrays.asList(bstImplMap.get("of"), bstImplMap.get("you"), bstImplMap.get("or")));
    }
}
