package fun.sherman.map;

/**
 * Created on 2020/4/30
 *
 * @author sherman tang
 */
public interface Map<K, V> {

    /**
     * 新增key-value
     */
    void add(K key, V value);

    /**
     * 移除一个key-value，并返回value
     */
    V remove(K key);

    /**
     * 是否包含key
     */
    boolean contains(K key);

    /**
     * 更新key-value
     */
    void put(K key, V newValue);

    /**
     * 返回对应key的value值
     */
    V get(K key);

    /**
     * 返回大小
     */
    int size();

    /**
     * 判断是否为空
     */
    boolean isEmpty();
}
