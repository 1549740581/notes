package dp;

/**
 * 376 wiggle sub-sequence
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。
 * 少于两个元素的序列也是摆动序列。给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）
 * 元素来获得子序列，剩下的元素保持其原始顺序。
 *
 * @author sherman
 */
public class Q17WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int res = 1;
        int[] diff = new int[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            diff[i - 1] = nums[i] - nums[i - 1];
        }
        int idx = 0;
        /**
         * 找到第一个不重复的元素
         */
        while (idx < diff.length && diff[idx] == 0) {
            ++idx;
        }
        if (idx == diff.length) {
            return res;
        }
        boolean isPos = diff[idx] > 0;
        ++res;
        for (int i = idx + 1; i < diff.length; i++) {
            if (isPos && diff[i] > 0 || !isPos && diff[i] < 0 || diff[i] == 0) {
                continue;
            }
            ++res;
            isPos = !isPos;
        }
        return res;
    }

    /**
     * 两个dp数组，空间：O(n)，时间O(n)
     */
    public int wiggleMaxLength01(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int[] down = new int[nums.length];
        int[] up = new int[nums.length];
        down[0] = up[0] = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    /**
     * 压缩空间的dp，空间：O(1)，时间O(n)
     */
    public int wiggleMaxLength02(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
