package oom;

/**
 * 演示OOM：unable to create new native thread
 * 注意：在Windows上运行这个程序容易出现假死现象！！！
 *
 * @author sherman
 */
public class UnableCreateNewNativeThread {
    public static void main(String[] args) {
        for (int i = 0; ; ++i) {
            System.out.println("+++++++" + i + "+++++++");
            // Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
