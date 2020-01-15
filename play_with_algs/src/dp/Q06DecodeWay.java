package dp;

/**
 * 91 decode ways
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * @author sherman
 */
public class Q06DecodeWay {
    public int numDecodings(String s) {
        if (s == null || "".equals(s) || s.charAt(0) == '0') {
            return 0;
        }
        int pre = 1;
        int cur = 1;
        for (int i = 1; i < s.length(); i++) {
            int tmp = cur;
            char first = s.charAt(i - 1);
            char second = s.charAt(i);
            if (second == '0') {
                if (first == '1' || first == '2') {
                    cur = pre;
                } else {
                    return 0;
                }
            } else if (first == '1' || first == '2' && second >= '0' && second <= '6') {
                cur += pre;
            }
            pre = tmp;
        }
        return cur;
    }
}
