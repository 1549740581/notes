package fun.sherman.set;

import java.util.LinkedList;

/**
 * 使用链表作为集合底层实现，对比二分搜索树实现运行速度
 * Created on 2020/4/27
 *
 * @author sherman tang
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.add(e);
        }
    }

    @Override
    public void remove(E e) {
        list.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }
}
