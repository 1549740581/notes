package array;

/**
 * 26 remove duplicated from sorted array
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度.
 *
 * @author sherman
 */
public class Q03RemDupFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int idx = 0;
        for (int item : nums) {
            if (item != nums[idx]) {
                nums[++idx] = item;
            }
        }
        return idx + 1;
    }
}
