package search;

import java.util.TreeSet;

/**
 * 220 contains duplicate III
 * 给定一个整数数组，判断数组中是否有两个不同的索引i和j，使得nums[i]和nums[j]的差的绝对值最大为t，并且i和j之间的差的绝对值最大为ķ。
 *
 * @author sherman
 */
public class Q17ContainDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> sets = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // 查找表中是否存在大于等于nums[i] - t且小于等于nums[i] + t的元素
            Long ceiling = sets.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            sets.add((long) nums[i]);
            if (sets.size() == k + 1) {
                sets.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
