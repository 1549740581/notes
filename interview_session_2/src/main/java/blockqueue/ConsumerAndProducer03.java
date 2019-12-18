package blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者-消费者实现方式三：使用阻塞队列
 *
 * @author sherman
 */
public class ConsumerAndProducer03 {
    private volatile boolean isWork = true;
    private BlockingQueue<String> blockingQueue;
    private AtomicInteger ai = new AtomicInteger();

    public ConsumerAndProducer03(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void stop() {
        isWork = false;
    }

    public void produce() {
        String data;
        while (isWork) {
            data = ai.incrementAndGet() + "";
            try {
                boolean res = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                String name = Thread.currentThread().getName();
                if (res) {
                    System.out.println(name + "：添加元素：" + data + "成功");
                } else {
                    System.out.println(name + "：添加元素：" + data + "失败");
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "：停止工作");
    }

    public void consume() {
        String res;
        while (isWork) {
            try {
                res = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (res == null || "".equals(res)) {
                    isWork = false;
                    System.out.println(Thread.currentThread().getName() + "：超过两秒为获取数据，即将退出");
                    return;
                }
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + "：取出元素：" + res + "成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConsumerAndProducer03 cs3 = new ConsumerAndProducer03(new LinkedBlockingQueue<>(3));
        new Thread(cs3::produce, "+++生产者线程AAA").start();
        new Thread(cs3::produce, "+++生产者线程BBB").start();
        new Thread(cs3::consume, "---生产者线程CCC").start();
        new Thread(cs3::consume, "---生产者线程DDD").start();
        Thread.sleep(8 * 1000);
        System.out.println("终止生产者-消费者线程");
        cs3.stop();
    }
}
