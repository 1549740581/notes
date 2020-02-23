package array;

/**
 * 27 remove element
 * 给定一个数组nums和一个值val，原地移除所有数值等于val的元素，返回移除后数组的新长度.
 *
 * @author sherman
 */
public class Q02RemoveElement {
    public int removeElement(int[] nums, int val) {
        int idx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[++idx] = nums[i];
            }
        }
        return idx + 1;
    }

    public int removeElement01(int[] nums, int val) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (i != idx) {
                    nums[idx++] = nums[i];
                } else {
                    ++idx;
                }
            }
        }
        return idx;
    }
}
