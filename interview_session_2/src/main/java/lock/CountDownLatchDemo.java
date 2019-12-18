package lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch模拟火箭发射过程：
 * 火箭发射之前需要十个线程进行前期检查工作，每个线程耗时0-4s，
 * 只有10个线程对应的检查工作全部完成后，火箭才能发射
 *
 * @author sherman
 */

public class CountDownLatchDemo implements Runnable {
    public static final int TASK_NUMBERS = 10;
    private static CountDownLatch cdl = new CountDownLatch(TASK_NUMBERS);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(countDownLatchDemo);
        }
        cdl.await();
        System.out.println("检查工作检查完毕：fire!");
        executorService.shutdown();
    }

    @Override
    public void run() {
        try {
            // 模拟火箭发射前的各种检查工作
            int millis = new Random().nextInt(5000);
            Thread.sleep(millis);
            System.out.println(Thread.currentThread().getName() + "：检查完毕! 耗时：" + millis + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 每次检查完毕后都将计数器减1
            cdl.countDown();
        }
    }
}
