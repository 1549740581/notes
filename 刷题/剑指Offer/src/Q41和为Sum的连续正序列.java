import java.util.ArrayList;

/**
 * 和为SUM的连续正数序列
 * 输出所有和为SUM的连续正数序列，序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序。
 *
 * @author sherman
 */
public class Q41和为Sum的连续正序列 {
    private ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();

    /**
     * 滑动窗口思路
     */
    public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        int lo = 1;
        int hi = 2;
        int curSum = 3;
        int mid = (sum + 1) / 2;
        // lo不能>=mid，否则lo + hi > sum
        while (lo < mid) {
            if (curSum == sum) {
                getRange(lo, hi);
            }
            while (curSum > sum && lo < mid) {
                curSum -= lo;
                lo++;
                if (curSum == sum) {
                    getRange(lo, hi);
                }
            }
            ++hi;
            curSum += hi;
        }
        return arrayList;
    }

    private void getRange(int lo, int hi) {
        ArrayList<Integer> lists = new ArrayList<>();
        for (int i = lo; i <= hi; ++i) {
            lists.add(i);
        }
        arrayList.add(lists);
    }
}
