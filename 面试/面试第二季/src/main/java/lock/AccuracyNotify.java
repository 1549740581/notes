package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock(condition)实现线程的精准唤醒
 *
 * 多个线程之间顺序调用：有三个线程A、B、C，要求：
 * - A线程打印AAA1次，紧接着B线程打印BBB3次，最后C线程打印CCC5次；
 * - 保证以上顺序，总共打印10轮；
 *
 * @author sherman
 */
public class AccuracyNotify {
    private int curThread = 1;
    private Lock lock = new ReentrantLock();
    private Condition cond1 = lock.newCondition();
    private Condition cond2 = lock.newCondition();
    private Condition cond3 = lock.newCondition();

    public void printOnce() {
        lock.lock();
        try {
            while (1 != curThread) {
                cond1.await();
            }
            System.out.println(Thread.currentThread().getName() + ": AAA");
            curThread = 2;
            cond2.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printTriple() {
        lock.lock();
        try {
            while (2 != curThread) {
                cond2.await();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ": BBB");
            }
            curThread = 3;
            cond3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printFifth() {
        lock.lock();
        try {
            while (3 != curThread) {
                cond3.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": CCC");
            }
            curThread = 1;
            cond1.signal();
            System.out.println("=======================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        AccuracyNotify an = new AccuracyNotify();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                an.printOnce();
            }
        }, "线程A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                an.printTriple();
            }
        }, "线程B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                an.printFifth();
            }
        }, "线程C").start();
    }
}
