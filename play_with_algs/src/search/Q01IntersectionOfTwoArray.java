package search;

import java.util.HashSet;
import java.util.Set;

/**
 * 349 intersection of two arrays
 * 给定两个数组，编写一个函数来计算它们的交集。
 * input: nums1=[1, 2, 2, 1], nums2=[2, 2]
 * output: [2]
 *
 * @author sherman
 */
public class Q01IntersectionOfTwoArray {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int item : nums1) {
            set1.add(item);
        }
        for (int item : nums2) {
            set2.add(item);
        }
        set1.retainAll(set2);
        int[] res = new int[set1.size()];
        int idx = 0;
        for (Integer item : set1) {
            res[idx++] = item;
        }
        return res;
    }
}
