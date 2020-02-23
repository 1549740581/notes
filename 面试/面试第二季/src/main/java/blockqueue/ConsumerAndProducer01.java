package blockqueue;

/**
 * 生产者-消费者实现方式一：使用synchronized-notifyAll-wait
 *
 * @author sherman
 */
public class ConsumerAndProducer01 {
    // produce和consume都有synchronized修饰，不需要使用volatile保证内存可见性
    private int num = 0;

    synchronized public void produce() {

        while (num != 0) {
            try {
                // 禁止生产
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 开始生产
        ++num;
        System.out.println(Thread.currentThread().getName() + ": " + num);
        // 返回通知其它所有阻塞的生产者线程
        this.notifyAll();
    }

    synchronized public void consume() {
        while (num == 0) {
            try {
                // 禁止消费
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 开始消费
        --num;
        System.out.println(Thread.currentThread().getName() + ": " + num);
        // // 返回通知其它所有阻塞的生产者线程
        this.notifyAll();
    }

    /**
     * 两个生产者、两个消费者
     */
    public static void main(String[] args) {
        ConsumerAndProducer01 cs1 = new ConsumerAndProducer01();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(100);
                    cs1.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "+++生产者线程AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(100);
                    cs1.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "+++生产者线程BBB").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(200);
                    cs1.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "---消费者线程CCC").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(200);
                    cs1.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "---消费者线程DDD").start();
    }
}
