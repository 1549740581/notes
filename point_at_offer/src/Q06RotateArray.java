/**
 * 旋转数组的最小值
 *
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @author sherman
 */
public class Q06RotateArray {
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 1) {
            return array[0];
        }
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (array[lo] < array[hi]) {
                return array[lo];
            } else if (array[mid] > array[mid + 1]) {
                return array[mid + 1];
            } else if (array[mid] < array[mid - 1]) {
                return array[mid];
            } else if (array[mid] > array[0]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return 0;
    }
}
