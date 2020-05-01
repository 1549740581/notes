package fun.sherman.segmentree;

import com.sun.istack.internal.NotNull;

import java.util.function.BiFunction;

/**
 * Created on 2020/5/1
 *
 * @author sherman tang
 */
public class SegmentTree<E> {
    /**
     * 存储真实数据
     */
    private E[] data;

    /**
     * 构建满二叉树，最多需要4n空间
     */
    private E[] tree;

    private BiFunction<E, E, E> strategy;

    @SuppressWarnings("unchecked")
    SegmentTree(E[] arr, @NotNull BiFunction<E, E, E> strategy) {
        data = (E[]) new Object[arr.length];
        System.arraycopy(arr, 0, data, 0, arr.length);
        tree = (E[]) new Object[arr.length * 4];
        this.strategy = strategy;

        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 创建以idx为根节点，区间在[left...right]上的线段树
     */
    private void buildSegmentTree(int idx, int left, int right) {
        if (left == right) {
            tree[idx] = data[left];
            return;
        }
        int leftChildIdx = leftChild(idx);
        int rightChildIdx = rightChild(idx);
        int mid = left + ((right - left) >> 1);

        // 类比后序遍历
        buildSegmentTree(leftChildIdx, left, mid);
        buildSegmentTree(rightChildIdx, mid + 1, right);

        // 策略设计模式，取决于具体业务逻辑
        tree[idx] = strategy.apply(tree[leftChildIdx], tree[rightChildIdx]);
    }

    public int size() {
        return data.length;
    }

    public E get(int idx) {
        if (idx < 0 || idx >= data.length) {
            throw new IllegalArgumentException("数组越界");
        }
        return data[idx];
    }

    /**
     * 线段树范围查询
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("索引异常");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 以idx为根节点的线段树（区间为[l...r]）中查找区间[queryL...queryR]对应strategy结果
     */
    private E query(int idx, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[idx];
        }
        int mid = l + ((r - l) >> 1);
        int leftIdx = leftChild(idx);
        int rightIdx = rightChild(idx);

        if (queryL >= mid + 1) {
            return query(rightIdx, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftIdx, l, mid, queryL, queryR);
        } else {
            return strategy.apply(
                    query(leftIdx, l, mid, queryL, mid),
                    query(rightIdx, mid + 1, r, mid + 1, queryR)
            );
        }
    }

    /**
     * 线段树更新操作
     */
    public void set(int idx, E e) {
        set(0, 0, data.length - 1, idx, e);
    }

    /**
     * 以rootIdx为根节点范围为[l...r]的线段树，更新idx位置的元素为e
     */
    private void set(int rootIdx, int l, int r, int idx, E e) {
        if (rootIdx == idx) { // 更新tree[idx]节点，但是注意当前节点的父节点即以上节点都会受到影响
            tree[idx] = e;
            return;
        }
        int mid = l + (r - l) >> 1;
        int leftIdx = leftChild(rootIdx);
        int rightIdx = rightChild(rootIdx);
        if (idx >= mid + 1) {
            set(rightIdx, mid + 1, r, idx, e);
        } else {
            set(leftIdx, l, mid, idx, e);
        }

        // 将rootIdx父节点及以上节点影响更新回去，思路类似后序遍历
        tree[rootIdx] = strategy.apply(tree[leftIdx], tree[rightIdx]);
    }

    private int leftChild(int idx) {
        return (idx << 1) + 1;
    }

    private int rightChild(int idx) {
        return (idx << 1) + 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (tree.length > 0) {
            sb.append("[");
        }
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                sb.append(tree[i]);
            } else {
                sb.append("null");
            }
            if (i != tree.length - 1) {
                sb.append(" ");
            } else {
                sb.append(("]"));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, -3, 4, -5, 6, 7, -8, 9};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(arr, Integer::max);
        System.out.println(segmentTree.query(0, arr.length - 1));
    }
}
