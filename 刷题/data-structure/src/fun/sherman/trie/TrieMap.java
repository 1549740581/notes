package fun.sherman.trie;

import java.util.TreeMap;

/**
 * 字典树和映射，字典树中每个节点包含一个权重值
 * 权重值大于0，代表从根节点到当前节点形成的字符串存在Trie中
 * Created on 2020/5/2
 *
 * @author sherman tang
 */
public class TrieMap {
    private static class Node {
        /**
         * wgt大于0，根节点到当前node构成的字符串存在
         */
        private int wgt;

        /**
         * 当前Node节点字符能够指向的其它所有Node节点
         */
        private TreeMap<Character, Node> next;

        public Node(int wgt) {
            this.wgt = wgt;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    /**
     * 当前字典树的大小
     */
    private int size;

    /**
     * 当前字典树的根节点
     */
    private Node root;

    TrieMap() {
        root = new Node();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向TrieMap中添加单词word，权重为wgt
     */
    public void add(String word, int wgt) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.next.get(ch) == null) {
                cur.next.put(ch, new Node());
            }
            cur = cur.next.get(ch);
        }
        // 当前node没有wgt才进行更新wgt和size操作
        if (cur.wgt == 0) {
            cur.wgt = wgt;
            ++size;
        }
    }

    /**
     * 统计Trie中以prefix为前缀的所有字符串wgt之和
     */
    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (cur.next.get(ch) == null) {
                return 0;
            }
            cur = cur.next.get(ch);
        }
        // 返回以cur为根节点的所有子树的wgt之和
        return sum(cur);
    }

    private int sum(Node node) {
        int wgt = node.wgt;
        for (Character ch : node.next.keySet()) {
            wgt += sum(node.next.get(ch));
        }
        return wgt;
    }

    public static void main(String[] args) {
        String prefix = "app";
        TrieMap trieMap = new TrieMap();
        trieMap.add("apple", 5);
        System.out.println(trieMap.sum(prefix));
        trieMap.add("application", 10);
        System.out.println(trieMap.sum(prefix));
        System.out.println(trieMap.size);
    }
}
