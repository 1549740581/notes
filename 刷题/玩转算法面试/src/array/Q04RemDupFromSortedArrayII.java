package array;

/**
 * 80 remove duplicated from sorted array II
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度.
 *
 * @author sherman
 */
public class Q04RemDupFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int idx = 0;
        for (int item : nums) {
            if (idx < 2 || item > nums[idx - 2]) {
                nums[idx++] = item;
            }
        }
        return idx;
    }
}
