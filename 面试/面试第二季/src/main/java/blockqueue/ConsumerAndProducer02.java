package blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者-消费者实现方式二：使用lock(condition)-signalAll-await实现
 *
 * @author sherman
 */
public class ConsumerAndProducer02 {
    private int num = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() {
        lock.lock();
        try {
            while (num != 0) {
                try {
                    // 不能生产
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 开始生产
            ++num;
            System.out.println(Thread.currentThread().getName() + ": " + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (0 == num) {
                try {
                    // 不能消费
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 开始消费
            --num;
            System.out.println(Thread.currentThread().getName() + ": " + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 两个生产者、两个消费者
     */
    public static void main(String[] args) {
        ConsumerAndProducer02 cs2 = new ConsumerAndProducer02();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cs2.produce();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "+++生产者线程AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cs2.produce();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "+++生产者线程BBB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cs2.consume();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "---消费者线程CCC").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                cs2.consume();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "---消费者线程DDD").start();
    }
}
