package fun.sherman.avl;

import fun.sherman.map.Map;

/**
 * 使用AVL作为底层数据结构实现映射
 * Created on 2020/5/3
 *
 * @author sherman tang
 */
public class AVLImplMap<K extends Comparable<K>, V> implements Map<K, V> {
    private AVL<K, V> avl = new AVL<>();

    @Override
    public void add(K key, V value) {
        avl.add(key, value);
    }

    @Override
    public V remove(K key) {
        return avl.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return avl.contains(key);
    }

    @Override
    public void put(K key, V newValue) {
        avl.put(key, newValue);
    }

    @Override
    public V get(K key) {
        return avl.get(key);
    }

    @Override
    public int size() {
        return avl.size();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
