package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁演示
 *
 * @author sherman
 */

// 演示ReentrantLock是可重入的
class ShareResouce implements Runnable {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": get()");
            set();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": set()");
        } finally {
            lock.unlock();
        }
    }
}

public class LockReentrantDemo2 {
    // outer()和inner()方法演示synchronized是可重入的
    private synchronized void outer() {
        System.out.println(Thread.currentThread().getName() + ": outer method()");
        inner();
    }

    // outer()和inner()方法演示synchronized是可重入的
    private synchronized void inner() {
        System.out.println(Thread.currentThread().getName() + ": inner method()");
    }

    public static void main(String[] args) {
        // 验证synchronized是可重入的
        LockReentrantDemo2 lrd = new LockReentrantDemo2();
        new Thread(lrd::outer, "thread-1").start();
        new Thread(lrd::outer, "thread-2").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 验证ReentrantLock是可重入的
        System.out.println("===================");
        new Thread(new ShareResouce(), "thread-3").start();
        new Thread(new ShareResouce(), "thread-4").start();
    }
}
