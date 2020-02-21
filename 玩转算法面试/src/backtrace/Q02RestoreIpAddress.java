package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 93 restore ip address
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * @author sherman
 */
public class Q02RestoreIpAddress {
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12 || s.length() < 4) {
            return res;
        }
        backtrace(s, 0, new ArrayList<String>());
        return res;
    }

    private void backtrace(String s, int idx, ArrayList<String> tmp) {
        /**
         * 剪枝操作
         */
        if (s.length() - idx > (4 - tmp.size()) * 3) {
            return;
        }
        if (idx == s.length() && tmp.size() == 4) {
            res.add(String.join(".", tmp));
            return;
        }
        for (int i = 1; i <= 3; ++i) {
            if (idx + i > s.length()) {
                return;
            }
            String seg = s.substring(idx, idx + i);
            if ((seg.startsWith("0") && seg.length() > 1) || ((i == 3) && Integer.parseInt(seg) > 255)) {
                return;
            }
            tmp.add(seg);
            backtrace(s, idx + i, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
