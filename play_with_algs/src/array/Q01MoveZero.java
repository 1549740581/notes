package array;

/**
 * 283 move zero
 * 给定一个数组nums，将所有0移动到数组的末尾，同时保持非零元素的相对顺序
 *
 * @author sherman
 */
public class Q01MoveZero {

    /**
     * 直接赋值，注意idx初始化为-1技巧
     */
    public void moveZeroes(int[] nums) {
        int idx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[++idx] = nums[i];
            }
        }
        for (int i = idx + 1; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }

    /**
     * 基于交换形式
     */
    public void mozeZeroes01(int[] nums) {
        int idx = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                if (i != idx) {
                    swap(nums, i, idx++);
                } else {
                    ++idx;
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
