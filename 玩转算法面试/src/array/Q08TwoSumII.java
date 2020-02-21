package array;

/**
 * 167 two sum II
 * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数.
 * 注意：
 * - 两个下标index1，index2从1开始，且index1 < index2
 * - 答案唯一，且不能使用相同元素
 *
 * @author sherman
 */
public class Q08TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int lo = 0, hi = numbers.length - 1;
        while (lo < hi) {
            int tmp = numbers[lo] + numbers[hi];
            if (tmp == target) {
                res[0] = lo;
                res[1] = hi;
                break;
            } else if (tmp > target) {
                hi--;
            } else {
                ++lo;
            }
        }
        return res;
    }
}
