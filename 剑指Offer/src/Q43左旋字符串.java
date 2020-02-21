/**
 * 左旋转字符串
 *
 * @author sherman
 */
public class Q43左旋字符串 {
    public String leftRotateString(String str, int n) {
        if ("".equals(str)) {
            return "";
        }
        n = n % str.length();
        return str.substring(n) + str.substring(0, n);
    }
}
