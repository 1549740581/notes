package lock;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore模拟抢车位过程（3个车位，10辆车）
 * 任意时刻只有3辆车持有线程
 *
 * @author sherman
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 模拟三个车位，十辆车
        // 任意时刻只有三辆车持有车位
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": 抢到车位");
                    // 每辆车占有车位[3,8]秒时间
                    Thread.sleep((new Random().nextInt(6) + 3) * 1000);
                    System.out.println(Thread.currentThread().getName() + ": 释放车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
