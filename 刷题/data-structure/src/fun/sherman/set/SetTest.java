package fun.sherman.set;

import fun.sherman.utils.FileUtil;

import java.time.Duration;
import java.time.Instant;

/**
 * Created on 2020/4/30
 *
 * @author sherman tang
 */
public class SetTest {
    public static void main(String[] args) {
        final String fileName = "apache license 2.0.txt";

        Instant now = Instant.now();
        // 测试BST实现的set
        System.out.println(FileUtil.countWordFromFile(fileName, new LinkedListSet<>()));
        System.out.println(String.format("time: %-5dms", Duration.between(now, Instant.now()).toMillis()));

        now = Instant.now();
        // 测试LinkedList实现的set
        System.out.println(FileUtil.countWordFromFile(fileName, new BSTImplSet<>()));
        System.out.println(String.format("time: %-5dms", Duration.between(now, Instant.now()).toMillis()));
    }
}
