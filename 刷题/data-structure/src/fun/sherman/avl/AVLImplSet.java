package fun.sherman.avl;

import fun.sherman.set.Set;

/**
 * 使用AVL树作为底层数据结构实现集合
 * Created on 2020/5/3
 *
 * @author sherman tang
 */
public class AVLImplSet<E extends Comparable<E>> implements Set<E> {
    private AVL<E, Object> avl = new AVL<>();
    private static Object dummy = new Object();

    @Override
    public void add(E e) {
        avl.add(e, dummy);
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    @Override
    public int size() {
        return avl.size();
    }
}
