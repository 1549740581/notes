/**
 * 机器人的运动范围
 * 地上有一个m行和n列的方格，一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。求该机器人能够到达多少个格子？
 *
 * @author sherman
 */
public class Q66机器人的运动范围 {
    private int rows;
    private int cols;
    private boolean[] isVisited;

    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        this.rows = rows;
        this.cols = cols;
        this.isVisited = new boolean[rows * cols];
        return movingCountInternal(threshold, 0, 0);
    }

    private int movingCountInternal(int threshold, int i, int j) {
        int cnt = 0;
        if (isValidPos(i, j, threshold)) {
            isVisited[i * cols + j] = true;
            cnt = 1 + movingCountInternal(threshold, i + 1, j) +
                    movingCountInternal(threshold, i - 1, j) +
                    movingCountInternal(threshold, i, j - 1) +
                    movingCountInternal(threshold, i, j + 1);
        }
        return cnt;
    }

    private boolean isValidPos(int i, int j, int threshold) {
        return i >= 0 && i < rows && j >= 0 && j < cols &&
                ((getSum(i) + getSum(j)) <= threshold) &&
                !isVisited[i * cols + j];
    }

    private int getSum(int i) {
        int sum = 0;
        while (i != 0) {
            sum += (i % 10);
            i /= 10;
        }
        return sum;
    }
}
