package fun.sherman.set;

/**
 * 高层数据结构Set
 * Created on 2020/4/27
 *
 * @author sherman tang
 */
public interface Set<E> {
    void add(E e);

    void remove(E e);

    boolean contains(E e);

    boolean isEmpty();

    int size();
}
