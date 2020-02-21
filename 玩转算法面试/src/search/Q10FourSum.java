package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18 4sum
 * 给定一个包含n个整数的数组nums和一个目标值target，判断nums中是否存在四个元素a，b，c 和 d，使得a + b + c + d的值与target相等。
 * 找出所有满足条件且不重复的四元组。
 *
 * @author sherman
 */
public class Q10FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            } else {
                List<List<Integer>> tmp = threeSumInternal(Arrays.copyOfRange(nums, i + 1, nums.length), target - nums[i]);
                for (List<Integer> list : tmp) {
                    list.add(nums[i]);
                    res.add(list);
                }
            }
        }
        return res;
    }

    private List<List<Integer>> threeSumInternal(int[] arr, int target) {
        /**
         * arr已经排序过
         */
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; ++i) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            } else {
                int l = i + 1, r = arr.length - 1;
                while (l < r) {
                    int sum = arr[i] + arr[l] + arr[r];
                    if (sum == target) {
                        /**
                         * 注意这里不能使用Arrays.asList(...)
                         * 因为后续还要使用add方法，会抛出UnsupportedOperationException
                         */
                        ArrayList<Integer> tmp = new ArrayList<>(3);
                        tmp.add(arr[i]);
                        tmp.add(arr[l]);
                        tmp.add(arr[r]);
                        res.add(tmp);

                        while (l < r && arr[l] == arr[l + 1]) {
                            ++l;
                        }
                        while (l < r && arr[r] == arr[r - 1]) {
                            --r;
                        }
                        ++l;
                        --r;
                    } else if (sum < target) {
                        while (l < r && arr[l] == arr[l + 1]) {
                            ++l;
                        }
                        ++l;
                    } else {
                        while (l < r && arr[r] == arr[r - 1]) {
                            --r;
                        }
                        --r;
                    }
                }
            }
        }
        return res;
    }
}
