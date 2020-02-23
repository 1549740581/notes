package lock;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示读写锁
 *
 * @author sherman
 */

class Cache {
    private volatile HashMap<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Object get(String key) {
        lock.readLock().lock();
        Object res = null;
        try {
            System.out.println(Thread.currentThread().getName() + ": 正在读取+++");
            Thread.sleep(100);
            res = map.get(key);
            System.out.println(Thread.currentThread().getName() + ": 读取完成---");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return res;
    }

    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": 正在写入>>>");
            Thread.sleep(1000);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "：写入完成<<<");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

public class LockReadWrite {
    public static void main(String[] args) {
        Cache cache = new Cache();

        // 写入操作是被一个线程独占的，一旦写线程开始
        // 其它线程必须等待其完成后才能继续执行
        for (int i = 0; i < 10; i++) {
            final int tmp = i;
            new Thread(() -> cache.put(tmp + "", tmp + ""), String.valueOf(i)).start();
        }

        // 读操作可以被多个线程持有
        // 其它线程不必等待当前读操作完成才操作
        for (int i = 0; i < 10; i++) {
            final int tmp = i;
            new Thread(() -> cache.get(tmp + ""), String.valueOf(i)).start();
        }
    }
}
