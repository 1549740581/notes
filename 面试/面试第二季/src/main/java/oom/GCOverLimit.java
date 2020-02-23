package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示GC overhead limit exceeded异常
 *
 * -Xms12m -Xmx12m -XX:+PrintGCDetails
 *
 * 注意上面Xms和Xmx值不能太小，否则还没到达GC limit的限制就直接移除了，抛出java heap space异常
 *
 * @author sherman
 */
public class GCOverLimit {
    public static void main(String[] args) {
        int i = 0;
        List<String> lists = new ArrayList<>();
        try {
            while (true) {
                // Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
                lists.add(String.valueOf(i++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
