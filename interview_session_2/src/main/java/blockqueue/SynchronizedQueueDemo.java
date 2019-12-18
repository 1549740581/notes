package blockqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 使用SynchronizedQueue完成生产者-消费者模型，并且是严格生产一个——消费一个
 *
 * @author sherman
 */
public class SynchronizedQueueDemo {
    private static BlockingQueue<String> bq = new SynchronousQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                final int tmp = i;
                try {
                    System.out.println("生产者线程: " + tmp);
                    bq.put(String.valueOf(tmp));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                final int tmp = i;
                try {
                    Thread.sleep(new Random().nextInt(2000));
                    System.out.println("消费者线程：" + tmp);
                    bq.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
