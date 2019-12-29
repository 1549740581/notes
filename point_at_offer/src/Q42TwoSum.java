import java.util.ArrayList;

/**
 * 和为S的两个数
 * 一个递增排序的数组和一个数字S，在数组中查找两个数，使得它们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 *
 * @author sherman
 */
public class Q42TwoSum {
    public ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int lo = 0, hi = array.length - 1;
        while (lo < hi) {
            int curSum = array[lo] + array[hi];
            /**
             * 两数和相同的情况下，lo和hi越靠近中间，乘积越大
             */
            if (curSum == sum) {
                arrayList.add(array[lo]);
                arrayList.add(array[hi]);
                break;
            } else if (curSum > sum) {
                --hi;
            } else {
                ++lo;
            }
        }
        return arrayList;
    }
}
