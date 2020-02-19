package oom;

/**
 * 演示OOM：java heap size
 * VM -Xms5m -Xmx5m -XX:+PrintGCDetails
 *
 * @author sherman
 */
public class JavaHeapSpace {
    public static void main(String[] args) {
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        byte[] bytes = new byte[80 * 1024 * 1024];
    }
}
