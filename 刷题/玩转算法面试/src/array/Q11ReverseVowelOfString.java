package array;

/**
 * 345 reverse vowels of a string
 * 反转字符串中的元音字母.
 *
 * @author sherman
 */
public class Q11ReverseVowelOfString {
    public String reverseVowels(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            while (lo < hi && !isVowel(chars[lo])) {
                ++lo;
            }
            while (lo < hi && !isVowel(chars[hi])) {
                --hi;
            }
            if (lo < hi) {
                swap(chars, lo++, hi--);
            }
        }
        return new String(chars);
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' ||
                ch == 'o' || ch == 'O' || ch == 'i' || ch == 'I' ||
                ch == 'u' || ch == 'U';
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
