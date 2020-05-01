package fun.sherman.trie;

import java.util.TreeMap;

/**
 * Trie（字典树、前缀树）实现
 * Created on 2020/5/1
 *
 * @author sherman tang
 */
public class Trie {
    private static class Node {
        /**
         * 根节点到当前节点位置是否构成一个单词
         */
        private boolean isWord;

        /**
         * 当前Node节点字符能够指向的其它所有Node节点
         */
        private TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    /**
     * Trie的根节点
     */
    private Node root;

    /**
     * Trie的大小
     */
    private int size;

    public Trie() {
        root = new Node();
    }

    /**
     * 获取Trie的大小
     */
    public int size() {
        return size;
    }

    /**
     * 向Trie中添加一个单词
     */
    public void add(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.next.get(ch) == null) {
                cur.next.put(ch, new Node());
            }
            cur = cur.next.get(ch);
        }

        // 当前之前不是单词才进行更新isWord、size操作
        if (!cur.isWord) {
            cur.isWord = true;
            ++size;
        }
    }

    /**
     * 查询Trie中是否包含单词word
     */
    public boolean contains(String word) {
        Node cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.next.get(ch) == null) {
                return false;
            }
            cur = cur.next.get(ch);
        }
        return cur.isWord;
    }

    /**
     * 查询Trie中是否包含前缀prefix
     */
    public boolean startWith(String prefix) {
        Node cur = root;
        for (char ch : prefix.toCharArray()) {
            if (cur.next.get(ch) == null) {
                return false;
            }
            cur = cur.next.get(ch);
        }
        return true;
    }

    /**
     * 简单正则表达式匹配，只包含特殊字符.
     */
    public boolean match(String pattern) {
        return match(root, pattern, 0);
    }

    private boolean match(Node node, String pattern, int idx) {
        if (idx == pattern.length()) {
            return node.isWord;
        }
        char ch = pattern.charAt(idx);
        if (ch != '.') {
            if (node.next.get(ch) != null) {
                return match(node.next.get(ch), pattern, idx + 1);
            } else {
                return false;
            }
        } else {
            for (Character nextCh : node.next.keySet()) {
                if (match(node.next.get(nextCh), pattern, idx + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
}
