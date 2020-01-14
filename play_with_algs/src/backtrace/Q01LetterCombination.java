package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 17 letter combinations of a phone number
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @author sherman
 */
public class Q01LetterCombination {
    private List<String> res = new ArrayList<>();
    private String[] letters = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null || "".equals(digits)) {
            return res;
        }
        backtrace(digits, 0, "");
        return res;
    }

    private void backtrace(String digits, int idx, String s) {
        if (idx == digits.length()) {
            res.add(s);
            return;
        }
        String letter = letters[digits.charAt(idx) - '0'];
        for (int i = 0; i < letter.length(); ++i) {
            backtrace(digits, idx + 1, s + letter.charAt(i));
        }
    }
}
