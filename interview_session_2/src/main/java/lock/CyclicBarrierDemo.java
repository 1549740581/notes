package lock;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier模拟集齐七颗龙珠才能召唤神龙
 * 设置common barrier point为7，每个线程收集到七颗龙珠之前都会被阻塞
 * 每个线程都到达common barrier point时候才会召唤神龙
 *
 * @author sherman
 */
public class CyclicBarrierDemo implements Runnable {
    private static CyclicBarrier cb = new CyclicBarrier(7, () -> System.out.println("召唤神龙"));

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": 到达同步点（收集到一个龙珠）!");
            cb.await();
            System.out.println(Thread.currentThread().getName() + ": 阻塞结束，继续执行!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierDemo cbd = new CyclicBarrierDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        for (int i = 0; i < 7; i++) {
            try {
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(cbd);
        }
        executorService.shutdown();
    }
}
