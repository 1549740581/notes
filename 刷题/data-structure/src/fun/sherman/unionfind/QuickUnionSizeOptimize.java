package fun.sherman.unionfind;

import java.util.Arrays;

/**
 * QuickUnion优化：
 * Union过程中，让size较小的树成为size较大树的子树，会使得最终形成的树高度较低
 * 存在的问题：
 * size较小的树高度并不一定比size较大的树高度低，有可能导致最终形成的树增加的高度大于1
 * Created on 2020/5/2
 *
 * @author sherman tang
 */
public class QuickUnionSizeOptimize implements UF {
    /**
     * parent[i] = j：元素i的父亲节点为j
     * parent[i] = i：元素i为根节点
     */
    private int[] parent;

    /**
     * size[i] = j：元素i构成的子树大小为1
     * 初始情况下，size[i] = 1
     */
    private int[] size;

    public QuickUnionSizeOptimize(int size) {
        this.parent = new int[size];
        this.size = new int[size];
        Arrays.fill(this.size, 1);
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    private int find(int idx) {
        if (idx < 0 || idx >= parent.length) {
            throw new IllegalArgumentException("索引越界");
        }
        while (idx != parent[idx]) {
            idx = parent[idx];
        }
        return idx;
    }

    @Override
    public int size() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 将size较小的子树合并到size较大的子树上
     */
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (size[pRoot] < size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else { // size[pRoot] >= size[qRoot]
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
    }
}
