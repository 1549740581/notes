package lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁演示
 *
 * @author sherman
 */
public class LockSpin {
    AtomicReference<Thread> ar = new AtomicReference<>();

    private void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ": come in!");
        while (!ar.compareAndSet(null, thread)) {

        }
    }

    private void unlock() {
        Thread thread = Thread.currentThread();
        ar.compareAndSet(thread, null);
        System.out.println(thread.getName() + ": get out!");
    }

    public static void main(String[] args) throws InterruptedException {
        LockSpin lockSpin = new LockSpin();
        new Thread(() -> {
            lockSpin.lock();
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockSpin.unlock();
        }, "线程A").start();

        // 保证线程A先进行自旋
        Thread.sleep(1000);

        new Thread(() -> {
            lockSpin.lock();
            lockSpin.unlock();
        }, "线程B").start();
    }
}
