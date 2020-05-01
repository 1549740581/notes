package fun.sherman.heap;

import java.util.ArrayList;

/**
 * Created on 2020/4/30
 *
 * @author sherman tang
 */
public class MaxHeap<E extends Comparable<E>> {
    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        data = new ArrayList<>();
    }

    /**
     * 返回堆中元素
     */
    public int size() {
        return data.size();
    }

    /**
     * 返回堆是否为空
     */
    public boolean isEmpty() {
        return data.size() == 0;
    }

    /**
     * 返回给定索引idx父节点的索引
     */
    private int parent(int idx) {
        if (idx <= 0) {
            throw new IllegalArgumentException(idx + " 非法");
        }
        return (idx - 1) / 2;
    }

    /**
     * 返回给定索引idx左孩子节点的索引
     */
    private int leftChild(int idx) {
        return (idx << 1) + 1;
    }

    /**
     * 返回给定索引idx右孩子节点的索引
     */
    private int rightChild(int idx) {
        return (idx << 1) + 2;
    }

    /**
     * 向最大堆中添加一个元素
     */
    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    /**
     * siftUp操作
     */
    private void siftUp(int idx) {
        // idx合法并且父节点小于当前节点，需要交换
        while (idx > 0 && data.get(parent(idx)).compareTo(data.get(idx)) < 0) {
            swap(parent(idx), idx);
            idx = parent(idx);
        }
    }

    /**
     * 从最大堆中取出最大元素
     */
    public E remove() {
        E maxNode = getMax();
        swap(0, data.size() - 1);
        data.remove(data.size() - 1); // 移除最大元素
        siftDown(0);
        return maxNode;
    }

    /**
     * siftDown操作
     */
    private void siftDown(int idx) {
        // 如果左孩子存在，开始循环
        while (leftChild(idx) < data.size()) {
            // 索引j代表idx对应节点左右孩子较大节点的索引
            int j = leftChild(idx);
            if (j + 1 < data.size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }
            // 父节点（idx）大于等于左右自孩子（j）的最大值，直接停止
            if (data.get(idx).compareTo(data.get(j)) >= 0) {
                break;
            }
            swap(idx, j);
            idx = j;
        }
    }

    private void swap(int i, int j) {
        if (i < 0 || i >= data.size() || j < 0 || j >= data.size()) {
            throw new IllegalArgumentException("参数不合法，索引越界");
        }
        E tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }

    /**
     * 获取堆中最大元素
     */
    public E getMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("堆为空");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大元素，并传入新元素e，只进行一次siftDown操作
     */
    public E replace(E e) {
        E ret = getMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    /**
     * heapify过程
     */
    public void heapify(E[] arr) {
        data = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            data.set(i, arr[i]);
        }
        // 从第一个非叶子节点开始进行siftDown操作
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }
}
