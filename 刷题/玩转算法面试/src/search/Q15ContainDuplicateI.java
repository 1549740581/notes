package search;

import java.util.HashMap;
import java.util.Map;

/**
 * 217 contains duplicate
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 *
 * @author sherman
 */
public class Q15ContainDuplicateI {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int num : nums) {
            if (maps.get(num) != null) {
                return true;
            } else {
                maps.put(num, 1);
            }
        }
        return false;
    }
}
