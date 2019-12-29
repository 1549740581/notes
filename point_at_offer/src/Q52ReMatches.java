/**
 * 正则表达式的匹配
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）
 * 注意：匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class Q52ReMatches {
    /**
     * 投机取巧:)
     */
    public boolean match(char[] str, char[] pattern) {
        return new String(str).matches(new String(pattern));
    }

    public boolean match01(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return match(str, 0, pattern, 0);
    }

    private boolean match(char[] str, int i, char[] pattern, int j) {
        if (i == str.length && j == pattern.length) {
            return true;
        }
        if (j == pattern.length) {
            return false;
        }
        /**
         * pattern下一个字符是*
         */
        if (j + 1 < pattern.length && pattern[j + 1] == '*') {
            // 当前字符匹配
            if (i != str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
                return match(str, i + 1, pattern, j + 2) || // *只匹配一个
                        match(str, i + 1, pattern, j) ||        // *匹配多个
                        match(str, i, pattern, j + 2);          // *一个也不匹配
            } else { // 当前字符不匹配，只能忽略当前*，判断pattern下一个字符是否匹配
                return match(str, i, pattern, j + 2);
            }
        }
        /**
         * pattern下一个字符不是*，当前当前两个字符必须相等
         */
        if (i != str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
            return match(str, i + 1, pattern, j + 1);
        }
        return false;
    }
}
