package queue;

import java.util.*;

/**
 * 127 word ladder I
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从beginWord到endWord的最短转换序列的长度。转换需遵循如下规则:
 * - 每次转换只能改变一个字母
 * - 转换过程中的中间单词必须是字典中的单词
 * 说明:
 * - 如果不存在这样的转换序列，返回0
 * - 所有单词具有相同的长度
 * - 所有单词只由小写字母组成
 * - 字典中不存在重复的单词
 * 你可以假设beginWord和endWord是非空的，且二者不相同
 *
 * @author sherman
 */
public class Q06WordLadderI {
    private Map<String, Boolean> visited;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        visited = new HashMap<>(wordList.size());
        for (String str : wordList) {
            visited.put(str, false);
        }
        if (!dict.contains(endWord)) {
            return 0;
        }
        int depth = 0;
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        visited.put(beginWord, true);
        while (!queue.isEmpty()) {
            ++depth;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String item = queue.remove();
//                for (String str : dict) {
//                    if (!visited.get(str) && diffNoMoreThanOne(item, str)) {
//                        if (str.equals(endWord)) {
//                            return depth + 1;
//                        } else {
//                            queue.offer(str);
//                            visited.put(str, true);
//                            dict.remove(str);
//                        }
//                    }
//                }

                for (int idx = 0; idx < item.length(); ++idx) {
                    char[] chars = item.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ++ch) {
                        chars[idx] = ch;
                        String tmp = String.valueOf(chars);
                        if (tmp.equals(endWord)) {
                            return depth + 1;
                        }
                        if (!tmp.equals(item) && dict.contains(tmp) && !visited.get(tmp)) {
                            queue.offer(tmp);
                            visited.put(tmp, true);
                        }
                    }
                }
            }

        }
        return 0;
    }

//    private boolean diffNoMoreThanOne(String str1, String str2) {
//        int cnt = 0;
//        for (int i = 0; i < str1.length(); i++) {
//            if (str1.charAt(i) != str2.charAt(i)) {
//                ++cnt;
//                if (cnt >= 2) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

}
