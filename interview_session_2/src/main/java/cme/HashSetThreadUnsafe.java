package cme;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * HashSet多线程不安全问题
 * HashSet底层就是HashMap，因此这个案例也是HashMap多线程不安全问题的演示
 *
 * @author sherman
 */
public class HashSetThreadUnsafe {
    public static void main(String[] args) {
        Set<String> sets = new HashSet<>();
        for (int i = 0; i < 100; ++i) {
            new Thread(() -> {
                sets.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(sets);
            }).start();
        }
    }
}
