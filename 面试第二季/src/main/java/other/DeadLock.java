package other;

/**
 * 演示死锁的产生
 *
 * @author sherman
 */
public class DeadLock implements Runnable {
    private String mutex1;
    private String mutex2;

    public DeadLock(String mutex1, String mutex2) {
        this.mutex1 = mutex1;
        this.mutex2 = mutex2;
    }


    @Override
    public void run() {
        synchronized (mutex1) {
            System.out.println(Thread.currentThread().getName() + ": 持有" + mutex1 + ", 尝试获取" + mutex2);
            try {
                // 睡眠一定时间，给别的线程获取资源，产生冲突
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (mutex2) {
                System.out.println(Thread.currentThread().getName() + "：持有" + mutex2 + ", 尝试获取" + mutex1);
            }
        }
    }

    public static void main(String[] args) {
        String mutex1 = "mutex1";
        String mutex2 = "mutex2";
        new Thread(new DeadLock(mutex1, mutex2), "AAA").start();
        new Thread(new DeadLock(mutex2, mutex1), "BBB").start();
    }
}
