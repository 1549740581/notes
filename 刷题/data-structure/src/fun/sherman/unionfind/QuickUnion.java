package fun.sherman.unionfind;

/**
 * Quick Union
 * find操作：O(h)，h为树的高度
 * union操作：O(h)，h为树的高度
 * Created on 2020/5/2
 *
 * @author sherman tang
 */
public class QuickUnion implements UF {
    /**
     * parent[i] = j：元素i的父亲节点为j，
     * 特别的，parent[i] = i：元素i为根节点
     */
    private int[] parent;

    public QuickUnion(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    /**
     * 查询idx元素的根节点编号
     * O(h)，h为树的高度
     */
    private int find(int idx) {
        if (idx < 0 || idx >= parent.length) {
            throw new IllegalArgumentException("索引越界");
        }
        // 注意这个是顺序索引，但是索引并不一定是连续空间内存上索引
        while (idx != parent[idx]) {
            idx = parent[idx];
        }
        return idx;
    }

    @Override
    public int size() {
        return parent.length;
    }

    /**
     * O(h)，h为树的高度
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * O(h)，h为树的高度
     */
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        parent[pRoot] = qRoot;
    }
}
