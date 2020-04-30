package fun.sherman.set;

import fun.sherman.bst.BinarySearchTree;

/**
 * 使用二分搜索树作为集合底层实现
 * Created on 2020/4/27
 *
 * @author sherman tang
 */
public class BSTImplSet<E extends Comparable<E>> implements Set<E> {
    private BinarySearchTree<E> bst;

    public BSTImplSet() {
        this.bst = new BinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        bst.addV1(e);
    }

    @Override
    public void remove(E e) {
        bst.delete(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public int size() {
        return bst.size();
    }
}
