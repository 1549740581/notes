package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 51 N-queens
 * n皇后问题研究的是如何将n个皇后放置在n×n的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * @author sherman
 */
public class Q17NQueue {
    private List<List<String>> res = new ArrayList<>();
    /**
     * cols[i] == true：第i列存在皇后
     */
    private boolean[] cols;
    /**
     * cols[i + j]上存在皇后
     */
    private boolean[] dia1;
    /**
     * cols[i - j]上存在皇后，但是i-j可能为负数，需要做一次平移，加上n-1即可
     */
    private boolean[] dia2;

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return res;
        }
        cols = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        findNQueue(n, 0, new ArrayList<>());
        return res;
    }

    /**
     * @param n      n*n个棋盘
     * @param row    第row行
     * @param queues 存在皇后的列
     */
    private void findNQueue(int n, int row, ArrayList<Integer> queues) {
        if (row == n) {
            generateBoard(n, queues);
            return;
        }
        for (int col = 0; col < n; ++col) {
            if (!cols[col] && !dia1[row + col] && !dia2[row - col + n - 1]) {
                queues.add(col);
                cols[col] = true;
                dia1[row + col] = true;
                dia2[row - col + n - 1] = true;
                findNQueue(n, row + 1, queues);
                dia2[row - col + n - 1] = false;
                dia1[row + col] = false;
                cols[col] = false;
                queues.remove(queues.size() - 1);
            }
        }
    }

    private void generateBoard(int n, ArrayList<Integer> queues) {
        List<String> tmp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append('.');
        }
        for (int i = 0; i < n; i++) {
            sb.setCharAt(queues.get(i), 'Q');
            tmp.add(sb.toString());
            sb.setCharAt(queues.get(i), '.');
        }
        res.add(tmp);
    }
}
