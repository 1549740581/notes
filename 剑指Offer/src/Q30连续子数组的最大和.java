/**
 * 连续子数组的最大和
 *
 * @author sherman
 */
public class Q30连续子数组的最大和 {
    public int findGreatestSumOfSubArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int curSum = 0;
        int sum = 0x80000000;
        for (int value : array) {
            if (curSum <= 0) {
                curSum = value;
            } else {
                curSum += value;
            }
            if (curSum > sum) {
                sum = curSum;
            }
        }
        return sum;
    }
}


