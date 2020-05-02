package fun.sherman.unionfind;

import java.util.Arrays;

/**
 * QuickUnion优化：
 * 将高度较小的子树合并到高度较大的子树上，这样最终合并形成的树高度最多加1，相对于基于size的合并更加合理
 * Created on 2020/5/2
 *
 * @author sherman tang
 */
public class QuickUnionRankOptimize implements UF {
    /**
     * parent[i] = j：元素i的父亲节点为j
     * parent[i] = i：元素i为根节点
     */
    private int[] parent;

    /**
     * rank[i] = j：元素i节点的高度为j
     * 初始情况下，rank[i] = 1
     */
    private int[] rank;

    public QuickUnionRankOptimize(int size) {
        parent = new int[size];
        rank = new int[size];
        Arrays.fill(rank, 1);
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
     * 将rank较低的子树合并到rank较高的子树上
     * 最终形成的树高度最多上升1
     */
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else { // rank[pRoot] == rank[qRoot]，将p合并到q上
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;   // 注意维护rank，此时高度加1
        }
    }
}
