import java.util.ArrayList;

/**
 * 蛇形打印数组
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印。例如
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * @author sherman
 */
public class Q19蛇形打印数组 {
    private ArrayList<Integer> lists = new ArrayList<>();

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] isVisited = new boolean[rows][cols];
        int cnt = 1;
        int x = 0, y = 0;
        lists.add(matrix[x][y]);
        isVisited[x][y] = true;
        while (cnt < rows * cols) {
            // 从左往右
            while (y + 1 < cols && !isVisited[x][y + 1]) {
                lists.add(matrix[x][++y]);
                isVisited[x][y] = true;
                ++cnt;
            }
            // 从上往下
            while (x + 1 < rows && !isVisited[x + 1][y]) {
                lists.add(matrix[++x][y]);
                isVisited[x][y] = true;
                ++cnt;
            }
            // 从右往左
            while (y - 1 >= 0 && !isVisited[x][y - 1]) {
                lists.add(matrix[x][--y]);
                isVisited[x][y] = true;
                ++cnt;
            }
            // 从下往上
            while (x - 1 >= 0 && !isVisited[x - 1][y]) {
                lists.add(matrix[--x][y]);
                isVisited[x][y] = true;
                ++cnt;
            }
        }
        return lists;
    }
}
