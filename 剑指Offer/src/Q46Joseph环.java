import java.util.LinkedList;
import java.util.List;

/**
 * 约瑟夫环
 * n个小朋友围成圈（编号0 -> n-1），从0 -> m-1报数，求最终留下来的编号
 *
 * @author sherman
 */
public class Q46Joseph环 {
    public int joseph(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        int idx = 0;
        for (int i = 2; i <= n; ++i) {
            idx = (idx + m) % i;
        }
        return idx;
    }

    /**
     * 使用双向链表解决
     */
    public int joseph01(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        List<Integer> lists = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            lists.add(i);
        }
        int idx = -1;
        while (lists.size() > 1) {
            idx = (idx + m) % lists.size();
            lists.remove(idx);
            /**
             * 移除一个元素后，链表总长度减一，idx索引位置就相对减1，防止溢出
             */
            --idx;
        }
        return lists.get(0);
    }
}
