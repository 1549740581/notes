/**
 * 翻转单词顺序列
 * "I am a student." -> "student. a am I"
 *
 * @author sherman
 */
public class Q44翻转单词 {
    public String reverseSentence(String str) {
        if ("".equals(str)) {
            return "";
        }
        String trimStr = str.trim();
        if ("".equals(trimStr)) {
            return str;
        }
        String[] split = trimStr.split("\\s+");
        for (int i = 0; i < split.length / 2; ++i) {
            String tmp = split[i];
            split[i] = split[split.length - 1 - i];
            split[split.length - 1 - i] = tmp;
        }
        return String.join(" ", split);
    }
}
