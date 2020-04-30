package fun.sherman.map;

/**
 * Created on 2020/4/30
 *
 * @author sherman tang
 */
public class LinkedListMap<K, V> implements Map<K, V> {
    /**
     * Map的键值对
     */
    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }

    /**
     * 虚拟头结点
     */
    private Node dummyHead;

    /**
     * Map键值对数量
     */
    private int size;

    public LinkedListMap() {
        this.dummyHead = new Node();
    }

    @Override
    public void add(K key, V value) {
        Node node = getNodeByKey(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else { // 更新或者抛出异常，取决于具体义务规范
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.value.equals(key)) {
                break;
            }
            pre = pre.next;
        }
        // 准备删除pre.next节点
        if (pre.next != null) {
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null; // for GC
            size--;
            return delNode.value;
        }
        // 未找到待删除节点
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNodeByKey(key) != null;
    }

    @Override
    public void put(K key, V newValue) {
        Node node = getNodeByKey(key);
        if (node == null) { // key必定要存在，注意区别java.util下Map的API
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    @Override
    public V get(K key) {
        Node node = getNodeByKey(key);
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

    /**
     * 给定key在链表中寻找到对应节点，否则返回null
     */
    private Node getNodeByKey(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

}
