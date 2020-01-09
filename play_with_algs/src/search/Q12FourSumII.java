package search;

import java.util.HashMap;
import java.util.Map;

/**
 * 454 4 sum II
 * 给定四个包含整数的数组列表A, B, C, D，计算有多少个元组(i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * A, B, C, D具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1。
 *
 * @author sherman
 */
public class Q12FourSumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int key = a + b;
                maps.put(key, maps.getOrDefault(key, 0) + 1);
            }
        }
        int res = 0;
        for (int c : C) {
            for (int d : D) {
                Integer value = maps.get(-c - d);
                if (value != null) {
                    res += value;
                }
            }
        }
        return res;
    }
}
