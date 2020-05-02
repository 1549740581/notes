package fun.sherman.unionfind;

/**
 * Quick Find
 * find操作：O(1)
 * union操作：O(n)
 * Created on 2020/5/2
 *
 * @author sherman tang
 */
public class QuickFind implements UF {
    /**
     * id[i] = j：元素i的集合编号为j
     */
    private int[] id;

    public QuickFind(int size) {
        id = new int[size];
        // 初始值所有元素都不联通
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    /**
     * 返回idx对应的集合标识
     */
    private int find(int idx) {
        if (idx < 0 || idx >= id.length) {
            throw new IllegalArgumentException("索引越界");
        }
        return id[idx];
    }

    @Override
    public int size() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Quick Find中union操作需要O(n)时间复杂度
     * 将q集合编号全部转换成p集合编号
     */
    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == qId) {
                id[i] = pId;
            }
        }
    }
}
