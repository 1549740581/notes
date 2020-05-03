package fun.sherman.map;

/**
 * 基于BST构建的高层数据结构Map
 * Created on 2020/4/30
 *
 * @author sherman tang
 */
public class BSTImplMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }
    }

    /**
     * 根节点
     */
    private Node root;

    /**
     * Map大小
     */
    private int size;

    public BSTImplMap() {
    }

    /**
     * 向二分搜索树中添加Node
     */
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = add(node.left, key, value);
        } else if (cmp > 0) {
            node.right = add(node.right, key, value);
        } else { // 注意这里实现是更新操作
            node.value = value;
        }
        return node;
    }

    /**
     * 在BST中删除指定key的节点
     */
    @Override
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
        if (cmp < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null; // for GC
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null; // for GC
                size--;
                return leftNode;
            } else { // Hibbard deletion
                // 在node的右子树中寻找node的后继节点
                Node successor = minimum(node.right);
                successor.right = deleteMin(node.right, key);
                successor.left = node.left;
                node.left = null;
                node.right = null;
                return successor;
            }
        }
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node deleteMin(Node node, K key) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null; // for GC
            size--;
            return rightNode;
        }
        node.left = deleteMin(node.left, key);
        return node;
    }


    @Override
    public boolean contains(K key) {
        return getNodeByKey(root, key) != null;
    }

    @Override
    public void put(K key, V newValue) {
        Node node = getNodeByKey(root, key);
        if (node == null) { // 注意区别java.util下Map的put方法实现
            throw new IllegalArgumentException(key + " 不存在");
        }
        node.value = newValue;
    }

    @Override
    public V get(K key) {
        Node node = getNodeByKey(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点，不存在返回null
    private Node getNodeByKey(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getNodeByKey(node.left, key);
        } else if (cmp > 0) {
            return getNodeByKey(node.right, key);
        }
        return node;
    }
}
