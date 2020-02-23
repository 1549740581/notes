package queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 279 perfect squares
 * 给定正整数 n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于n，求完全平方数最少多少个？
 *
 * @author sherman
 */
public class Q05PerfectSquares {
    public int numSquares(int n) {
        Deque<String> queue = new ArrayDeque<>();
        /**
         * 简化成【num:次数】形式，否则需要将num和次数绑定成一个pair对
         */
        queue.offer(n + ":" + 0);
        boolean[] visited = new boolean[n + 1];
        visited[n] = true;
        while (!queue.isEmpty()) {
            String elem = queue.remove();
            int idx = elem.indexOf(":");
            int num = Integer.parseInt(elem.substring(0, idx));
            int step = Integer.parseInt(elem.substring(idx + 1));
            for (int i = 1; ; ++i) {
                int tmp = num - i * i;
                if (tmp < 0) {
                    break;
                }
                if (tmp == 0) {
                    return step + 1;
                }
                if (!visited[tmp]) {
                    queue.add(tmp + ":" + (step + 1));
                    visited[tmp] = true;
                }
            }
        }
        throw new RuntimeException();
    }

    /**
     * 使用类似键值对形式
     */
    public int numSquares01(int n) {
        int[] maps = new int[n + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        deque.add(n);
        visited[n] = true;
        while (!deque.isEmpty()) {
            Integer item = deque.remove();
            for (int i = 1; ; ++i) {
                int tmp = item - i * i;
                if (tmp < 0) {
                    break;
                }
                if (tmp == 0) {
                    return maps[item] + 1;
                }
                if (!visited[tmp]) {
                    deque.add(tmp);
                    maps[tmp] = maps[item] + 1;
                    visited[tmp] = true;
                }
            }
        }
        throw new RuntimeException();
    }
}
