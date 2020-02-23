/**
 * 把字符串转换成整数
 * 将一个字符串转换成一个整数，要求不能使用字符转换成整数的库函数。数值0或者字符串不是一个合法的数值都会返回0
 *
 * @author sherman
 */
public class Q49字符串转成整数 {
    public int strToInt(String str) {
        if (str == null || "".equals(str.trim()) || !isValid(str)) {
            return 0;
        }
        String trim = str.trim();
        char first = str.charAt(0);
        int idx = 0;
        int sign = 1;
        if (!Character.isDigit(first)) {
            ++idx;
            if (first == '-') {
                sign = -1;
            }
        }
        char[] charArray = trim.substring(idx).toCharArray();
        long res = 0;
        for (char c : charArray) {
            res = 10 * res + (c - '0');
        }
        res *= sign;
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) res;
    }

    private boolean isValid(String str) {
        return str.trim().matches("[+-]?\\d+");
    }
}
