/**
 * 数组中的逆序对
 * 最终的逆序数需要对1000000007取模输出
 *
 * @author sherman
 */
public class Q35逆序对 {
    /**
     * 归并排序的思路
     */
    public int inversePairs(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        long res = inversePairs(array, 0, array.length - 1);
        return (int) (res % 1000000007);
    }

    private long inversePairs(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return 0;
        }
        int mid = lo + ((hi - lo) >> 1);
        long left = inversePairs(array, lo, mid);
        long right = inversePairs(array, mid + 1, hi);
        return merge(array, lo, mid, hi) + left + right;
    }

    private long merge(int[] array, int lo, int mid, int hi) {
        long cnt = 0;
        int i = lo, j = mid + 1;
        int[] tmp = new int[hi - lo + 1];
        int k = 0;
        while (i <= mid && j <= hi) {
            if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            } else if (array[i] > array[j]) { // array[i...mid] > array[j]，都是逆序对
                tmp[k++] = array[j++];
                cnt += (mid - i + 1);
            }
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= hi) {
            tmp[k++] = array[j++];
        }
        System.arraycopy(tmp, 0, array, lo, hi - lo + 1);
        return cnt;
    }
}
