package queue;

import java.util.*;

/**
 * 126 word ladder II
 * 给定两个单词（beginWord 和 endWord）和一个字典，找出所有从beginWord到endWord的最短转换序列。转换需遵循如下规则:
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
public class Q07WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        /**
         * endWord必定在wordList中
         */
        if (!wordList.contains(endWord)) {
            return ans;
        }
        bfs(beginWord, endWord, wordList, ans);
        return ans;
    }

    private void bfs(String beginWord, String endWord, List<String> wordList, List<List<String>> ans) {
        Queue<List<String>> queue = new LinkedList<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        queue.offer(path);
        boolean isFound = false;
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();
            for (int j = 0; j < size; j++) {
                List<String> p = queue.poll();
                String temp = p.get(p.size() - 1);
                /**
                 * 得到当前节点下一层所有节点
                 */
                ArrayList<String> neighbors = getNeighbors(temp, dict);
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        /**
                         * 注意是求最短转换序列，因此如果在第k层找到满足条件的序列
                         * 主要将该层遍历完毕即可结束，不需要再向下一层遍历
                         */
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                            p.add(neighbor);
                            ans.add(new ArrayList<>(p));
                            p.remove(p.size() - 1);
                        }
                        p.add(neighbor);
                        queue.offer(new ArrayList<>(p));
                        p.remove(p.size() - 1);
                        subVisited.add(neighbor);
                    }
                }
            }
            visited.addAll(subVisited);
            if (isFound) {
                break;
            }
        }
    }

    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char chs[] = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) {
                    continue;
                }
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }
        }
        return res;
    }
}
