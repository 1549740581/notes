package search;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 350 intersection of two array II
 * 给定两个数组，编写一个函数来计算它们的交集
 * input: nums1=[1, 2, 2, 1], nums2=[2, 2]
 * output: [2, 2]
 *
 * @author sherman
 */
public class Q02IntersectionOfTwoArrayII {
    public int[] intersect(int[] nums1, int[] nums2) {
        /**
         * 保证nums1的长度小于nums2
         */
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int item : nums1) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        int idx = 0;
        for (int item : nums2) {
            int cnt = map.getOrDefault(item, 0);
            if (cnt > 0) {
                nums1[idx++] = item;
                map.put(item, cnt - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, idx);
    }
}
