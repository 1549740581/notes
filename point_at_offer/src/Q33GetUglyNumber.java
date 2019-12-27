/**
 * 丑数
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number），例如6、8都是丑数。
 * 求按从小到大的顺序的第N个丑数，1是第一个丑数。
 *
 * @author sherman
 */
public class Q33GetUglyNumber {
    public int getUglyNumber(int index) {
        if (index <= 0) {
            return 0;
        }
        int p2 = 0, p3 = 0, p5 = 0;
        int[] arr = new int[index];
        arr[0] = 1;
        for (int i = 1; i < index; ++i) {
            int min = Math.min(Math.min(arr[p2] * 2, arr[p3] * 3), arr[p5] * 5);
            arr[i] = min;
            if (arr[p2] * 2 == min) {
                ++p2;
            }
            if (arr[p3] * 3 == min) {
                ++p3;
            }
            if (arr[p5] * 5 == min) {
                ++p5;
            }
        }
        return arr[index - 1];
    }
}
