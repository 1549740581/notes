import java.util.*;

/**
 * 最小的k个数
 *
 * @author sherman
 */
public class Q29最小的K个数 {
    /**
     * 使用大顶堆
     */
    public ArrayList<Integer> getLeastNumbers01(int[] input, int k) {
        if (input == null || k > input.length || k == 0)
            return new ArrayList<Integer>();
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, Collections.reverseOrder());
        for (int elem : input) {
            if (pq.size() < k)
                pq.add(elem);
            else {
                if (pq.element() > elem) {
                    pq.remove();
                    pq.add(elem);
                }
            }
        }
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        return res;
    }

    /**
     * 使用partition操作
     */
    public ArrayList<Integer> getLeastNumbers(int[] input, int k) {
        if (input == null || k > input.length || k == 0)
            return new ArrayList<>();
        return getLeastNumbersInternal(input, 0, input.length - 1, k);
    }

    private ArrayList<Integer> getLeastNumbersInternal(int[] arr, int lo, int hi, int target) {
        ArrayList<Integer> res = new ArrayList<>();
        int idx = partition(arr, lo, hi);
        /**
         * idx的范围是[0, arr.length)
         * target的范围是[1, arr.length]
         */
        while (idx != target - 1) {
            if (idx < target - 1) {
                idx = partition(arr, idx + 1, hi);
            } else {
                idx = partition(arr, lo, idx - 1);
            }
        }
        for (int i = 0; i <= idx; ++i) {
            res.add(arr[i]);
        }
        Collections.sort(res);
        return res;
    }

    private int partition(int[] input, int lo, int hi) {
        int rnd = new Random().nextInt((hi - lo + 1)) + lo;
        swap(input, rnd, hi);
        int pivot = input[hi];
        int idx = lo;
        for (int i = lo; i < hi; ++i) {
            if (input[i] < pivot) {
                swap(input, i, idx++);
            }
        }
        // input[0...idx) < pivot
        swap(input, idx, hi);
        return idx;
    }

    private void swap(int[] input, int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }
}
