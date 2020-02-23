/**
 * 数字在排序数组中出现的次数
 *
 * @author sherman
 */
public class Q37数字在排序数组中出现次数 {
    public int getNumberOfK(int[] array, int k) {
        int hi = binarySearchRangeHi(array, k);
        int lo = binarySearchRangeLo(array, k);
        if (lo < 0 || hi < 0) {
            return 0;
        }
        return (hi - lo + 1);
    }

    /**
     * 二分查找下界
     */
    private int binarySearchRangeLo(int[] array, int k) {
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (array[mid] > k) {
                hi = mid - 1;
            } else if (array[mid] < k) {
                lo = mid + 1;
            } else {
                if (mid == 0 || array[mid - 1] != k) {
                    return mid;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 二分查找上界
     */
    private int binarySearchRangeHi(int[] array, int k) {
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (array[mid] > k) {
                hi = mid - 1;
            } else if (array[mid] < k) {
                lo = mid + 1;
            } else {
                if (mid == array.length - 1 || array[mid + 1] != k) {
                    return mid;
                } else {
                    lo = mid + 1;
                }
            }
        }
        return -1;
    }

}
