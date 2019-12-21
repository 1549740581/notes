/**
 * 调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分
 * 并保证奇数之间、偶数之间【稳定性】
 *
 * @author sherman
 */
public class Q13ReOrderArray {
    public void reOrderArray(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int i = 0;
        // 找到第一个偶数，下标记为i
        while (i < array.length & (array[i] & 1) == 1) {
            ++i;
        }
        if (i == array.length) {
            return;
        }
        int j = i + 1;
        while (j < array.length) {
            // 找到第一个为奇数的下标，记为j
            while (j < array.length && (array[j] & 1) == 0) {
                ++j;
            }
            if (j == array.length) {
                break;
            }
            int val = array[j];
            // 将array[i...j-1]拷贝到array[i+1, j]
            System.arraycopy(array, i, array, i + 1, j - i);
            array[i] = val;
            ++i;
            ++j;
        }
    }
}
