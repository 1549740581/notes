package queue;

import java.util.*;

/**
 * 347 top k frequent elements
 * 给定一个非空的整数数组，返回其中出现频率前k高的元素。
 *
 * @author sherman
 */
public class Q08TopKFreqElem {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(nums.length);
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        /**
         * 小顶堆
         */
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(freq::get));
        for (int key : freq.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (freq.get(pq.element()) < freq.get(key)) {
                pq.remove();
                pq.add(key);
            }
        }
        return new ArrayList<>(pq);
    }

    /**
     * 维护nums.length大小的大顶堆，然后取前k大个元素
     */
    public List<Integer> topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(nums.length);
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        /**
         * 使用大顶堆
         */
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> freq.get(o2) - freq.get(o1));
        pq.addAll(freq.keySet());
        List<Integer> res = new ArrayList<>(k);
        for (int i = 0; i < k; ++i) {
            res.add(pq.remove());
        }
        return res;
    }
}
