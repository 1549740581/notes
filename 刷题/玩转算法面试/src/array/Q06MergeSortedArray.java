package array;

/**
 * 88 merge sorted array
 * 给定两个有序整数数组nums1和nums2，将nums2合并到nums1中，使得num1成为一个有序数组.
 * 注意：
 * - 初始化nums1和nums2的元素数量分别为m和n
 * - 假设nums1有足够的空间（空间大小大于或等于 m + n）来保存nums2中的元素
 *
 * @author sherman
 */
public class Q06MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[idx--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        // 如果nums2还有剩余
        while (n >= 0) {
            nums1[idx--] = nums2[n--];
        }
    }
}
