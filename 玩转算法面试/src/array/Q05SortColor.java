package array;

/**
 * 75 sort color
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列.
 * 使用整数 0、 1 和 2 分别表示红色、白色和蓝色.
 *
 * @author sherman
 */
public class Q05SortColor {
    public void sortColors(int[] nums) {
        /**
         * 荷兰国旗问题
         * nums[0, lo-1] == 0
         * nums[lo, hi] == 1
         * nums[hi+1, nums.length-1] == 2
         */
        int i = 0;
        int lo = 0;
        int hi = nums.length - 1;
        while (i <= hi) {
            if (nums[i] == 1) {
                ++i;
            } else if (nums[i] == 0) {
                swap(nums, lo++, i++);
            } else {
                swap(nums, hi--, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
