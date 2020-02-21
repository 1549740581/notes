package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 401 binary watch
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 *
 * @author sherman
 */
public class Q12BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= 11; ++i) {
            for (int j = 0; j <= 59; ++j) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    res.add(i + ":" + (j < 10 ? "0" + j : j));
                }
            }
        }
        return res;
    }
}
