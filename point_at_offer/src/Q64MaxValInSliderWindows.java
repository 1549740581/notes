import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 滑动窗口的最大值
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如：
 * 输入数组{2, 3, 4, 2, 6, 2, 5, 1}，滑动窗口size=3，则输出：{4, 4, 6, 6, 6, 5}
 *
 * @author sherman
 */
public class Q64MaxValInSliderWindows {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || size <= 0) {
            return res;
        }
        /**
         * deque中保存的是索引
         */
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < num.length; ++i) {
            /**
             * 移除超过windows大小size的索引值
             */
            while (!deque.isEmpty() && deque.peekFirst() + size <= i) {
                deque.pollFirst();
            }
            /**
             * 当前索引对应的元素大于deque最后索引对应的元素，移除deque最后索引
             */
            while (!deque.isEmpty() && num[i] > num[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if (i >= size - 1) {
                res.add(num[deque.getFirst()]);
            }
        }
        return res;
    }
}
