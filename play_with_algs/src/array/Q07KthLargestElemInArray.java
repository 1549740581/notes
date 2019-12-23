package array;

import java.util.Random;

/**
 * 215 kth largest element in an array
 * 在未排序的数组中找到第k个最大的元素.
 *
 * @author sherman
 */
public class Q07KthLargestElemInArray {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    /**
     * 1 6 7 2 8 5  pivot = 5
     * >>> partition
     * 1 2 5 6 8 7  idx = 2
     * 第4大元素为5，k = len - idx = 2
     */
    private int findKthLargest(int[] arr, int lo, int hi, int k) {
        int idx = partition(arr, lo, hi);
        while (arr.length - k != idx) {
            if (idx < arr.length - k) {
                idx = partition(arr, idx + 1, hi);
            } else {
                idx = partition(arr, lo, idx - 1);
            }
        }
        return arr[idx];

    }

    private int partition(int[] arr, int lo, int hi) {
        // shuffle is needed
        int rnd = new Random().nextInt((hi - lo + 1)) + lo;
        swap(arr, rnd, hi);
        int pivot = arr[hi];
        int idx = lo;
        for (int i = lo; i < hi; ++i) {
            if (arr[i] < pivot) {
                swap(arr, idx++, i);
            }
        }
        // arr[lo, idx-1] < pivot
        swap(arr, idx, hi);
        return idx;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
