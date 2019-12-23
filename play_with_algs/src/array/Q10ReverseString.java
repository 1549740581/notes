package array;

/**
 * 344 reverse string
 * 原地反转字符串
 *
 * @author sherman
 */
public class Q10ReverseString {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        int lo = 0, hi = s.length - 1;
        while (lo < hi) {
            swap(s, lo++, hi--);
        }
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
