package fun.sherman.unionfind;

/**
 * Created on 2020/5/2
 *
 * @author sherman tang
 */
public interface UF {
    /**
     * 并查集连通域数量
     */
    int size();

    /**
     * 索引p和索引q对应的节点是否连接
     */
    boolean isConnected(int p, int q);

    /**
     * 连接索引p和索引q两个节点
     */
    void union(int p, int q);
}
