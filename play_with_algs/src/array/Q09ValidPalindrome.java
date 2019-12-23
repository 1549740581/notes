package array;

/**
 * 125 valid palindrome
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写.
 * 注意：空字符串定义为有效的回文字符串
 *
 * @author sherman
 */
public class Q09ValidPalindrome {
    public boolean isPalindrome(String s) {
        if ("".equals(s)) {
            return true;
        }
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(lo))) {
                ++lo;
            }
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(hi))) {
                --hi;
            }
            if (lo < hi) {
                if (Character.toLowerCase(s.charAt(lo)) != Character.toLowerCase(s.charAt(hi))) {
                    return false;
                }
                ++lo;
                --hi;
            }
        }
        return true;
    }
}
