package fun.sherman.map;

import fun.sherman.utils.FileUtil;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * Created on 2020/4/30
 *
 * @author sherman tang
 */
public class MapTest {
    public static void main(String[] args) {
        final String fileName = "apache license 2.0.txt";
        List<String> words = FileUtil.getAllWords(fileName);

        Instant now = Instant.now();
        // 测试链表实现的Map
        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        words.forEach(e -> {
            if (linkedListMap.contains(e)) {
                linkedListMap.put(e, linkedListMap.get(e) + 1);
            } else {
                linkedListMap.add(e, 1);
            }
        });
        System.out.println(String.format("time: %-5dms", Duration.between(now, Instant.now()).toMillis()));
        System.out.println(linkedListMap.size());

        now = Instant.now();
        // 测试二分搜索树实现的Map
        BSTImplMap<String, Integer> bstMap = new BSTImplMap<>();
        words.forEach(e -> {
            if (bstMap.contains(e)) {
                bstMap.put(e, bstMap.get(e) + 1);
            } else {
                bstMap.add(e, 1);
            }
        });
        System.out.println(String.format("time: %-5dms", Duration.between(now, Instant.now()).toMillis()));
        System.out.println(bstMap.size());
    }
}
