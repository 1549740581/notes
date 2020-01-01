package search;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 451 sort character by frequency
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * 注意：区分大小写，相同频率字符可以不考虑顺序
 *
 * @author sherman
 */
public class Q07SortCharByFreq {
    public String frequencySort(String s) {
        Map<Character, Integer> maps = new HashMap<>();
        for (char ch : s.toCharArray()) {
            maps.put(ch, maps.getOrDefault(ch, 0) + 1);
        }
        List<Character> keys = new ArrayList<>(maps.keySet());
        keys.sort((a, b) -> maps.get(b) - maps.get(a));
        StringBuilder sb = new StringBuilder();
        for (Character ch : keys) {
            for (int i = 0; i < maps.get(ch); ++i) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * Java8 Stream流
     */
    public String frequencySort1(String s) {
        Map<Character, Integer> maps = new HashMap<>();
        for (char ch : s.toCharArray()) {
            maps.put(ch, maps.getOrDefault(ch, 0) + 1);
        }
        LinkedHashMap<Character, Integer> sortedMap = maps.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (old, now) -> old, LinkedHashMap::new));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : sortedMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
}
