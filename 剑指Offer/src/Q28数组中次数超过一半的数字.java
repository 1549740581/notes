/**
 * 数组中出现次数超过一半的数字
 * 如果不存在，返回0
 *
 * @author sherman2571
 */
public class Q28数组中次数超过一半的数字 {
    public int moreThanHalfNum(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }

        int result = array[0];
        int times = 1;
        for (int i = 1; i < array.length; ++i) {
            if (times == 0) {
                result = array[i];
                times = 1;
            } else if (array[i] != result) {
                --times;
            } else {
                ++times;
            }
        }
        times = 0;
        for (int value : array) {
            if (value == result) {
                ++times;
            }
        }
        return (times > array.length / 2) ? result : 0;
    }

}
