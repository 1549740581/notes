package fun.sherman.unionfind;

import java.util.Arrays;

/**
 * QuickUnion优化：
 * 在Rank优化的基础上添加路径压缩（Path Compression），路径压缩之后并不需要维护
 * 各个子树的准确高度，因为压缩之后的各个子树的相对高度没有变化，这也是为什么叫Rank
 * 优化而不叫Height优化的原因，没有必要维护各个子树的准确高度
 * Created on 2020/5/2
 *
 * @author sherman tang
 */
public class QuickUnionPathCompression implements UF {

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

    public QuickUnionPathCompression(int size) {
        parent = new int[size];
        rank = new int[size];
        Arrays.fill(rank, 1);
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    /**
     * 路径压缩：parent[idx] = parent[parent[idx]]即可
     */
    private int find(int idx) {
        if (idx < 0 || idx >= parent.length) {
            throw new IllegalArgumentException("索引越界");
        }
        while (idx != parent[idx]) {
            parent[idx] = parent[parent[idx]];
            idx = parent[idx];
        }
        return idx;
    }

    /**
     * 完全路径压缩：让所有的子节点都直接成为根节点的子节点，递归实现
     */
    private int findWay2(int idx) {
        if (idx < 0 || idx >= parent.length) {
            throw new IllegalArgumentException("索引越界");
        }
        if (idx != parent[idx]) {
            parent[idx] = find(parent[idx]);
        }
        return parent[idx];
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
     * 最终形成的树rank最多上升1
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
            rank[qRoot] += 1;   // 注意维护rank，此时rank加1
        }
    }
}
