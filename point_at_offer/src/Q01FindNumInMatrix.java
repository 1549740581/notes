/**
 * 在一个二维数组中（每个一维数组的长度相同）
 * 1、每一行都按照从左到右递增的顺序排序
 * 2、每一列都按照从上到下递增的顺序排序
 * 查找target
 *
 * @author sherman
 */
public class Q01FindNumInMatrix {
    public boolean findNumInMatrix(int target, int[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        int i = 0, j = cols - 1;
        /**
         * 从右上角
         */
        while (i < rows && j >= 0) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                --j;
            } else {
                ++i;
            }
        }
        return false;
    }
}
