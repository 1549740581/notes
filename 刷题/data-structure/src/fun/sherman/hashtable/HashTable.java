package fun.sherman.hashtable;

import java.util.TreeMap;

/**
 * 使用数组 & 红黑树实现HashTable，注意一个小bug：
 * HashTable本身而言不要求Key是Comparable的，但是使用HashTable作为数组中每个元素，又需要要求Key是Comparable的
 * Created on 2020/5/4
 *
 * @author sherman tang
 */
public class HashTable<K extends Comparable<K>, V> {
    /**
     * 动态扩容时数组长度
     */
    private final int[] capacity = {
            53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317, 196613,
            393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843, 50331653,
            100663319, 201326611, 402653189, 805306457, 1610612741
    };

    /**
     * 容忍上界
     */
    private static final int upperTol = 10;

    /**
     * 容忍下界
     */
    private static final int lowerTol = 2;

    /**
     * 底层数组，每个数组元素直接是TreeMap
     */
    private TreeMap<K, V>[] hashtable;

    /**
     * 数组容量下标
     */
    private int capIdx;

    /**
     * 数组容量
     */
    private int cap;

    /**
     * hashtable中节点数量
     */
    private int size;

    public HashTable() {
        this.cap = capacity[capIdx];
        size = 0;
        hashtable = new TreeMap[cap];
        for (int i = 0; i < cap; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    /**
     * 计算key的hash值
     */
    public int hash(K key) {
        return hash(key, cap);
    }

    private int hash(K key, int cap) {
        return (key.hashCode() & 0x7fffffff) % cap;
    }

    /**
     * 获取hashtable中元素个数
     */
    public int size() {
        return this.size;
    }

    /**
     * 向hashtable中添加一个元素
     */
    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value); // 更新
        } else {
            map.put(key, value); // 增加
            size++;
            if (size >= upperTol * cap && capIdx + 1 < capacity.length) {
                resize(++capIdx);
            }
        }
    }

    private void resize(int newCap) {
        TreeMap<K, V>[] treeMaps = new TreeMap[newCap];
        for (int i = 0; i < newCap; i++) {
            treeMaps[i] = new TreeMap<>();
        }

        // rehash
        for (int i = 0; i < cap; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet()) {
                treeMaps[hash(key, newCap)].put(key, map.get(key)); // 注意不能直接使用hash(key)，hashtable大小已经更新
            }
        }

        this.hashtable = treeMaps;
    }

    /**
     * 从hashtable中删除一个元素
     */
    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            size--;
            if (size < lowerTol * cap && capIdx - 1 >= 0) {
                resize(--capIdx);
            }
            return map.get(key);
        }
        return null;
    }

    /**
     * 更新hashtable中元素
     */
    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            throw new IllegalArgumentException(key + " 不存在");
        }
    }

    /**
     * 查找是否存在
     */
    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }
}
